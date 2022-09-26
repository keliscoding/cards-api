CREATE TABLE IF NOT EXISTS origins (
    id integer not null AUTO_INCREMENT,
    name varchar(255) not null,
    description varchar(255) not null,
    creator varchar(255) not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    primary key(id)
);