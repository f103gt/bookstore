CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE book (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    isbn VARCHAR(255),
    quantity INTEGER
);
