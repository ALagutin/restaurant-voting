INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User_Alex', 'user_alex@yandex.ru', '{noop}user'),
       ('User_Mike', 'user_mike@yandex.ru', '{noop}user'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('ROLE_USER', 1),
       ('ROLE_USER', 2),
       ('ROLE_USER', 3),
       ('ROLE_ADMIN', 3);

INSERT INTO RESTAURANT(NAME, ADDRESS)
VALUES ('Золотая вобла', 'ул. Сущёвский Вал, 9'),
       ('Вера Парк', 'Нахимовский просп., 35, корп. 2'),
       ('Джорджия', 'Балаклавский просп., 7');

INSERT INTO MENU (NAME, PRICE_CENTS, RESTAURANT_ID, DATE_MENU)
VALUES ('Брауни', 1000, 1, '2022-05-12'),
       ('Сырники из творога', 1000, 1, '2022-05-13'),
       ('Спагетти карбонара с красным луком', 2000, 2, '2022-05-13'),
       ('Классическая шарлотка', 2000, 2, '2022-05-13'),
       ('Американский тыквенный пирог с корицей', 1000, 1, '2022-05-14'),
       ('Азу по-татарски', 1000, 1, '2022-05-14'),
       ('Лазанья классическая с мясом', 3000, 3, '2022-05-14'),
       ('Тонкие блины на молоке', 3000, 3, '2022-05-14');

INSERT INTO VOTE(RESTAURANT_ID, USER_ID, DATE_OF_MENU)
VALUES (1, 1, '2020-05-13'),
       (1, 2, '2020-05-13'),
       (2, 3, '2020-05-13'),
       (1, 1, '2020-05-14'),
       (3, 2, '2020-05-14'),
       (3, 3, '2020-05-14');
