CREATE TABLE games (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255),
                          difficulty VARCHAR(255),
                        UNIQUE(name)

);
CREATE TABLE game_scores (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               game_id BIGINT,
                               user_id INT,
                               score INT,
                               FOREIGN KEY (user_id) REFERENCES user(id),
                               FOREIGN KEY (game_id) REFERENCES games(id)
);
