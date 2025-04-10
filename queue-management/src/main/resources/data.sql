CREATE TABLE queues (
    name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    queue_name VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    FOREIGN KEY (queue_name) REFERENCES queues(name)
);

INSERT INTO queues (name) VALUES ('Queue1');
INSERT INTO queues (name) VALUES ('Queue2');
INSERT INTO queues (name) VALUES ('Queue3');

INSERT INTO messages (queue_name, content) VALUES ('Queue1', '{"text": "Message 1 for Queue1"}');
INSERT INTO messages (queue_name, content) VALUES ('Queue1', '{"text": "Message 2 for Queue1"}');
INSERT INTO messages (queue_name, content) VALUES ('Queue2', '{"text": "Message 1 for Queue2"}');
INSERT INTO messages (queue_name, content) VALUES ('Queue3', '{"text": "Message 1 for Queue3"}');