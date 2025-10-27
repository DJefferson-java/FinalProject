DROP TABLE IF EXISTS skater_events;
DROP TABLE IF EXISTS skater;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS location;

CREATE TABLE location (
location_id int NOT NULL AUTO_INCREMENT,
location_name varchar(256) NOT NULL,
address varchar(128),
city varchar (60),
state varchar (20),
zip varchar(20),
PRIMARY KEY (location_id)	
);

CREATE TABLE event (
event_id int NOT NULL AUTO_INCREMENT,
location_id int NOT NULL,
event_name varchar(256),
event_date varchar(128),
duration varchar (30),
PRIMARY KEY (event_id),
FOREIGN KEY (location_id ) REFERENCES location (location_id) ON DELETE CASCADE
);

CREATE TABLE skater (
skater_id int NOT NULL AUTO_INCREMENT,
skater_name varchar(128),
skater_age int,
PRIMARY KEY (skater_id)
);

CREATE TABLE skater_events (
skater_id int NOT NULL,
event_id int NOT NULL,
FOREIGN KEY (skater_id) REFERENCES skater (skater_id) ON DELETE CASCADE,
FOREIGN KEY (event_id) REFERENCES event (event_id) ON DELETE CASCADE
);