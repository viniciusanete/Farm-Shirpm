create table usuarios (
 id bigint primary key default nextval('serial_user'),
 username varchar(255),
 password varchar(255),
 perfil int
)