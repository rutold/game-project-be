ALTER TABLE enemies
DROP FOREIGN KEY fk_enemies_exp;

ALTER TABLE enemies
    DROP COLUMN exp_id;


Drop table Exp;

ALTER TABLE enemies
    ADD COLUMN exp INT;