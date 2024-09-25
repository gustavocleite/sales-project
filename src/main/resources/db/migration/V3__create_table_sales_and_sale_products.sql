CREATE TABLE sales (
                      id SERIAL PRIMARY KEY,
                      client_id BIGINT NOT NULL,
                      is_deleted BOOLEAN DEFAULT FALSE,
                      CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE sale_products (
                              sale_id BIGINT NOT NULL,
                              product_id BIGINT NOT NULL,
                              PRIMARY KEY (sale_id, product_id),
                              CONSTRAINT fk_sale FOREIGN KEY (sale_id) REFERENCES sales(id),
                              CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id)
);
