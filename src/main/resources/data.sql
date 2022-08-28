insert into roles (name) values ('ROLE_USER'), ('ROLE_ADMIN');
insert into users (username, password, email) values
('john', '$2a$12$0U9GRmh8/fC.Q3M9kNEPQeBjHCG9Izld2woffGJNFxSpCxbMYbrRy', 'test@test.com');
insert into users_roles (user_id, role_id) values (1, 2);