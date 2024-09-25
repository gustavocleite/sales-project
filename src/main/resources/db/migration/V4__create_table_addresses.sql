CREATE TABLE address (
                         id BIGSERIAL PRIMARY KEY,
                         street VARCHAR(255) NOT NULL,
                         number VARCHAR(10) NOT NULL,
                         complement VARCHAR(255),
                         neighborhood VARCHAR(255) NOT NULL,
                         city VARCHAR(255) NOT NULL,
                         state VARCHAR(255) NOT NULL,
                         country VARCHAR(255) NOT NULL,
                         postal_code VARCHAR(20) NOT NULL
);
