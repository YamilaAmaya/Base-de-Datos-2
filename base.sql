PGDMP                      |            estudiantes    16.2    16.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16525    estudiantes    DATABASE     �   CREATE DATABASE estudiantes WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Bolivia.1252';
    DROP DATABASE estudiantes;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    16527 
   estudiante    TABLE     �   CREATE TABLE public.estudiante (
    id integer NOT NULL,
    nombre_completo character varying(100) NOT NULL,
    fecha_nacimiento date NOT NULL,
    carrera character varying(100) NOT NULL
);
    DROP TABLE public.estudiante;
       public         heap    postgres    false    4            �            1259    16526    estudiante_id_seq    SEQUENCE     �   CREATE SEQUENCE public.estudiante_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.estudiante_id_seq;
       public          postgres    false    4    216            �           0    0    estudiante_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.estudiante_id_seq OWNED BY public.estudiante.id;
          public          postgres    false    215            �            1259    16533    materias    TABLE     �   CREATE TABLE public.materias (
    codigo character varying(20) NOT NULL,
    nombre character varying(100) NOT NULL,
    creditos integer NOT NULL
);
    DROP TABLE public.materias;
       public         heap    postgres    false    4            �            1259    16539    notas    TABLE     �   CREATE TABLE public.notas (
    id integer NOT NULL,
    id_estudiante integer,
    codigo_materia character varying(20),
    nota double precision NOT NULL
);
    DROP TABLE public.notas;
       public         heap    postgres    false    4            �            1259    16538    notas_id_seq    SEQUENCE     �   CREATE SEQUENCE public.notas_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.notas_id_seq;
       public          postgres    false    219    4            �           0    0    notas_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.notas_id_seq OWNED BY public.notas.id;
          public          postgres    false    218            Y           2604    16530    estudiante id    DEFAULT     n   ALTER TABLE ONLY public.estudiante ALTER COLUMN id SET DEFAULT nextval('public.estudiante_id_seq'::regclass);
 <   ALTER TABLE public.estudiante ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    216    216            Z           2604    16542    notas id    DEFAULT     d   ALTER TABLE ONLY public.notas ALTER COLUMN id SET DEFAULT nextval('public.notas_id_seq'::regclass);
 7   ALTER TABLE public.notas ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    219    219            �          0    16527 
   estudiante 
   TABLE DATA           T   COPY public.estudiante (id, nombre_completo, fecha_nacimiento, carrera) FROM stdin;
    public          postgres    false    216   !       �          0    16533    materias 
   TABLE DATA           <   COPY public.materias (codigo, nombre, creditos) FROM stdin;
    public          postgres    false    217   �       �          0    16539    notas 
   TABLE DATA           H   COPY public.notas (id, id_estudiante, codigo_materia, nota) FROM stdin;
    public          postgres    false    219   �                   0    0    estudiante_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.estudiante_id_seq', 2, true);
          public          postgres    false    215                       0    0    notas_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.notas_id_seq', 1, false);
          public          postgres    false    218            \           2606    16532    estudiante estudiante_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.estudiante
    ADD CONSTRAINT estudiante_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.estudiante DROP CONSTRAINT estudiante_pkey;
       public            postgres    false    216            ^           2606    16537    materias materias_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.materias
    ADD CONSTRAINT materias_pkey PRIMARY KEY (codigo);
 @   ALTER TABLE ONLY public.materias DROP CONSTRAINT materias_pkey;
       public            postgres    false    217            `           2606    16544    notas notas_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.notas
    ADD CONSTRAINT notas_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.notas DROP CONSTRAINT notas_pkey;
       public            postgres    false    219            a           2606    16550    notas notas_codigo_materia_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.notas
    ADD CONSTRAINT notas_codigo_materia_fkey FOREIGN KEY (codigo_materia) REFERENCES public.materias(codigo);
 I   ALTER TABLE ONLY public.notas DROP CONSTRAINT notas_codigo_materia_fkey;
       public          postgres    false    219    217    4702            b           2606    16545    notas notas_id_estudiante_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.notas
    ADD CONSTRAINT notas_id_estudiante_fkey FOREIGN KEY (id_estudiante) REFERENCES public.estudiante(id);
 H   ALTER TABLE ONLY public.notas DROP CONSTRAINT notas_id_estudiante_fkey;
       public          postgres    false    219    4700    216            �   Z   x��;
�0 �99E.PI��#���4H�?�Y<����$�b���cf����l����)���V�X�z���-?��� t+�M��\�D%      �      x������ � �      �      x������ � �     