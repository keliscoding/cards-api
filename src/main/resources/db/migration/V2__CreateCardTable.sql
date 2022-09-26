CREATE TABLE IF NOT EXISTS cards (
    id integer not null AUTO_INCREMENT,
    name varchar(255) not null,
    description varchar(255) not null,
    strength integer not null,
    speed integer not null,
    skill integer not null,
    gear integer not null,
    intellect integer not null,
    image_url varchar(255) not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    origin_id integer not null,
    foreign key (origin_id) references `origins`(`id`),
    primary key(id)
);