drop table if exists g01_rolxmenu;
drop table if exists g01_menu;
drop table if exists g01_direccion_servicio;
drop table if exists g01_telefono;
drop table if exists g01_historial_transaccion;
drop table if exists g01_servicioxusuario;
drop table if exists g01_servicioxpuntoconexion;
drop table if exists g01_punto_conexion;
drop table if exists g01_servicio;
drop table if exists g01_renglon_factura;
drop table if exists g01_factura;
drop table if exists g01_usuario;
drop table if exists g01_rol;
drop table if exists g01_ciudad;

create table g01_ciudad(
	id_ciudad integer not null,
	nombre varchar(30) not null,
	codigo_postal integer not null,
	constraint g01_pk_ciudad primary key(id_ciudad),
	constraint g01_ck_codigononegativo check(codigo_postal > 0)
);

create table g01_menu(
	id_menu integer not null,
	opcion varchar(40) not null,
	constraint g01_pk_menu primary key(id_menu)
);

create table g01_rol(
	id_rol varchar(3) not null,
	grupo varchar(15) not null,
	constraint g01_pk_rol primary key(id_rol)
);

create table g01_rolxmenu(
	id_rol varchar(3) not null,
	id_menu integer not null,
	constraint g01_pk_rolxmenu primary key(id_rol,id_menu),
	constraint g01_fk_rolxmenu_rol foreign key(id_rol) references g01_rol(id_rol) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint g01_fk_rolxmenu_menu foreign key(id_menu) references g01_menu(id_menu) ON DELETE CASCADE ON UPDATE CASCADE
);

create table g01_usuario(
	id_usuario integer not null,
	nombre varchar(20) not null,
	apellido varchar(20) not null,
	direccion_real varchar(30) not null,
	fecha_nacimiento date not null,
	dni numeric(8,0) not null,
	saldo numeric(9,2),
	id_ciudad integer not null,
	id_rol varchar(3),
	constraint g01_pk_usuario primary key(id_usuario),
	constraint g01_fk_usuario_ciudad foreign key(id_ciudad) references g01_ciudad(id_ciudad) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint g01_fk_usuario_rol foreign key(id_rol) references g01_rol(id_rol) ON DELETE SET NULL ON UPDATE CASCADE,
	constraint g01_ck_saldononegativo check(saldo >= 0),
	constraint g01_ck_dninonegativo check(dni > 0),
	constraint g01_ck_cumplenocien check(((date '01-01-2016' - fecha_nacimiento)/365)<100)
);

create table g01_telefono(
	id_telefono integer not null,
	id_usuario integer not null,
	tipo varchar(10) not null,
	caracteristica numeric(5,0) not null,
	numero numeric(10,0) not null,
	constraint g01_pk_telefono primary key(id_telefono),
	constraint g01_fk_telefono_usuario foreign key(id_usuario) references g01_usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint g01_ck_caracteristicanonegativo check(caracteristica > 0),
	constraint g01_ck_numerononegativo check(numero > 0),
	constraint g01_ck_tipotelefono check(tipo in ('Fijo','Celular'))
);

create table g01_direccion_servicio(
	id_direccion_servicio integer not null,
	id_usuario integer not null,
	direccion varchar(30) not null,
	id_ciudad integer not null,
	constraint g01_pk_direccion_servicio primary key(id_direccion_servicio),
	constraint g01_fk_direccion_servicio_ciudad foreign key(id_ciudad) references g01_ciudad(id_ciudad) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint g01_fk_direccion_servicio_usuario foreign key(id_usuario) references g01_usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

create table g01_historial_transaccion(
	id_historial integer not null,
	id_usuario integer not null,
	tipo_transaccion varchar(10) not null,
	fecha date not null,
	monto numeric(9,2) not null,
	constraint g01_pk_historial_transaccion primary key(id_historial),
	constraint g01_fk_historial_transaccion foreign key(id_usuario) references g01_usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint g01_ck_tipotransaccion check(tipo_transaccion in ('Paga','Debe'))
);

create table g01_punto_conexion(
	mac varchar(17) not null,
	modelo varchar(8) not null,
	marca varchar(16) not null,
	modo_conexion varchar(8) not null,
	id_usuario integer not null,
	direccion_equipo varchar(30) not null,
	constraint g01_pk_punto_conexion primary key(mac),
	constraint g01_fk_punto_conexion_usuario foreign key(id_usuario) references g01_usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint g01_ck_modoconexion check(modo_conexion in ('PPTP','DHCP','IP FIJA'))
);

create table g01_servicio(
	id_servicio integer not null,
	nombre varchar(40) not null,
	precio_base numeric(9,2) not null,
	tipo char(1) not null,
	descripcion varchar(100) not null,
	constraint g01_pk_servicio primary key(id_servicio),
	constraint g01_ck_preciononegativo check(precio_base > 0),
	constraint g01_ck_tiposervicio check(tipo in ('D','P'))
);

create table g01_servicioxusuario(
	id_servicio integer not null,
	id_usuario integer not null,
	fecha_realizado date not null,
	constraint g01_pk_servicioxusuario primary key(id_servicio,id_usuario),
	constraint g01_fk_servicioxusuario_servicio foreign key(id_servicio) references g01_servicio(id_servicio) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint g01_fk_servicioxusuario_usuario foreign key(id_usuario) references g01_usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

create table g01_servicioxpuntoconexion(
	id_servicio integer not null,
	mac varchar(17) not null,
	constraint g01_pk_servicioxpuntoconexion primary key(id_servicio,mac),
	constraint g01_fk_servicioxpuntoconexion_servicio foreign key(id_servicio) references g01_servicio(id_servicio) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint g01_fk_servicioxpuntoconexion_puntoconexion foreign key(mac) references g01_punto_conexion(mac) ON DELETE CASCADE ON UPDATE CASCADE
);

create table g01_factura(
	id_factura integer not null,
	id_usuario integer,
	direccion varchar(30) not null,
	monto_total numeric(9,2) not null,
	tipo char(1) not null,
	fecha_emision date not null,
	fecha_vencimiento date not null,
	constraint g01_pk_factura primary key(id_factura,tipo),
	constraint g01_fk_factura_usuario foreign key(id_usuario) references g01_usuario(id_usuario) ON DELETE SET NULL ON UPDATE CASCADE,
	constraint g01_ck_totalnonegativo check(monto_total > 0),
	constraint g01_ck_tipofactura check(tipo in ('A','B'))
);

create table g01_renglon_factura(
	nro_renglon numeric(2) not null,
	id_factura integer not null,
	tipo char(1) not null,
	descripcion varchar(20) not null,
	importe numeric(9,2) not null,
	direccion_servicio varchar(30) not null,
	constraint g01_pk_renglon_factura primary key(nro_renglon,id_factura,tipo),
	constraint g01_fk_renglon_factura_factura foreign key(id_factura,tipo) references g01_factura(id_factura,tipo) ON DELETE CASCADE ON UPDATE CASCADE,
	constraint g01_ck_importenonegativo check(importe > 0),
	constraint g01_ck_tipoenrenglon check(tipo in ('A','B')),
	constraint g01_ck_renglonnonegativo check(nro_renglon > 0)
);
