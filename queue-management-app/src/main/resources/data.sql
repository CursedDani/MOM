CREATE TABLE queues (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE messages (
    id INT PRIMARY KEY,
    queue_id INT NOT NULL,
    content VARCHAR(255) NOT NULL,
    FOREIGN KEY (queue_id) REFERENCES queues(id)
);

INSERT INTO queues (id, name) VALUES (1, 'Queue1');
INSERT INTO queues (id, name) VALUES (2, 'Queue2');
INSERT INTO queues (id, name) VALUES (3, 'Queue3');

INSERT INTO messages (id, queue_id, content) VALUES (1, 1, '{"text": "Message 1 for Queue1"}');
INSERT INTO messages (id, queue_id, content) VALUES (2, 1, '{"text": "Message 2 for Queue1"}');
INSERT INTO messages (id, queue_id, content) VALUES (3, 2, '{"text": "Message 1 for Queue2"}');
INSERT INTO messages (id, queue_id, content) VALUES (4, 3, '{"text": "Message 1 for Queue3"}');