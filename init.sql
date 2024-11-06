CREATE TABLE bivouacs(
    bivouac_id serial NOT NULL PRIMARY KEY,
    price numeric(18, 16) NOT NULL,
    rentalType varchar(30),
    fieldType varchar(80),
    area numeric(18, 16),
    description varchar(200) NOT NULL,
    isPmr boolean NOT NULL,
    privacy varchar(7) NOT NULL
);

CREATE TABLE equipments(
    equipment_id serial NOT NULL PRIMARY KEY,
    label varchar(30) NOT NULL,
    icon varchar(30) NOT NULL,
);

CREATE TABLE bivouac_equipments
(
    bivouac_id integer NOT NULL REFERENCES bivouacs (bivouac_id),
    equipment_id integer NOT NULL REFERENCES equipments (equipment_id)
);

INSERT INTO bivouacs(price,rentalType,fieldType,area,description,isPmr,privacy) VALUES(34,'nothing','forest',56,'Trop bien', false, 'public');
INSERT INTO locations(label,icon) VALUES("shower","shower");