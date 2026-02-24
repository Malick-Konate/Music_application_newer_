# Music Platform – Backend

This project is a Domain-Driven Design (DDD) based Spring Boot application for a music platform.

It manages artists, albums, podcasts, episodes, orders, and advertising campaigns through well-defined bounded contexts and aggregates.

The application is built using a layered architecture per subdomain and uses MySQL for persistence.

---

## Tech Stack
- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- MapStruct (DTO Mapping)
- MySQL
- Gradle
- RESTful API design

---

## Architectural Approach

The system follows:

- **Domain-Driven Design (DDD)**
- **Bounded Contexts**
- **Aggregate Roots**
- **Value Objects**
- **Invariants enforcement**
- **Layered architecture per subdomain**

Each subdomain contains 4 layers:
business/ → Domain logic & application services
data/ → Repositories (JPA)
mapping/ → MapStruct mappers
presentation/ → REST controllers + request/response models


---

# 📦 Bounded Contexts / Subdomains

## 👤 User Service
Handles identity and user accounts.

Core Entity:
- `User`

Value Objects:
- `UserIdentifier`

---

## 🎤 Artist & Talent Management Subdomain

Manages artists and their profiles.

Entity:
- `Artist`

Value Objects:
- `ArtistIdentifier`
- `Genre`
- `ArtistBio`
- `SocialMediaLink`

---

## 💿 Catalogue Subdomain

Manages albums and songs.

Entity:
- `Album`

Value Objects:
- `Song`
- `AlbumIdentifier`

Enums:
- `AlbumType (LP, EP, SINGLE)`

Cross-context reference:
- Album references `ArtistIdentifier`

---

## 🎙️ Podcast Subdomain

Manages podcasts and episodes.

Entities:
- `Podcast`
- `Episode`

Value Objects:
- `PodcastIdentifier`
- `EpisodeIdentifier`

Enums:
- `PodcastPricing`
- `EpisodeStatus`

Episodes are retrieved through nested endpoints: GET /api/v1/podcasts/{podcastId}/episodes



---

# 🛒 Order Subdomain (Aggregate Root)

The Order service is an **Aggregate Root**.

### Aggregate Root:
- `Order`

### Value Objects:
- `OrderItem`
- `Payment`
- `OrderId`

### Enums:
- `OrderStatus`
- `ProductType`
- `PaymentMethod`
- `PaymentStatus`

### Domain Invariant

Once an Order reaches:
COMPLETED
CANCELLED


It **cannot be modified**.

This invariant is enforced inside the business layer to protect aggregate consistency.

---

# 📢 Ad Subdomain (Aggregate Root)

The Ad Campaign is another aggregate root.

### Aggregate Root:
- `AdCampaign`

### Value Objects:
- `AdCreative`
- `TargetingRules`

### Enums:
- `CampaignStatus`
- `CreativeType`
- `GenreEnum`
- `Region`

AdCampaign references:

- `UserIdentifier`
- `ArtistIdentifier`
- `PodcastIdentifier`

---

# Example API Endpoints

### Artists
GET /api/v1/artists
GET /api/v1/artists/{artistId}

### Albums
GET /api/v1/albums
GET /api/v1/albums/{albumId}

### Podcasts
GET /api/v1/podcasts
GET /api/v1/podcasts/{podcastId}
GET /api/v1/podcasts/{podcastId}/episodes


### Orders

POST /api/v1/orders
PUT /api/v1/orders/{orderId}


### Ad Campaigns

POST /api/v1/ads
PUT /api/v1/ads/{campaignId}


---

# Database

The application uses:

- **MySQL**

Configuration is located in: src/main/resources/application.yaml
configuration:
  datasource:
    url: jdbc:mysql://mysql1/music_app
    username: user
    password: pwd

  jpa:
    show-sql: true
    hibernate:
      ddl-auto: none   #use create to allow jpa to auto-generate from Entity classes; use none to use schema-h2.sql


