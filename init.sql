CREATE TABLE bivouacs(
    bivouac_id serial NOT NULL PRIMARY KEY,
    price numeric(18, 16) NOT NULL,
    rental_type varchar(30),
    field_type varchar(80),
    area numeric(18, 16),
    description varchar(200) NOT NULL,
    is_pmr boolean NOT NULL,
    privacy varchar(7) NOT NULL,
    is_host boolean default false,
    is_admin boolean default false
);

CREATE TABLE equipments(
    equipment_id serial NOT NULL PRIMARY KEY,
    label varchar(30) NOT NULL,
    icon varchar(30) NOT NULL
);

CREATE TABLE bivouac_equipments
(
    bivouac_id integer NOT NULL REFERENCES bivouacs (bivouac_id),
    equipment_id integer NOT NULL REFERENCES equipments (equipment_id)
);

CREATE TABLE users (
    user_id serial PRIMARY KEY,
    first_name varchar(30),
    last_name varchar(30),
    email varchar(80) NOT NULL,
    phone_number varchar(20),
    password varchar(100) NOT NULL,
    is_host boolean default false,
    is_admin boolean default false
);



CREATE TABLE locations(
    location_id serial PRIMARY KEY,
    latitude numeric(18, 16) NOT NULL,
    longitude numeric(18, 16) NOT NULL,
    location_date timestamp without time zone NOT NULL
);

CREATE TABLE user_locations
(
    user_id integer NOT NULL REFERENCES users (user_id),
    location_id integer NOT NULL REFERENCES locations (location_id)
);

INSERT INTO bivouacs(price,rental_type,field_type,area,description,is_pmr,privacy) VALUES(34,'nothing','forest',56,'Trop bien', false, 'public');
INSERT INTO equipments(label,icon) VALUES('shower','shower');
INSERT INTO users(first_name,last_name,email,phone_number,password) VALUES('Tom','Robinson','tom.rob@yopmail.com','+15103754657','123456');
INSERT INTO locations(latitude,longitude,location_date) VALUES(43.63746472422702,3.8409670228559136,now());