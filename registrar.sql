--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: courses; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE courses (
    id integer NOT NULL,
    course_name character varying,
    course_number character varying
);


ALTER TABLE courses OWNER TO "Guest";

--
-- Name: courses_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE courses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE courses_id_seq OWNER TO "Guest";

--
-- Name: courses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE courses_id_seq OWNED BY courses.id;


--
-- Name: courses_students; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE courses_students (
    id integer NOT NULL,
    course_id integer,
    student_id integer
);


ALTER TABLE courses_students OWNER TO "Guest";

--
-- Name: courses_students_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE courses_students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE courses_students_id_seq OWNER TO "Guest";

--
-- Name: courses_students_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE courses_students_id_seq OWNED BY courses_students.id;


--
-- Name: students; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE students (
    id integer NOT NULL,
    name character varying,
    enrollment_date date
);


ALTER TABLE students OWNER TO "Guest";

--
-- Name: students_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE students_id_seq OWNER TO "Guest";

--
-- Name: students_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE students_id_seq OWNED BY students.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY courses ALTER COLUMN id SET DEFAULT nextval('courses_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY courses_students ALTER COLUMN id SET DEFAULT nextval('courses_students_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY students ALTER COLUMN id SET DEFAULT nextval('students_id_seq'::regclass);


--
-- Data for Name: courses; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY courses (id, course_name, course_number) FROM stdin;
1	Home Economics	ECON410
2	Albinos in World History 	HIST403
3	Mystery Novels	ENG203
4	My Life In Butterflies	ENG440
5	Colors in Music	MUS334
6	Adventures in Architecture	ARCH999
7	Advanced Regular Expressions	COMP400
8	History	101
\.


--
-- Name: courses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('courses_id_seq', 8, true);


--
-- Data for Name: courses_students; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY courses_students (id, course_id, student_id) FROM stdin;
1	1	1
2	1	2
3	1	1
4	1	1
5	1	1
6	1	1
7	1	1
8	1	1
9	2	1
10	4	1
11	4	2
12	1	2
13	2	2
14	3	2
15	4	2
16	2	2
17	1	6
18	1	3
19	2	3
20	1	7
21	1	5
22	1	8
23	1	4
24	3	1
25	6	1
\.


--
-- Name: courses_students_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('courses_students_id_seq', 25, true);


--
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY students (id, name, enrollment_date) FROM stdin;
1	Mary	1999-02-22
2	Mary Jones	2016-03-16
3	Britney Spears	2016-03-17
4	Mariah Carey	2016-02-02
5	Tom Jones	2016-01-01
6	Hillary Clinton	2016-06-08
7	Bernie Sanders	2016-02-10
8	Eleanor of Aquitaine	2015-09-08
9	Matt	2016-03-02
\.


--
-- Name: students_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('students_id_seq', 9, true);


--
-- Name: courses_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY courses
    ADD CONSTRAINT courses_pkey PRIMARY KEY (id);


--
-- Name: courses_students_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY courses_students
    ADD CONSTRAINT courses_students_pkey PRIMARY KEY (id);


--
-- Name: students_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY students
    ADD CONSTRAINT students_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

