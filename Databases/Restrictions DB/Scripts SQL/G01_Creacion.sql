-- tables
CREATE TABLE GR01_Barrio (
    id_barrio int  NOT NULL,
    nombre varchar(20)  NOT NULL,
    id_ciudad int  NOT NULL,
    CONSTRAINT PK_GR01_Barrio PRIMARY KEY  (id_barrio)
);

CREATE TABLE GR01_Categoria (
    id_cat int  NOT NULL,
    nombre varchar(50)  NOT NULL,
    CONSTRAINT PK_GR01_Categoria PRIMARY KEY  (id_cat)
);

CREATE TABLE GR01_Ciudad (
    id_ciudad int  NOT NULL,
    nombre varchar(80)  NOT NULL,
    CONSTRAINT PK_GR01_Ciudad PRIMARY KEY  (id_ciudad)
);

CREATE TABLE GR01_Comprobante (
    id_comp bigint  NOT NULL,
    id_tcomp int  NOT NULL,
    fecha timestamp  NULL,
    id_lugar int  NOT NULL,
    comentario varchar(2048)  NOT NULL,
    estado varchar(20)  NULL,
    fecha_vencimiento timestamp  NULL,
    id_persona int  NULL,
    id_turno int  NULL,
    CONSTRAINT PK_GR01_Comprobante PRIMARY KEY  (id_comp,id_tcomp)
);

CREATE TABLE GR01_Direccion (
    id_direccion int  NOT NULL,
    id_persona int  NOT NULL,
    calle varchar(50)  NOT NULL,
    numero int  NOT NULL,
    piso int  NULL,
    depto varchar(50)  NULL,
    tipo varchar(20)  NOT NULL,
    id_barrio int  NOT NULL,
    CONSTRAINT PK_GR01_Direccion PRIMARY KEY  (id_direccion,id_persona)
);

CREATE TABLE GR01_Equipo (
    id_persona int  NULL,
    id_equipo int  NOT NULL,
    nombre varchar(80)  NOT NULL,
    MAC varchar(20)  NULL,
    IP varchar(20)  NULL,
    AP varchar(20)  NULL,
    id_servicio int  NOT NULL,
    CONSTRAINT PK_GR01_Equipo PRIMARY KEY  (id_equipo)
);

CREATE TABLE GR01_LineaComprobante (
    nro_linea int  NOT NULL,
    id_comp bigint  NOT NULL,
    id_tcomp int  NOT NULL,
    descripcion varchar(80)  NOT NULL,
    cantidad int  NOT NULL,
    importe numeric(18,5)  NOT NULL,
    CONSTRAINT PK_GR01_LineaComprobante PRIMARY KEY  (nro_linea,id_comp,id_tcomp)
);

CREATE TABLE GR01_Lugar (
    id_lugar int  NOT NULL,
    nombre varchar(80)  NULL,
    CONSTRAINT PK_GR01_Lugar PRIMARY KEY  (id_lugar)
);

CREATE TABLE GR01_Mail (
    id_persona int  NOT NULL,
    mail varchar(120)  NOT NULL,
    tipo varchar(20)  NOT NULL,
    CONSTRAINT PK_GR01_Mail PRIMARY KEY  (id_persona,mail)
);

CREATE TABLE GR01_Menu (
    id_menu int NOT NULL,
    nombre varchar(50) NOT NULL,
    CONSTRAINT PK_GR01_Menu PRIMARY KEY  (id_menu)
);

CREATE TABLE GR01_Persona (
    id_persona int  NOT NULL,
    tipo varchar(10)  NOT NULL,
    tipodoc varchar(10)  NOT NULL,
    nrodoc varchar(10)  NOT NULL,
    nombre varchar(40)  NOT NULL,
    apellido varchar(40)  NOT NULL,
    fecha_nacimiento timestamp  NOT NULL,
    saldo numeric(18,3)  NULL DEFAULT 0,
    fecha_baja timestamp  NULL,
    id_rol int  NULL,
    CUIT varchar(20)  NULL,
    activo boolean  NOT NULL,
    CONSTRAINT PK_GR01_cliente PRIMARY KEY  (id_persona)
);

CREATE TABLE GR01_Rol (
    id_rol int  NOT NULL,
    nombre varchar(50)  NOT NULL,
    CONSTRAINT PK_GR01_Rol PRIMARY KEY  (id_rol)
);

CREATE TABLE GR01_RolMenu (
    id_rol int  NOT NULL,
    id_menu int  NOT NULL,
    CONSTRAINT PK_GR01_RolMenu PRIMARY KEY  (id_rol,id_menu)
);

CREATE TABLE GR01_Servicio (
    id_servicio int  NOT NULL,
    nombre varchar(80)  NOT NULL,
    periodico boolean  NOT NULL,
    costo numeric(18,3)  NOT NULL,
    intervalo int  NULL,
    tipo_intervalo varchar(20) CHECK (tipo_intervalo in ('semana','quincena','mes','bimestre')),
    activo boolean  NOT NULL DEFAULT true,
    id_cat int  NOT NULL,
    CONSTRAINT PK_GR01_Servicio PRIMARY KEY  (id_servicio)
);

CREATE TABLE GR01_Telefono (
    id_persona int  NOT NULL,
    carac int  NOT NULL,
    numero int  NOT NULL,
    tipo varchar(20)  NOT NULL,
    CONSTRAINT PK_GR01_Telefono PRIMARY KEY  (id_persona,carac,numero)
);

CREATE TABLE GR01_TipoComprobante (
    id_tcomp int  NOT NULL,
    nombre varchar(30)  NOT NULL,
    tipo varchar(80)  NOT NULL,
    CONSTRAINT PK_GR01_Tipo_Comprobante PRIMARY KEY  (id_tcomp)
);

CREATE TABLE GR01_Turno (
    id_turno int  NOT NULL,
    id_lugar int  NOT NULL,
    id_persona int  NOT NULL,
    desde timestamp  NOT NULL,
    hasta timestamp  NULL,
    dinero_inicio numeric(18,3)  NOT NULL,
    dinero_fin numeric(18,3)  NULL,
    CONSTRAINT PK_GR01_Turno PRIMARY KEY  (id_turno)
);

-- views
CREATE view GR01_v_menurol as
select m.id_menu,
 m.nombre menu,
    r.id_rol,
    r.nombre rol,
 coalesce((select count(*) from GR01_RolMenu rm where r.id_rol = rm.id_rol and m.id_menu = rm.id_menu), 0) esta
from GR01_menu m, GR01_rol r
;


-- foreign keys
ALTER TABLE GR01_Equipo ADD CONSTRAINT FK_GR01_Equipo_Servicio
    FOREIGN KEY (id_servicio)
    REFERENCES GR01_Servicio (id_servicio);

ALTER TABLE GR01_Barrio ADD CONSTRAINT FK_GR01_Barrio_Ciudad
    FOREIGN KEY (id_ciudad)
    REFERENCES GR01_Ciudad (id_ciudad);

ALTER TABLE GR01_Direccion ADD CONSTRAINT FK_GR01_Direccion_Barrio
    FOREIGN KEY (id_barrio)
    REFERENCES GR01_Barrio (id_barrio);

ALTER TABLE GR01_Persona ADD CONSTRAINT FK_GR01_Persona_Rol
    FOREIGN KEY (id_rol)
    REFERENCES GR01_Rol (id_rol);

ALTER TABLE GR01_RolMenu ADD CONSTRAINT FK_GR01_RolMenu_Menu
    FOREIGN KEY (id_menu)
    REFERENCES GR01_Menu (id_menu);

ALTER TABLE GR01_RolMenu ADD CONSTRAINT FK_GR01_RolMenu_Rol
    FOREIGN KEY (id_rol)
    REFERENCES GR01_Rol (id_rol);

ALTER TABLE GR01_Turno ADD CONSTRAINT FK_GR01_Turno_Lugar
    FOREIGN KEY (id_lugar)
    REFERENCES GR01_Lugar (id_lugar);

ALTER TABLE GR01_Comprobante ADD CONSTRAINT FK_GR01_Comprobante_Lugar
    FOREIGN KEY (id_lugar)
    REFERENCES GR01_Lugar (id_lugar);

ALTER TABLE GR01_Comprobante ADD CONSTRAINT FK_GR01_Comprobante_Persona
    FOREIGN KEY (id_persona)
    REFERENCES GR01_Persona (id_persona);

ALTER TABLE GR01_Comprobante ADD CONSTRAINT FK_GR01_Comprobante_TipoComprobante
    FOREIGN KEY (id_tcomp)
    REFERENCES GR01_TipoComprobante (id_tcomp);

ALTER TABLE GR01_Direccion ADD CONSTRAINT FK_GR01_Direccion
    FOREIGN KEY (id_persona)
    REFERENCES GR01_Persona (id_persona);

ALTER TABLE GR01_Equipo ADD CONSTRAINT FK_GR01_Equipo_Persona
    FOREIGN KEY (id_persona)
    REFERENCES GR01_Persona (id_persona);

ALTER TABLE GR01_LineaComprobante ADD CONSTRAINT FK_GR01_LineaComprobante_Comprobante
    FOREIGN KEY (id_comp,id_tcomp)
    REFERENCES GR01_Comprobante (id_comp,id_tcomp);

ALTER TABLE GR01_Mail ADD CONSTRAINT FK_GR01_Mail
    FOREIGN KEY (id_persona)
    REFERENCES GR01_Persona (id_persona);

ALTER TABLE GR01_Servicio ADD CONSTRAINT FK_GR01_Servicio_Categoria
    FOREIGN KEY (id_cat)
    REFERENCES GR01_Categoria (id_cat);

ALTER TABLE GR01_Telefono ADD CONSTRAINT FK_GR01_Telefono
    FOREIGN KEY (id_persona)
    REFERENCES GR01_Persona (id_persona);

ALTER TABLE GR01_Turno ADD CONSTRAINT FK_GR01_Turno_Persona
    FOREIGN KEY (id_persona)
    REFERENCES GR01_Persona (id_persona);

ALTER TABLE GR01_Comprobante ADD CONSTRAINT FK_GR01_Comprobante_Turno
    FOREIGN KEY (id_turno)
    REFERENCES GR01_Turno (id_turno);

-- End of file.
