drop table account;

CREATE TABLE ACCOUNT (
  account_number INT PRIMARY KEY,
  pin INT,
  opening_balance INT,
  overdraft INT);

insert into account (account_number, pin, opening_balance, overdraft) VALUES
(123456789, 1234, 800, 300),
(987654321, 4321, 1230, 150);
