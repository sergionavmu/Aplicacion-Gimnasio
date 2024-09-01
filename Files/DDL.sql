use gym;


create table usuario(
    id int primary key auto_increment,
    username varchar(100),
    email varchar(100),
    pass varchar(100),
    fechaAlta date,
    tipus int,
    admin boolean

);

create table curso(
    id int primary key auto_increment,
    nombre varchar(20),
    precio decimal,
    fecha varchar(20),
    info varchar(100),
    imagen varchar(200),
    favorito boolean

);

create table reserva(
    id_curso int,
    id_usuario int,
    nombre varchar(100),
    fechaReserva datetime,
    comentarios varchar(255),
    estado varchar(100),
    primary key(id_curso, id_usuario),
    constraint fk_usario_favorito foreign key (id_curso) references curso(id),
    constraint fk_curso_favorito foreign key (id_usuario) references usuario(id)
);

create table favorito(
    id_usuario int,
    id_curso int,
    nombre varchar(20),
    precio decimal,
    fecha varchar(20),
    info varchar(100),
    imagen varchar(200),
    primary key(id_curso, id_usuario),
    constraint fk_usario_favorito foreign key (id_curso) references curso(id),
    constraint fk_curso_favorito foreign key (id_usuario) references usuario(id)
);
select usuario.id as id_usuario, curso.id as id_curso, usuario.username, curso.nombre
from usuario
inner join favorito on usuario.id = favorito.id_usuario
inner join curso on favorito.id_curso = curso.id;