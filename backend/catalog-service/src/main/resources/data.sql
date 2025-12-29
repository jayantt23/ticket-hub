-- =======================================================
-- 1. THEATRES
-- =======================================================
INSERT INTO theatres (id, name, city, address) VALUES
(1, 'PVR Icon', 'Mumbai', 'Infinity Mall, Versova'),
(2, 'IMAX Wadala', 'Mumbai', 'Bhakti Park, Anik Wadala Link Rd'),
(3, 'PVR Plaza', 'Delhi', 'Connaught Place, Block H'),
(4, 'PVR Select', 'Delhi', 'Select Citywalk, Saket'),
(5, 'PVR Gold', 'Bangalore', 'VR Bengaluru, Whitefield')
ON CONFLICT (id) DO NOTHING;

-- =======================================================
-- 2. HALLS
-- =======================================================

-- --- MUMBAI: PVR Icon (Halls 1 & 2) ---
-- Hall 1: Standard
INSERT INTO halls (id, name, theatre_id, seat_layout) VALUES
(1, 'Audi 1', 1, '{"totalSeats": 10, "columns": 5, "rows": [{"rowLabel": "A", "premium": false, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}]}, {"rowLabel": "B", "premium": false, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}]}]}'::jsonb)
ON CONFLICT (id) DO NOTHING;

-- Hall 2: Premium (Gold Class)
INSERT INTO halls (id, name, theatre_id, seat_layout) VALUES
(2, 'Gold Class', 1, '{"totalSeats": 8, "columns": 4, "rows": [{"rowLabel": "A", "premium": true, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}]}, {"rowLabel": "B", "premium": true, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}]}]}'::jsonb)
ON CONFLICT (id) DO NOTHING;

-- --- MUMBAI: IMAX Wadala (Halls 3 & 4) ---
-- Hall 3: IMAX Large
INSERT INTO halls (id, name, theatre_id, seat_layout) VALUES
(3, 'IMAX Screen', 2, '{"totalSeats": 20, "columns": 10, "rows": [{"rowLabel": "J", "premium": true, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}, {"number": 6, "type": "SEAT"}, {"number": 7, "type": "SEAT"}, {"number": 8, "type": "SEAT"}, {"number": 9, "type": "SEAT"}, {"number": 10, "type": "SEAT"}]}, {"rowLabel": "K", "premium": true, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}, {"number": 6, "type": "SEAT"}, {"number": 7, "type": "SEAT"}, {"number": 8, "type": "SEAT"}, {"number": 9, "type": "SEAT"}, {"number": 10, "type": "SEAT"}]}]}'::jsonb)
ON CONFLICT (id) DO NOTHING;

-- Hall 4: Standard
INSERT INTO halls (id, name, theatre_id, seat_layout) VALUES
(4, 'Screen 2', 2, '{"totalSeats": 10, "columns": 5, "rows": [{"rowLabel": "A", "premium": false, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}]}, {"rowLabel": "B", "premium": false, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}]}]}'::jsonb)
ON CONFLICT (id) DO NOTHING;

-- --- DELHI: PVR Plaza (Halls 5 & 6) ---
-- Hall 5: Standard
INSERT INTO halls (id, name, theatre_id, seat_layout) VALUES
(5, 'Audi 1', 3, '{"totalSeats": 12, "columns": 6, "rows": [{"rowLabel": "A", "premium": false, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}, {"number": 6, "type": "SEAT"}]}, {"rowLabel": "B", "premium": false, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}, {"number": 6, "type": "SEAT"}]}]}'::jsonb)
ON CONFLICT (id) DO NOTHING;

-- Hall 6: Premium
INSERT INTO halls (id, name, theatre_id, seat_layout) VALUES
(6, 'Audi 2', 3, '{"totalSeats": 8, "columns": 4, "rows": [{"rowLabel": "A", "premium": true, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}]}, {"rowLabel": "B", "premium": true, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}]}]}'::jsonb)
ON CONFLICT (id) DO NOTHING;

-- --- DELHI: PVR Select (Halls 7 & 8) ---
-- Hall 7: IMAX
INSERT INTO halls (id, name, theatre_id, seat_layout) VALUES
(7, 'IMAX Screen', 4, '{"totalSeats": 20, "columns": 10, "rows": [{"rowLabel": "J", "premium": true, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}, {"number": 6, "type": "SEAT"}, {"number": 7, "type": "SEAT"}, {"number": 8, "type": "SEAT"}, {"number": 9, "type": "SEAT"}, {"number": 10, "type": "SEAT"}]}, {"rowLabel": "K", "premium": true, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}, {"number": 6, "type": "SEAT"}, {"number": 7, "type": "SEAT"}, {"number": 8, "type": "SEAT"}, {"number": 9, "type": "SEAT"}, {"number": 10, "type": "SEAT"}]}]}'::jsonb)
ON CONFLICT (id) DO NOTHING;

-- Hall 8: Standard
INSERT INTO halls (id, name, theatre_id, seat_layout) VALUES
(8, 'Screen 2', 4, '{"totalSeats": 10, "columns": 5, "rows": [{"rowLabel": "A", "premium": false, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}]}, {"rowLabel": "B", "premium": false, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}]}]}'::jsonb)
ON CONFLICT (id) DO NOTHING;

-- --- BANGALORE (Halls 7 & 8) ---
-- Hall 9: Standard
INSERT INTO halls (id, name, theatre_id, seat_layout) VALUES
(9, 'Screen 1', 5, '{"totalSeats": 10, "columns": 5, "rows": [{"rowLabel": "A", "premium": false, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}]}, {"rowLabel": "B", "premium": false, "seats": [{"number": 1, "type": "SEAT"}, {"number": 2, "type": "SEAT"}, {"number": 3, "type": "SEAT"}, {"number": 4, "type": "SEAT"}, {"number": 5, "type": "SEAT"}]}]}'::jsonb)
ON CONFLICT (id) DO NOTHING;

-- =======================================================
-- 3. MOVIES
-- =======================================================
INSERT INTO movies (id, title, genre, description) VALUES
(1, 'Interstellar', 'Sci-Fi', 'Space travel epic'),
(2, 'The Dark Knight', 'Action', 'Batman vs Joker'),
(3, 'Inception', 'Sci-Fi', 'Dream within a dream'),
(4, 'Parasite', 'Drama', 'Class struggle in Korea'),
(5, 'Avengers: Endgame', 'Action', 'Thanos snap aftermath'),
(6, 'Jawan', 'Action', 'SRK in double role'),
(7, '3 Idiots', 'Comedy', 'Engineering students life'),
(8, 'Spider-Man: No Way Home', 'Action', 'Multiverse unleashed'),
(9, 'The Godfather', 'Crime', 'Mafia family saga'),
(10, 'Barbie', 'Comedy', 'Life in plastic')
ON CONFLICT (id) DO NOTHING;

-- =======================================================
-- 4. SHOWS
-- =======================================================

-- DAY 1 (Tomorrow): Morning, Afternoon, Evening
-- Mumbai (Theatres 1 & 2)
INSERT INTO shows (movie_id, hall_id, start_time, base_price) VALUES
(1, 1, CURRENT_TIMESTAMP + INTERVAL '1 day 10 hours', 350.00), -- Interstellar
(2, 1, CURRENT_TIMESTAMP + INTERVAL '1 day 14 hours', 350.00), -- Dark Knight
(6, 2, CURRENT_TIMESTAMP + INTERVAL '1 day 11 hours', 500.00), -- Jawan (Gold)
(5, 3, CURRENT_TIMESTAMP + INTERVAL '1 day 09 hours', 800.00), -- Endgame (IMAX)
(5, 3, CURRENT_TIMESTAMP + INTERVAL '1 day 13 hours', 850.00), -- Endgame (IMAX)
(8, 3, CURRENT_TIMESTAMP + INTERVAL '1 day 17 hours', 850.00), -- Spiderman (IMAX)
(7, 4, CURRENT_TIMESTAMP + INTERVAL '1 day 12 hours', 300.00), -- 3 Idiots
(4, 4, CURRENT_TIMESTAMP + INTERVAL '1 day 16 hours', 300.00); -- Parasite

-- Delhi (Theatres 3 & 4)
INSERT INTO shows (movie_id, hall_id, start_time, base_price) VALUES
(9, 5, CURRENT_TIMESTAMP + INTERVAL '1 day 10 hours', 250.00), -- Godfather
(3, 5, CURRENT_TIMESTAMP + INTERVAL '1 day 15 hours', 300.00), -- Inception
(10, 6, CURRENT_TIMESTAMP + INTERVAL '1 day 12 hours', 450.00), -- Barbie (Premium)
(2, 6, CURRENT_TIMESTAMP + INTERVAL '1 day 18 hours', 450.00), -- Dark Knight (Premium)
(1, 7, CURRENT_TIMESTAMP + INTERVAL '1 day 11 hours', 750.00), -- Interstellar (IMAX)
(6, 7, CURRENT_TIMESTAMP + INTERVAL '1 day 15 hours', 750.00), -- Jawan (IMAX)
(8, 8, CURRENT_TIMESTAMP + INTERVAL '1 day 13 hours', 300.00); -- Spiderman

-- DAY 2 (Day After Tomorrow)
-- Mumbai
INSERT INTO shows (movie_id, hall_id, start_time, base_price) VALUES
(3, 1, CURRENT_TIMESTAMP + INTERVAL '2 days 10 hours', 350.00), -- Inception
(4, 1, CURRENT_TIMESTAMP + INTERVAL '2 days 14 hours', 300.00), -- Parasite
(9, 2, CURRENT_TIMESTAMP + INTERVAL '2 days 18 hours', 600.00), -- Godfather (Gold)
(1, 3, CURRENT_TIMESTAMP + INTERVAL '2 days 10 hours', 800.00), -- Interstellar (IMAX)
(2, 3, CURRENT_TIMESTAMP + INTERVAL '2 days 15 hours', 800.00), -- Dark Knight (IMAX)
(10, 4, CURRENT_TIMESTAMP + INTERVAL '2 days 12 hours', 300.00); -- Barbie

-- Delhi
INSERT INTO shows (movie_id, hall_id, start_time, base_price) VALUES
(5, 5, CURRENT_TIMESTAMP + INTERVAL '2 days 10 hours', 300.00), -- Endgame
(7, 5, CURRENT_TIMESTAMP + INTERVAL '2 days 15 hours', 300.00), -- 3 Idiots
(6, 6, CURRENT_TIMESTAMP + INTERVAL '2 days 19 hours', 500.00), -- Jawan (Premium)
(8, 7, CURRENT_TIMESTAMP + INTERVAL '2 days 11 hours', 750.00), -- Spiderman (IMAX)
(3, 7, CURRENT_TIMESTAMP + INTERVAL '2 days 16 hours', 750.00); -- Inception (IMAX)

-- DAY 3 (3 Days from now)
-- Mixing remaining movies into remaining slots
INSERT INTO shows (movie_id, hall_id, start_time, base_price) VALUES
(10, 1, CURRENT_TIMESTAMP + INTERVAL '3 days 10 hours', 350.00), -- Barbie
(8, 1, CURRENT_TIMESTAMP + INTERVAL '3 days 14 hours', 350.00), -- Spiderman
(1, 2, CURRENT_TIMESTAMP + INTERVAL '3 days 18 hours', 600.00), -- Interstellar (Gold)
(6, 3, CURRENT_TIMESTAMP + INTERVAL '3 days 12 hours', 850.00), -- Jawan (IMAX)
(5, 4, CURRENT_TIMESTAMP + INTERVAL '3 days 15 hours', 300.00), -- Endgame
(9, 5, CURRENT_TIMESTAMP + INTERVAL '3 days 11 hours', 250.00), -- Godfather
(4, 6, CURRENT_TIMESTAMP + INTERVAL '3 days 17 hours', 450.00), -- Parasite (Premium)
(2, 7, CURRENT_TIMESTAMP + INTERVAL '3 days 14 hours', 750.00), -- Dark Knight (IMAX)
(7, 8, CURRENT_TIMESTAMP + INTERVAL '3 days 10 hours', 300.00); -- 3 Idiots