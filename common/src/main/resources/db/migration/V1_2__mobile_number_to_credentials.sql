alter table user_credentials
    add if not exists mobile_number varchar(20);

alter table users
    drop column if exists mobile_number;