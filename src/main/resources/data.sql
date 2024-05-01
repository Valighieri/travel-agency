insert into guides (first_name, last_name)
values ('Tim', 'Robbins');
insert into guides (first_name, last_name)
values ('Morgan', 'Freeman');
insert into guides(first_name, last_name)
values ('Bob', 'Gunton');


insert into clients (first_name, last_name, passport_number)
values ('Andye', 'Dufresne', 'A0-1111');
insert into clients (first_name, last_name, passport_number)
values ('Ellis', 'Red', 'A0-2222');
insert into clients (first_name, last_name, passport_number)
values ('Samuel', 'Norton', 'A0-3333');


insert into tours (departure_point, destination, departure_date, return_date, initial_price, guide_id)
values ('Shawshank', 'USA', '2024-08-01', '2024-08-08', 573.22, 1);
insert into tours (departure_point, destination, departure_date, return_date, initial_price, guide_id)
values ('Washington', 'USA', '2024-07-22', '2024-07-29', 600, 2);
insert into tours (departure_point, destination, departure_date, return_date, initial_price, guide_id)
values ('Rome', 'Italy', '2024-08-01', '2024-08-05', 800, 3);


insert into clients_tours(client_id, tour_id)
values (1, 1);

insert into clients_tours(client_id, tour_id)
values (1, 2);
insert into clients_tours(client_id, tour_id)
values (2, 2);
insert into clients_tours(client_id, tour_id)
values (3, 2);

insert into clients_tours(client_id, tour_id)
values (3, 3);

