/* Create Sequence */
create sequence hibernate_sequence start with 10000 increment by 1;

/* Create Tables */
create table product (
    id int PRIMARY KEY,
    name varchar(20),
    description varchar(100),
    price decimal(10,3)
);

create table customer (
    id int PRIMARY KEY,
    name varchar(50),
    email varchar(60)
);

--create table employee (
--    id int PRIMARY KEY AUTO_INCREMENT,
--    name varchar(50)
--);

create table employee (
    id int PRIMARY KEY,
    name varchar(50)
);

create table id_gen (
    gen_name varchar(60) PRIMARY KEY,
    gen_val int(20)
);

create table student (
    id int PRIMARY KEY AUTO_INCREMENT,
    last_name varchar(50),
    first_name varchar(50),
    score int
);

create table payment (
    id int PRIMARY KEY AUTO_INCREMENT,
    payment_mode varchar(2),
    amount decimal(8, 2),
    card_number varchar(20),
    check_number varchar(20)
);

create table credit_card_payment (
    id int PRIMARY KEY,
    amount decimal(8, 2),
    card_number varchar(20)
);

create table check_payment (
    id int PRIMARY KEY,
    amount decimal(8, 2),
    check_number varchar(20)
);

create table payment_joined (
    id int PRIMARY KEY AUTO_INCREMENT,
    amount decimal(8, 2)
);

create table credit_card_payment_joined (
    id int,
    card_number varchar(20),
    FOREIGN KEY (id) REFERENCES payment_joined(id)
);

create table check_payment_joined (
    id int,
    check_number varchar(20),
    FOREIGN KEY (id) REFERENCES payment_joined(id)
);

create table employee_nested (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(50),
    street_address varchar(30),
    city varchar(25),
    state varchar(25),
    zip_code varchar(20),
    country varchar(20)
);

create table customer_one_to_many (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(50)
);

create table phone_number_many_to_one (
    id int PRIMARY KEY AUTO_INCREMENT,
    customer_id int,
    number varchar(20),
    type varchar(20),
    FOREIGN KEY (customer_id) REFERENCES customer_one_to_many(id)
);

/* Many-To-Many */
create table programmer (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(30),
    salary int
);

create table project (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(100)
);

create table programmers_project (
    programmer_id int,
    project_id int,
    FOREIGN KEY (programmer_id) REFERENCES programmer(id),
    FOREIGN KEY (project_id) REFERENCES project(id)
);

/* One-To-One */
create table person (
    id int PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(30),
    last_name varchar(30),
    age int
);

create table license (
    id int PRIMARY KEY AUTO_INCREMENT,
    type varchar(20),
    valid_from date,
    valid_to date,
    person_id int,
    FOREIGN KEY (person_id) REFERENCES person(id)
);

/* Library for cache */
create table library (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(30)
);

/* bank_account for Transaction */
create table bank_account (
    id int PRIMARY KEY AUTO_INCREMENT,
    account_id int,
    name varchar(30),
    balance int
);

/* image */
create table image (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(50),
    image blob
);


