ALTER TABLE clients
    ADD COLUMN address_id BIGINT;
ALTER TABLE clients
    ADD CONSTRAINT fk_address
        FOREIGN KEY (address_id) REFERENCES address(id);
