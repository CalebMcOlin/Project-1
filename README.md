# Project-1
Repo for Project 1 (Group 2  Bryton, Caleb, Yan, Ezekiel)

# Database Mock data 
```SQL
--DROP TABLE IF EXISTS loans, users, accounts;

--TRUNCATE TABLE users;
--TRUNCATE TABLE accounts;
--TRUNCATE TABLE loans;

INSERT INTO users (user_is_admin, user_password, user_username)
	VALUES 
	(FALSE, 'password', 'username1'),
	(FALSE, 'password', 'username2'),
	(FALSE, 'password', 'username3'),
	(FALSE, 'password', 'username4'),
	(TRUE, 'password', 'username5'),
	(TRUE, 'password', 'username6');

INSERT INTO accounts (account_balance, user_id)
	VALUES
	(666,1),
	(100000,2),
	(200000,2),
	(10,3),
	(20,3),
	(100,4),
	(200,4),
	(110,5),
	(120,5),
	(130,5);

INSERT INTO loans (loan_amount, loan_is_approved, account_id)
	VALUES
	(66,FALSE,1),
	(666,FALSE,1),
	(666,FALSE,1),
	(1000,FALSE,2),
	(10,TRUE,3),
	(700,TRUE,5);


SELECT * FROM users;
SELECT * FROM accounts;
SELECT * FROM loans;
```
