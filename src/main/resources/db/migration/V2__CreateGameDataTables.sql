CREATE TABLE characters (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255),
                            walk_speed INT,
                            health INT,
                            damage_multiplier DOUBLE,
                            cooldown_reduction DOUBLE,
                            image_url VARCHAR(255),
                            atlas_json_url VARCHAR(255)
);

CREATE TABLE abilities (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255),
                           image_url VARCHAR(255),
                           atlas_json_url VARCHAR(255),
                           character_id BIGINT,
                           damage INT,
                           walk_speed INT,
                           cooldown DOUBLE,
                           projectile_count INT,
                           multi_hit BOOLEAN,
                           multi_hit_count INT,
                           FOREIGN KEY (character_id) REFERENCES characters(id)
);
CREATE TABLE character_abilities (
                                     character_id BIGINT,
                                     ability_id BIGINT,
                                     FOREIGN KEY (character_id) REFERENCES characters(id),
                                     FOREIGN KEY (ability_id) REFERENCES abilities(id),
                                     PRIMARY KEY (character_id, ability_id)
);

CREATE TABLE enemies (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255),
                         image_url VARCHAR(255),
                         atlas_json_url VARCHAR(255)
);

CREATE TABLE game_ui (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255),
                         image_url VARCHAR(255)
);
