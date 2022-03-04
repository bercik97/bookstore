INSERT INTO roles (created_date, role) VALUES (CURRENT_TIMESTAMP, 'ROLE_USER'), (CURRENT_TIMESTAMP, 'ROLE_ADMIN');

INSERT INTO users (created_date, username, password)
VALUES (CURRENT_TIMESTAMP, 'admin', '$2a$12$co5TaHW5CeSho.QCTvV3oeDKjVlPYvz9zYIqLB7PBMXUMgQadp11i');

INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 2);
