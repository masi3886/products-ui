CREATE TABLE suppliers (
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    company_code VARCHAR(20),
    vat_code VARCHAR(20)
);

INSERT INTO suppliers (id, name) VALUES ('ec6333bf-e94a-4ef6-8474-275363949699', 'Sup 1');
INSERT INTO suppliers (id, name) VALUES ('1d511e8b-e831-4fa8-82c9-23cc6289e570', 'Sup 2');
INSERT INTO suppliers (id, name) VALUES ('18af4a27-0e04-41d1-bbac-acf31d6da82b', 'Sup 3');
INSERT INTO suppliers (id, name) VALUES ('4fe4a5a6-6e99-40bc-a6f2-707f0f53a54c', 'Sup 4');


CREATE TABLE products (
    id UUID PRIMARY KEY,
    external_id VARCHAR(50),
    name VARCHAR(50) NOT NULL,
    price NUMERIC(20, 2),
    quantity NUMERIC(5, 2),
    description VARCHAR(255),
    supplier_id UUID,
    image_name VARCHAR(50),
    image_content_type VARCHAR(20),
    image_file_contents BLOB(10M),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
);

INSERT INTO products (id, name, price, quantity, description)
VALUES ('5d80f71a-383d-4486-90f8-0c447ef20fd7', 'Mane in USA', 10.5, 1.2, 'D1');

INSERT INTO products (id, name, price, quantity, description)
VALUES ('0598c331-667e-42e6-ba3b-42a3ef1cc838', 'France champagne and perfumes', 12.3, 1.3, 'D2');

INSERT INTO products (id, name, price, quantity, description)
VALUES ('67ebf82a-e8f8-4db9-8dec-228ece530154', 'Brazil ', 9.07, 11, 'D3');

INSERT INTO products (id, name, price, quantity, description)
VALUES ('09d6ec35-4bf1-45f2-89e6-7776895a86ee', 'Pizza Italiana', 3.99, 5.8, 'D4');

INSERT INTO products (id, name, price, quantity, description)
VALUES ('8ecccf13-89c6-41a4-9bc3-943993683cd4', 'Canadian maple syrup', 59.7, 3.0, 'D5');

CREATE TABLE users (
    id INT PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(15) NOT NULL,
    status VARCHAR(10) NOT NULL,
    created_date DATE,
    last_login_ts TIMESTAMP
);

INSERT INTO users VALUES (1, 'admin', '123', 'ADMIN', 'ACTIVE', '2022-05-30', LOCALTIMESTAMP());
INSERT INTO users VALUES (2, 'a1', 'a', 'USER', 'ACTIVE', '2022-06-09', NULL);
INSERT INTO users VALUES (3, 'b2', 'b', 'USER', 'ACTIVE', '2022-06-09', NULL);