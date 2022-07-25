--Custom AuthProvider
--insert into person (username, birth_year, password) values ('test_user1', 1995, 'password');

--DAO-authentication
insert into roles (name) values ('ROLE_USER'), ('ROLE_ADMIN');
insert into users (username, password, email) values
('user', '$2a$12$CBwR64JvVCS/47QgtebvLunJlJn4wvmvjcRSWVkaqmvuncGxSja1u', 'test@test.com');
insert into users_roles (user_id, role_id) values (1, 2);