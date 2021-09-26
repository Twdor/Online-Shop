DROP TABLE IF EXISTS public.suppliers;
CREATE TABLE public.suppliers (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    description text NOT NULL
);

DROP TABLE IF EXISTS public.product_categories;
CREATE TABLE public.product_categories (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    department text NOT NULL
);

DROP TABLE IF EXISTS public.product_subcategories;
CREATE TABLE public.product_subcategories (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    product_category_id integer NOT NULL
);

DROP TABLE IF EXISTS public.products;
CREATE TABLE public.products (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    price decimal NOT NULL,
    currency text NOT NULL,
    description text NOT NULL,
    product_subcategory_id integer NOT NULL,
    product_supplier_id integer NOT NULL
);

DROP TABLE IF EXISTS public.users;
CREATE TABLE public.users (
    id serial NOT NULL PRIMARY KEY,
    name text NOT NULL,
    email text NOT NULL,
    password CHARACTER VARYING(255) NOT NULL,
    phone_number text,
    country text,
    city text,
    zipcode text,
    address text
);

DROP TABLE IF EXISTS public.orders;
CREATE TABLE public.orders (
    id serial NOT NULL PRIMARY KEY,
    date date NOT NULL,
    status text NOT NULL,
    customer_id integer NOT NULL,
    product_id integer NOT NULL
);

DROP TABLE IF EXISTS public.shopping_cards;
CREATE TABLE public.shopping_cards (
    id serial NOT NULL PRIMARY KEY,
    customer_id CHARACTER VARYING(255) NOT NULL,
    product_id integer NOT NULL,
    quantity integer NOT NULL
);