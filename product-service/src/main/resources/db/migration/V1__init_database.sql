create table if not exists category
(
    id integer not null primary key ,
    description varchar(255),
    name varchar(255)
);

create table if not exists products
(
    id integer not null primary key ,
    description varchar(255),
    name varchar(255),
    available_quantity double precision not null,
    price double precision not null ,
    category_id integer constraint fk1cxznfdksjsdafl references category
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists products_seq increment by 50;