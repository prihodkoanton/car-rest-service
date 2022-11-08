CREATE TABLE categories
(
    id          			bigserial NOT NULL,
    category_name       text      NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (id)
);

CREATE TABLE makes
(
    id   		bigserial	NOT NULL,
    make_name 	text      	NOT NULL,
    CONSTRAINT make_pkey primary key (id)
);

CREATE TABLE models
(
    id   			bigserial     			NOT NULL,
    year 			bigint          		NOT NULL,
    make_ref 		bigserial         	REFERENCES makes (id) ON DELETE CASCADE,
    constraint model_pkey primary key (id),
    unique (make_ref)
);

CREATE TABLE models_categories
(
    model_id  	bigserial,
    category_id	bigserial,
    CONSTRAINT fk_models FOREIGN KEY  (model_id) REFERENCES models (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_categories FOREIGN KEY  (category_id) REFERENCES categories (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE users
(
	id 			bigserial		NOT NULL,
	username 	text				NOT NULL,
	password		text				NOT NULL,
	created		TIMESTAMPTZ		NOT NULL		DEFAULT NOW(),
	updated		TIMESTAMPTZ		NOT NULL		DEFAULT NOW(),
	status varchar(25) 			NOT NULL 	DEFAULT ('ACTIVE'),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE  roles
(
	id 			bigserial		NOT NULL,
	name 			text				NOT NULL,
	created		TIMESTAMPTZ		NOT NULL		DEFAULT NOW(),
	updated		TIMESTAMPTZ		NOT NULL		DEFAULT NOW(),
	status varchar(25) 			NOT NULL 	DEFAULT ('ACTIVE'),
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);

CREATE TABLE  user_roles
(
	user_id			bigserial		NOT NULL,
	role_id			bigserial		NOT NULL,
	CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_roles FOREIGN KEY (role_id) REFERENCES roles (id) ON UPDATE CASCADE ON DELETE CASCADE 
);

insert into roles (id, name) values (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');







