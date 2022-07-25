--Custom AuthProvider
--drop table if exists person;
--
--create table person (
--    id int primary key generated by default as identity,
--    username varchar(100) not null,
--    birth_year int not null,
--    password varchar not null
--);


--JDBC-authentication
--drop table if exists authorities;
--drop table if exists users;
--
--create table users (
--    username varchar(255) not null primary key,
--    password varchar(255) not null,
--    enabled boolean not null
--);
--
--create table authorities (
--    username varchar(255) not null,
--    authority varchar(255) not null,
--    foreign key (username) references users (username),
--    unique (username, authority)
--);

--DAO-authentication
drop table if exists users_roles;
drop table if exists roles;
drop table if exists users;
create table users (
    id bigserial,
    username varchar(30) not null,
    password varchar(80),
    email varchar(50) UNIQUE,
    primary key (id)
);

create table roles (
    id serial,
    name varchar(50) not null,
    primary key (id)
);

create table users_roles (
    user_id bigint not null,
    role_id int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);