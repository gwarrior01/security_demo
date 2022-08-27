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