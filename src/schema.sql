DROP ALL OBJECTS;

CREATE TABLE account(
	  number VARCHAR(50) PRIMARY KEY NOT NULL
	, owner VARCHAR(150)
	, balance INT
	, currency_id VARCHAR(20)
	, locked boolean
); 

CREATE TABLE currency(
	  id 	VARCHAR(20) PRIMARY KEY
	, name 	VARCHAR(150)
); 

CREATE TABLE exchange_rate(
	  id int AUTO_INCREMENT	PRIMARY KEY NOT NULL 
	, currency_from VARCHAR(20)
	, currency_to VARCHAR(20)
	, rate decimal 
); 

CREATE TABLE fee_strategy(
	  id VARCHAR(50) NOT NULL
	, currency_from VARCHAR(20) NOT NULL
	, currency_to VARCHAR(20) NOT NULL
	, fee_method VARCHAR(20)
	, fee decimal
	, active boolean
);

CREATE TABLE fee_strategy_types(
	  id VARCHAR(50) PRIMARY KEY
);

CREATE TABLE transaction_history(
	  id VARCHAR(50) PRIMARY KEY
	, creation_time timestamp 
	, account_from VARCHAR(50) 
	, account_to VARCHAR(50) 
	, amount decimal
	, fee_strategy_id VARCHAR(50)
	, fee_colected decimal 
);

--Foreign keys 
ALTER TABLE account ADD FOREIGN KEY (currency_id) REFERENCES currency(id);

ALTER TABLE exchange_rate ADD FOREIGN KEY (currency_from) REFERENCES currency(id);
ALTER TABLE exchange_rate ADD FOREIGN KEY (currency_to) REFERENCES currency(id);

ALTER TABLE fee_strategy ADD FOREIGN KEY (currency_from) REFERENCES currency(id);
ALTER TABLE fee_strategy ADD FOREIGN KEY (currency_to) REFERENCES currency(id);

ALTER TABLE fee_strategy ADD PRIMARY KEY (id, currency_from, currency_to);

ALTER TABLE transaction_history ADD FOREIGN KEY (account_from) REFERENCES account(number);
ALTER TABLE transaction_history ADD FOREIGN KEY (account_to) REFERENCES account(number);
ALTER TABLE transaction_history ADD FOREIGN KEY (fee_strategy_id) REFERENCES fee_strategy(id);




-----------------
--  Test data  --
-----------------
	
--Currency data
INSERT INTO currency(id, name) VALUES('GBP', 'Pound sterling');
INSERT INTO currency(id, name) VALUES('USD', 'United States dollar');

--	 Accounts 
INSERT INTO account(number, owner, balance, currency_id, locked) VALUES('990099A', 'Albert Einstein', 1000.34, 'GBP', false); 
INSERT INTO account(number, owner, balance, currency_id, locked) VALUES('AAAA', 'J. Robert Oppenheimer', 20000, 'USD', false); 


INSERT INTO account(number, owner, balance, currency_id, locked) VALUES('STANDARD_TRASFER_IN_GBP_FROM', 'Albert Einstein', 1000, 'GBP', false);
INSERT INTO account(number, owner, balance, currency_id, locked) VALUES('STANDARD_TRASFER_IN_GBP_TO', 'Albert Einstein', 0, 'GBP', false);

INSERT INTO account(number, owner, balance, currency_id, locked) VALUES('ZERO_BALANCE_IN_GBP_FROM', 'Albert Einstein', 1000, 'GBP', false);
INSERT INTO account(number, owner, balance, currency_id, locked) VALUES('ZERO_BALANCE_IN_GBP_TO', 'Albert Einstein', 0, 'GBP', false);


INSERT INTO account(number, owner, balance, currency_id, locked) VALUES('STANDARD_TRASFER_IN_GBP_TO_USD_FROM', 'Albert Einstein', 1000, 'GBP', false);
INSERT INTO account(number, owner, balance, currency_id, locked) VALUES('STANDARD_TRASFER_IN_GBP_TO_USD_TO', 'Albert Einstein', 0, 'USD', false);


--Exchange rates data
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('GBP', 'USD', 	1.32325); 
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('USD', 'GBP', 	0.78685); 
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('GBP', 'GBP', 	1); 
INSERT INTO exchange_rate(currency_from, currency_to, rate) VALUES ('USD', 'USD', 	1); 

--Fee strategy types
INSERT INTO fee_strategy_types(id) VALUES ('PERCENT');
INSERT INTO fee_strategy_types(id) VALUES ('FIX');

--Fee data
INSERT INTO fee_strategy(id, currency_from, currency_to, fee_method, fee, active) VALUES ('STANDARD_FEE', 'GBP', 'USD', 'PERCENT', '0.05', true);
INSERT INTO fee_strategy(id, currency_from, currency_to, fee_method, fee, active) VALUES ('STANDARD_FEE1', 'USD', 'GBP', 'FIX', '1', true);