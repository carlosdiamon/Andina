-- Table for Client
CREATE TABLE client (
											client_id SERIAL PRIMARY KEY,
											username VARCHAR(100) NOT NULL,
											email VARCHAR(150) UNIQUE NOT NULL,
											password VARCHAR(255) NOT NULL,
											created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for Investor
CREATE TABLE investor (
												investor_id SERIAL PRIMARY KEY,
												client_id INT UNIQUE REFERENCES client(client_id) ON DELETE CASCADE,
												balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
												registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for Currencies
CREATE TABLE currency (
												currency_id SERIAL PRIMARY KEY,
												code CHAR(3) UNIQUE NOT NULL,
												symbol VARCHAR(10) NOT NULL,
												name VARCHAR(50) NOT NULL
);

-- Table for Countries
CREATE TABLE country (
														 country_id SERIAL PRIMARY KEY,
														 name VARCHAR(100) NOT NULL,
														 economic_status TEXT
);

-- Table for Cities
CREATE TABLE city (
													city_id SERIAL PRIMARY KEY,
													name VARCHAR(100) NOT NULL,
													country_id INT REFERENCES country(country_id) ON DELETE CASCADE,
													economic_indicators TEXT
);

-- Table for Operators
CREATE TABLE operator (
															operator_id SERIAL PRIMARY KEY,
															name VARCHAR(100) NOT NULL,
															mic VARCHAR(50) UNIQUE NOT NULL,
															country_id INT REFERENCES country(country_id) ON DELETE SET NULL,
															city_id INT REFERENCES city(city_id) ON DELETE SET NULL,
															website VARCHAR(150),
															currency_code CHAR(3) REFERENCES currency(code),
															commission_rate DECIMAL(5, 2) NOT NULL DEFAULT 0.00
);

-- Table for Contracts
CREATE TABLE contract (
															contract_id SERIAL PRIMARY KEY,
															investor_id INT REFERENCES investor(investor_id) ON DELETE CASCADE,
															operator_id INT REFERENCES operator(operator_id) ON DELETE CASCADE,
															stock_name VARCHAR(150) NOT NULL,
															quantity INT NOT NULL,
															price DECIMAL(10, 2) NOT NULL,
															status VARCHAR(20) CHECK (status IN ('active', 'completed', 'cancelled')) NOT NULL DEFAULT 'active',
															creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
															acquisition_date TIMESTAMP  -- Nuevo campo opcional para la fecha de adquisición
);

-- Table for Transactions
CREATE TABLE transaction (
													 transaction_id SERIAL PRIMARY KEY,
													 contract_id INT REFERENCES contract(contract_id) ON DELETE CASCADE,
													 transaction_type VARCHAR(20) CHECK (transaction_type IN ('buy', 'sell')) NOT NULL,
													 amount DECIMAL(15, 2) NOT NULL,
													 transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE audit_log (
												 log_id SERIAL PRIMARY KEY,
												 event VARCHAR(10) CHECK (event IN ('INSERT', 'UPDATE', 'DELETE')) NOT NULL,												 log_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
												 username VARCHAR(100) NOT NULL,
												 table_name VARCHAR(255) NOT NULL
);

