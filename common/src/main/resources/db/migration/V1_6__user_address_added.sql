alter table users
    add if not exists street varchar(30);

alter table users
    add if not exists building varchar(5);
