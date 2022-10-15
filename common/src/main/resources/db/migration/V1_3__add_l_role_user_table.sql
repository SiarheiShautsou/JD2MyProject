alter table roles
    drop constraint if exists roles_users_id_fk;

alter table roles
    drop column if exists user_id;

create table if not exists l_role_user
(
    id                bigserial
        constraint l_role_user_pk
            primary key,
    role_id           integer                                   not null
        constraint l_role_user_roles_id_fk
            references roles (id)
            on update cascade on delete cascade,
    user_id           bigint                                    not null
        constraint l_role_user_users_id_fk
            references users (id)
            on update cascade on delete cascade,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table l_role_user
    owner to postgres;

create unique index if not exists l_role_user_id_uindex
    on l_role_user (id);



