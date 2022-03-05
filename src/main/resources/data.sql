INSERT INTO roles (created_date, role)
VALUES (CURRENT_TIMESTAMP, 'ROLE_USER'),
       (CURRENT_TIMESTAMP, 'ROLE_ADMIN');

INSERT INTO users (created_date, username, password)
VALUES (CURRENT_TIMESTAMP, 'admin', '$2a$12$co5TaHW5CeSho.QCTvV3oeDKjVlPYvz9zYIqLB7PBMXUMgQadp11i');

INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 2);

INSERT INTO books (created_date, title, author)
VALUES (CURRENT_TIMESTAMP, 'Steve Jobs bio', 'Walter Isaacson'),
       (CURRENT_TIMESTAMP, 'Pan Tadeusz', 'Adam Mickiewicz');
