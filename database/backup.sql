--
-- PostgreSQL database dump
--

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

-- Started on 2026-01-22 16:03:21

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 25862)
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    id bigint NOT NULL,
    birth date,
    cpf character varying(255),
    mail character varying(255),
    name character varying(255),
    password character varying(255),
    phonenumber character varying(255),
    role character varying(255),
    verified boolean NOT NULL,
    CONSTRAINT account_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'USER'::character varying])::text[])))
);


ALTER TABLE public.account OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 25901)
-- Name: account_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.account_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.account_seq OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 25872)
-- Name: box; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.box (
    id bigint NOT NULL,
    color character varying(255),
    identifier character varying(255)
);


ALTER TABLE public.box OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 25902)
-- Name: box_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.box_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.box_seq OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 25880)
-- Name: comic; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comic (
    id bigint NOT NULL,
    available boolean NOT NULL,
    collection character varying(50),
    edition integer NOT NULL,
    title character varying(50),
    year integer NOT NULL,
    box_id bigint NOT NULL,
    CONSTRAINT comic_year_check CHECK ((year <= 9999))
);


ALTER TABLE public.comic OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 25903)
-- Name: comic_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.comic_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.comic_seq OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 25891)
-- Name: loan; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.loan (
    id bigint NOT NULL,
    devolutiondate timestamp(6) without time zone,
    loandate timestamp(6) without time zone NOT NULL,
    predicteddevolutiondate timestamp(6) without time zone NOT NULL,
    comic_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.loan OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 25904)
-- Name: loan_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.loan_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.loan_seq OWNER TO postgres;

--
-- TOC entry 5031 (class 0 OID 25862)
-- Dependencies: 219
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account (id, birth, cpf, mail, name, password, phonenumber, role, verified) FROM stdin;
2	1999-01-02	777.777.777-77	admin@comichub.com	administrador	$2a$10$MXCD0WVOEZkR1KpU/SXXgOK8XD6VfWeNv4phFsPlGeeCoCOcGfRI6	(76) 76767-6767	ADMIN	t
1	2000-01-07	888.888.888-88	daniel@email.com	Daniel	$2a$10$MXCD0WVOEZkR1KpU/SXXgOK8XD6VfWeNv4phFsPlGeeCoCOcGfRI6	(32) 98889-9888	USER	t
3	2004-05-14	744.623.920-43	vini@email.com	Vini	$2a$10$MXCD0WVOEZkR1KpU/SXXgOK8XD6VfWeNv4phFsPlGeeCoCOcGfRI6	(23) 65676-6221	USER	t
4	1901-01-04	757.293.829-37	linus@email.com	Linus	$2a$10$MXCD0WVOEZkR1KpU/SXXgOK8XD6VfWeNv4phFsPlGeeCoCOcGfRI6	(53) 42423-4245	USER	t
\.


--
-- TOC entry 5032 (class 0 OID 25872)
-- Dependencies: 220
-- Data for Name: box; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.box (id, color, identifier) FROM stdin;
3	#E0C3FC	20011
4	#F0F0F0	20012
5	#FFC6FF	20013
6	#FFADAD	20014
7	#FFD6A5	20015
\.


--
-- TOC entry 5033 (class 0 OID 25880)
-- Dependencies: 221
-- Data for Name: comic; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comic (id, available, collection, edition, title, year, box_id) FROM stdin;
1	t	Batman	1	O Cavaleiro das Trevas	1986	3
2	f	Superman	75	A Morte do Superman	1993	3
4	f	Marvel Deluxe	1	Guerra Civil	2006	4
5	f	Iron Man	128	O Homem de Ferro: O Demônio na Garrafa	1979	4
7	f	Uncanny X-Men	141	X-Men: Dias de um Futuro Esquecido	1981	6
8	f	Web of Spider-Man	31	Homem-Aranha: A Última Caçada de Kraven	1987	7
3	f	DC Universe	1	Crise nas Infinitas Terras	1985	3
10	t	The Flash	123	Flash de Dois Mundos	1961	3
9	t	Marvel Graphic Novel	5	Deus Ama, o Homem Mata	1982	7
6	t	Batman (Graphic Novel)	1	A Piada Mortal	1988	5
\.


--
-- TOC entry 5034 (class 0 OID 25891)
-- Dependencies: 222
-- Data for Name: loan; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.loan (id, devolutiondate, loandate, predicteddevolutiondate, comic_id, user_id) FROM stdin;
1	\N	2026-01-22 15:47:52.974157	2026-01-29 15:47:52.974157	2	3
2	\N	2026-01-22 15:49:03.31936	2026-01-29 15:49:03.31936	4	3
3	\N	2026-01-22 15:49:07.648341	2026-01-29 15:49:07.648341	5	3
4	\N	2026-01-22 15:49:31.069129	2026-01-29 15:49:31.069129	7	3
6	\N	2026-01-22 15:50:07.446217	2026-01-29 15:50:07.446217	8	4
7	\N	2026-01-22 15:50:11.082615	2026-01-29 15:50:11.082615	3	4
9	2026-01-22 15:51:10.957449	2026-01-22 15:50:36.614729	2026-01-29 15:50:36.614729	10	1
8	2026-01-22 15:51:14.497261	2026-01-22 15:50:26.526481	2026-01-29 15:50:26.526481	9	1
5	2026-01-22 15:51:16.059833	2026-01-22 15:50:02.55925	2026-01-29 15:50:02.55925	6	4
\.


--
-- TOC entry 5044 (class 0 OID 0)
-- Dependencies: 223
-- Name: account_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.account_seq', 4, true);


--
-- TOC entry 5045 (class 0 OID 0)
-- Dependencies: 224
-- Name: box_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.box_seq', 7, true);


--
-- TOC entry 5046 (class 0 OID 0)
-- Dependencies: 225
-- Name: comic_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comic_seq', 10, true);


--
-- TOC entry 5047 (class 0 OID 0)
-- Dependencies: 226
-- Name: loan_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.loan_seq', 9, true);


--
-- TOC entry 4874 (class 2606 OID 25871)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- TOC entry 4876 (class 2606 OID 25879)
-- Name: box box_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.box
    ADD CONSTRAINT box_pkey PRIMARY KEY (id);


--
-- TOC entry 4878 (class 2606 OID 25890)
-- Name: comic comic_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comic
    ADD CONSTRAINT comic_pkey PRIMARY KEY (id);


--
-- TOC entry 4880 (class 2606 OID 25900)
-- Name: loan loan_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loan
    ADD CONSTRAINT loan_pkey PRIMARY KEY (id);


--
-- TOC entry 4882 (class 2606 OID 25915)
-- Name: loan fk4har7xtrgbek73ayhd4dehc1a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loan
    ADD CONSTRAINT fk4har7xtrgbek73ayhd4dehc1a FOREIGN KEY (user_id) REFERENCES public.account(id);


--
-- TOC entry 4883 (class 2606 OID 25910)
-- Name: loan fk73sbpeoouj2ncnlxamvw961wi; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.loan
    ADD CONSTRAINT fk73sbpeoouj2ncnlxamvw961wi FOREIGN KEY (comic_id) REFERENCES public.comic(id);


--
-- TOC entry 4881 (class 2606 OID 25905)
-- Name: comic fkep1vc5iqsfqephu4afsr7j9lh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comic
    ADD CONSTRAINT fkep1vc5iqsfqephu4afsr7j9lh FOREIGN KEY (box_id) REFERENCES public.box(id);


-- Completed on 2026-01-22 16:03:21

--
-- PostgreSQL database dump complete
--