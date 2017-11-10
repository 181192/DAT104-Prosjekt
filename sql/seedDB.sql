/* 
REVISION VERSIONS
rev.2.3.0
	- Changed all entities to start with lower case char
rev.2.2.0
	- Added new entity: "Live_tilbakemelding"
	- Added tid to Tilbakemelding and Live_tilbakemelding
	- Added sted and beskrivelse to Event

rev.2.1.0
	- Totally rewamped the design
*/

-- object: db | type: SCHEMA --
DROP SCHEMA IF EXISTS db CASCADE;
CREATE SCHEMA db;
-- ddl-end --

SET search_path TO db;
-- ddl-end --

-- object: db.bruker | type: TABLE --
DROP TABLE IF EXISTS db.bruker CASCADE;
CREATE TABLE db.bruker(
	id serial NOT NULL,
	mail varchar(100) NOT NULL,
	fornavn varchar(50) NOT NULL,
	etternavn varchar(70) NOT NULL,
	passord varchar NOT NULL,
	salt varchar NOT NULL,
	id_rolle integer NOT NULL,
	CONSTRAINT bruker_pk PRIMARY KEY (id)

);

-- object: db.rolle | type: TABLE --
DROP TABLE IF EXISTS db.rolle CASCADE;
CREATE TABLE db.rolle(
	id serial NOT NULL,
	type text NOT NULL,
	CONSTRAINT rolle_pk PRIMARY KEY (id)

);

-- object: db.rettigheter | type: TABLE --
DROP TABLE IF EXISTS db.rettigheter CASCADE;
CREATE TABLE db.rettigheter(
	id serial NOT NULL,
	godkjenne_bruker bool NOT NULL,
	slette_bruker bool NOT NULL,
	opprette_aktivitet bool NOT NULL,
	id_rolle integer NOT NULL,
	CONSTRAINT rettigheter_pk PRIMARY KEY (id)

);

-- object: db.aktivitet | type: TABLE --
DROP TABLE IF EXISTS db.aktivitet CASCADE;
CREATE TABLE db.aktivitet(
	id serial NOT NULL,
	navn text NOT NULL,
	status varchar NOT NULL,
	id_bruker integer NOT NULL,
	CONSTRAINT aktivitet_pk PRIMARY KEY (id)

);

-- object: db.event | type: TABLE --
DROP TABLE IF EXISTS db.event CASCADE;
CREATE TABLE db.event(
	id serial NOT NULL,
	navn varchar NOT NULL,
	beskrivelse text,
	tid_fra timestamp NOT NULL,
	tid_til timestamp NOT NULL,
	faktisk_start timestamp,
	faktisk_slutt timestamp,
	status varchar NOT NULL,
	sted varchar,
	id_aktivitet integer NOT NULL,
	CONSTRAINT event_pk PRIMARY KEY (id)

);

-- object: db.kodeord | type: TABLE --
DROP TABLE IF EXISTS db.kodeord CASCADE;
CREATE TABLE db.kodeord(
	id serial NOT NULL,
	kode integer NOT NULL,
	id_event integer NOT NULL,
	CONSTRAINT kodeord_pk PRIMARY KEY (id),
	CONSTRAINT kode_uq UNIQUE (kode)

);

-- object: db.tilbakemelding | type: TABLE --
DROP TABLE IF EXISTS db.tilbakemelding CASCADE;
CREATE TABLE db.tilbakemelding(
	id serial NOT NULL,
	stemme varchar NOT NULL,
	tid timestamp NOT NULL,
	id_event integer NOT NULL,
	CONSTRAINT tilbakemelding_pk PRIMARY KEY (id)

);

-- object: db.livetm | type: TABLE --
DROP TABLE IF EXISTS db.livetm CASCADE;
CREATE TABLE db.livetm(
	id serial NOT NULL,
	stemme varchar NOT NULL,
	tid timestamp NOT NULL,
	id_event integer NOT NULL,
	CONSTRAINT livetm_pk PRIMARY KEY (id)

);

-- object: rolle_fk | type: CONSTRAINT --
-- ALTER TABLE db.rettigheter DROP CONSTRAINT IF EXISTS rolle_fk CASCADE;
ALTER TABLE db.rettigheter ADD CONSTRAINT rolle_fk FOREIGN KEY (id_rolle)
REFERENCES db.rolle (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: rolle_fk | type: CONSTRAINT --
-- ALTER TABLE db.bruker DROP CONSTRAINT IF EXISTS rolle_fk CASCADE;
ALTER TABLE db.bruker ADD CONSTRAINT rolle_fk FOREIGN KEY (id_rolle)
REFERENCES db.rolle (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: bruker_fk | type: CONSTRAINT --
-- ALTER TABLE db.aktivitet DROP CONSTRAINT IF EXISTS bruker_fk CASCADE;
ALTER TABLE db.aktivitet ADD CONSTRAINT bruker_fk FOREIGN KEY (id_bruker)
REFERENCES db.bruker (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: aktivitet_fk | type: CONSTRAINT --
-- ALTER TABLE db.event DROP CONSTRAINT IF EXISTS aktivitet_fk CASCADE;
ALTER TABLE db.event ADD CONSTRAINT aktivitet_fk FOREIGN KEY (id_aktivitet)
REFERENCES db.aktivitet (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: event_fk | type: CONSTRAINT --
-- ALTER TABLE db.kodeord DROP CONSTRAINT IF EXISTS event_fk CASCADE;
ALTER TABLE db.kodeord ADD CONSTRAINT event_fk FOREIGN KEY (id_event)
REFERENCES db.event (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: kodeord_uq | type: CONSTRAINT --
-- ALTER TABLE db.kodeord DROP CONSTRAINT IF EXISTS kodeord_uq CASCADE;
ALTER TABLE db.kodeord ADD CONSTRAINT kodeord_uq UNIQUE (id_event);
-- ddl-end --

-- object: event_fk | type: CONSTRAINT --
-- ALTER TABLE db.tilbakemelding DROP CONSTRAINT IF EXISTS event_fk CASCADE;
ALTER TABLE db.tilbakemelding ADD CONSTRAINT event_fk FOREIGN KEY (id_event)
REFERENCES db.event (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: event_fk | type: CONSTRAINT --
-- ALTER TABLE db.livetm DROP CONSTRAINT IF EXISTS event_fk CASCADE;
ALTER TABLE db.livetm ADD CONSTRAINT event_fk FOREIGN KEY (id_event)
REFERENCES db.event (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- Dummy data

INSERT INTO rolle (id, type) VALUES('1', 'Admin');
INSERT INTO rolle (id, type) VALUES('2', 'Aktivitetsstyrer');
INSERT INTO rolle (id, type) VALUES('3', 'Ikke godkjent');

INSERT INTO rettigheter (godkjenne_bruker, slette_bruker, opprette_aktivitet, "id_rolle") VALUES ('1','1','0','1');
INSERT INTO rettigheter (godkjenne_bruker, slette_bruker, opprette_aktivitet, "id_rolle") VALUES ('0','0','1','2');
INSERT INTO rettigheter (godkjenne_bruker, slette_bruker, opprette_aktivitet, "id_rolle") VALUES ('0','0','0','3');