CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL ,
                       login TEXT NOT NULL  UNIQUE ,
                       password TEXT NOT NULL,
                       role TEXT NOT NULL
)