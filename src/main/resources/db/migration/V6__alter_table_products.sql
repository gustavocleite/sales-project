ALTER TABLE products
    ADD COLUMN barcode VARCHAR(13) UNIQUE;
ALTER TABLE products
    ADD COLUMN manufacturing date;