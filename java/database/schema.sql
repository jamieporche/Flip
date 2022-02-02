BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS decks;
DROP TABLE IF EXISTS card_deck;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS card_tag;
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
	card_tags varchar (100) NULL,
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




INSERT INTO cards (front , back, user_id, card_tags) VALUES ('How long do Alligators live? ' , ' Alligators generally live between 30 & 50 years.', 1, 'Animal Facts');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('What is the scientific name of a grey wolf? ' , ' Canis lupus lupus is the scientific name for a grey wolf.', 1, 'Animal Facts');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('How many muscles do cats have in each ear? ' , ' A cat has 32 muscles in each ear.', 1, 'Animal Facts');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('How many kills a year does a lion usually make? ' , ' A lion in the wild usually makes no more than twenty kills a year.', 1, 'Animal Facts');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('What is the most poisonous fish? ' , ' The most poisonous fish in the world is the stone fish.', 1, 'Animal Facts');

-- educated guess that the card ids will be 1 - 5. using this guess to make tags and deck



INSERT INTO decks (deck_name, user_id) VALUES ('Animal Deck' , 1);

INSERT INTO card_deck (card_id, deck_id) VALUES (1,1);
INSERT INTO card_deck (card_id, deck_id) VALUES (2,1);
INSERT INTO card_deck (card_id, deck_id) VALUES (3,1);
INSERT INTO card_deck (card_id, deck_id) VALUES (4,1);
INSERT INTO card_deck (card_id, deck_id) VALUES (5,1);

-- making a second deck of fake cards also from user id # 1

INSERT INTO cards (front , back, user_id, card_tags) VALUES ('Magnanimnous ' , ' generous in forgiving an insult or injury: free from petty resentfulness or vindictiveness', 1, 'useless vocab definitions');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('Jackdaw ' , ' a glossy, black, European bird of the crow family, that nests in towers, ruins, ect', 1, 'useless vocab definitions');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('Wayzgoose ' , ' a works outing made annually by a printing house', 1, 'useless vocab definitions');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('Daglock ' , ' a dangling, matted lock of wool on a sheep', 1, 'useless vocab definitions');
INSERT INTO cards (front , back, user_id, card_tags) VALUES ('Kalanchoe ' , ' any of several cheifly African and ASian succulent plants or shrubs belonging to the genus Kalanchoe', 1, 'useless vocab definitions');

-- educated guess that the card ids will be 6 -10. using this guess to make tags and deck



INSERT INTO decks (deck_name, user_id) VALUES ('Random Vocab Words' , 1);

INSERT INTO card_deck (card_id, deck_id) VALUES (6,2);
INSERT INTO card_deck (card_id, deck_id) VALUES (7,2);
INSERT INTO card_deck (card_id, deck_id) VALUES (8,2);
INSERT INTO card_deck (card_id, deck_id) VALUES (9,2);
INSERT INTO card_deck (card_id, deck_id) VALUES (10,2);



COMMIT TRANSACTION;
