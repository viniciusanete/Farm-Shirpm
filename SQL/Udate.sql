alter table usuarios alter column id set default nextval('serial_user');
alter table usuarios add column inactive boolean default false;