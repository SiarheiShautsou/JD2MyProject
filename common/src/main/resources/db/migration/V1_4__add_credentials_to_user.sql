alter table users
    add if not exists user_login varchar(20);

alter table users
    add if not exists user_password varchar(30);

alter table users
    add if not exists email varchar(100);

alter table users
    add if not exists mobile_number varchar(20);

drop table if exists user_credentials cascade;