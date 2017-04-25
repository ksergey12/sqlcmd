-- Database: sqlcmd_log

-- DROP DATABASE sqlcmd_log;

CREATE DATABASE sqlcmd_log
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- SCHEMA: public

-- DROP SCHEMA public ;

CREATE SCHEMA public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT ALL ON SCHEMA public TO postgres;

GRANT ALL ON SCHEMA public TO PUBLIC;

-- Sequence: user_action_seq

-- DROP SEQUENCE user_action_seq;

CREATE SEQUENCE public.user_action_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.user_action_seq
    OWNER TO postgres;

-- Table: public.user_actions

-- DROP TABLE public.user_actions;

CREATE TABLE public.user_actions
(
    id integer NOT NULL DEFAULT nextval('user_action_seq'::regclass),
    user_name text COLLATE pg_catalog."default",
    db_name text COLLATE pg_catalog."default",
    action text COLLATE pg_catalog."default",
    CONSTRAINT id_pk PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.user_actions
    OWNER to postgres;

-- Index: id_pk

-- DROP INDEX id_pk;

CREATE INDEX id_pk
  ON user_actions
  USING btree
  (id);