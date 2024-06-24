CREATE TABLE upgrades (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255),
                           upgrade_multiplier Decimal(5,2),
                           number_of_tiers INT,
                           base_cost INT,
                           increase_per_tier Decimal(5,2),
                           image_url VARCHAR(255)
);
CREATE TABLE user_upgrades (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               user_id INT,
                               upgrade_id BIGINT,
                               tier int,
                               FOREIGN KEY (user_id) REFERENCES user(id),
                               FOREIGN KEY (upgrade_id) REFERENCES upgrades(id)
);
CREATE TABLE user_characters (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               user_id INT,
                               character_id BIGINT,
                               FOREIGN KEY (user_id) REFERENCES user(id),
                               FOREIGN KEY (character_id) REFERENCES characters(id)
);