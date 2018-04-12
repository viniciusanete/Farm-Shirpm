alter table usuarios alter column id set default nextval('serial_user');
--alter table usuarios add column inactive boolean default false;
alter table usuarios alter column perfil type bigint;
alter table C001 alter column telefone rename usu_tel





alter table usuarios add column name varchar(100);
alter table usuarios add column email varchar(50);


alter table C001 add constraint PERFIS_FK foreign key (USU_PERFIL) references perfis (P_ID);
--create sequence serial_perfil start 1
--create sequence serial_tel start 1
--insert into C001(USU_ID,USU_USERNAME,USU_PASSWORD,USU_PERFIL,USU_INACTIVE) select id, username, password, perfil, inactive  from usuarios;
--drop table if exists usuarios;

alter table C001 drop column telefone 
alter table telefones add column usu_tel bigint
alter table telefones add constraint telefone_FK foreign key (usu_tel) references C001 (usu_id);