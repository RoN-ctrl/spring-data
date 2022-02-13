INSERT INTO users(name, email) VALUES ('john', 'john@mail.com');
INSERT INTO users(name, email) VALUES ('john', 'john2@mail.com');

INSERT INTO events(title, date) VALUES ('New Year Eve', CAST('2021-12-31 18:00' AS DateTime));
INSERT INTO events(title, date) VALUES ('New Year Eve', CAST('2021-12-31 23:00' AS DateTime));

INSERT INTO tickets(event_id, user_id, category, place) VALUES (1, 1, 0, 14);
INSERT INTO tickets(event_id, user_id, category, place) VALUES (2, 2, 0, 18);