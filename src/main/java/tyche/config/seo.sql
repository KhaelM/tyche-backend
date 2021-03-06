CREATE DATABASE seo;
create role debabe superuser;
grant all privileges on database seo to debabe;
create user razoky superuser password 'boss';
grant debabe to razoky;

DROP TABLE login CASCADE;
DROP TABLE review CASCADE;
DROP TABLE product_tags CASCADE;
DROP TABLE tag CASCADE;
DROP TABLE product_categories CASCADE;
DROP TABLE category CASCADE;
DROP TABLE product CASCADE;
DROP TABLE related_products CASCADE;

CREATE TABLE product (
    name varchar(50) PRIMARY KEY,
    price numeric(5,2),
    sale_price numeric(5,2),
    recap varchar(500),
    description varchar(1500)
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
	
	DELETE FROM related_products;
	INSERT INTO related_products (current_product, related_product) VALUES
	('Alani T-Shirt', 'Little Black Shirt'),
	('Alani T-Shirt', 'Marina Style'),
	('Alani T-Shirt', 'Andora Scarf'),
	('Alani T-Shirt', 'Jennifer Scarf'),
	('Amari Shirt', 'Belka T-Shirt'),
	('Amari Shirt', 'Little Black Shirt'),
	('Amari Shirt', 'Alani T-Shirt'),
	('Amari Shirt', 'Marina Style'),
	('Andora Scarf', 'Belka T-Shirt'),
	('Andora Scarf', 'Merchantile - Blouse'),
	('Andora Scarf', 'Jennifer Scarf'),
	('Andora Scarf', 'Visual M. T-Shirt'),
	('Asabi - Jeans', 'Rocadi Jeans'),
	('Asabi - Jeans', 'Blue Magawi Shoes'),
	('Asabi - Jeans', 'Blue Sweater'),
	('Asabi - Jeans', 'Manago Shirt'),
	('Beije Magawi Shoes', 'Red Magawi Shoes'),
	('Beije Magawi Shoes', 'Blue Magawi Shoes'),
	('Belka T-Shirt', 'Little Black Top'),
	('Belka T-Shirt', 'Merchantile - Blouse'),
	('Belka T-Shirt', 'Amari Shirt'),
	('Belka T-Shirt', 'Alani T-Shirt'),
	('Black Top', 'Marcara Sleeveless Dress'),
	('Black Top', 'Little Black Shirt'),
	('Black Top', 'Marina Style'),
	('Black Top', 'Little Black Top'),
	('Blue Magawi Shoes', 'Asabi - Jeans'),
	('Blue Magawi Shoes', 'Blue Sweater'),
	('Blue Magawi Shoes', 'Red Magawi Shoes'),
	('Blue Magawi Shoes', 'Beije Magawi Shoes'),
	('Blue Sweater', 'Blue Magawi Shoes'),
	('Blue Sweater', 'Asabi - Jeans'),
	('Blue Sweater', 'Merchantile - Blouse'),
	('Blue Sweater', 'Manago Shirt'),
	('FITT Belts', 'Rocadi Jeans'),
	('FITT Belts', 'Istwic Scarf'),
	('FITT Belts', 'Magnolia Dress'),
	('Istwic Scarf', 'Alani T-Shirt'),
	('Istwic Scarf', 'Belka T-Shirt'),
	('Istwic Scarf', 'FITT Belts'),
	('Istwic Scarf', 'Visual M. T-Shirt'),
	('Jennifer Scarf', 'Andora Scarf'),
	('Jennifer Scarf', 'Visual M. T-Shirt'),
	('Jennifer Scarf', 'Istwic Scarf'),
	('Jennifer Scarf', 'Alani T-Shirt'),
	('Little Black Shirt', 'Marina Style'),
	('Little Black Shirt', 'Belka T-Shirt'),
	('Little Black Shirt', 'Amari Shirt'),
	('Little Black Shirt', 'Black Top'),
	('Little Black Top', 'Marina Style'),
	('Little Black Top', 'Amari Shirt'),
	('Little Black Top', 'Visual M. T-Shirt'),
	('Little Black Top', 'Little Black Shirt'),
	('Magnolia Dress', 'FITT Belts'),
	('Magnolia Dress', 'Marcara Sleeveless Dress'),
	('Magnolia Dress', 'Rocadi Jeans'),
	('Manago Shirt', 'Asabi - Jeans'),
	('Manago Shirt', 'Merchantile - Blouse'),
	('Manago Shirt', 'Little Black Shirt'),
	('Manago Shirt', 'Blue Sweater'),
	('Marcara Sleeveless Dress', 'Magnolia Dress'),
	('Marcara Sleeveless Dress', 'Black Top'),
	('Marcara Sleeveless Dress', 'Marina Style'),
	('Marcara Sleeveless Dress', 'Amari Shirt'),
	('Marina Style', 'Marcara Sleeveless Dress'),
	('Marina Style', 'Asabi - Jeans'),
	('Marina Style', 'Amari Shirt'),
	('Marina Style', 'Visual M. T-Shirt'),
	('Merchantile - Blouse', 'Blue Sweater'),
	('Merchantile - Blouse', 'Alani T-Shirt'),
	('Merchantile - Blouse', 'Marina Style'),
	('Merchantile - Blouse', 'Belka T-Shirt'),
	('Red Magawi Shoes', 'Jennifer Scarf'),
	('Red Magawi Shoes', 'Beije Magawi Shoes'),
	('Red Magawi Shoes', 'Blue Magawi Shoes'),
	('Rocadi Jeans', 'Blue Sweater'),
	('Rocadi Jeans', 'Blue Magawi Shoes'),
	('Rocadi Jeans', 'Asabi - Jeans'),
	('Rocadi Jeans', 'FITT Belts'),
	('Visual M. T-Shirt', 'Merchantile - Blouse'),
	('Visual M. T-Shirt', 'Alani T-Shirt'),
	('Visual M. T-Shirt', 'Belka T-Shirt'),
	('Visual M. T-Shirt', 'Jennifer Scarf');

INSERT INTO review (name, comment, rate, product_name) VALUES
('LeMooky', 'Milay be le produit eh!', 5, 'Alani T-Shirt');
	
	
	DELETE FROM related_products where current_product = 'Bobobo';
	DELETE FROM related_products where related_product = 'Bobobo';
	DELETE FROM product_tags where product_name = 'Bobobo';
	DELETE FROM product_categories where product_name = 'Bobobo';
	DELETE FROM product where product.name = 'Bobobo';