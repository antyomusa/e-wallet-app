CREATE TABLE balance (
     id int NOT NULL AUTO_INCREMENT,
     user_id int NOT NULL,
     balance int NOT NULL DEFAULT '0',
     PRIMARY KEY (id),
     FOREIGN KEY (user_id) REFERENCES user(id)
);
CREATE TABLE transaction (
     id int NOT NULL AUTO_INCREMENT,
     sender_id int NOT NULL,
     receiver_id int NOT NULL,
     amount int NOT NULL DEFAULT '0',
     transaction_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (id),
     FOREIGN KEY (sender_id) REFERENCES user(id),
     FOREIGN KEY (receiver_id) REFERENCES user(id)
)