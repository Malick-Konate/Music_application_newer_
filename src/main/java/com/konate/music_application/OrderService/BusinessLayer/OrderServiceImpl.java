package com.konate.music_application.OrderService.BusinessLayer;

import com.konate.music_application.CatalogueService.BusinessLayer.AlbumService;
import com.konate.music_application.CatalogueService.PresentationLayer.AlbumResponseModel;
import com.konate.music_application.Exceptions.InvalidInputException;
import com.konate.music_application.Exceptions.InvalidOrderStateException;
import com.konate.music_application.Exceptions.NotFoundException;
import com.konate.music_application.OrderService.DataLayer.*;
import com.konate.music_application.OrderService.MappingLayer.OrderRequestMapper;
import com.konate.music_application.OrderService.MappingLayer.OrderResponseMapper;
import com.konate.music_application.OrderService.PresentationLayer.OrderController;
import com.konate.music_application.OrderService.PresentationLayer.OrderRequestModel;
import com.konate.music_application.OrderService.PresentationLayer.OrderResponseModel;
import com.konate.music_application.PodcastService.BusinessLayer.Podcast.PodcastService;
import com.konate.music_application.PodcastService.DataLayer.Podcast.PodcastPricing;
import com.konate.music_application.PodcastService.PresentationLayer.Podcast.PodcastResponseModel;
import com.konate.music_application.UserService.BusinessLayer.UserService;
import com.konate.music_application.UserService.DataLayer.User;
import com.konate.music_application.UserService.DataLayer.UserIdentifier;
import com.konate.music_application.UserService.PresentationLayer.UserResponseModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderRequestMapper requestMapper;
    private final OrderResponseMapper responseMapper;
    private final UserService userService;
    private final PodcastService podcastService;
    private final AlbumService albumService;

    public OrderServiceImpl(OrderRepository repository, OrderRequestMapper requestMapper, OrderResponseMapper responseMapper, UserService userService, PodcastService podcastService, AlbumService albumService) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
        this.userService = userService;
        this.podcastService = podcastService;
        this.albumService = albumService;
    }

    @Override
    public List<OrderResponseModel> getAllOrders() {
        List<Order> orders = repository.findAll();
        List<OrderResponseModel> responseModels = new ArrayList<>();

        for (Order order : orders) {
            UserResponseModel user = userService.getUserById(order.getUserIdentifier().getUserId());

            OrderResponseModel orderResponseModel = responseMapper.toRespondModel(order);
            orderResponseModel.setFullname(user.getFullname());
            orderResponseModel.setCountry(user.getCountry());
            orderResponseModel.setEmail(user.getEmail());

            orderResponseModel.add(linkTo(methodOn(OrderController.class)
                    .getOrderById(order.getOrderIdentifier().getOrderId())).withSelfRel());
            responseModels.add(orderResponseModel);
        }


        return responseModels;
    }

    @Override
    public OrderResponseModel getOrderById(String orderId) {
        Order order = repository.findAllByOrderIdentifier_OrderId(orderId);
        if (order == null)
            throw new NotFoundException("order not found with this id: " + orderId);
        UserResponseModel user = userService.getUserById(order.getUserIdentifier().getUserId());

        OrderResponseModel orderResponseModel = responseMapper.toRespondModel(order);
        orderResponseModel.setFullname(user.getFullname());
        orderResponseModel.setCountry(user.getCountry());
        orderResponseModel.setEmail(user.getEmail());

        orderResponseModel.add(linkTo(methodOn(OrderController.class)
                .getOrderById(order.getOrderIdentifier().getOrderId())).withSelfRel());
        return orderResponseModel;
    }

    @Override
    public List<OrderResponseModel> getAllOrdersForUser(String userId) {
        UserResponseModel user = userService.getUserById(userId);
        List<Order> orders = repository.findAllByUserIdentifier_UserId(userId);
        List<OrderResponseModel> responseModels = new ArrayList<>();

        for (Order order : orders) {


            OrderResponseModel orderResponseModel = responseMapper.toRespondModel(order);
            orderResponseModel.setFullname(user.getFullname());
            orderResponseModel.setCountry(user.getCountry());
            orderResponseModel.setEmail(user.getEmail());

            orderResponseModel.add(linkTo(methodOn(OrderController.class)
                    .getOrderById(order.getOrderIdentifier().getOrderId())).withSelfRel());
            responseModels.add(orderResponseModel);
        }


        return responseModels;
    }

    @Override
    public void deleteOrder(String orderId) {
        Order order = repository.findAllByOrderIdentifier_OrderId(orderId);
        if (order == null)
            throw new NotFoundException("order not found with this id: " + orderId);

        repository.delete(order);
    }


    @Override
    public OrderResponseModel createOrder(OrderRequestModel requestModel) {
        if (requestModel == null)
            throw new InvalidInputException("The request cannot be null.");

        // 1. Validate and Fetch User
        UserResponseModel user = userService.getUserByEmail(requestModel.getUserEmail());
//        if (user == null)
//            throw new NotFoundException("User not found with email: " + requestModel.getUserEmail());

        // 2. (Fetch details from other services)
        List<OrderItem> hydratedItems = new ArrayList<>();
        BigDecimal totalOrderAmount = BigDecimal.ZERO;

        for (OrderItem item : requestModel.getOrderItems()) {
            OrderItem hydratedItem = null;

            if (item.getProductType() == ProductType.ALBUM_PURCHASE) {
                // Use the ID from the request to fetch the real album data
                AlbumResponseModel album = albumService.getAlbumByTitle(item.getDisplayName());
                BigDecimal albumPrice = new BigDecimal("14.99");

                hydratedItem = new OrderItem(
                        ProductType.ALBUM_PURCHASE,
                        album.getTitle(),
                        album.getArtistFirstName() + " " + album.getArtistLastName(),
                        albumPrice,
                        item.getQuantity()
                );
            } else if (item.getProductType() == ProductType.PODCAST_SUBSCRIPTION) {


                PodcastResponseModel podcast = podcastService.getPodcastByTitle(item.getDisplayName());
                BigDecimal podcastPrice = determinePodcastPrice(podcast);

                hydratedItem = new OrderItem(
                        ProductType.PODCAST_SUBSCRIPTION,
                        podcast.getTitle(),
                        podcast.getHostname(),
                        podcastPrice,
                        item.getQuantity()
                );
            } else if (item.getProductType() == ProductType.ARTIST_DONATION) {
                hydratedItem = new OrderItem(
                        ProductType.ARTIST_DONATION,
                        "Artist Support Donation",
                        item.getArtistName(),
                        item.getPrice(), // User defined amount
                        1
                );
            }

            if (hydratedItem != null) {
                hydratedItems.add(hydratedItem);
                // Calculate total: price * quantity
                totalOrderAmount = totalOrderAmount.add(
                        hydratedItem.getPrice().multiply(BigDecimal.valueOf(hydratedItem.getQuantity()))
                );
            }
        }

        // 3. Process Payment (Linking the calculated total)
        List<Payment> paymentList = new ArrayList<>();
        for (Payment p : requestModel.getPayments()) {
            paymentList.add(new Payment(
                    totalOrderAmount, // Use the calculated total from items
                    java.time.LocalDateTime.now(),
                    p.getMethod(),
                    PaymentStatus.PENDING,
                    "USD"
            ));
        }

        // 4. Assemble and Save Order
        Order orderNew = requestMapper.toOder(
                requestModel,
                new OrderIdentifier(),
                new UserIdentifier(user.getUserId())
        );
//        Order newOrder = new Order();
//        newOrder.setOrderIdentifier(new OrderIdentifier());
//        newOrder.setUserIdentifier(new UserIdentifier(user.getUserId()));
        orderNew.setOrderStatus(OrderStatus.PENDING);
        orderNew.setOrderItems(hydratedItems);
        orderNew.setPayments(paymentList);

        Order savedOrder = repository.save(orderNew);

        // 5. Map to Response
        OrderResponseModel response = responseMapper.toRespondModel(savedOrder);
        response.setFullname(user.getFullname());
        response.setEmail(user.getEmail());

        return response;
    }

    // Help method to keep the create method clean
    private BigDecimal determinePodcastPrice(PodcastResponseModel podcast) {
        if (podcast.getPricingModel().equals(PodcastPricing.FREE)) return BigDecimal.ZERO;
        if (podcast.getPricingModel().equals(PodcastPricing.SUBSCRIPTION)) return new BigDecimal("4.99");
        if (podcast.getPricingModel().equals(PodcastPricing.PER_EPISODE)) return new BigDecimal("7.99");
        return BigDecimal.ZERO;
    }

    @Override
    public OrderResponseModel updateOrder(String orderId, OrderRequestModel requestModel) {
        // 1. Find the existing order
        Order existingOrder = repository.findAllByOrderIdentifier_OrderId(orderId);
        if (existingOrder == null) {
            throw new NotFoundException("Order not found with id: " + orderId);
        }

        // 2. Business Logic: Prevent updates to cancelled or completed orders
        // AKA aggregate invariant
        if (existingOrder.getOrderStatus() == OrderStatus.CANCELLED ||
                existingOrder.getOrderStatus() == OrderStatus.COMPLETED) {
            throw new InvalidOrderStateException(
                    orderId,
                    existingOrder.getOrderStatus()
            );
        }

        // 3. Update the Status (If provided in the request)

        if (requestModel.getOrderStatus() != null) {
            existingOrder.setOrderStatus(requestModel.getOrderStatus());
        }

        // 4. Update Payment Status if needed
        // Typically, if Order becomes COMPLETED, we mark all payments as COMPLETED
        if (existingOrder.getOrderStatus() == OrderStatus.COMPLETED) {
            for (Payment payment : existingOrder.getPayments()) {
                // Note: Since Payment is a Value Object, we replace the list or use a helper
                // For simplicity in this aggregate:
                existingOrder.setPayments(updatePaymentStatus(existingOrder.getPayments(), PaymentStatus.COMPLETED));
            }
        }

        // 5. Save and Return
        Order updatedOrder = repository.save(existingOrder);

        // Map to response and add links (HATEOAS)
        UserResponseModel user = userService.getUserById(updatedOrder.getUserIdentifier().getUserId());
        OrderResponseModel response = responseMapper.toRespondModel(updatedOrder);
        response.setFullname(user.getFullname());
        response.setEmail(user.getEmail());

        response.add(linkTo(methodOn(OrderController.class)
                .getOrderById(updatedOrder.getOrderIdentifier().getOrderId())).withSelfRel());

        return response;
    }


    private List<Payment> updatePaymentStatus(List<Payment> currentPayments, PaymentStatus newStatus) {
        List<Payment> updatedPayments = new ArrayList<>();
        for (Payment p : currentPayments) {
            updatedPayments.add(new Payment(
                    p.getAmount(), p.getPaidAt(), p.getMethod(), newStatus, p.getCurrency()
            ));
        }
        return updatedPayments;
    }
}
