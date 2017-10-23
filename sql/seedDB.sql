-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.0
-- PostgreSQL version: 9.6
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: h17_dat104_p03 | type: DATABASE --
-- -- DROP DATABASE IF EXISTS h17_dat104_p03;
-- CREATE DATABASE h17_dat104_p03
-- ;
-- -- ddl-end --
-- 

-- object: db | type: SCHEMA --
-- DROP SCHEMA IF EXISTS db CASCADE;
CREATE SCHEMA db;
-- ddl-end --
ALTER SCHEMA db OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,db;
-- ddl-end --

-- object: db."Bruker" | type: TABLE --
-- DROP TABLE IF EXISTS db."Bruker" CASCADE;
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
-- ddl-end --
ALTER TABLE db."Bruker" OWNER TO postgres;
-- ddl-end --

-- object: db."Rolle" | type: TABLE --
-- DROP TABLE IF EXISTS db."Rolle" CASCADE;
CREATE TABLE db."Rolle"(
	id serial NOT NULL,
	type text NOT NULL,
	CONSTRAINT "Rolle_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db."Rolle" OWNER TO postgres;
-- ddl-end --

-- object: db."Rettigheter" | type: TABLE --
-- DROP TABLE IF EXISTS db."Rettigheter" CASCADE;
CREATE TABLE db."Rettigheter"(
	id serial NOT NULL,
	godkjenne_bruker bool NOT NULL,
	slette_bruker bool NOT NULL,
	opprette_bruker bool NOT NULL,
	"id_Rolle" integer NOT NULL,
	CONSTRAINT "Rettigheter_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db."Rettigheter" OWNER TO postgres;
-- ddl-end --

-- object: db."Aktivitet" | type: TABLE --
-- DROP TABLE IF EXISTS db."Aktivitet" CASCADE;
CREATE TABLE db."Aktivitet"(
	id serial NOT NULL,
	navn text NOT NULL,
	status varchar NOT NULL,
	"id_Bruker" integer NOT NULL,
	CONSTRAINT "Aktivitet_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db."Aktivitet" OWNER TO postgres;
-- ddl-end --

-- object: db."Event" | type: TABLE --
-- DROP TABLE IF EXISTS db."Event" CASCADE;
CREATE TABLE db."Event"(
	id serial NOT NULL,
	navn varchar NOT NULL,
	tid_fra timestamp NOT NULL,
	tid_til timestamp NOT NULL,
	faktisk_start timestamp,
	faktisk_slutt timestamp,
	status varchar NOT NULL,
	sted varchar,
	"id_Aktivitet" integer NOT NULL,
	CONSTRAINT "Event_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db."Event" OWNER TO postgres;
-- ddl-end --

-- object: db."Kodeord" | type: TABLE --
-- DROP TABLE IF EXISTS db."Kodeord" CASCADE;
CREATE TABLE db."Kodeord"(
	id serial NOT NULL,
	kode varchar NOT NULL,
	"id_Event" integer NOT NULL,
	CONSTRAINT "Kodeord_pk" PRIMARY KEY (id),
	CONSTRAINT kode_uq UNIQUE (kode)

);
-- ddl-end --
ALTER TABLE db."Kodeord" OWNER TO postgres;
-- ddl-end --

-- object: db."Tilbakemelding" | type: TABLE --
-- DROP TABLE IF EXISTS db."Tilbakemelding" CASCADE;
CREATE TABLE db."Tilbakemelding"(
	id serial NOT NULL,
	stemme varchar NOT NULL,
	"id_Event" integer NOT NULL,
	CONSTRAINT "Tilbakemelding_pk" PRIMARY KEY (id)

);
-- ddl-end --
ALTER TABLE db."Tilbakemelding" OWNER TO postgres;
-- ddl-end --

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