create table if not exists users
(
    id                bigint       default nextval('user_id_seq'::regclass) not null,
    user_name         varchar(20)                                                          not null,
    user_surname      varchar(30)                                                          not null,
    birth             timestamp(6)                                                         not null,
    country           varchar(30)                                                          not null,
    city              varchar(30)                                                          not null,
    is_deleted        boolean      default false                                           not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6)                            not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6),
    user_latitude     integer,
    user_longitude    integer,
    mobile_number     bigint                                                               not null,
    gender            varchar(15)  default 'NOT_SELECTED'::character varying               not null
);

alter table users
    owner to postgres;

create unique index if not exists user_id_uindex
    on users (id);

create table if not exists gyms
(
    id                serial,
    gym_name          varchar(20)                               not null
        constraint gyms_pk
            unique,
    country           varchar(30)                               not null,
    city              varchar(30)                               not null,
    square            integer      default 100                  not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    gym_latitude      integer,
    gym_longitude     integer,
    is_deleted        boolean      default false                not null
);

alter table gyms
    owner to postgres;

create unique index if not exists gyms_id_uindex
    on gyms (id);

create table if not exists training_types
(
    id            integer default nextval('trainings_id_seq'::regclass) not null,
    training_type varchar(30)                                                          not null
        constraint trainings_pk
            unique
);

alter table training_types
    owner to postgres;

create table if not exists l_trainings
(
    id                bigint       default nextval('l_trainings_id_seq'::regclass) not null,
    trainer_id        bigint                                                                                not null
        constraint l_trainings_users_id_fk
            references users (id),
    client_id         bigint                                                                                not null
        constraint l_trainings_clients_id_fk
            references users (id)
            on update cascade on delete cascade,
    training_date     timestamp(6)                                                                          not null
        constraint l_trainings_pk
            primary key,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6),
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6),
    training_type_id  integer
        constraint l_trainings_training_types_id_fk
            references training_types (id)
            on update cascade on delete cascade
);

alter table l_trainings
    owner to postgres;

create table if not exists body_parameters
(
    id            bigint       default nextval('bogy_parameters_id_seq'::regclass) not null,
    user_id       bigint                                                                          not null
        constraint bogy_parameters_clients_id_fk
            references users (id)
            on update cascade on delete cascade,
    height        integer                                                                         not null,
    weight        double precision                                                                not null,
    bust          integer                                                                         not null,
    waist         integer                                                                         not null,
    hip           integer                                                                         not null,
    creation_date timestamp(6) default CURRENT_TIMESTAMP(6)                                       not null
);

alter table body_parameters
    owner to postgres;

create unique index if not exists bogy_parameters_id_uindex
    on body_parameters (id);

create table if not exists l_gyms_trainers
(
    id                serial,
    trainer_id        bigint                                    not null
        constraint l_gyms_trainers_users_id_fk
            references users (id)
            on update cascade on delete cascade,
    gym_id            integer                                   not null
        constraint l_gyms_trainers_gyms_id_fk
            references gyms (id)
            on update cascade on delete cascade,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table l_gyms_trainers
    owner to postgres;

create unique index if not exists l_gyms_trainers_id_uindex
    on l_gyms_trainers (id);

create unique index if not exists l_gyms_trainers_trainer_id_uindex
    on l_gyms_trainers (trainer_id);

create table if not exists roles
(
    id                bigserial,
    role_name         varchar(20)                               not null,
    user_id           bigint                                    not null
        constraint roles_users_id_fk
            references users (id)
            on update cascade on delete cascade,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table roles
    owner to postgres;

create unique index if not exists roles_id_uindex
    on roles (id);

create table if not exists subscription
(
    id              bigserial,
    user_id         bigint                                    not null
        constraint subscription_users_id_fk
            references users (id)
            on update cascade on delete cascade,
    gym_id          integer                                   not null
        constraint subscription_gyms_id_fk
            references gyms (id)
            on update cascade on delete cascade,
    valid_from      timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    valid_to        timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    is_unlimited    boolean      default false                not null,
    trainings_count integer      default 999999               not null
);

alter table subscription
    owner to postgres;

create unique index if not exists subscription_id_uindex
    on subscription (id);

create table if not exists user_credentials
(
    id                bigserial,
    user_id           bigint                                    not null
        constraint user_credentials_users_id_fk
            references users (id),
    user_login        varchar(20)                               not null,
    user_password     varchar(30)                               not null,
    user_email        varchar(100)                              not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table user_credentials
    owner to postgres;

create unique index if not exists user_credentials_id_uindex
    on user_credentials (id);

create unique index if not exists user_credentials_user_email_uindex
    on user_credentials (user_email);

create unique index if not exists user_credentials_user_id_uindex
    on user_credentials (user_id);

create unique index if not exists user_credentials_user_login_uindex
    on user_credentials (user_login);

create unique index if not exists user_credentials_user_password_uindex
    on user_credentials (user_password);


