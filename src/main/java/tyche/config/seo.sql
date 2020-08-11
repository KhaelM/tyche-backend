CREATE DATABASE seo;
create role debabe superuser;
grant all privileges on database seo to debabe;
create user razoky superuser password 'boss';
grant debabe to razoky;

DROP TABLE review CASCADE;
DROP TABLE product_tags CASCADE;
DROP TABLE tag CASCADE;
DROP TABLE product_categories CASCADE;
DROP TABLE category CASCADE;
DROP TABLE product CASCADE;

CREATE TABLE product (
    name varchar(50) PRIMARY KEY,
    price numeric(5,2),
    sale_price numeric(5,2),
    recap varchar(500),
    description varchar(500)
);

CREATE TABLE category (
    name varchar(30) PRIMARY KEY
);

CREATE TABLE product_categories (
    product_name varchar(50),
    category_name varchar(30),
    PRIMARY KEY(product_name, category_name),
    FOREIGN KEY(product_name) REFERENCES product(name),
    FOREIGN KEY(category_name) REFERENCES category(name)
);

CREATE TABLE tag (
    name varchar(30) PRIMARY KEY
);

CREATE TABLE product_tags (
    product_name varchar(50),
    tag_name varchar(30),
    PRIMARY KEY(product_name, tag_name),
    FOREIGN KEY(product_name) REFERENCES product(name),
    FOREIGN KEY(tag_name) REFERENCES tag(name)
);

CREATE TABLE review (
    id smallserial PRIMARY KEY,
    name varchar(50),
    review_date date DEFAULT CURRENT_DATE,
    comment text,
    rate smallint,
    product_name varchar(50),
    FOREIGN KEY(product_name) REFERENCES product(name)
);

CREATE TABLE related_products (
    current_product varchar(50),
    related_product varchar(50),
    PRIMARY KEY(current_product, related_product),
    FOREIGN KEY(current_product) REFERENCES product(name),
    FOREIGN KEY(related_product) REFERENCES product(name)
);

CREATE TABLE login (
	username varchar(50) PRIMARY KEY,
	password varchar
);

INSERT INTO login (username, password) VALUES
	('it', 'university');

INSERT INTO category (name) VALUES
	('Shirts'),
	('Trends'),
	('Scarfs'),
	('Featured'),
	('Jeans'),
	('Shoes'),
	('Tops'),
	('Blouse'),
	('Belts'),
	('Most Wanted'),
	('Dresses');
	
INSERT INTO tag (name) VALUES
	('shirt'),
	('white'),
	('blue'),
	('jeans'),
	('heels'),
	('red'),
	('shoes'),
	('blouse'),
	('dress'),
	('sleeveless'),
	('manago'),
	('magnolia'),
	('yellow'),
	('black'),
	('top'),
	('scarf'),
	('colorful'),
	('belt'),
	('fitt'),
	('print'),
	('beije'),
	('asabi'),
	('orange'),
	('amari'),
	('pokemon');

INSERT INTO product(name, price, sale_price, recap, description) VALUES
	('Alani T-Shirt',				12.00,	10.00,	NULL,	NULL),
	('Amari Shirt',					25.00,	NULL,	NULL,	NULL),
	('Andora Scarf',				37.00,	NULL,	NULL,	NULL),
	('Asabi - Jeans',				25.00,	NULL,	NULL,	NULL),
	('Beije Magawi Shoes',			30.00,	NULL,	NULL,	NULL),
	('Belka T-Shirt',				15.00,	NULL,	NULL,	NULL),
	('Black Top',					35.00,	NULL,	NULL,	NULL),
	('Blue Magawi Shoes',			37.00,	NULL,	NULL,	NULL),
	('Blue Sweater',				25.00,	15.00,	NULL,	NULL),
	('FITT Belts',					35.00,	NULL,	NULL,	NULL),
	('Istwic Scarf',				37.00,	23.00,	NULL,	NULL),
	('Jennifer Scarf',				36.00,	NULL,	NULL,	NULL),
	('Little Black Shirt',			40.00,	NULL,	NULL,	NULL),
	('Little Black Top',			35.00,	20.00,	NULL,	NULL),
	('Magnolia Dress',				25.00,	NULL,	NULL,	NULL),
	('Manago Shirt',				25.00,	NULL,	NULL,	NULL),
	('Marcara Sleeveless Dress',	55.00,	NULL,	NULL,	NULL),
	('Marina Style',				35.00,	NULL,	NULL,	NULL),
	('Merchantile - Blouse',		15.00,	NULL,	NULL,	NULL),
	('Red Magawi Shoes',			35.00,	25.00,	NULL,	NULL),
	('Rocadi Jeans',				70.00,	60.00,	NULL,	NULL),
	('Visual M. T-Shirt',			10.00,	NULL,	NULL,	NULL);
	
INSERT INTO product_categories (product_name, category_name) VALUES
	('Alani T-Shirt',	'Shirts'),
	('Amari Shirt',	'Shirts'),
	('Amari Shirt',	'Trends'),
	('Andora Scarf',	'Scarfs'),
	('Asabi - Jeans',	'Featured'),
	('Asabi - Jeans',	'Jeans'),
	('Beije Magawi Shoes',	'Shoes'),
	('Belka T-Shirt',	'Shirts'),
	('Black Top',	'Tops'),
	('Black Top',	'Trends'),
	('Blue Magawi Shoes',	'Shoes'),
	('Blue Sweater',	'Blouse'),
	('Blue Sweater',	'Featured'),
	('FITT Belts',	'Belts'),
	('FITT Belts',	'Most Wanted'),
	('Istwic Scarf',	'Scarfs'),
	('Jennifer Scarf',	'Scarfs'),
	('Little Black Shirt',	'Tops'),
	('Little Black Top',	'Tops'),
	('Magnolia Dress',	'Dresses'),
	('Magnolia Dress',	'Most Wanted'),
	('Manago Shirt',	'Featured'),
	('Manago Shirt',	'Shirts'),
	('Marcara Sleeveless Dress',	'Dresses'),
	('Marcara Sleeveless Dress',	'Trends'),
	('Marina Style',	'Shirts'),
	('Marina Style',	'Trends'),
	('Merchantile - Blouse',	'Blouse'),
	('Merchantile - Blouse',	'Featured'),
	('Red Magawi Shoes',	'Shoes'),
	('Rocadi Jeans',	'Jeans'),
	('Rocadi Jeans',	'Most Wanted'),
	('Visual M. T-Shirt',	'Shirts');
	
	
	INSERT INTO related_products (current_product, related_product) VALUES
	('Alani T-Shirt', 'Little Black Shirt'),
	('Alani T-Shirt', 'Marina Style'),
	('Alani T-Shirt', 'Andora Scarf'),
	('Alani T-Shirt', 'Jennifer Scarf');
	
	
	DELETE FROM related_products where current_product = 'Itachi Uchiha';
	DELETE FROM product_tags where product_name = 'Itachi Uchiha';
	DELETE FROM product_categories where product_name = 'Itachi Uchiha';
	DELETE FROM product where product.name = 'Itachi Uchiha';