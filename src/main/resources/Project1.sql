DROP TABLE IF EXISTS transactions, accounts, users;

CREATE TABLE users(
    user_id serial PRIMARY KEY,
	username TEXT UNIQUE,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL,
	user_password TEXT NOT NULL,
	user_admin BOOL NOT NULL
);

CREATE TABLE accounts(
    account_id serial PRIMARY KEY,
    account_type TEXT NOT NULL,
    account_balance DECIMAL NOT NULL,
    user_id int REFERENCES users(user_id)
);

CREATE TABLE loan_apps(
    loan_id serial PRIMARY KEY,
    loan_amount DECIMAL NOT NULL,
    approval BOOL NOT NULL,
    account_id int REFERENCES account(account_id)
)

SELECT * FROM users, accounts;