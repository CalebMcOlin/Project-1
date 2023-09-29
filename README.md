# Project 1 (Banking Application)

## Description:

The Banking Application will function as an emulation of a real-world banking system. Core features are available for
end users, such as creating accounts, making deposits and withdrawals from a user’s accounts, and applying for loans.

Managers have access to all of the above functionalities, as well as the ability to approve loans and view all accounts
in the Banking Application.

Trello Board can be found [here](https://trello.com/b/U5FNzX2p/project-1-group-2-bryton-caleb-yan).


### Database Mockdata:

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

INSERT INTO accounts (account_balance, account_interest_rate, user_id)
	VALUES
	(666.00, 0.045, 1),
	(100000.53, 0.101, 2),
	(200000.10, 0.042, 2),
	(10.00, 0.050, 3),
	(20.01, 0.045, 3),
	(100.30, 0.024, 4),
	(200.24, 0.036, 4),
	(110.44, 0.047, 5),
	(120.99, 0.041, 5),
	(130.09, 0.000, 5);

INSERT INTO loans (loan_amount, loan_is_approved, account_id)
	VALUES
	(66, FALSE, 1),
	(666, FALSE, 1),
	(666, FALSE, 1),
	(1000, FALSE, 2),
	(10, TRUE, 3),
	(700, TRUE, 5);

--SELECT * FROM users;
--SELECT * FROM accounts;
--SELECT * FROM loans;
```

### Members: Group 2
- Bryton, Caleb, Yan, and Ezekiel