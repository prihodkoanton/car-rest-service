CREATE TABLE categories
(
    id          			bigserial NOT NULL,
    name       text      NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (id)
);

CREATE TABLE makes
(
    id   		bigserial	NOT NULL,
    name 		text    		NOT NULL,
    CONSTRAINT make_pkey primary key (id)
);

CREATE TABLE models
(
    id   			bigserial     			NOT NULL,
    name 			text	          		NOT NULL,
    make_ref 		bigserial         	REFERENCES makes (id) ON DELETE CASCADE,
    constraint model_pkey primary key (id),
    unique (make_ref)
);

CREATE TABLE cars
(
    id   			bigserial     			NOT NULL,
    year 			bigint          		NOT NULL,
    make_ref 		bigserial         	REFERENCES makes (id) ON DELETE CASCADE,
    model_ref 		bigserial         	REFERENCES models (id) ON DELETE CASCADE,
    constraint cars_pkey primary key (id),
    unique (make_ref, model_ref)
);



CREATE TABLE cars_categories
(
    car_id  	bigserial,
    category_id	bigserial,
    CONSTRAINT fk_models FOREIGN KEY  (car_id) REFERENCES cars (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_categories FOREIGN KEY  (category_id) REFERENCES categories (id) ON UPDATE CASCADE ON DELETE CASCADE
);







