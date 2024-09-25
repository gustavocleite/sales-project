CREATE TABLE clients
(
    id            BIGSERIAL PRIMARY KEY,
    cpf           VARCHAR(14)  NOT NULL,
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE,
    date_of_birth DATE,
    is_deleted    BOOLEAN DEFAULT FALSE
);
