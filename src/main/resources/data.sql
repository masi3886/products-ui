CREATE TABLE products (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price NUMERIC(20, 2),
    quantity NUMERIC(5, 2),
    description VARCHAR(255)
);

INSERT INTO products (id, name, price, quantity, description) VALUES ('5d80f71a-383d-4486-90f8-0c447ef20fd7', 'USA', 10.5, 1.2, 'D1');
INSERT INTO products (id, name, price, quantity, description) VALUES ('0598c331-667e-42e6-ba3b-42a3ef1cc838', 'France', 12.3, 1.3, 'D2');
INSERT INTO products (id, name, price, quantity, description) VALUES ('67ebf82a-e8f8-4db9-8dec-228ece530154', 'Brazil', 9.07, 11, 'D3');
INSERT INTO products (id, name, price, quantity, description) VALUES ('09d6ec35-4bf1-45f2-89e6-7776895a86ee', 'Italy', 3.99, 5.8, 'D4');
INSERT INTO products (id, name, price, quantity, description) VALUES ('8ecccf13-89c6-41a4-9bc3-943993683cd4', 'Canada', 59.7, 3.0, 'D5');
