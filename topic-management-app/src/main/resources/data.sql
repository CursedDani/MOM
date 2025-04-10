-- Create the Topic table
CREATE TABLE topic (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Insert initial data into the Topic table
INSERT INTO topic (id, name) VALUES (1, 'Topic 1');
INSERT INTO topic (id, name) VALUES (2, 'Topic 2');
INSERT INTO topic (id, name) VALUES (3, 'Topic 3');