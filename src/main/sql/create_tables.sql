create table roles
(
    id   serial primary key,
    role varchar(255) not null
);

create table users
(
    id         serial primary key,
    username   varchar(255)                   not null,
    password   varchar(255)                   not null,
    surname    varchar(255)                   not null,
    name       varchar(255)                   not null,
    patronymic varchar(255)                   not null,
    role_id    smallint references roles (id) not null
);

