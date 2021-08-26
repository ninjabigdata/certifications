/* Product */
INSERT INTO product VALUES (1, 'babylon', 'personal finance', 250);
INSERT INTO product VALUES (2, '10 commandments', 'personal finance', 350);

/* Customer */
INSERT INTO customer VALUES (1, 'balaji', 'balaji@prod.com');
INSERT INTO customer VALUES (2, 'kelvin', 'kelvin@prod.com');

/* Employee */
INSERT INTO employee VALUES (1, 'balaji');
INSERT INTO employee VALUES (2, 'kelvin');

/* Student */
INSERT INTO student (last_name, first_name, score) VALUES ('sridharan', 'balaji', 98);
INSERT INTO student (last_name, first_name, score) VALUES ('john', 'vijay', 89);
INSERT INTO student (last_name, first_name, score) VALUES ('joseph', 'vijay', 95);

/* Payment */
INSERT INTO payment (payment_mode, amount, card_number) VALUES ('cc', 540.75, '12341234123412341234');
INSERT INTO payment (payment_mode, amount, card_number) VALUES ('cc', 140.05, '12341234123412341234');
INSERT INTO payment (payment_mode, amount, check_number) VALUES ('ch', 540.75, '12345123451234512345');
INSERT INTO payment (payment_mode, amount, check_number) VALUES ('ch', 140.75, '12345123451234512346');

/* Credit Card Payment */
INSERT INTO credit_card_payment (id, amount, card_number) VALUES (1, 540.75, '12341234123412341234');
INSERT INTO credit_card_payment (id, amount, card_number) VALUES (2, 140.05, '12341234123412341234');

/* Check Payment */
INSERT INTO check_payment (id, amount, check_number) VALUES (1, 540.75, '12345123451234512345');
INSERT INTO check_payment (id, amount, check_number) VALUES (2, 140.75, '12345123451234512346');

/* Payment_joined */
INSERT INTO payment_joined (id, amount) VALUES (1, 540.75);
INSERT INTO payment_joined (id, amount) VALUES (2, 140.05);
INSERT INTO payment_joined (id, amount) VALUES (3, 540.75);
INSERT INTO payment_joined (id, amount) VALUES (4, 140.75);

/* Credit Card Payment Joined */
INSERT INTO credit_card_payment_joined (id, card_number) VALUES (1, '12341234123412341234');
INSERT INTO credit_card_payment_joined (id, card_number) VALUES (2, '12341234123412341234');

/* Check Payment Joined */
INSERT INTO check_payment_joined (id, check_number) VALUES (3, '12345123451234512345');
INSERT INTO check_payment_joined (id, check_number) VALUES (4, '12345123451234512346');

/* employee_nested */
INSERT INTO employee_nested(name, street_address, city, state, zip_code, country) VALUES ('Balaji', '10, Main Street', 'Chennai', 'Tamil Nadu', '600119', 'India');
INSERT INTO employee_nested(name, street_address, city, state, zip_code, country) VALUES ('Arun', '100, Main Street', 'Chennai', 'Tamil Nadu', '600119', 'India');

/* library */
INSERT INTO library(name) VALUES ('ONE');
INSERT INTO library(name) VALUES ('TWO');

/* bank account */
INSERT INTO bank_account(account_id, name, balance) VALUES (100, 'jack', 10000);
INSERT INTO bank_account(account_id, name, balance) VALUES (101, 'tarzan', 10000);

