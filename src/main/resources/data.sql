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
       ('ART-010', 'Carrie', 'Underwood', 'American Idol winner turned country star.'),
       ('ART-011', 'Alana', 'Springsteen',
        'An American country-pop artist known for emotional storytelling and modern country production.'),
       ('ART-012', 'Megan', 'Patrick',
        'A Canadian country singer blending traditional country with modern influences.'),
       ('ART-013', 'Robyn', 'Ottolini',
        'A Canadian country artist recognized for honest lyrics and viral country hits.'),
       ('ART-014', 'The', 'Reklaws',
        'A Canadian country sibling duo known for energetic performances and chart-topping hits.'),
       ('ART-015', 'Madeline', 'Merlo',
        'A Canadian country-pop artist known for heartfelt lyrics and modern production.'),
       ('ART-016', 'MacKenzie', 'Porter',
        'A Canadian country singer-songwriter with strong success in both Canada and the United States.');

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
       ('ART-010', 'Christian Country'),
       ('ART-011', 'Country Pop'),
       ('ART-011', 'Contemporary Country'),
       ('ART-012', 'Contemporary Country'),
       ('ART-012', 'Country Rock'),
       ('ART-013', 'Country Pop'),
       ('ART-013', 'Contemporary Country'),
       ('ART-014', 'Contemporary Country'),
       ('ART-014', 'Country Pop'),
       ('ART-015', 'Country Pop'),
       ('ART-015', 'Contemporary Country'),
       ('ART-016', 'Country Pop'),
       ('ART-016', 'Contemporary Country');

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
       ('ART-010', 'Facebook', 'https://facebook.com/carrieunderwood'),
       ('ART-011', 'Instagram', 'https://instagram.com/alanaspringsteen'),
       ('ART-011', 'TikTok', 'https://tiktok.com/@alanaspringsteen'),
       ('ART-012', 'Instagram', 'https://instagram.com/meganpatrickmusic'),
       ('ART-012', 'Facebook', 'https://facebook.com/meganpatrickmusic'),
       ('ART-013', 'Instagram', 'https://instagram.com/robynottolini'),
       ('ART-013', 'TikTok', 'https://tiktok.com/@robynottolini'),
       ('ART-014', 'Instagram', 'https://instagram.com/thereklaws'),
       ('ART-014', 'TikTok', 'https://tiktok.com/@thereklaws'),
       ('ART-015', 'Instagram', 'https://instagram.com/madelinemerlo'),
       ('ART-015', 'Facebook', 'https://facebook.com/madelinemerlo'),
       ('ART-016', 'Instagram', 'https://instagram.com/mackenziepmusic'),
       ('ART-016', 'TikTok', 'https://tiktok.com/@mackenzieporter');

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
       ('ALB-010', 'ART-010', 'Moonshine Lullabies', '2023-03-03', 'Wild West Vibes', 'EP'),
       -- Alana Springsteen
       ('ALB-011', 'ART-011', 'TWENTY SOMETHING', '2023-05-05', 'Columbia Records', 'LP'),

-- Megan Patrick
       ('ALB-012', 'ART-012', 'Greatest Show on Dirt', '2024-03-15', 'Riser House Records', 'LP'),

-- Robyn Ottolini
       ('ALB-013', 'ART-013', 'Growing Up to Do', '2023-09-22', 'Warner Music Canada', 'LP'),
       -- The Reklaws
       ('ALB-014', 'ART-014', 'Good Ol’ Days', '2022-11-04', 'Universal Music Canada', 'LP'),

-- Madeline Merlo
       ('ALB-015', 'ART-015', 'Slide', '2023-03-10', 'Open Road Recordings', 'EP'),

-- MacKenzie Porter
       ('ALB-016', 'ART-016', 'Nobody’s Born With a Broken Heart', '2022-04-08', 'Big Loud Records', 'LP');



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
('ALB-010', 'Moonshine Lullaby', '00:03:45', 'Lyrics for Moonshine Lullaby...'),
-- Alana Springsteen - TWENTY SOMETHING
('ALB-011', 'goodbye looks good on you', '00:03:12', 'Lyrics for goodbye looks good on you...'),
('ALB-011', 'you don’t deserve a country song', '00:03:08', 'Lyrics for you don’t deserve a country song...'),
('ALB-011', 'ghost in my guitar', '00:03:30', 'Lyrics for ghost in my guitar...'),

-- Megan Patrick - Greatest Show on Dirt
('ALB-012', 'Greatest Show on Dirt', '00:03:25', 'Lyrics for Greatest Show on Dirt...'),
('ALB-012', 'She’s No Good for Me', '00:03:18', 'Lyrics for She’s No Good for Me...'),

-- Robyn Ottolini - Growing Up to Do
('ALB-013', 'F-150', '00:03:22', 'Lyrics for F-150...'),
('ALB-013', 'Say It', '00:03:05', 'Lyrics for Say It...'),
-- The Reklaws - Good Ol’ Days
('ALB-014', 'Good Ol’ Days', '00:03:12', 'Lyrics for Good Ol’ Days...'),
('ALB-014', 'What the Truck', '00:02:55', 'Lyrics for What the Truck...'),

-- Madeline Merlo - Slide
('ALB-015', 'Slide', '00:03:05', 'Lyrics for Slide...'),
('ALB-015', 'Same Car', '00:03:18', 'Lyrics for Same Car...'),

-- MacKenzie Porter - Nobody’s Born With a Broken Heart
('ALB-016', 'Pickup', '00:03:22', 'Lyrics for Pickup...'),
('ALB-016', 'These Days', '00:03:30', 'Lyrics for These Days...');



INSERT INTO users (user_id, username, password, email, fullname, country, age)
VALUES ('user-001', 'malick', 'hashed_pwd_1', 'malick@email.com', 'Malick Konate', 'Canada', 22),
       ('user-002', 'amina', 'hashed_pwd_2', 'amina@email.com', 'Amina Diallo', 'France', 25),
       ('user-003', 'john_d', 'hashed_pwd_3', 'john@email.com', 'John Doe', 'USA', 30),
       ('user-004', 'fatima', 'hashed_pwd_4', 'fatima@email.com', 'Fatima Noor', 'UK', 27),
       ('user-005', 'alexm', 'hashed_pwd_5', 'alex@email.com', 'Alex Martin', 'Germany', 35),
       ('user-006', 'ebrown', 'hashed_pw_010', 'Ella.Brown@email.com', 'Ella Brown', 'USA', 17),
       ('user-007', 'cgerald', 'hashed_pw_006', 'Christine.Gerald@email.com', 'Christine Gerald', 'UK', 35),
       ('user-008', 'jroos', 'hashed_pw_006', 'Jeremy.Roos@email.com', 'Jeremy Roos', 'Italia', 20),
       ('user-009', 'nroos', 'hashed_pw_006', 'Nathan.Roos@email.com', 'Nathan Roos', 'Brasil', 20),
       ('user-010', 'mphillip-poulin', 'hashed_pw_006', 'Marie.Phillip-Poulin@email.com', 'Marie Phillip-Poulin', 'UK',
        30);

-- --------------------------------------------------------
-- DATA FOR TABLE: podcast
-- Theme: Country Music, Bluegrass, Americana
-- --------------------------------------------------------

INSERT INTO podcast (podcast_id, title, hostname, description, pricing_model)
VALUES ('pod_country_001', 'Honky Tonk History', 'Clint Blackman',
        'Deep dives into the history of classic country music from the 1950s to the 1990s.', 'FREE'),
       ('pod_country_002', 'Nashville Insider', 'Sarah Twang',
        'Behind the scenes news and interviews from Music City USA.', 'SUBSCRIPTION'),
       ('pod_country_003', 'Bluegrass Breakdown', 'Earl Scruggs Jr.',
        'Exploring the fast-picking world of banjos, fiddles, and mandolins.', 'FREE'),
       ('pod_country_004', 'The Slide Guitar Hour', 'Jerry Douglas Fan',
        'Tutorials and appreciation for dobro and slide guitar techniques.', 'PER_EPISODE'),
       ('pod_country_005', 'Modern Country Monthly', 'Taylor Shelton',
        'Reviews and discussions on the latest hits topping the Billboard Country charts.', 'FREE'),
       ('pod_country_006', 'Legends of the Grand Ole Opry', 'Opry Fanatic',
        'Stories about the most legendary performances on the most famous stage in country music.', 'SUBSCRIPTION'),
       ('pod_country_007', 'Texas Red Dirt Radio', 'Pat Greenish',
        'Focusing on the gritty, independent country music scene from Texas and Oklahoma.', 'FREE'),
       ('pod_country_008', 'Songwriters Circle', 'Kris Kristofferson AI',
        'Deconstructing the lyrics and composition of the greatest country songs ever written.', 'PER_EPISODE'),
       ('pod_country_009', 'Banjo & Bourbon', 'The Kentucky Boys',
        'Casual conversations about roots music over a glass of fine bourbon.', 'FREE'),
       ('pod_country_010', 'Women of Country', 'Dolly Parton Tribute',
        'Celebrating the female trailblazers of country music from Patsy Cline to Miranda Lambert.', 'FREE'),
       ('pod_country_011', 'Outlaw Country Chronicles', 'Waylon Willie',
        'Tales from the rebellious side of country music featuring Willie, Waylon, and Merle.', 'SUBSCRIPTION'),
       ('pod_country_012', 'Folk & Americana Roots', 'John Prine Spirit',
        'Exploring the softer, storytelling side of American roots music.', 'FREE'),
       ('pod_country_013', 'Truckers Tunes', 'Big Rig Rob',
        'The best driving songs for long hauls across the interstate.', 'PER_EPISODE'),
       ('pod_country_014', 'Gospel Country Sunday', 'Reverend Cash',
        'Uplifting country gospel music for your Sunday morning.', 'FREE'),
       ('pod_country_015', 'Steel Guitar Secrets', 'Paul Franklin Fan',
        'Technical deep dives into pedal steel guitar mechanics and playing styles.', 'SUBSCRIPTION'),
       ('pod_country_016', 'Canadian Country Spotlight', 'Northern Nashville',
        'Highlighting the biggest names and rising stars in Canadian country music.', 'FREE'),

       ('pod_country_017', 'Country Song Breakdown', 'Studio Session Sam',
        'Breaking down the production, lyrics, and songwriting techniques behind today’s biggest country hits.', 'SUBSCRIPTION'),

       ('pod_country_018', 'Backstage at the Opry', 'Southern Stage Mike',
        'Exclusive backstage-style storytelling from legendary country venues and festivals.', 'PER_EPISODE');
-- --------------------------------------------------------
-- DATA FOR TABLE: episode
-- Theme: Country Music Deep Dives
-- --------------------------------------------------------

INSERT INTO episode (episode_id, podcast_id, title, duration, publish_date, status)
VALUES
-- Episodes for pod_country_001 (Honky Tonk History)
('ep_001', 'pod_country_001', 'The Rise of Hank Williams', '00:45:00', '2023-01-15', 'PUBLISHED'),
('ep_002', 'pod_country_001', 'Johnny Cash at Folsom Prison: The Full Story', '01:10:00', '2023-02-01', 'PUBLISHED'),
('ep_003', 'pod_country_001', 'The Outlaw Movement: Waylon and Willie', '00:55:00', '2023-02-15', 'PUBLISHED'),
('ep_004', 'pod_country_001', 'Classic Duets: George and Tammy', '00:50:00', '2023-03-01', 'PUBLISHED'),

-- Episodes for pod_country_002 (Nashville Insider)
('ep_005', 'pod_country_002', 'Exclusive: Interview with Carrie Underwoods Producer', '00:30:00', '2023-10-12',
 'PUBLISHED'),
('ep_006', 'pod_country_002', 'The Future of the Grand Ole Opry', '00:35:00', '2023-10-19', 'PUBLISHED'),
('ep_007', 'pod_country_002', 'CMA Awards: Predictions and Snubs', '00:40:00', '2023-10-26', 'PUBLISHED'),

-- Episodes for pod_country_003 (Bluegrass Breakdown)
('ep_008', 'pod_country_003', 'Bill Monroe: The Father of Bluegrass', '00:55:00', '2023-05-20', 'PUBLISHED'),
('ep_009', 'pod_country_003', 'Fiddle vs. Violin: The Great Debate', '00:40:00', '2023-06-15', 'PUBLISHED'),
('ep_010', 'pod_country_003', 'Modern Bluegrass: The Billy Strings Effect', '01:05:00', '2023-07-01', 'PUBLISHED'),
('ep_011', 'pod_country_003', 'Mastering the Scruggs Style Banjo', '00:50:00', '2023-07-15', 'PUBLISHED'),

-- Episodes for pod_country_004 (The Slide Guitar Hour)
('ep_012', 'pod_country_004', 'Mastering Open G Tuning', '00:25:00', '2023-11-05', 'PUBLISHED'),
('ep_013', 'pod_country_004', 'Dobro Legends: Josh Graves to Jerry Douglas', '00:45:00', '2023-11-12', 'PUBLISHED'),
('ep_014', 'pod_country_004', 'Lap Steel Basics for Beginners', '00:30:00', '2023-11-19', 'PUBLISHED'),

-- Episodes for pod_country_005 (Modern Country Monthly)
('ep_015', 'pod_country_005', 'Review: The New Luke Combs Album', '00:45:00', '2023-09-01', 'PUBLISHED'),
('ep_016', 'pod_country_005', 'Morgan Wallen and the Streaming Era', '00:50:00', '2023-09-15', 'PUBLISHED'),
('ep_017', 'pod_country_005', 'The Evolution of Country-Pop', '00:40:00', '2023-10-01', 'PUBLISHED'),
('ep_018', 'pod_country_005', 'Rising Stars: Who to Watch in 2024', '00:35:00', '2023-11-01', 'PUBLISHED'),

-- pod_country_006 (Legends of the Grand Ole Opry)
('ep_019', 'pod_country_006', 'Minnie Pearls Best Jokes', '00:20:00', '2023-03-10', 'ARCHIVED'),
('ep_020', 'pod_country_006', 'The Night Hank Williams Was Banned', '00:55:00', '2023-03-17', 'PUBLISHED'),
('ep_021', 'pod_country_006', 'Dolly Partons Opry Debut', '00:45:00', '2023-03-24', 'PUBLISHED'),

-- pod_country_007 (Texas Red Dirt Radio)
('ep_022', 'pod_country_007', 'Why Texas Country is Different from Nashville', '01:00:00', '2023-07-04', 'PUBLISHED'),
('ep_023', 'pod_country_007', 'Live from Gruene Hall: Robert Earl Keen', '01:15:00', '2023-07-11', 'PUBLISHED'),
('ep_024', 'pod_country_007', 'The Red Dirt Revolution in Oklahoma', '00:50:00', '2023-07-18', 'PUBLISHED'),
('ep_025', 'pod_country_007', 'Acoustic Session: Cody Johnson', '00:40:00', '2023-07-25', 'PUBLISHED'),

-- pod_country_008 (Songwriters Circle)
('ep_026', 'pod_country_008', 'Deconstructing The Gambler by Kenny Rogers', '00:50:00', '2023-08-15', 'PUBLISHED'),
('ep_027', 'pod_country_008', 'The Art of the Story Song', '00:45:00', '2023-08-22', 'PUBLISHED'),
('ep_028', 'pod_country_008', 'Guy Clark: The Craftsman of Country', '01:05:00', '2023-08-29', 'PUBLISHED'),

-- pod_country_009 (Banjo & Bourbon)
('ep_029', 'pod_country_009', 'Old-Time vs. Three-Finger Picking', '00:55:00', '2023-09-05', 'PUBLISHED'),
('ep_030', 'pod_country_009', 'Kentucky Bourbon Tasting with Bela Fleck', '01:10:00', '2023-09-12', 'PUBLISHED'),
('ep_031', 'pod_country_009', 'The History of the Clawhammer Style', '00:45:00', '2023-09-19', 'PUBLISHED'),

-- pod_country_010 (Women of Country)
('ep_032', 'pod_country_010', 'Patsy Cline: Walking After Midnight', '00:35:00', '2023-04-22', 'PUBLISHED'),
('ep_033', 'pod_country_010', 'Loretta Lynn: The Coal Miners Daughter', '00:50:00', '2023-04-29', 'PUBLISHED'),
('ep_034', 'pod_country_010', 'The Highwomen: A New Era', '00:45:00', '2023-05-06', 'PUBLISHED'),
('ep_035', 'pod_country_010', 'Tammy Wynette and the First Lady of Country', '00:40:00', '2023-05-13', 'PUBLISHED'),

-- pod_country_011 (Outlaw Country Chronicles)
('ep_036', 'pod_country_011', 'The Story of Willie Nelsons Guitar Trigger', '00:48:00', '2023-12-01', 'PUBLISHED'),
('ep_037', 'pod_country_011', 'Waylon Jennings: Dreaming My Dreams', '00:55:00', '2023-12-08', 'PUBLISHED'),
('ep_038', 'pod_country_011', 'Merle Haggard: Mama Tried', '01:00:00', '2023-12-15', 'PUBLISHED'),

-- pod_country_012 (Folk & Americana Roots)
('ep_039', 'pod_country_012', 'The Carter Family Tree Explained', '01:15:00', '2023-02-28', 'PUBLISHED'),
('ep_040', 'pod_country_012', 'Woody Guthrie: This Land is Your Land', '00:45:00', '2023-03-07', 'PUBLISHED'),
('ep_041', 'pod_country_012', 'The Newport Folk Festival Scandal', '00:50:00', '2023-03-14', 'PUBLISHED'),

-- pod_country_013 (Truckers Tunes)
('ep_042', 'pod_country_013', 'Top 10 Songs for a Cross-Country Drive', '00:42:00', '2023-11-20', 'PUBLISHED'),
('ep_043', 'pod_country_013', 'Six Days on the Road: History of a Classic', '00:30:00', '2023-11-27', 'PUBLISHED'),
('ep_044', 'pod_country_013', 'Jerry Reed: The Snowman of Country Music', '00:45:00', '2023-12-04', 'PUBLISHED'),
('ep_045', 'pod_country_013', 'The Best Truck Stops and Radio Stations', '00:50:00', '2023-12-11', 'PUBLISHED'),

-- pod_country_014 (Gospel Country Sunday)
('ep_046', 'pod_country_014', 'The Gospel Side of Elvis Presley', '00:40:00', '2024-01-07', 'PUBLISHED'),
('ep_047', 'pod_country_014', 'Old Rugged Cross: Evolution of a Hymn', '00:35:00', '2024-01-14', 'PUBLISHED'),
('ep_048', 'pod_country_014', 'Bluegrass Gospel Essentials', '00:45:00', '2024-01-21', 'PUBLISHED'),

-- pod_country_015 (Steel Guitar Secrets)
('ep_049', 'pod_country_015', 'Pedal Steel Maintenance 101', '00:28:00', '2023-10-30', 'PUBLISHED'),
('ep_050', 'pod_country_015', 'The C6 vs E9 Tuning Explained', '01:05:00', '2023-11-06', 'PUBLISHED'),
('ep_051', 'pod_country_015', 'How to Play a Cryin Steel Lick', '00:40:00', '2023-11-13', 'PUBLISHED'),
('ep_052', 'pod_country_015', 'Interview: Modern Steel Legend Buddy Emmons', '01:20:00', '2023-11-20', 'PUBLISHED'), -- --------------------------------------------------------
-- pod_country_016 (Canadian Country Spotlight)
-- --------------------------------------------------------
('ep_053', 'pod_country_016', 'The Rise of MacKenzie Porter', '00:42:00', '2024-02-01', 'PUBLISHED'),
('ep_054', 'pod_country_016', 'The Reklaws and the New Party Country Wave', '00:38:00', '2024-02-08', 'PUBLISHED'),
('ep_055', 'pod_country_016', 'Madeline Merlo: From BC to Nashville', '00:35:00', '2024-02-15', 'PUBLISHED'),
('ep_056', 'pod_country_016', 'Top 10 Canadian Country Songs of 2024', '00:50:00', '2024-02-22', 'PUBLISHED'),
('ep_057', 'pod_country_016', 'How Canada Shapes Modern Country Music', '00:45:00', '2024-03-01', 'PUBLISHED'),

-- --------------------------------------------------------
-- pod_country_017 (Country Song Breakdown)
-- --------------------------------------------------------
('ep_058', 'pod_country_017', 'Inside the Production of a Luke Combs Hit', '00:55:00', '2024-03-05', 'PUBLISHED'),
('ep_059', 'pod_country_017', 'The Secret Formula of Country-Pop Hooks', '00:48:00', '2024-03-12', 'PUBLISHED'),
('ep_060', 'pod_country_017', 'Writing the Perfect Country Love Song', '00:40:00', '2024-03-19', 'PUBLISHED'),
('ep_061', 'pod_country_017', 'Acoustic vs. Full Band: Arrangement Choices', '00:46:00', '2024-03-26', 'PUBLISHED'),
('ep_062', 'pod_country_017', 'Why Storytelling Still Wins in 2024', '00:52:00', '2024-04-02', 'PUBLISHED'),

-- --------------------------------------------------------
-- pod_country_018 (Backstage at the Opry)
-- --------------------------------------------------------
('ep_063', 'pod_country_018', 'Behind the Curtains of the Grand Ole Opry', '01:05:00', '2024-04-10', 'PUBLISHED'),
('ep_064', 'pod_country_018', 'Stage Fright: Confessions from Touring Artists', '00:50:00', '2024-04-17', 'PUBLISHED'),
('ep_065', 'pod_country_018', 'Festival Life: CMA Fest Stories', '00:45:00', '2024-04-24', 'PUBLISHED'),
('ep_066', 'pod_country_018', 'Soundcheck Secrets from Nashville Studios', '00:38:00', '2024-05-01', 'PUBLISHED'),
('ep_067', 'pod_country_018', 'The Evolution of Live Country Performances', '00:55:00', '2024-05-08', 'PUBLISHED');


-- 10 ORDERS
-- 1. ORDERS
-- Linked to user-001 through user-007 as defined in your users table
INSERT INTO `orders` (order_id, user_id, order_status)
VALUES ('ord_101', 'user-001', 'COMPLETED'),
       ('ord_102', 'user-002', 'COMPLETED'),
       ('ord_103', 'user-003', 'PENDING'),
       ('ord_104', 'user-004', 'COMPLETED'),
       ('ord_105', 'user-005', 'CANCELLED'),
       ('ord_106', 'user-006', 'COMPLETED'),
       ('ord_107', 'user-007', 'PENDING'),
       ('ord_108', 'user-005', 'COMPLETED'),
       ('ord_109', 'user-001', 'COMPLETED'),
       ('ord_110', 'user-006', 'COMPLETED');

-- 2. ORDER ITEMS
-- display_name and artist_name now match your ALBUM and PODCAST tables exactly
INSERT INTO order_items (order_id, product_type, display_name, price, quantity, artist_name)
VALUES
-- Order 101: Johnny Cash Album
('ord_101', 'ALBUM_PURCHASE', 'Whiskey Roads', 14.99, 1, 'Johnny Cash'),
-- Order 102: Nashville Insider (Subscription)
('ord_102', 'PODCAST_SUBSCRIPTION', 'Nashville Insider', 4.99, 1, 'Sarah Twang'),
-- Order 103: Support for Johnny Cash
('ord_103', 'ARTIST_DONATION', 'Artist Support', 25.00, 1, 'Johnny Cash'),
-- Order 104: Reba Album + Donation
('ord_104', 'ALBUM_PURCHASE', 'Cowboy Sunset', 14.99, 1, 'Reba McEntire'),
('ord_104', 'ARTIST_DONATION', 'Tip Jar', 5.00, 1, 'Reba McEntire'),
-- Order 105: Dolly Parton Album (Cancelled)
('ord_105', 'ALBUM_PURCHASE', 'Southern Skies', 12.99, 1, 'Dolly Parton'),
-- Order 106: Honky Tonk History (Free Podcast, but let's assume a 'Support' tier)
('ord_106', 'PODCAST_SUBSCRIPTION', 'Honky Tonk History', 4.99, 1, 'Clint Blackman'),
-- Order 107: Willie Nelson Album
('ord_107', 'ALBUM_PURCHASE', 'Heartland Echoes', 14.99, 1, 'Willie Nelson'),
-- Order 108: Chris Stapleton Donation
('ord_108', 'ARTIST_DONATION', 'Monthly Patron', 10.00, 1, 'Chris Stapleton'),
-- Order 109: Shania Twain Album
('ord_109', 'ALBUM_PURCHASE', 'Gravel and Grace', 14.99, 1, 'Shania Twain'),
-- Order 110: Bluegrass Breakdown (Free Podcast)
('ord_110', 'PODCAST_SUBSCRIPTION', 'Bluegrass Breakdown', 0.00, 1, 'Earl Scruggs Jr.');

-- 3. PAYMENTS
-- Sum of items must match the amount here
INSERT INTO payment (order_id, amount, paid_at, method, payment_status, currency)
VALUES ('ord_101', 14.99, '2023-10-01 10:00:00', 'CREDIT_CARD', 'COMPLETED', 'USD'),
       ('ord_102', 4.99, '2023-10-02 11:30:00', 'PAYPAL', 'COMPLETED', 'USD'),
       ('ord_103', 25.00, NULL, 'STRIPE', 'PENDING', 'USD'),
       ('ord_104', 19.99, '2023-10-04 09:15:00', 'CREDIT_CARD', 'COMPLETED', 'USD'), -- (14.99 + 5.00)
       ('ord_105', 12.99, NULL, 'CREDIT_CARD', 'FAILED', 'USD'),
       ('ord_106', 4.99, '2023-10-06 14:00:00', 'PAYPAL', 'COMPLETED', 'USD'),
       ('ord_107', 14.99, NULL, 'STRIPE', 'PENDING', 'USD'),
       ('ord_108', 10.00, '2023-10-08 16:45:00', 'CREDIT_CARD', 'COMPLETED', 'USD'),
       ('ord_109', 14.99, '2023-10-09 12:00:00', 'STRIPE', 'COMPLETED', 'USD'),
       ('ord_110', 0.00, '2023-10-10 08:00:00', 'CREDIT_CARD', 'COMPLETED', 'USD');


INSERT INTO ad_campaigns
(ad_id, user_id, target_type, target_id, name, budget, remaining_spend, status)
VALUES ('ad_001', 'user-001', 'ARTIST', 'ART-001', 'Johnny Cash Revival', 500.00, 320.00, 'ACTIVE'),
       ('ad_002', 'user-002', 'ARTIST', 'ART-006', 'Shania Twain Comeback', 800.00, 800.00, 'DRAFT'),
       ('ad_003', 'user-003', 'PODCAST', 'pod_country_002', 'Nashville Insider Promo', 300.00, 120.00, 'ACTIVE'),
       ('ad_004', 'user-004', 'ARTIST', 'ART-010', 'Carrie Underwood Tour', 1000.00, 0.00, 'EXHAUSTED'),
       ('ad_005', 'user-005', 'PODCAST', 'pod_country_011', 'Outlaw Country Chronicles', 400.00, 200.00, 'PAUSED'),
       ('ad_006', 'user-006', 'ARTIST', 'ART-007', 'Chris Stapleton Spotlight', 600.00, 600.00, 'DRAFT'),
       ('ad_007', 'user-007', 'PODCAST', 'pod_country_005', 'Modern Country Monthly Boost', 250.00, 75.00, 'ACTIVE'),
       ('ad_008', 'user-001', 'ARTIST', 'ART-009', 'Luke Combs New Album', 700.00, 430.00, 'ACTIVE'),
       ('ad_009', 'user-002', 'PODCAST', 'pod_country_010', 'Women of Country Awareness', 350.00, 350.00, 'DRAFT'),
       ('ad_010', 'user-003', 'ARTIST', 'ART-003', 'Willie Nelson Legacy', 900.00, 480.00, 'PAUSED');

-- --------------------------------------------------------
-- ADDITIONAL DATA FOR TABLE: ad_creatives
-- Every ad (ad_001 to ad_010) gets at least 2 creatives
-- --------------------------------------------------------

-- Clean up existing specific creatives to avoid duplicates if re-running

INSERT INTO ad_creatives (ad_id, media_url, creative_type)
VALUES
-- ad_001
('ad_001', 'https://cdn.ads.com/johnny_cash/audio_30s.mp3', 'AUDIO_CLIP'),
('ad_001', 'https://cdn.ads.com/johnny_cash/banner.jpg', 'BANNER_IMAGE'),

-- ad_002
('ad_002', 'https://cdn.ads.com/shania_twain/banner.jpg', 'BANNER_IMAGE'),
('ad_002', 'https://cdn.ads.com/shania_twain/video.mp4', 'VIDEO_SPOT'),

-- ad_003
('ad_003', 'https://cdn.ads.com/nashville_insider/audio_intro.mp3', 'AUDIO_CLIP'),
('ad_003', 'https://cdn.ads.com/nashville_insider/banner.jpg', 'BANNER_IMAGE'),

-- ad_004
('ad_004', 'https://cdn.ads.com/carrie_underwood/video_tour.mp4', 'VIDEO_SPOT'),
('ad_004', 'https://cdn.ads.com/carrie_underwood/banner.jpg', 'BANNER_IMAGE'),

-- ad_005
('ad_005', 'https://cdn.ads.com/outlaw_chronicles/audio_trailer.mp3', 'AUDIO_CLIP'),
('ad_005', 'https://cdn.ads.com/outlaw_chronicles/banner.jpg', 'BANNER_IMAGE'),

-- ad_006
('ad_006', 'https://cdn.ads.com/chris_stapleton/audio_30s.mp3', 'AUDIO_CLIP'),
('ad_006', 'https://cdn.ads.com/chris_stapleton/banner.jpg', 'BANNER_IMAGE'),

-- ad_007
('ad_007', 'https://cdn.ads.com/modern_country/audio_ad.mp3', 'AUDIO_CLIP'),
('ad_007', 'https://cdn.ads.com/modern_country/video.mp4', 'VIDEO_SPOT'),

-- ad_008
('ad_008', 'https://cdn.ads.com/luke_combs/banner.jpg', 'BANNER_IMAGE'),
('ad_008', 'https://cdn.ads.com/luke_combs/video_album.mp4', 'VIDEO_SPOT'),

-- ad_009
('ad_009', 'https://cdn.ads.com/women_country/banner.jpg', 'BANNER_IMAGE'),
('ad_009', 'https://cdn.ads.com/women_country/audio_clip.mp3', 'AUDIO_CLIP'),

-- ad_010
('ad_010', 'https://cdn.ads.com/willie_nelson/audio_legacy.mp3', 'AUDIO_CLIP'),
('ad_010', 'https://cdn.ads.com/willie_nelson/banner.jpg', 'BANNER_IMAGE');


INSERT INTO targeting_rules (ad_id, target_genre, target_region, min_age)
VALUES
-- ad_001 (Johnny Cash)
('ad_001', 'OUTLAW', 'NORTH_AMERICA', 18),
('ad_001', 'HONKY_TONK', 'GLOBAL', 21),

-- ad_002 (Shania Twain)
('ad_002', 'COUNTRY_POP', 'GLOBAL', 16),
('ad_002', 'CANADIAN_COUNTRY', 'NORTH_AMERICA', 18),

-- ad_003 (Nashville Insider)
('ad_003', 'COUNTRY_POLITAN', 'NORTH_AMERICA', 18),
('ad_003', 'COUNTRY_POP', 'EUROPE', 18),

-- ad_004 (Carrie Underwood)
('ad_004', 'COUNTRY_POP', 'GLOBAL', 16),
('ad_004', 'COUNTRY_POLITAN', 'NORTH_AMERICA', 18),

-- ad_005 (Outlaw Country Chronicles)
('ad_005', 'OUTLAW', 'NORTH_AMERICA', 21),
('ad_005', 'ALTERNATIVE_COUNTRY', 'EUROPE', 21),

-- ad_006 (Chris Stapleton)
('ad_006', 'ALTERNATIVE_COUNTRY', 'GLOBAL', 18),
('ad_006', 'OUTLAW', 'NORTH_AMERICA', 21),

-- ad_007 (Modern Country Monthly)
('ad_007', 'COUNTRY_POP', 'GLOBAL', 16),
('ad_007', 'COUNTRY_POLITAN', 'NORTH_AMERICA', 18),

-- ad_008 (Luke Combs)
('ad_008', 'HONKY_TONK', 'NORTH_AMERICA', 18),
('ad_008', 'COUNTRY_POP', 'GLOBAL', 16),

-- ad_009 (Women of Country)
('ad_009', 'COUNTRY_POP', 'GLOBAL', 16),
('ad_009', 'CANADIAN_COUNTRY', 'NORTH_AMERICA', 18),

-- ad_010 (Willie Nelson)
('ad_010', 'OUTLAW', 'GLOBAL', 25),
('ad_010', 'ALTERNATIVE_COUNTRY', 'EUROPE', 21);
