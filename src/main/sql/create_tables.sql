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

create table types
(
    id   serial primary key,
    name varchar(255) not null
);

create table products
(
    id           serial primary key,
    name         varchar(1000)                not null,
    type_id      bigint references types (id) not null,
    count        integer                      not null,
    price        real                         not null,
    manufacturer varchar(255),
    description  text
);

create table sales
(
    id         serial primary key              not null,
    product_id bigint references products (id) not null,
    user_id    bigint references users (id)    not null,
    count      bigint                          not null,
    date       timestamp                       not null
);

