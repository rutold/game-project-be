CREATE TABLE Exp (
                     id INT PRIMARY KEY,
                     experience_value INT NOT NULL
);

INSERT INTO Exp (id, experience_value) VALUES
                                           (1, 10),
                                           (2, 25),
                                           (3, 50),
                                           (4, 100);

ALTER TABLE enemies
    ADD COLUMN exp_id INT;

ALTER TABLE enemies
    ADD CONSTRAINT fk_enemies_exp FOREIGN KEY (exp_id) REFERENCES Exp(id);