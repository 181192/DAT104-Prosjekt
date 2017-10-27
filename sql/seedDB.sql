/* 
REVISION VERSIONS
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

SET search_path TO db;
-- ddl-end --

-- object: db."Bruker" | type: TABLE --
DROP TABLE IF EXISTS db."Bruker" CASCADE;
CREATE TABLE db."Bruker"(
	id serial NOT NULL,
	mail varchar(100) NOT NULL,
	fornavn varchar(50) NOT NULL,
	etternavn varchar(70) NOT NULL,
	passord varchar NOT NULL,
	salt varchar NOT NULL,
	"id_Rolle" integer NOT NULL,
	CONSTRAINT "Bruker_pk" PRIMARY KEY (id)

);

-- object: db."Rolle" | type: TABLE --
DROP TABLE IF EXISTS db."Rolle" CASCADE;
CREATE TABLE db."Rolle"(
	id serial NOT NULL,
	type text NOT NULL,
	CONSTRAINT "Rolle_pk" PRIMARY KEY (id)

);

-- object: db."Rettigheter" | type: TABLE --
DROP TABLE IF EXISTS db."Rettigheter" CASCADE;
CREATE TABLE db."Rettigheter"(
	id serial NOT NULL,
	godkjenne_bruker bool NOT NULL,
	slette_bruker bool NOT NULL,
	opprette_aktivitet bool NOT NULL,
	"id_Rolle" integer NOT NULL,
	CONSTRAINT "Rettigheter_pk" PRIMARY KEY (id)

);

-- object: db."Aktivitet" | type: TABLE --
DROP TABLE IF EXISTS db."Aktivitet" CASCADE;
CREATE TABLE db."Aktivitet"(
	id serial NOT NULL,
	navn text NOT NULL,
	status varchar NOT NULL,
	"id_Bruker" integer NOT NULL,
	CONSTRAINT "Aktivitet_pk" PRIMARY KEY (id)

);

-- object: db."Event" | type: TABLE --
DROP TABLE IF EXISTS db."Event" CASCADE;
CREATE TABLE db."Event"(
	id serial NOT NULL,
	navn varchar NOT NULL,
	beskrivelse text,
	tid_fra timestamp NOT NULL,
	tid_til timestamp NOT NULL,
	faktisk_start timestamp,
	faktisk_slutt timestamp,
	status varchar NOT NULL,
	sted varchar,
	"id_Aktivitet" integer NOT NULL,
	CONSTRAINT "Event_pk" PRIMARY KEY (id)

);

-- object: db."Kodeord" | type: TABLE --
DROP TABLE IF EXISTS db."Kodeord" CASCADE;
CREATE TABLE db."Kodeord"(
	id serial NOT NULL,
	kode varchar NOT NULL,
	"id_Event" integer NOT NULL,
	CONSTRAINT "Kodeord_pk" PRIMARY KEY (id),
	CONSTRAINT kode_uq UNIQUE (kode)

);

-- object: db."Tilbakemelding" | type: TABLE --
DROP TABLE IF EXISTS db."Tilbakemelding" CASCADE;
CREATE TABLE db."Tilbakemelding"(
	id serial NOT NULL,
	stemme varchar NOT NULL,
	tid timestamp NOT NULL,
	"id_Event" integer NOT NULL,
	CONSTRAINT "Tilbakemelding_pk" PRIMARY KEY (id)

);

-- object: db."Live_tilbakemelding" | type: TABLE --
DROP TABLE IF EXISTS db."Live_tilbakemelding" CASCADE;
CREATE TABLE db."Live_tilbakemelding"(
	id serial NOT NULL,
	stemme varchar NOT NULL,
	tid timestamp NOT NULL,
	"id_Event" integer NOT NULL,
	CONSTRAINT "Live_tilbakemelding_pk" PRIMARY KEY (id)

);

-- object: "Rolle_fk" | type: CONSTRAINT --
-- ALTER TABLE db."Rettigheter" DROP CONSTRAINT IF EXISTS "Rolle_fk" CASCADE;
ALTER TABLE db."Rettigheter" ADD CONSTRAINT "Rolle_fk" FOREIGN KEY ("id_Rolle")
REFERENCES db."Rolle" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Rolle_fk" | type: CONSTRAINT --
-- ALTER TABLE db."Bruker" DROP CONSTRAINT IF EXISTS "Rolle_fk" CASCADE;
ALTER TABLE db."Bruker" ADD CONSTRAINT "Rolle_fk" FOREIGN KEY ("id_Rolle")
REFERENCES db."Rolle" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Bruker_fk" | type: CONSTRAINT --
-- ALTER TABLE db."Aktivitet" DROP CONSTRAINT IF EXISTS "Bruker_fk" CASCADE;
ALTER TABLE db."Aktivitet" ADD CONSTRAINT "Bruker_fk" FOREIGN KEY ("id_Bruker")
REFERENCES db."Bruker" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Aktivitet_fk" | type: CONSTRAINT --
-- ALTER TABLE db."Event" DROP CONSTRAINT IF EXISTS "Aktivitet_fk" CASCADE;
ALTER TABLE db."Event" ADD CONSTRAINT "Aktivitet_fk" FOREIGN KEY ("id_Aktivitet")
REFERENCES db."Aktivitet" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Event_fk" | type: CONSTRAINT --
-- ALTER TABLE db."Kodeord" DROP CONSTRAINT IF EXISTS "Event_fk" CASCADE;
ALTER TABLE db."Kodeord" ADD CONSTRAINT "Event_fk" FOREIGN KEY ("id_Event")
REFERENCES db."Event" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Kodeord_uq" | type: CONSTRAINT --
-- ALTER TABLE db."Kodeord" DROP CONSTRAINT IF EXISTS "Kodeord_uq" CASCADE;
ALTER TABLE db."Kodeord" ADD CONSTRAINT "Kodeord_uq" UNIQUE ("id_Event");
-- ddl-end --

-- object: "Event_fk" | type: CONSTRAINT --
-- ALTER TABLE db."Tilbakemelding" DROP CONSTRAINT IF EXISTS "Event_fk" CASCADE;
ALTER TABLE db."Tilbakemelding" ADD CONSTRAINT "Event_fk" FOREIGN KEY ("id_Event")
REFERENCES db."Event" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: "Event_fk" | type: CONSTRAINT --
-- ALTER TABLE db."Live_tilbakemelding" DROP CONSTRAINT IF EXISTS "Event_fk" CASCADE;
ALTER TABLE db."Live_tilbakemelding" ADD CONSTRAINT "Event_fk" FOREIGN KEY ("id_Event")
REFERENCES db."Event" (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

--Dummy data
INSERT INTO "Rolle" (type) VALUES('Admin');
INSERT INTO "Rolle" (type) VALUES('Aktivitetsstyrer');

INSERT INTO "Rettigheter" (godkjenne_bruker, slette_bruker, opprette_aktivitet, "id_Rolle") VALUES ('1','1','0','1');
INSERT INTO "Rettigheter" (godkjenne_bruker, slette_bruker, opprette_aktivitet, "id_Rolle") VALUES ('0','0','1','2');

INSERT INTO "Bruker" (mail, fornavn, etternavn, passord, salt, "id_Rolle") VALUES ('admin@gruppe3.no', 'Ola', 'Olsen', 'foobar', 'dioawd633a', '1');
INSERT INTO "Bruker" (mail, fornavn, etternavn, passord, salt, "id_Rolle") VALUES ('kari@gruppe3.no', 'Kari', 'Pettersen', 'foobar', 'afa7fa9dwa', '2');
INSERT INTO "Bruker" (mail, fornavn, etternavn, passord, salt, "id_Rolle") VALUES ('per@gruppe3.no', 'Per', 'Hansen', 'foobar', 'njnpojniu2', '2');

INSERT INTO "Aktivitet" (navn, status, "id_Bruker") VALUES ('DAT100', 'avsluttet', '2');
INSERT INTO "Aktivitet" (navn, status, "id_Bruker") VALUES ('MAT101', 'pagaende', '2');
INSERT INTO "Aktivitet" (navn, status, "id_Bruker") VALUES ('DAT102', 'planlagt', '3');

INSERT INTO "Event" (navn, tid_fra, tid_til, status, sted, "id_Aktivitet") VALUES ('DAT100', '2017-10-20 12:00:00', '2017-10-20 14:00:00', 'avsluttet', 'F115', '1');
INSERT INTO "Event" (navn, tid_fra, tid_til, status, sted, "id_Aktivitet") VALUES ('MAT101', '2017-10-26 10:00:00', '2017-10-26 22:00:00', 'pagaende', 'F110', '2');
INSERT INTO "Event" (navn, tid_fra, tid_til, status, sted, "id_Aktivitet") VALUES ('DAT102', '2017-12-20 09:00:00', '2017-12-20 10:30:00', 'planlagt', 'E403', '3');

INSERT INTO "Tilbakemelding" (stemme, tid, "id_Event") VALUES ('0','2017-10-20 14:01:00','1');
INSERT INTO "Tilbakemelding" (stemme, tid, "id_Event") VALUES ('1','2017-10-20 14:02:00','1');
INSERT INTO "Tilbakemelding" (stemme, tid, "id_Event") VALUES ('2','2017-10-20 14:03:00','1');
INSERT INTO "Tilbakemelding" (stemme, tid, "id_Event") VALUES ('0','2017-10-20 14:04:00','1');
INSERT INTO "Tilbakemelding" (stemme, tid, "id_Event") VALUES ('0','2017-10-20 14:05:00','1');
INSERT INTO "Tilbakemelding" (stemme, tid, "id_Event") VALUES ('0','2017-10-20 14:06:00','1');
INSERT INTO "Tilbakemelding" (stemme, tid, "id_Event") VALUES ('1','2017-10-20 14:07:00','1');
INSERT INTO "Tilbakemelding" (stemme, tid, "id_Event") VALUES ('1','2017-10-20 14:08:00','1');
INSERT INTO "Tilbakemelding" (stemme, tid, "id_Event") VALUES ('2','2017-10-20 14:09:00','1');
INSERT INTO "Tilbakemelding" (stemme, tid, "id_Event") VALUES ('2','2017-10-20 14:10:00','1');
INSERT INTO "Tilbakemelding" (stemme, tid, "id_Event") VALUES ('2','2017-10-20 14:11:00','1');
INSERT INTO "Tilbakemelding" (stemme, tid, "id_Event") VALUES ('2','2017-10-20 14:12:00','1');