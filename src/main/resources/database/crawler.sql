drop table if exists seller cascade;
create table seller
(
    s_id serial not null,
    s_name varchar(50) not null,
    s_category varchar(50),
    s_rate float not null,
    s_size integer not null,
    s_location varchar(50),
    s_time_on_lazada integer not null,
    s_shipped_on_time integer not null,
    primary key (s_id)
);
drop table if exists review;
create table review
(
    r_id serial not null,
    r_type varchar(10),
    r_message varchar(500),
    r_author varchar(20),
    r_time timestamp,
    s_id serial not null,
    primary key (r_id)
);
alter table review
add constraint seller_review foreign key (s_id) references seller (s_id);