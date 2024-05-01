CREATE TABLE IF NOT EXISTS public.guides(
    id serial primary key,
    first_name varchar(255),
    last_name varchar(255)
);

CREATE TABLE IF NOT EXISTS public.tours(
    id serial primary key,
    departure_point varchar(255),
    destination varchar(255),
    departure_date date,
    return_date date,
    initial_price decimal(8,2),
    guide_id integer references public.guides
--         on delete ... (for cascade operation)
);

CREATE TABLE IF NOT EXISTS public.clients(
    id serial primary key,
    first_name varchar(255),
    last_name varchar(255),
    passport_number varchar(255)
);

CREATE TABLE IF NOT EXISTS public.clients_tours(
    client_id integer references public.clients,
    tour_id integer references public.tours
);