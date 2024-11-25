-- Suppression et création de la table users
DROP TABLE IF EXISTS users;

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

INSERT INTO users(first_name, last_name, email, phone_number, password)
VALUES ('Tom', 'Robinson', 'tom.rob@yopmail.com', '+15103754657', '123456');

-- Suppression et création de la table favourite_bivouacs
DROP TABLE IF EXISTS favourite_bivouacs;

CREATE TABLE favourite_bivouacs (
    user_id integer NOT NULL,
    bivouac_id integer NOT NULL,
    PRIMARY KEY (user_id, bivouac_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

-- Insertion d'une ligne exemple dans favourite_bivouacs
INSERT INTO favourite_bivouacs(user_id, bivouac_id) VALUES (1, 101);
