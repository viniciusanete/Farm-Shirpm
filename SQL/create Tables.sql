create table IF NOT EXISTS  C001 (
 USU_ID bigint primary key default nextval('serial_user'),
 USU_USERNAME varchar(50),
 USU_PASSWORD varchar(50),
 USU_INACTIVE boolean default false,
 USU_PERFIL bigint,
 USU_NAME varchar(100),
 USU_EMAIL varchar(50),
 foreign key (USU_PERFIL) references perfis (id)
 on delete set null
 on update cascade
 
);

create table if not exists perfis(
P_ID bigint primary key default nextval('serial_perfil'),
P_DESCRICAO varchar(20) default null,
P_CAD_USER boolean default false,
P_EDT_MED  boolean default false,
P_VISU_MED boolean default false
);
insert into perfis (P_ID, P_CAD_USER, P_EDT_MED, P_VISU_MED) values (1, true, true, true);