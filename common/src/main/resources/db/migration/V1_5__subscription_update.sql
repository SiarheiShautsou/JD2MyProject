alter table subscription
    add if not exists creation_date timestamp(6) default current_timestamp(6);

alter table subscription
    add if not exists modification_date timestamp(6) default current_timestamp(6);