
-- Insert Categories (Must Be Done First)
-- INSERT INTO category (id, description, name)
-- VALUES (nextval('category_seq'), 'Electronics gadgets and devices', 'Electronics');
--
-- INSERT INTO category (id, description, name)
-- VALUES (nextval('category_seq'), 'Furniture for home and office', 'Furniture');
--
-- INSERT INTO category (id, description, name)
-- VALUES (nextval('category_seq'), 'Fashion and apparel items', 'Fashion');
--

-- Insert Products
INSERT INTO products (id, description, name, available_quantity, price, category_id)
VALUES (nextval('products_seq'), 'Smartphone with 128GB storage', 'Smartphone', 50, 699.99, (SELECT id FROM category WHERE name = 'Electronics'));

INSERT INTO products (id, description, name, available_quantity, price, category_id)
VALUES (nextval('products_seq'), 'Noise-cancelling over-ear headphones', 'Headphones', 30, 199.99, (SELECT id FROM category WHERE name = 'Electronics'));

INSERT INTO products (id, description, name, available_quantity, price, category_id)
VALUES (nextval('products_seq'), 'Ergonomic office chair', 'Office Chair', 20, 149.99, (SELECT id FROM category WHERE name = 'Furniture'));

INSERT INTO products (id, description, name, available_quantity, price, category_id)
VALUES (nextval('products_seq'), 'Wooden dining table for six', 'Dining Table', 10, 299.99, (SELECT id FROM category WHERE name = 'Furniture'));

INSERT INTO products (id, description, name, available_quantity, price, category_id)
VALUES (nextval('products_seq'), 'Men''s slim-fit jeans', 'Jeans', 100, 49.99, (SELECT id FROM category WHERE name = 'Fashion'));

INSERT INTO products (id, description, name, available_quantity, price, category_id)
VALUES (nextval('products_seq'), 'Women''s cotton summer dress', 'Dress', 80, 59.99, (SELECT id FROM category WHERE name = 'Fashion'));
