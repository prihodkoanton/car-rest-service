CREATE TABLE categories
(
    id          			bigserial NOT NULL,
    category_name       text      NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (id)
);

create table makes
(
    id   		bigserial	NOT NULL,
    make_name 	text      	NOT NULL,
    CONSTRAINT make_pkey primary key (id)
);

create table models
(
    id   			bigserial     			NOT NULL,
    year 			bigint          		NOT NULL,
    make_ref 		bigserial         	REFERENCES makes (id) ON DELETE CASCADE,
    constraint model_pkey primary key (id),
    unique (make_ref)
);

create table models_categories
(
    model_id  	bigserial,
    category_id	bigserial,
    CONSTRAINT fk_models FOREIGN KEY  (model_id) REFERENCES models (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_categories FOREIGN KEY  (category_id) REFERENCES categories (id) ON UPDATE CASCADE ON DELETE CASCADE
);

