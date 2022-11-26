insert into makes (id, make_name)
VALUES (100, 'Audi'),
       (101, 'BMW'),
       (102, 'Mercedes');
       
insert into models (id, name, make_ref)
VALUES (100, 'test1', 100),
		(101, 'test2', 101),	
		(102, 'test3', 102);	
		
insert into categories (id, category_name)
VALUES (100, 'Sedan'),
       (101, 'Suv'),
       (102, 'Pickup');		

insert into cars (id, make_ref, model_ref)
VALUES (100, 100, 100),
		 (101,  101, 101),
		 (102,  102, 102);
       
       
insert into cars_categories(car_id, category_id)
VALUES (100, 100),
		 (101, 101),
		 (102, 102); 