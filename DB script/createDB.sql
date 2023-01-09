DROP DATABASE IF EXISTS dressApp;
CREATE DATABASE dressApp;
USE dressApp;

CREATE TABLE User(
		id int NOT NULL AUTO_INCREMENT,
        email varchar(50),
        password varchar(50),
        nome varchar(50),
        cognome varchar(50),
        is_admin int default 0,
        is_guest int default 0,
        PRIMARY KEY(id));

CREATE TABLE Cappello(
	id  int NOT NULL AUTO_INCREMENT,
    nome varchar(50),
    descrizione varchar(150),
    prezzo float,
    categoria varchar(50),
    foto varchar(40),
    disponibilita int,
    data_ultima_modifica date,
    PRIMARY KEY(id));
    
CREATE TABLE Card(
	id int NOT NULL AUTO_INCREMENT,
	proprietario VARCHAR(50),
	numero_carta VARCHAR(16),
	data_scadenza varchar(8),
	cvv INT,
	user INT,
	PRIMARY KEY(id),
    FOREIGN KEY(user) REFERENCES User(id) on delete cascade on update cascade
    );  
    
CREATE TABLE Ordine(
	id int NOT NULL AUTO_INCREMENT,
	data DATE,
	user INT,
	is_buy BOOLEAN DEFAULT 0,
	PRIMARY KEY(id),
    FOREIGN KEY(user) REFERENCES User(id) on delete cascade on update cascade
    );

CREATE TABLE DettaglioOrdine(
	cappello INT,
	ordine INT,
	quantita INT,
	FOREIGN KEY(cappello) REFERENCES Cappello(id) on delete cascade on update cascade,
	FOREIGN KEY(ordine) REFERENCES Ordine(id) on delete cascade on update cascade
    );