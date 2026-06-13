-- Initial data load
-- users (passwords are BCrypt hashes)
INSERT INTO users (username, password, role) VALUES
('admin@example.com','$2a$10$Wq3ZyqjFJ8q2pud6C9Fp6uO7G4YvO0R0q4zd0Fh2u6dXn8u3tZP8G','ADMIN'),
('user@example.com','$2a$10$hDfdmrtQh2B1k1lYv3lYeuQeK7rQxk1AvN0XMQVw6aJh1WvF0vO2K','USER');

-- movies
INSERT INTO movies (id, title, genre, duration, description, poster_url, active) VALUES
(1,'The Matrix Resurgence','Sci-Fi',148,'A sci-fi action film.','/images/matrix.jpg',true),
(2,'Space Odyssey','Sci-Fi',142,'Epic space film.','/images/space.jpg',true),
(3,'Love in Spring','Romance',120,'Romantic drama.','/images/romance.jpg',true),
(4,'Comedy Night','Comedy',95,'Laugh out loud.','/images/comedy.jpg',true);

-- theaters
INSERT INTO theaters (id, name, location) VALUES
(1,'Galaxy Cinema','MG Road'),
(2,'Star Cineplex','City Center');

-- shows
INSERT INTO shows (id, movie_id, theater_id, show_time, price) VALUES
(1,1,1,'2025-10-20 18:00:00',200),
(2,1,2,'2025-10-20 21:00:00',220),
(3,2,1,'2025-10-21 19:30:00',250);
