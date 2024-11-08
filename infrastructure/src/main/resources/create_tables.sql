CREATE TABLE client (
											client_id SERIAL PRIMARY KEY,
											username VARCHAR(100) NOT NULL,
											email VARCHAR(150) UNIQUE NOT NULL,
											password VARCHAR(255) NOT NULL,
											created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE role (
										role_id SERIAL PRIMARY KEY,
										name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE client_role (
													 client_id INT REFERENCES client(client_id) ON DELETE CASCADE,
													 role_id INT REFERENCES role(role_id) ON DELETE CASCADE,
													 PRIMARY KEY (client_id, role_id)
);

CREATE TABLE contract (
												contract_id SERIAL PRIMARY KEY,
												investor_id INT REFERENCES client(client_id) ON DELETE SET NULL,
												date DATE NOT NULL, -- la fecha ingresada puede ser de dias anteriores
												company VARCHAR(100) NOT NULL,
												stock_value DECIMAL(10, 2),
												quantity INT NOT NULL,
												status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
												created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
												updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE contract_acceptance (
																	 contract_acceptance_id SERIAL PRIMARY KEY,
																	 contract_id INT REFERENCES contractEntity(contract_id) ON DELETE CASCADE,
																	 operator_id INT REFERENCES client(client_id) ON DELETE SET NULL,
																	 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- referencia a cuando la acepto :v
);