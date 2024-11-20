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

INSERT INTO users(first_name,last_name,email,phone_number,password) VALUES('Tom','Robinson','tom.rob@yopmail.com','+15103754657','123456');
