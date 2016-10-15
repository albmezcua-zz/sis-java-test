CREATE TABLE players (
	id INTEGER IDENTITY NOT NULL,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE teams (
	id INTEGER IDENTITY NOT NULL,
	name VARCHAR(50) NOT NULL,
	city VARCHAR(50) NOT NULL,
	owner VARCHAR(50) NOT NULL,
	competition VARCHAR(50) NOT NULL,
	date_creation TIMESTAMP NOT NULL
);
CREATE TABLE team_players (
	team_id INTEGER NOT NULL,
	player_id INTEGER NOT NULL,
	PRIMARY KEY (team_id,player_id)
);

CREATE TABLE user (
	id INTEGER IDENTITY NOT NULL,
	username VARCHAR(50) NOT NULL,
	user_uuid VARCHAR(36) NOT NULL,
	secret VARCHAR(36) NOT NULL,
	first_name VARCHAR(32),
	last_name VARCHAR(32)
);

ALTER TABLE team_players
	ADD FOREIGN KEY (team_id)
	REFERENCES teams (id);

ALTER TABLE team_players
	ADD FOREIGN KEY (player_id)
	REFERENCES players (id);

INSERT INTO players(id, name) VALUES (1, 'Ronaldo');
INSERT INTO players(id, name) VALUES (2, 'Bale');
INSERT INTO players(id, name) VALUES (3, 'Ramos');
INSERT INTO players(id, name) VALUES (4, 'Benzema');
INSERT INTO players(id, name) VALUES (5, 'Kross');
INSERT INTO players(id, name) VALUES (6, 'De Gea');
INSERT INTO players(id, name) VALUES (7, 'Pogba');
INSERT INTO players(id, name) VALUES (8, 'Rooney');
INSERT INTO players(id, name) VALUES (9, 'Ibrahimovic');
INSERT INTO players(id, name) VALUES (10, 'Mata');
INSERT INTO teams(id, name, city, owner, competition, date_creation) VALUES (1, 'Real Madrid', 'Madrid','Florentino','La Liga', '2016-10-14 12:55:48');
INSERT INTO teams(id, name, city, owner, competition, date_creation) VALUES (2, 'Manchester United','Manchester','Glazers','Premier League', '2016-10-14 12:55:48');
INSERT INTO team_players(team_id, player_id) VALUES (1, 1);
INSERT INTO team_players(team_id, player_id) VALUES (1, 2);
INSERT INTO team_players(team_id, player_id) VALUES (1, 3);
INSERT INTO team_players(team_id, player_id) VALUES (1, 4);
INSERT INTO team_players(team_id, player_id) VALUES (1, 5);
INSERT INTO team_players(team_id, player_id) VALUES (2, 6);
INSERT INTO team_players(team_id, player_id) VALUES (2, 7);
INSERT INTO team_players(team_id, player_id) VALUES (2, 8);
INSERT INTO team_players(team_id, player_id) VALUES (2, 9);
INSERT INTO team_players(team_id, player_id) VALUES (2, 10);
INSERT INTO user(id, username, user_uuid, secret, first_name, last_name) VALUES (1, 'alberto', '1234-1234-1234-1234','secret-alberto', 'Alberto', 'Mezcua');
INSERT INTO user(id, username, user_uuid, secret, first_name, last_name) VALUES (2, 'sis', '1234-1234-1234-1234', 'secret-sis','SIS', 'Testing');

