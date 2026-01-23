-- Artists
INSERT INTO artist (artist_id, first_name, last_name, biography_text)
VALUES ('ART-001', 'Johnny', 'Cash', 'An iconic country music singer.'),
       ('ART-002', 'Dolly', 'Parton', 'Known for her distinctive voice and songwriting.'),
       ('ART-003', 'Willie', 'Nelson', 'A legend in outlaw country music.'),
       ('ART-004', 'Reba', 'McEntire', 'Reigning queen of country music.'),
       ('ART-005', 'Garth', 'Brooks', 'One of the best-selling solo artists in the U.S.'),
       ('ART-006', 'Shania', 'Twain', 'Queen of country pop.'),
       ('ART-007', 'Chris', 'Stapleton', 'A modern-day soulful country singer.'),
       ('ART-008', 'Kacey', 'Musgraves', 'Blends traditional and progressive country.'),
       ('ART-009', 'Luke', 'Combs', 'Known for chart-topping hits and deep voice.'),
       ('ART-010', 'Carrie', 'Underwood', 'American Idol winner turned country star.');

-- Genres
INSERT INTO artist_genre (artist_id, genre)
VALUES ('ART-001', 'Outlaw Country'),
       ('ART-001', 'Country Gospel'),
       ('ART-002', 'Country Pop'),
       ('ART-003', 'Outlaw Country'),
       ('ART-004', 'Traditional Country'),
       ('ART-005', 'Contemporary Country'),
       ('ART-006', 'Country Pop'),
       ('ART-007', 'Southern Rock'),
       ('ART-007', 'Blues'),
       ('ART-008', 'Alternative Country'),
       ('ART-009', 'Bro-Country'),
       ('ART-010', 'Country Pop'),
       ('ART-010', 'Christian Country');

-- Social Media Links
INSERT INTO artist_social_media_links (artist_id, platform, uri)
VALUES ('ART-001', 'Twitter', 'https://twitter.com/johnnycash'),
       ('ART-001', 'Facebook', 'https://facebook.com/johnnycash'),
       ('ART-002', 'Instagram', 'https://instagram.com/dollyparton'),
       ('ART-003', 'YouTube', 'https://youtube.com/willienelson'),
       ('ART-004', 'Twitter', 'https://twitter.com/reba'),
       ('ART-005', 'Facebook', 'https://facebook.com/garthbrooks'),
       ('ART-006', 'Instagram', 'https://instagram.com/shaniatwain'),
       ('ART-007', 'Twitter', 'https://twitter.com/chrisstapleton'),
       ('ART-008', 'Instagram', 'https://instagram.com/spaceykacey'),
       ('ART-009', 'TikTok', 'https://tiktok.com/@lukecombs'),
       ('ART-010', 'Facebook', 'https://facebook.com/carrieunderwood');

INSERT INTO album (album_id, artist_id, title, release_date, record_label, album_type)
VALUES ('ALB-001', 'ART-001', 'Whiskey Roads', '2021-03-15', 'Lone Star Records', 'LP'),
       ('ALB-002', 'ART-002', 'Southern Skies', '2020-06-01', 'Skyline Sounds', 'EP'),
       ('ALB-003', 'ART-003', 'Heartland Echoes', '2022-11-20', 'Dusty Trail', 'SINGLE'),
       ('ALB-004', 'ART-004', 'Cowboy Sunset', '2023-01-10', 'Gold Boot Records', 'LP'),
       ('ALB-005', 'ART-005', 'Maple Leaf Dreams', '2019-09-05', 'Front Porch Sound', 'EP'),
       ('ALB-006', 'ART-006', 'Gravel and Grace', '2022-07-17', 'Prairie Tunes', 'LP'),
       ('ALB-007', 'ART-007', 'Steel Strings', '2020-12-24', 'Outlaw Harmony', 'EP'),
       ('ALB-008', 'ART-008', 'Dust Bowl Diaries', '2021-05-30', 'Ramblin’ Soul', 'SINGLE'),
       ('ALB-009', 'ART-009', 'Neon Honky Tonk', '2018-08-19', 'Blue Ridge Beats', 'LP'),
       ('ALB-010', 'ART-010', 'Moonshine Lullabies', '2023-03-03', 'Wild West Vibes', 'EP');






INSERT INTO song (album_id, title, duration, lyrics)
VALUES
-- Songs for Johnny Cash's "Whiskey Roads"
('ALB-001', 'Highway of Shadows', '00:03:45', 'Lyrics for Highway of Shadows...'),
('ALB-001', 'Whiskey Roads', '00:04:12', 'Lyrics for Whiskey Roads...'),

-- Songs for Dolly Parton's "Southern Skies"
('ALB-002', 'Southern Skies', '00:02:58', 'Lyrics for Southern Skies...'),
('ALB-002', 'Morning Light', '00:03:21', 'Lyrics for Morning Light...'),

-- Songs for Willie Nelson's "Heartland Echoes"
('ALB-003', 'Echoes of Home', '00:04:05', 'Lyrics for Echoes of Home...'),

-- Songs for Reba McEntire's "Cowboy Sunset"
('ALB-004', 'Cowboy Sunset', '00:03:50', 'Lyrics for Cowboy Sunset...'),
('ALB-004', 'Ride Into the Night', '00:04:10', 'Lyrics for Ride Into the Night...'),

-- Songs for Garth Brooks' "Maple Leaf Dreams"
('ALB-005', 'Dreams of the Maple', '00:03:15', 'Lyrics for Dreams of the Maple...'),

-- Songs for Shania Twain's "Gravel and Grace"
('ALB-006', 'Gravel Roads', '00:03:55', 'Lyrics for Gravel Roads...'),
('ALB-006', 'Graceful Nights', '00:04:20', 'Lyrics for Graceful Nights...'),

-- Songs for Chris Stapleton's "Steel Strings"
('ALB-007', 'Steel Strings', '00:03:40', 'Lyrics for Steel Strings...'),

-- Songs for Kacey Musgraves' "Dust Bowl Diaries"
('ALB-008', 'Dust Bowl Dreams', '00:03:35', 'Lyrics for Dust Bowl Dreams...'),

-- Songs for Luke Combs' "Neon Honky Tonk"
('ALB-009', 'Neon Nights', '00:04:00', 'Lyrics for Neon Nights...'),
('ALB-009', 'Honky Tonk Heart', '00:03:30', 'Lyrics for Honky Tonk Heart...'),

-- Songs for Carrie Underwood's "Moonshine Lullabies"
('ALB-010', 'Moonshine Lullaby', '00:03:45', 'Lyrics for Moonshine Lullaby...');



INSERT INTO users (user_id, username, password, email, fullname)
VALUES ('USR-001', 'nroos', 'hashed_pw_001', 'Nathan.Roos@example.com', 'Nathan Roos'),
       ('USR-002', 'jroos', 'hashed_pw_002', 'Jeremy.Roos@example.com', 'Jeremy Roos'),
       ('USR-003', 'iwallace', 'hashed_pw_003', 'Isaac.Wallace@example.com', 'Isaac Wallace'),
       ('USR-004', 'nkonate', 'hashed_pw_004', 'Naima.Konate@example.com', 'Naima Konate'),
       ('USR-005', 'Bwood', 'hashed_pw_005', 'Brendon.Wood@example.com', 'Brendon Wood'),
       ('USR-006', 'cgerald', 'hashed_pw_006', 'Christine.Gerald@example.com', 'Christine Gerald'),
       ('USR-007', 'mkonate', 'hashed_pw_007', 'Malick.Konate@example.com', 'Malick Konate'),
       ('USR-008', 'hnguyen', 'hashed_pw_008', 'Hannah.Nguyen@example.com', 'Hannah Nguyen'),
       ('USR-009', 'jmiller', 'hashed_pw_009', 'Julia.Miller@example.com', 'Julia Miller'),
       ('USR-010', 'ebrown', 'hashed_pw_010', 'Ella.Brown@example.com', 'Ella Brown');


INSERT INTO documentaries (documentary_id, artist_id, title, release_date, description, duration, language,
                           country_of_origin, director, producer, production_company, genre, video_url,
                           media_key, media_type)
VALUES ('DOC-001', 'ART-001', 'The Road of a Legend', '2021-05-12',
        'An intimate look at the life and struggles of a legendary country artist.',
        95, 'English', 'USA', 'James Carter', 'Megan White', 'Lonestar Studios',
        'Biography', 'http://videos.example.com/doc1.mp4', 'media-001', 'DOCUMENTARY'),
       ('DOC-002', 'ART-002', 'Behind the Strings', '2020-09-22',
        'A journey into the creative process of one of the greatest guitar players.',
        80, 'English', 'Canada', 'Sophia Turner', 'Michael Reed', 'Skyline Productions',
        'Music', 'http://videos.example.com/doc2.mp4', 'media-002', 'DOCUMENTARY'),
       ('DOC-003', 'ART-003', 'Voices of the Heartland', '2022-03-15',
        'Exploring how country music connects generations and cultures.',
        120, 'English', 'USA', 'Daniel Scott', 'Rachel Green', 'Dusty Trails Media',
        'Cultural', 'http://videos.example.com/doc3.mp4', 'media-003', 'DOCUMENTARY'),
       ('DOC-004', 'ART-004', 'Cowboy Chronicles', '2019-11-30',
        'An authentic portrayal of cowboy culture through the lens of music.',
        105, 'Spanish', 'Mexico', 'Alejandro Ruiz', 'Carmen López', 'Sol y Sombra Films',
        'Cultural', 'http://videos.example.com/doc4.mp4', 'media-004', 'DOCUMENTARY'),
       ('DOC-005', 'ART-005', 'Northern Lights & Country Nights', '2023-01-18',
        'Canadian country music and its rise on the international stage.',
        90, 'English', 'Canada', 'Emily Johnson', 'Robert Lee', 'Maple Leaf Productions',
        'Music', 'http://videos.example.com/doc5.mp4', 'media-005', 'DOCUMENTARY'),
       ('DOC-006', 'ART-006', 'Queen of Country Pop', '2022-07-10',
        'A behind-the-scenes journey into the life and career of Shania Twain, exploring her rise to global stardom.',
        110, 'English', 'Canada', 'Laura Bennett', 'Chris Morgan', 'Prairie Light Films',
        'Biography', 'http://videos.example.com/doc6.mp4', 'media-006', 'DOCUMENTARY'),

       ('DOC-007', 'ART-007', 'Strings of the Soul', '2021-09-25',
        'Chris Stapleton’s soulful voice and guitar artistry captured in this powerful and emotional documentary.',
        85, 'English', 'USA', 'David Miller', 'Anna Roberts', 'Southern Star Media',
        'Music', 'http://videos.example.com/doc7.mp4', 'media-007', 'DOCUMENTARY'),

       ('DOC-008', 'ART-008', 'Golden Horizons', '2023-03-12',
        'Kacey Musgraves shares her creative journey, blending tradition and innovation in modern country music.',
        95, 'English', 'USA', 'Olivia Perez', 'Nathan Clark', 'Harmony Pictures',
        'Artistic', 'http://videos.example.com/doc8.mp4', 'media-008', 'DOCUMENTARY'),

       ('DOC-009', 'ART-009', 'Born for the Stage', '2020-11-08',
        'From small-town beginnings to sold-out stadiums, Luke Combs’ rise to fame is documented in raw detail.',
        100, 'English', 'USA', 'Mark Wilson', 'Jessica Hayes', 'Big Sky Productions',
        'Biography', 'http://videos.example.com/doc9.mp4', 'media-009', 'DOCUMENTARY'),

       ('DOC-010', 'ART-010', 'Faith and Fame', '2024-02-14',
        'Carrie Underwood’s inspiring journey of faith, resilience, and success in the world of country music.',
        88, 'English', 'USA', 'Rachel Adams', 'Thomas King', 'Bright Path Studios',
        'Inspirational', 'http://videos.example.com/doc10.mp4', 'media-010', 'DOCUMENTARY');


-- Insert Orders
# INSERT INTO orders (order_id, concert_id, documentary_id, user_id, total_amount, order_status)
# VALUES ('ord-001', 'CON-003', 'DOC-003', 'USR-001', 120.00, 'PENDING'),
#        ('ord-002', 'CON-002', 'DOC-005', 'USR-009', 200.00, 'PAID'),
#        ('ord-003', 'CON-004', 'DOC-001', 'USR-007', 15.00, 'FAILED'),
#        ('ord-004', 'CON-001', 'DOC-004', 'USR-010', 350.00, 'PAID'),
#        ('ord-005', 'CON-005', 'DOC-002', 'USR-006', 25.00, 'PENDING'),
#        ('ord-006', 'CON-006', 'DOC-006', 'USR-002', 75.00, 'PAID'),
#        ('ord-007', 'CON-007', 'DOC-007', 'USR-003', 180.00, 'PENDING'),
#        ('ord-008', 'CON-008', 'DOC-008', 'USR-004', 99.99, 'FAILED'),
#        ('ord-009', 'CON-009', 'DOC-009', 'USR-005', 250.00, 'PAID'),
#        ('ord-010', 'CON-010', 'DOC-010', 'USR-008', 45.00, 'PAID'),
#        ('ord-011', 'CON-003', 'DOC-003', 'USR-001', 130.00, 'PAID');
# -- Additional order for testing
#
# -- Insert Payments
# INSERT INTO payment (payment_id, payment_status, order_id)
# VALUES ('pay-001', 'PENDING', 'ord-001'),
#        ('pay-002', 'COMPLETED', 'ord-002'),
#        ('pay-003', 'FAILED', 'ord-003'),
#        ('pay-004', 'COMPLETED', 'ord-004'),
#        ('pay-005', 'PENDING', 'ord-005'),
#        ('pay-006', 'COMPLETED', 'ord-006'),
#        ('pay-007', 'PENDING', 'ord-007'),
#        ('pay-008', 'FAILED', 'ord-008'),
#        ('pay-009', 'COMPLETED', 'ord-009'),
#        ('pay-010', 'REFUNDED', 'ord-010'),
#        ('pay-011', 'COMPLETED', 'ord-011');

-- Insert orders
INSERT INTO orders (order_id, concert_id, documentary_title, customer_name, customer_email, total_amount, order_status)
VALUES ('ORD-001', 'CON-001', 'Cowboy Chronicles', 'Alice Johnson', 'alice@example.com', 120.00, 'PAID'),
       ('ORD-002', 'CON-005', 'Strings of the Soul', 'Brian Smith', 'bob@example.com', 15.99, 'PAID'),
       ('ORD-003', 'CON-003', 'Faith and Fame', 'Clara Brown', 'diana@example.com', 200.00, 'PENDING'),
       ('ORD-004', 'CON-002', 'The Road of a Legend', 'David Wilson', 'george@example.com', 75.50, 'FAILED'),
       ('ORD-005', 'CON-008', 'Born for the Stage', 'Eva Martinez', 'isabella@example.com', 25.00, 'PAID'),
       ('ORD-006', 'CON-002', 'Voices of the Heartland', 'Farah', 'farah@example.com', 75.50, 'FAILED'),
       ('ORD-007', 'CON-009', 'Faith and Fame', 'David Wilson', 'hannah@example.com', 75.50, 'FAILED'),
       ('ORD-008', 'CON-007', 'Voices of the Heartland', 'Julia', 'julia@example.com', 75.50, 'PAID'),
       ('ORD-009', 'CON-007', 'Voices of the Heartland', 'Ivan', 'ivan@example.com', 75.50, 'PAID')

;

-- Insert payments
INSERT INTO payment (order_id, payment_status, payment_method, amount, transaction_date)
VALUES ('ORD-001', 'COMPLETED', 'CREDIT_CARD', 120.00, '2025-08-15 14:30:00'),
       ('ORD-002', 'COMPLETED', 'PAYPAL', 15.99, '2025-08-16 09:10:00'),
       ('ORD-003', 'PENDING', 'BANK_TRANSFER', 200.00, '2025-08-17 18:45:00'),
       ('ORD-004', 'FAILED', 'CASH', 75.50, '2025-08-18 11:20:00'),
       ('ORD-005', 'COMPLETED', 'DEBIT_CARD', 25.00, '2025-08-18 21:05:00');

-- Payment for the additional order

-- Link Payments ↔ Orders
# INSERT INTO order_payments (payment_id, order_id)
# VALUES ('pay-001', 'ord-001'), -- pay-001 → ord-001
#        ('pay-002', 'ord-002'), -- pay-002 → ord-002
#        ('pay-003', 'ord-003'), -- pay-003 → ord-003
#        ('pay-004', 'ord-004'), -- pay-004 → ord-004
#        ('pay-005', 'ord-005'), -- pay-005 → ord-005
#        ('pay-006', 'ord-006'),
#        ('pay-007', 'ord-007'),
#        ('pay-008', 'ord-008'),
#        ('pay-009', 'ord-009'),
#        ('pay-010', 'ord-010'),
#        ('pay-011', 'ord-011');
-- pay-011 → ord-011


-- Insert Fans
# INSERT INTO fan (fan_id, first_name, last_name, email, phone_number, country, password, order_id)
# VALUES
#     ('fan-001', 'Alice', 'Johnson', 'alice@example.com', '1234567890', 'USA', 'hashed_pw1'),
#     ('fan-002', 'Bob', 'Smith', 'bob@example.com', '9876543210', 'Canada', 'hashed_pw2'),
#     ('fan-003', 'Charlie', 'Brown', 'charlie@example.com', '5557778888', 'UK', 'hashed_pw3'),
#     ('fan-004', 'Malick', 'Konate', 'malick@example.com','1115559977','Australia','hashed_pw4'),
#     ('fan-005', 'Diana', 'Miller', 'diana@example.com','2226661234','USA','hashed_pw5'),
#     ('fan-006', 'Ethan', 'Wang', 'ethan@example.com','3337775678','China','hashed_pw6'),
#     ('fan-007', 'Fatima', 'Ali', 'fatima@example.com','4448883456','UAE','hashed_pw7'),
#     ('fan-008', 'George', 'Lopez', 'george@example.com','5559994567','Mexico','hashed_pw8'),
#     ('fan-009', 'Hiro', 'Tanaka', 'hiro@example.com','6661117890','Japan','hashed_pw9'),
#     ('fan-010', 'Isabella', 'Rossi', 'isabella@example.com','7772226543','Italy','hashed_pw10');
-- Insert Fans (with user_id + aligned columns)
# INSERT INTO fan (fan_id, first_name, last_name, email, phone_number, country, order_id, user_id, password)
# VALUES
#     ('fan-001', 'Alice', 'Johnson', 'alice@example.com', '1234567890', 'USA', 'ord-001', 'USR-001', 'hashed_pw1'),
#     ('fan-002', 'Bob', 'Smith', 'bob@example.com', '9876543210', 'Canada', 'ord-002', 'USR-002', 'hashed_pw2'),
#     ('fan-003', 'Charlie', 'Brown', 'charlie@example.com', '5557778888', 'UK', 'ord-003', 'USR-003', 'hashed_pw3'),
#     ('fan-004', 'Malick', 'Konate', 'malick@example.com','1115559977','Australia','ord-004','USR-004','hashed_pw4'),
#     ('fan-005', 'Diana', 'Miller', 'diana@example.com','2226661234','USA','ord-005','USR-005','hashed_pw5'),
#     ('fan-006', 'Ethan', 'Wang', 'ethan@example.com','3337775678','China','ord-006','USR-006','hashed_pw6'),
#     ('fan-007', 'Fatima', 'Ali', 'fatima@example.com','4448883456','UAE','ord-007','USR-007','hashed_pw7'),
#     ('fan-008', 'George', 'Lopez', 'george@example.com','5559994567','Mexico','ord-008','USR-008','hashed_pw8'),
#     ('fan-009', 'Hiro', 'Tanaka', 'hiro@example.com','6661117890','Japan','ord-009','USR-009','hashed_pw9'),
#     ('fan-010', 'Isabella', 'Rossi', 'isabella@example.com','7772226543','Italy','ord-010','USR-010','hashed_pw10');
-- Fans (no order_id anymore)
# INSERT INTO fan (fan_id, first_name, last_name, email, phone_number, country, user_id, password)
# VALUES ('FAN-001', 'Alice', 'Johnson', 'alice@example.com', '555-111-2222', 'Canada', 'USR-001', 'pw1'),
#        ('FAN-002', 'Bob', 'Smith', 'bob@example.com', '555-333-4444', 'USA', 'USR-002', 'pw2'),
#        ('FAN-003', 'Clara', 'Martinez', 'clara@example.com', '555-555-6666', 'Mexico', 'USR-003', 'pw3'),
#        ('FAN-004', 'David', 'Lee', 'david@example.com', '555-777-8888', 'South Korea', 'USR-004', 'pw4'),
#        ('FAN-005', 'Ella', 'Brown', 'ella@example.com', '555-999-0000', 'UK', 'USR-005', 'pw5'),
#        ('FAN-006', 'Farah', 'Ali', 'farah@example.com', '555-121-1314', 'UAE', 'USR-006', 'pw6'),
#        ('FAN-007', 'George', 'Taylor', 'george@example.com', '555-141-1516', 'USA', 'USR-007', 'pw7'),
#        ('FAN-008', 'Hannah', 'Nguyen', 'hannah@example.com', '555-171-1819', 'Vietnam', 'USR-008', 'pw8'),
#        ('FAN-009', 'Ivan', 'Petrov', 'ivan@example.com', '555-191-2021', 'Russia', 'USR-009', 'pw9'),
#        ('FAN-010', 'Julia', 'Miller', 'julia@example.com', '555-212-2223', 'Germany', 'USR-010', 'pw10');

-- Fan orders (1..N per fan)
# INSERT INTO fan_orders (fan_id, order_id)
# VALUES ('FAN-001', 'ORD-001'),
#        ('FAN-001', 'ORD-002'),
#        ('FAN-002', 'ORD-002'),
#        ('FAN-003', 'ORD-003'),
#        ('FAN-004', 'ORD-004'),
#        ('FAN-005', 'ORD-005'),
#        ('FAN-006', 'ORD-006'),
#        ('FAN-007', 'ORD-007'),
#        ('FAN-008', 'ORD-008'),
#        ('FAN-009', 'ORD-009'),
#        ('FAN-010', 'ORD-010');

-- Insert Fan Preferences
# INSERT INTO fan_preferences (fan_id, receive_notifications, preferred_genre, preferred_language, artist_id,
#                              favorite_artist_name, created_at)
# VALUES ('FAN-001', TRUE, 'Country Pop', 'English', 'ART-001', 'Dolly Parton', '2025-01-10'),
#        ('FAN-002', TRUE, 'Outlaw Country', 'English', 'ART-002', 'Johnny Cash', '2025-02-11'),
#        ('FAN-003', FALSE, 'Country Rock', 'Spanish', 'ART-003', 'Shania Twain', '2025-02-15'),
#        ('FAN-004', TRUE, 'K-Country', 'Korean', 'ART-004', 'Jung Dong Ha', '2025-03-02'),
#        ('FAN-005', FALSE, 'Bluegrass', 'English', 'ART-005', 'Alison Krauss', '2025-03-05'),
#        ('FAN-006', TRUE, 'Country Soul', 'Arabic', 'ART-006', 'LeAnn Rimes', '2025-03-07'),
#        ('FAN-007', TRUE, 'Classic Country', 'English', 'ART-007', 'Garth Brooks', '2025-03-10'),
#        ('FAN-008', FALSE, 'Folk Country', 'Vietnamese', 'ART-008', 'Cam', '2025-03-12'),
#        ('FAN-009', TRUE, 'Country Blues', 'Russian', 'ART-009', 'Kris Kristofferson', '2025-03-15'),
#        ('FAN-010', TRUE, 'Modern Country', 'German', 'ART-010', 'Kacey Musgraves', '2025-03-18');
#
# -- Insert Playlists
# INSERT INTO playlists (playlist_id, fan_id, playlist_name, description, added_at)
# VALUES ('PL-001', 'FAN-001', 'Morning Country Vibes', 'Relaxing country music to start the day', '2025-03-01'),
#        ('PL-002', 'FAN-002', 'Outlaw Classics', 'Best of outlaw country legends', '2025-03-02'),
#        ('PL-003','FAN-003', 'Spanish Country Mix', 'Spanish and Latin country hits', '2025-03-03'),
#        ('PL-004','FAN-004', 'K-Country Hits', 'Top Korean country-style ballads', '2025-03-04'),
#        ('PL-005','FAN-005', 'Bluegrass Sunrise', 'Instrumental and vocal bluegrass', '2025-03-05'),
#        ('PL-006','FAN-006', 'Desert Country Nights', 'Middle Eastern flavored country tracks', '2025-03-06'),
#        ('PL-007','FAN-007', 'Classic Rodeo Tunes', 'Traditional rodeo country songs', '2025-03-07'),
#        ('PL-008','FAN-008', 'Folk Journey', 'Vietnamese folk-country fusion', '2025-03-08'),
#        ('PL-009','FAN-009', 'Russian Country Ballads', 'Local twist on American country blues', '2025-03-09'),
#        ('PL-010','FAN-010', 'Modern Country Stars', 'Today’s best modern country artists', '2025-03-10');
#
# INSERT INTO playlist_items (playlist_id, album_id, album_title, song_title, song_duration, artist_name, date_added)
# VALUES
# -- Playlist 1
# ('PL-001', 'ALB-001', 'Whiskey Roads', 'Highway of Shadows', '00:02:58', 'Parton', '10:30:00'),
# ('PL-001', 'ALB-002', 'Southern Skies', 'Morning Light', '00:03:45', 'Underwood', '11:00:00'),
#
# -- Playlist 2
# ('PL-002', 'ALB-003', 'Heartland Echoes', 'Echoes of Home', '00:04:12', 'Cash', '09:15:00'),
# ('PL-002', 'ALB-004', 'Cowboy Sunset', 'Cowboy Sunset', '00:04:05', 'Nelson', '09:45:00'),
#
# -- Playlist 3
# ('PL-003', 'ALB-005', 'Maple Leaf Dreams', 'Dreams of the Maple', '00:03:25', 'Twain', '12:00:00'),
#
# -- Playlist 4
# ('PL-004', 'ALB-006', 'Gravel and Grace', 'Graceful Nights', '00:03:40', 'Dong Ha', '13:00:00'),
#
# -- Playlist 5
# ('PL-005', 'ALB-007', 'Steel Strings', 'Steel Strings', '00:03:30', 'Krauss', '14:15:00'),
#
# -- Playlist 6
# ('PL-006', 'ALB-008', 'Dust Bowl Diaries', 'Dust Bowl Dreams', '00:04:10', 'Rimes', '15:00:00'),
#
# -- Playlist 7
# ('PL-007', 'ALB-009', 'Neon Honky Tonk', 'Honky Tonk Heart', '00:03:55', 'Brooks', '16:20:00'),
#
# -- Playlist 8
# ('PL-008', 'ALB-010', 'Moonshine Lullabies', 'Moonshine Lullaby', '00:04:00', 'Cam', '17:00:00'),
#
# -- Playlist 9
# ('PL-009', 'ALB-006', 'Gravel and Grace', 'Graceful Nights', '00:04:20', 'Kristofferson', '18:00:00'),
#
# -- Playlist 10
# ('PL-010', 'ALB-006', 'Gravel and Grace', 'Graceful Nights', '00:03:50', 'Musgraves', '19:00:00');
