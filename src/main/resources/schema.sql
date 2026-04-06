CREATE TABLE IF NOT EXISTS transaction (
	id int primary key AUTO_INCREMENT,
	valor DECIMAL(19, 4) NOT NULL, 
	data_hora DATETIME NOT NULL
);