create table usuarios (
 id bigint primary key default nextval('serial_user'),
 username varchar(50),
 password varchar(50),
 inactive boolean default false,
 perfil int,
 name varchar(100),
 email varchar(50)
)