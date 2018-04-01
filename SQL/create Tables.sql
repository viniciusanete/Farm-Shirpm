create table IF NOT EXISTS  C001 (
 USU_ID bigint primary key default nextval('serial_user'),
 USU_USERNAME varchar(50),
 USU_PASSWORD varchar(50),
 USU_INACTIVE boolean default false,
 USU_PERFIL bigint,
 USU_NAME varchar(100),
 USU_EMAIL varchar(50),
 USU_TEL bigint,
 foreign key (USU_PERFIL) references perfis (P_ID)
 on delete set null
 on update cascade,
 foreign key (USU_TEL) references telefone (tel_id)
 on delete set null
 on update cascade
);

--create sequence serial_tel start 1
create table if not exists telefones(
tel_id bigint primary key default nextval('serial_tel'),
tel_ddd varchar(4) default null,
tel_number varchar(15) default null, 
tel_ddi varchar(4) default null
)

--create sequence serial_perfil start 1
create table if not exists perfis(
P_ID bigint primary key default nextval('serial_perfil'),
P_DESCRICAO varchar(20) default null,
P_CAD_USER boolean default false,
P_EDT_MED  boolean default false,
P_VISU_MED boolean default false
);
insert into perfis (P_ID, P_CAD_USER, P_EDT_MED, P_VISU_MED) values (1, true, true, true);

create schema registro

--create sequence serial_tanque start 1
--verificar o que deseja guardar do tanque
create table if not exists registro.tanque ( 
TANQ_ID bigint primary key default nextval('serial_tanque'),
TANQ_NOME varchar(20),
TANQ_CAPACIDADE  varchar(10)
);

--create sequence serial_medicao start 1
create table if not exists registro.medicao ( 
MED_ID bigint primary key default nextval('serial_medicao'),
MED_DATAHORA date default null,
MED_REGISTRO varchar(100) default null,
MED_TANQUE bigint,
foreign key (MED_TANQUE) references registro.tanque (tanq_id)
 on delete set null
 on update cascade
);

