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
    id       INT AUTO_INCREMENT PRIMARY KEY,
    user_id  VARCHAR(100) NOT NULL UNIQUE,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(150) NOT NULL UNIQUE,
    fullname VARCHAR(150) NOT NULL,
    country  VARCHAR(100) NOT NULL,
    age      INT          NOT NULL
);
drop TABLE IF EXISTS podcast;

CREATE TABLE podcast
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    podcast_id    VARCHAR(100)                                 NOT NULL UNIQUE,
    title         VARCHAR(200)                                 NOT NULL,
    hostname      VARCHAR(150)                                 NOT NULL,
    description   TEXT                                         NOT NULL,
    pricing_model Enum ('FREE', 'SUBSCRIPTION', 'PER_EPISODE') NOT NULL
);
drop TABLE IF EXISTS episode;
CREATE TABLE episode
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    episode_id   VARCHAR(100) NOT NULL UNIQUE,
    podcast_id   VARCHAR(100) NOT NULL,
    title        VARCHAR(200) NOT NULL,
    duration     TIME         NOT NULL,
    publish_date DATE         NOT NULL,
    status       VARCHAR(30)  NOT NULL,
    CONSTRAINT fk_episode_podcast
        FOREIGN KEY (podcast_id) REFERENCES podcast (podcast_id)
);


-- Use backticks because 'order' is a reserved keyword in SQL
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders`
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    order_id     VARCHAR(100)                               NOT NULL UNIQUE, -- From OrderIdentifier
    user_id      VARCHAR(100)                               NOT NULL,        -- From UserIdentifier
    order_status ENUM ('PENDING', 'COMPLETED', 'CANCELLED') NOT NULL
);

CREATE TABLE order_items
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    order_id     VARCHAR(100)                                                       NOT NULL,
    product_type ENUM ('ALBUM_PURCHASE', 'PODCAST_SUBSCRIPTION', 'ARTIST_DONATION') NOT NULL,
    display_name VARCHAR(255),
    price        DECIMAL(19, 2),
    quantity     INT,
    artist_name  VARCHAR(255),
    CONSTRAINT fk_items_order FOREIGN KEY (order_id) REFERENCES `orders` (order_id) ON DELETE CASCADE
);

CREATE TABLE payment
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    order_id       VARCHAR(100) NOT NULL,
    amount         DECIMAL(19, 2),
    paid_at        DATETIME,
    method         ENUM ('CREDIT_CARD', 'PAYPAL', 'STRIPE'),
    payment_status ENUM ('PENDING', 'COMPLETED', 'FAILED', 'REFUNDED'),
    currency       VARCHAR(10),
    CONSTRAINT fk_payment_order FOREIGN KEY (order_id) REFERENCES `orders` (order_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS targeting_rules;
DROP TABLE IF EXISTS ad_creatives;
DROP TABLE IF EXISTS ad_campaigns;

CREATE TABLE ad_campaigns
(
    id              INT AUTO_INCREMENT PRIMARY KEY,

    ad_id           VARCHAR(255)                                    NOT NULL UNIQUE,

    user_id         VARCHAR(100)                                    NOT NULL,

    target_type     ENUM ('ARTIST', 'PODCAST')                      NOT NULL,
    target_id       VARCHAR(255)                                    NOT NULL,

    name            VARCHAR(255)                                    NOT NULL,

    budget          DECIMAL(19, 2)                                  NOT NULL,
    remaining_spend DECIMAL(19, 2)                                  NOT NULL,

    status          ENUM ('DRAFT', 'ACTIVE', 'PAUSED', 'EXHAUSTED') NOT NULL
);

CREATE TABLE ad_creatives
(
    ad_id         VARCHAR(255) NOT NULL,
    media_url     VARCHAR(500),
    creative_type ENUM ('AUDIO_CLIP', 'BANNER_IMAGE', 'VIDEO_SPOT'),

    CONSTRAINT fk_creative_ad
        FOREIGN KEY (ad_id) REFERENCES ad_campaigns (ad_id)
            ON DELETE CASCADE
);

CREATE TABLE targeting_rules
(
    ad_id         VARCHAR(255) NOT NULL,

    target_genre  ENUM (
        'BLUEGRASS',
        'HONKY_TONK',
        'OUTLAW',
        'COUNTRY_POP',
        'ALTERNATIVE_COUNTRY',
        'CANADIAN_COUNTRY',
        'COUNTRY_POLITAN'
        ),

    target_region ENUM (
        'GLOBAL',
        'NORTH_AMERICA',
        'EUROPE',
        'ASIA',
        'LATAM'
        ),

    min_age       INT,

    CONSTRAINT fk_targeting_ad
        FOREIGN KEY (ad_id) REFERENCES ad_campaigns (ad_id)
            ON DELETE CASCADE
);
