insert into users (id, username, password) 
values (100, 'Anton', '12345678'),
       (101, 'Anton1', '12345678');
       
insert into roles (id, name)
values (1, 'ROLE_USER'),
		 (2, 'ROLE_ADMIN');
		 
insert into user_roles (user_id, role_id)
values (100, 1),
       (101, 2);		 