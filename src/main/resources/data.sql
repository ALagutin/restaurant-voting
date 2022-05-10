INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User_Alex', 'user_alex@yandex.ru', 'user'),
       ('User_Mike', 'user_mike@yandex.ru', 'user'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('ROLE_USER', 1),
       ('ROLE_USER', 2),
       ('ROLE_USER', 3),
       ('ROLE_ADMIN', 3);

SELECT 1;