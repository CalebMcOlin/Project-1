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
    user_id TEXT REFERENCES users(user_id)
);


INSERT INTO users(username, first_name, last_name, user_password, user_admin) VALUES ('jdoe', 'John', 'Doe', 'password', FALSE);
INSERT INTO users(username, first_name, last_name, user_password, user_admin) VALUES ('theSponge', 'Spongebob', 'Squarepants', 'word', FALSE);
INSERT INTO users(username, first_name, last_name, user_password, user_admin) VALUES ('clarinetman', 'Squidward', 'Tentacles', 'pass', FALSE);
INSERT INTO users(username, first_name, last_name, user_password, user_admin) VALUES ('funnymoneyman', 'Eugene', 'Krabs', 'Formula', TRUE);
INSERT INTO users(username, first_name, last_name, user_password, user_admin) VALUES ('betat', 'Bryton', 'Tateishi', 'Password', TRUE);

INSERT INTO accounts(account_type, account_balance, user_username) VALUES ('Checking', 100.20, 'jdoe');
INSERT INTO accounts(account_type, account_balance, user_username) VALUES ('Savings', 50.20, 'jdoe');
INSERT INTO accounts(account_type, account_balance, user_username) VALUES ('Checking', 10.20,'theSponge');
INSERT INTO accounts(account_type, account_balance, user_username) VALUES ('Retirement', 100, 'theSponge');
INSERT INTO accounts(account_type, account_balance, user_username) VALUES ('Checking', 250.20, 'clarinetman');
INSERT INTO accounts(account_type, account_balance, user_username) VALUES ('HSA', 300.60, 'funnymoneyman');
INSERT INTO accounts(account_type, account_balance, user_username) VALUES ('Savings', 1000.87, 'funnymoneyman');
INSERT INTO accounts(account_type, account_balance, user_username) VALUES ('Checking', 100, 'betat');


SELECT * FROM users, accounts;