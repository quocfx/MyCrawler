drop table if exists seller cascade;
create table seller
(
    s_id serial not null,
    s_name char(50) not null,
    s_rate integer not null,
    s_location char(50),
    s_time_on_lazada integer not null,
    s_shipped_on_time integer not null,
    primary key (s_id)
);
drop table if exists review;
create table review
(
    r_id serial not null,
    r_type char(10),
    r_message char(500),
    r_author char(20),
    r_time timestamp,
    s_id serial not null,
    primary key (r_id)
);
alter table review
add constraint seller_review foreign key (s_id) references seller (s_id);