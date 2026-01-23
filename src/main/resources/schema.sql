drop table if exists artist_genre;
drop table if exists artist_social_media_links;
drop table if exists artist;
DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS concert;
DROP TABLE IF EXISTS tour;
DROP TABLE IF EXISTS tour_concert;

CREATE TABLE artist
(
    id             INT PRIMARY KEY auto_increment,
    artist_id      VARCHAR(255) NOT NULL unique,
    first_name     VARCHAR(255),
    last_name      VARCHAR(255),
    biography_text TEXT
);

CREATE TABLE artist_genre
(
    id        INT PRIMARY KEY auto_increment,

    artist_id VARCHAR(255) NOT NULL,
    genre     VARCHAR(255),
    FOREIGN KEY (artist_id) REFERENCES artist (artist_id)
);

CREATE TABLE artist_social_media_links
(
    id        INT PRIMARY KEY auto_increment,

    artist_id VARCHAR(255) NOT NULL,
    platform  VARCHAR(255) NOT NULL,
    uri       VARCHAR(355) NOT NULL,
    FOREIGN KEY (artist_id) REFERENCES artist (artist_id)
);


CREATE TABLE album
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    album_id     VARCHAR(255)                NOT NULL UNIQUE,
    artist_id    VARCHAR(255)                NOT NULL,
    title        VARCHAR(255)                NOT NULL,
    release_date DATE                        NOT NULL,
    record_label VARCHAR(255),
    album_type   Enum ('LP', 'EP', 'SINGLE') NOT NULL
);
CREATE TABLE song
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    album_id VARCHAR(255) references album (album_id) ON DELETE CASCADE,
    title    VARCHAR(255) NOT NULL,
    duration TIME,
    lyrics   TEXT
);

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    user_id  VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    fullname     VARCHAR(50)  NOT NULL
);


DROP TABLE IF EXISTS documentaries;

CREATE TABLE documentaries
(
    id                 INT PRIMARY KEY AUTO_INCREMENT,
    documentary_id     VARCHAR(255)                                            NOT NULL UNIQUE,
    artist_id          VARCHAR(255)                                            NOT NULL,
    title              VARCHAR(255)                                            NOT NULL,
    release_date       DATE                                                    NOT NULL,
    description        TEXT,
    duration           INT                                                     NOT NULL, -- in minutes
    language           VARCHAR(100)                                            NOT NULL,
    country_of_origin  VARCHAR(100)                                            NOT NULL,
    director           VARCHAR(255)                                            NOT NULL,
    producer           VARCHAR(255)                                            NOT NULL,
    production_company VARCHAR(255)                                            NOT NULL,
    genre              VARCHAR(100)                                            NOT NULL,
    video_url          VARCHAR(500)                                            NOT NULL,
    media_key          VARCHAR(255)                                            NOT NULL,
    media_type         ENUM ('DOCUMENTARY', 'MUSIC_VIDEO', 'LIVE_PERFORMANCE') NOT NULL
);


-- Orders table
drop table if exists orders;
-- Orders table
CREATE TABLE orders
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    order_id          VARCHAR(50)                        NOT NULL UNIQUE, -- from OrderIdentifier
    concert_id        VARCHAR(50),                                        -- from ConcertIdentifier
    documentary_title VARCHAR(255),                                       -- from DocumentaryIdentifier
    customer_name     VARCHAR(255),
    customer_email    VARCHAR(255),
    total_amount      DECIMAL(10, 2),
    order_status      ENUM ('PENDING', 'PAID', 'FAILED') NOT NULL         -- ENUM: PENDING, PAID, FAILED
);
drop table if exists payment;
-- Payments table (ElementCollection)
CREATE TABLE payment
(
    payment_id       INT AUTO_INCREMENT PRIMARY KEY,
    order_id         VARCHAR(50)                                                                     NOT NULL, -- references orders.order_id
    payment_status   ENUM ('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED', 'CANCELLED')                NOT NULL, -- ENUM: PENDING, COMPLETED, FAILED, REFUNDED, CANCELLED
    payment_method   ENUM ('CREDIT_CARD', 'PAYPAL', 'CASH', 'CRYPTO', 'BANK_TRANSFER', 'DEBIT_CARD') NOT NULL, -- ENUM: CREDIT_CARD, PAYPAL, CASH, CRYPTO, BANK_TRANSFER, DEBIT_CARD
    amount           DECIMAL(10, 2),
    transaction_date TIMESTAMP,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders (order_id)
);

-- Join table for order ↔ payment relationship
-- Fan table (remove order_id column, because it's now normalized)
-- =====================
-- FAN
-- =====================
DROP TABLE IF EXISTS fan_preferences;
DROP TABLE IF EXISTS fan_orders;
DROP TABLE IF EXISTS playlist_items;
DROP TABLE IF EXISTS playlists;
DROP TABLE IF EXISTS fan;

CREATE TABLE fan
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    fan_id       VARCHAR(255) NOT NULL UNIQUE,
    first_name   VARCHAR(100),
    last_name    VARCHAR(100),
    email        VARCHAR(255) UNIQUE,
    phone_number VARCHAR(50),
    country      VARCHAR(100),
    user_id      VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL
);

-- =====================
-- FAN ORDERS
-- =====================
CREATE TABLE fan_orders
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    fan_id   VARCHAR(255) NOT NULL,
    order_id VARCHAR(255) NOT NULL,
    CONSTRAINT fk_fan_orders_fan FOREIGN KEY (fan_id) REFERENCES fan (fan_id) ON DELETE CASCADE
);

-- =====================
-- FAN PREFERENCES
-- =====================
CREATE TABLE fan_preferences
(
    id                    INT PRIMARY KEY AUTO_INCREMENT,
    fan_id                VARCHAR(255) NOT NULL,
    receive_notifications BOOLEAN DEFAULT TRUE,
    preferred_genre       VARCHAR(100),
    preferred_language    VARCHAR(100),
    artist_id             VARCHAR(255),
    favorite_artist_name  VARCHAR(255),
    created_at            DATE,
    FOREIGN KEY (fan_id) REFERENCES fan (fan_id) ON DELETE CASCADE
);

-- =====================
-- PLAYLISTS
-- =====================
drop Table if exists playlists;
CREATE TABLE playlists
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    playlist_id VARCHAR(255) NOT NULL UNIQUE,
    fan_id        VARCHAR(255) NOT NULL,
    playlist_name VARCHAR(100) NOT NULL,
    description   VARCHAR(500),
    added_at      DATE,
    FOREIGN KEY (fan_id) REFERENCES fan (fan_id) ON DELETE CASCADE
);

-- =====================
-- PLAYLIST ITEMS
-- =====================
CREATE TABLE playlist_items
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    playlist_id   VARCHAR(255) NOT NULL,
    album_id      VARCHAR(255),
    album_title   VARCHAR(255),
    song_title    VARCHAR(255),
    song_duration TIME,
    artist_name   VARCHAR(255),
    date_added    TIME,
    constraint fk_playlist_items_playlists FOREIGN KEY (playlist_id) REFERENCES playlists (playlist_id)
);

