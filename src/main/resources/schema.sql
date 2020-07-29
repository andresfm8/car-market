CREATE TABLE manufacturer (
	manufacturerID int NOT NULL AUTO_INCREMENT,
	manufacturerName varchar(30) NOT NULL,
	PRIMARY KEY (manufacturerID)
);

CREATE TABLE car (
	carID int NOT NULL AUTO_INCREMENT,
	manufacturerID int NOT NULL,
	carModel VARCHAR(30) NOT NULL,
	year int NOT NULL,
	color VARCHAR(30) NOT NULL,
	price decimal(7,2) NOT NULL,
	PRIMARY KEY (carID),
	FOREIGN KEY (manufacturerID) REFERENCES manufacturer(manufacturerID)
);