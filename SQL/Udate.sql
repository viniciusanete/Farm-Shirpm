alter table usuarios alter column id set default nextval('serial_user');
alter table usuarios add column inactive boolean default false;


alter table usuarios add column name varchar(100);
alter table usuarios add column email varchar(50);