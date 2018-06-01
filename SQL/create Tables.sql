create table IF NOT EXISTS  C001 (
 USU_ID bigint primary key default nextval('serial_user'),
 USU_USERNAME varchar(50),
 USU_PASSWORD varchar(50),
 USU_INACTIVE boolean default false,
 USU_PERFIL bigint,
 USU_NAME varchar(100),
 USU_EMAIL varchar(50),
 foreign key (USU_PERFIL) references perfis (P_ID)
 on delete set null
 on update cascade
);

--create sequence serial_tel start 1
create table if not exists telefones(
tel_id bigint primary key default nextval('serial_tel'),
tel_ddd varchar(4) default null,
tel_number varchar(15) default null, 
tel_ddi varchar(4) default null,
USU_TEL bigint,
foreign key (USU_TEL) references C001 (usu_id)
on delete set null
on update cascade
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
MED_TIPO INT,
MED_USU bigint,
foreign key (MED_USU) references c001 (USU_USERNAME) 
on delete set null 
on update cascade,
foreign key (MED_TANQUE) references registro.tanque (tanq_id)
 on delete cascade
 on update cascade
);

--create sequence serial_arduino start 1;
create table if not exists registro.arduino(
arduino_id bigint primary key default nextval('serial_arduino'),
codigo varchar(100),
tipo int,
TANQ_ID bigint,
foreign key (TANQ_ID) references registro.tanque (TANQ_ID)
on delete cascade
on update cascade
);

