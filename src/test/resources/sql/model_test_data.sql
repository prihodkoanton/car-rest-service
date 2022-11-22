insert into makes (id, make_name)
VALUES (100, 'Audi'),
       (101, 'BMW'),
       (102, 'Mercedes');
       
insert into models (id, year, make_ref)
VALUES (100, 2020, 100),
		(101, 2022, 101),	
		(102, 2021, 102);	
		
insert into categories (id, category_name)
VALUES (100, 'Sedan'),
       (101, 'Suv'),
       (102, 'Pickup');		
       
       
insert into models_categories(model_id, category_id)
VALUES (100, 100),
		 (101, 101),
		 (102, 102);      
