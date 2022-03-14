INSERT INTO roles (created_date, role)
VALUES (CURRENT_TIMESTAMP, 'ROLE_USER'),
       (CURRENT_TIMESTAMP, 'ROLE_ADMIN');

INSERT INTO users (created_date, username, password)
VALUES (CURRENT_TIMESTAMP, 'admin@gmail.com', '$2a$12$co5TaHW5CeSho.QCTvV3oeDKjVlPYvz9zYIqLB7PBMXUMgQadp11i'),
       (CURRENT_TIMESTAMP, 'user1', '$2a$12$4HYLqYGKlN.cIZvGm3pxS.TIyRiWX.5lM6L2WtpJpMhGTkTqf8xcm'),
       (CURRENT_TIMESTAMP, 'user2', '$2a$12$98l15u0mhiwNw9VP6yv2PuiL.i8Z47J10VhLa0USFPZSsGX4E8xTq'),
       (CURRENT_TIMESTAMP, 'user3', '$2a$12$IPojbzKIETrpcjfX.VlEMeRn1Jyg9crOBjY9mMhqA0WLIs1wAqFF6'),
       (CURRENT_TIMESTAMP, 'user4', '$2a$12$pbhnAh..V0FzEdlvvpYsFuYLDJbHtgmLw4rvMLj3wCc5UVvA.Om92'),
       (CURRENT_TIMESTAMP, 'user5', '$2a$12$vjOkKhKMxkIzhxVA84yqkuDiiEhiMFBYNhqvv4EsXEzkf72LFD4l.'),
       (CURRENT_TIMESTAMP, 'user6', '$2a$12$8Fy5tpIxqgsEtRvU9ye9sO7zZaXZA.siLsV/SZC1VQv3n6QlPxlxO'),
       (CURRENT_TIMESTAMP, 'user7', '$2a$12$9JNjkbDRNUfStiD/0uKVxuPkMqhEOBbbKanUpwAngszmbindnMd7G'),
       (CURRENT_TIMESTAMP, 'user8', '$2a$12$C0h3tmI9yDjKAk9dXdLm9OgGyrgXs1rAmIor9icV5lNi6f1iCLCZK'),
       (CURRENT_TIMESTAMP, 'user9', '$2a$12$XuKgPqlpnTW17h4k3BXhH.MGKBf4qR9ZPwAmGqqAvM6SpDD4jberS'),
       (CURRENT_TIMESTAMP, 'user10', '$2a$12$8Yr83XwZ3hMrQ8vMSpfFje1wCxO/7nnhG7xsjwL3OROWRXA1N1U4G'),
       (CURRENT_TIMESTAMP, 'user11', '$2a$12$SzgFCH9BEyYDTHnKfpiriORbs0DLcK5tw2IBYcFn1biPLgY1lRkru'),
       (CURRENT_TIMESTAMP, 'user12', '$2a$12$Spmlztayjp6CVYQ65E5MuOHhAkYF8gdgJJ9Jh8WNn9Itmz848/XLC');

INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 2),
       (2, 2),
       (3, 1),
       (3, 2),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1),
       (9, 1),
       (10, 1),
       (11, 1),
       (12, 1),
       (13, 1);

INSERT INTO books (created_date, title, author, price)
VALUES (CURRENT_TIMESTAMP, 'Steve Jobs bio', 'Walter Isaacson', 39),
       (CURRENT_TIMESTAMP, 'Pan Tadeusz', 'Adam Mickiewicz', 12),
       (CURRENT_TIMESTAMP, 'Java. Podstawy', 'Horstmann Cay S.', 99),
       (CURRENT_TIMESTAMP, 'Java. Techniki zaawansowane. Wydanie XI', 'Horstmann Cay S.', 110),
       (CURRENT_TIMESTAMP, 'Język C++. Szkoła programowania. Wydanie VI', 'Stephen Prata', 99),
       (CURRENT_TIMESTAMP, 'Czysty kod. Podręcznik dobrego programisty', 'Robert C. Martin', 44),
       (CURRENT_TIMESTAMP, 'Zielona Mila', 'Stephen King', 27),
       (CURRENT_TIMESTAMP, 'Ślepnąc od świateł', 'Jakub Żulczyk', 24),
       (CURRENT_TIMESTAMP, 'Bezpieczeństwo aplikacji webowych', 'Sekurak', 124),
       (CURRENT_TIMESTAMP, 'Czarny łabędź', 'Nassim Nicholas Taleb', 30),
       (CURRENT_TIMESTAMP, 'Standard Bitcoina', 'Ammous Saifedean', 36),
       (CURRENT_TIMESTAMP, 'Blockchain. Podstawy technologii łańcucha bloków w 25 krokach', 'Drescher Daniel', 38),
       (CURRENT_TIMESTAMP, 'Labirynty Scruma', 'Jacek Wieczorek', 39),
       (CURRENT_TIMESTAMP, 'W głowie twórcy Bitcoina', 'Feniks', 69),
       (CURRENT_TIMESTAMP, 'Spring Framework. Wprowadzenie do tworzenia aplikacji', 'Sharma J. ,Sarin Ashish', 44);

INSERT INTO orders (created_date, status, user_email)
VALUES (CURRENT_TIMESTAMP, 'UNPAID', 'admin@gmail.com'),
       (CURRENT_TIMESTAMP, 'PAID', 'admin@gmail.com'),
       (CURRENT_TIMESTAMP, 'ON_THE_WAY', 'admin@gmail.com'),
       (CURRENT_TIMESTAMP, 'DELIVERED', 'admin@gmail.com');

INSERT INTO orders_books (order_id, books_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 5),
       (3, 5),
       (3, 6),
       (3, 9),
       (4, 15);
