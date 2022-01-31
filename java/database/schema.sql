BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE cards (
	card_id serial NOT NULL,
	front varchar(200) NOT NULL,
	back varchar(500) NOT NULL,
	user_id int NOT NULL,
	CONSTRAINT PK_cards PRIMARY KEY (card_id),
	CONSTRAINT FK_cards_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE decks (
	deck_id serial NOT NULL,
	deck_name varchar(100) NOT NULL,
	user_id int NOT NULL,
	isPublic boolean NOT NULL DEFAULT FALSE,
	CONSTRAINT PK_decks PRIMARY KEY (deck_id),
	CONSTRAINT FK_decks_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE card_deck (
	card_id int NOT NULL,
	deck_id int NOT NULL,
	CONSTRAINT PK_card_deck PRIMARY KEY(card_id, deck_id),
	CONSTRAINT FK_card_deck_card FOREIGN KEY (card_id) REFERENCES cards(card_id),
	CONSTRAINT FK_card_deck_deck FOREIGN KEY (deck_id) REFERENCES decks(deck_id)
);

CREATE TABLE tags (
	tag_id serial NOT NULL,
	name varchar(100) NOT NULL,
	CONSTRAINT PK_tags PRIMARY KEY (tag_id)
);

CREATE TABLE card_tag (
	card_id int NOT NULL,
	tag_id int NOT NULL,
	CONSTRAINT PK_card_tag PRIMARY KEY(card_id, tag_id),
	CONSTRAINT FK_card_tag_card PRIMARY KEY (card_id) REFERENCES cards(card_id),
	CONSTRAINT FK_card_tag_tag PRIMARY KEY (tag_id) REFERENCES tags(tag_id)
);

COMMIT TRANSACTION;
