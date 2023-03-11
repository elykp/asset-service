drop index if exists idx_photo_id;

drop index if exists idx_owner_id;

drop table if exists asset_entity cascade;

create table asset_entity
(
    id             bigserial   not null,
    created_at     bigint,
    height         integer     not null,
    owner_id       varchar(36) not null,
    responsive_key varchar(20) not null,
    url            varchar(255) default '',
    width          integer     not null,
    photo_id       varchar(8),
    primary key (id)
);

create index idx_photo_id on asset_entity (photo_id);

create index idx_owner_id on asset_entity (owner_id);
