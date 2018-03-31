alter table usuarios alter column id set default nextval('serial_user');
--alter table usuarios add column inactive boolean default false;
alter table usuarios alter column perfil type bigint;



alter table usuarios add column name varchar(100);
alter table usuarios add column email varchar(50);


alter table C001 add constraint PERFIS_FK foreign key (USU_PERFIL) references perfis (P_ID);
--create sequence serial_perfil start 1
--insert into C001(USU_ID,USU_USERNAME,USU_PASSWORD,USU_PERFIL,USU_INACTIVE) select id, username, password, perfil, inactive  from usuarios;
--drop table if exists usuarios;