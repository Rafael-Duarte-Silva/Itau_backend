CREATE TABLE IF NOT EXISTS transaction (
	id int primary key AUTO_INCREMENT,
	valor DECIMAL NOT NULL, 
	dataHora DATETIME NOT NULL
);