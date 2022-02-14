INSERT INTO users(name, email) VALUES ('john', 'john@mail.com');
INSERT INTO users(name, email) VALUES ('john', 'john2@mail.com');

INSERT INTO user_accounts(user_id, amount) VALUES (1, 35);
INSERT INTO user_accounts(user_id, amount) VALUES (2, 70);

INSERT INTO events(title, date, price) VALUES ('New Year Eve', CAST('2021-12-31 18:00' AS DateTime), 15.0);
INSERT INTO events(title, date, price) VALUES ('New Year Eve', CAST('2021-12-31 23:00' AS DateTime), 17.5);

INSERT INTO tickets(event_id, user_id, category, place) VALUES (1, 1, 0, 14);
INSERT INTO tickets(event_id, user_id, category, place) VALUES (2, 2, 0, 18);