insert into roles (id, name)
VALUES (100, 'ADMIN'),
       (101, 'USER');
       
insert into user_roles (user_id, role_id)
VALUES (100, 100),
       (101, 101);
       
insert into users (id, username, password)
VALUES (100, 'Anton', '12345678'),
       (101, 'Andrey', '12345678');
       