insert into makes (id, name)
VALUES (100, 'Audi'),
       (101, 'BMW'),
       (102, 'Mercedes');
       
insert into models (id, name, make_ref)
VALUES (100, 'test1', 100),
		(101, 'test2', 101),	
		(102, 'test3', 102);	
		
insert into categories (id, name)
VALUES (100, 'Sedan'),
       (101, 'Suv'),
       (102, 'Pickup');		      