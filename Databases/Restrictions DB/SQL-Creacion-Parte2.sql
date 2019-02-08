-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-06-06 18:27:27.986

-- tables
-- Table: Barrio
CREATE TABLE Barrio (
    id_barrio int  NOT NULL,
    nombre varchar(20)  NOT NULL,
    id_ciudad int  NOT NULL,
    CONSTRAINT Barrio_pk PRIMARY KEY  (id_barrio)
);

-- Table: Categoria
CREATE TABLE Categoria (
    id_cat int  NOT NULL,
    nombre int  NOT NULL,
    CONSTRAINT Categoria_pk PRIMARY KEY  (id_cat)
);

-- Table: Ciudad
CREATE TABLE Ciudad (
    id_ciudad int  NOT NULL,
    nombre varchar(80)  NOT NULL,
    CONSTRAINT Ciudad_pk PRIMARY KEY  (id_ciudad)
);

-- Table: Comprobante
CREATE TABLE Comprobante (
    id_comp bigint  NOT NULL,
    id_tcomp int  NOT NULL,
    fecha timestamp  NULL,
    id_lugar int  NOT NULL,
    comentario varchar(2048)  NOT NULL,
    estado varchar(20)  NULL,
    fecha_vencimiento timestamp  NULL,
    id_persona int  NULL,
    id_turno int  NULL,
    CONSTRAINT pk_comprobante PRIMARY KEY  (id_comp,id_tcomp)
);

-- Table: Direccion
CREATE TABLE Direccion (
    id_direccion int  NOT NULL,
    id_persona int  NOT NULL,
    calle varchar(50)  NOT NULL,
    numero int  NOT NULL,
    piso int  NULL,
    depto varchar(50)  NULL,
    tipo varchar(20)  NOT NULL,
    id_barrio int  NOT NULL,
    CONSTRAINT Direccion_pk PRIMARY KEY  (id_direccion,id_persona)
);

-- Table: Equipo
CREATE TABLE Equipo (
    id_persona int  NULL,
    id_equipo int  NOT NULL,
    nombre varchar(80)  NOT NULL,
    MAC varchar(20)  NULL,
    IP varchar(20)  NULL,
    AP varchar(20)  NULL,
    id_servicio int  NOT NULL,
    CONSTRAINT Equipo_pk PRIMARY KEY  (id_equipo)
);

-- Table: LineaComprobante
CREATE TABLE LineaComprobante (
    nro_linea int  NOT NULL,
    id_comp bigint  NOT NULL,
    id_tcomp int  NOT NULL,
    descripcion varchar(80)  NOT NULL,
    cantidad int  NOT NULL,
    importe numeric(18,5)  NOT NULL,
    CONSTRAINT pk_lineacomp PRIMARY KEY  (nro_linea,id_comp,id_tcomp)
);

-- Table: Lugar
CREATE TABLE Lugar (
    id_lugar int  NOT NULL,
    nombre varchar(80)  NULL,
    CONSTRAINT pk_lugar PRIMARY KEY  (id_lugar)
);

-- Table: Mail
CREATE TABLE Mail (
    id_persona int  NOT NULL,
    mail varchar(120)  NOT NULL,
    tipo varchar(20)  NOT NULL,
    CONSTRAINT Mail_pk PRIMARY KEY  (id_persona,mail)
);

-- Table: Menu
CREATE TABLE Menu (
    id_menu int  NOT NULL,
    nombre int  NOT NULL,
    CONSTRAINT Menu_pk PRIMARY KEY  (id_menu)
);

-- Table: Persona
CREATE TABLE Persona (
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
    CONSTRAINT pk_cliente PRIMARY KEY  (id_persona)
);

-- Table: Rol
CREATE TABLE Rol (
    id_rol int  NOT NULL,
    nombre varchar(50)  NOT NULL,
    CONSTRAINT Rol_pk PRIMARY KEY  (id_rol)
);

-- Table: RolMenu
CREATE TABLE RolMenu (
    id_rol int  NOT NULL,
    id_menu int  NOT NULL,
    CONSTRAINT RolMenu_pk PRIMARY KEY  (id_rol,id_menu)
);

-- Table: Servicio
CREATE TABLE Servicio (
    id_servicio int  NOT NULL,
    nombre varchar(80)  NOT NULL,
    periodico boolean  NOT NULL,
    costo numeric(18,3)  NOT NULL,
    intervalo int  NULL,
    tipo_intervalo varchar(20) CHECK (tipo_intervalo in ('semana','quincena','mes','bimestre')),
    activo boolean  NOT NULL DEFAULT true,
    id_cat int  NOT NULL,
    CONSTRAINT pk_servicio PRIMARY KEY  (id_servicio)
);

-- Table: Telefono
CREATE TABLE Telefono (
    id_persona int  NOT NULL,
    carac int  NOT NULL,
    numero int  NOT NULL,
    tipo varchar(20)  NOT NULL,
    CONSTRAINT Telefono_pk PRIMARY KEY  (id_persona,carac,numero)
);

-- Table: TipoComprobante
CREATE TABLE TipoComprobante (
    id_tcomp int  NOT NULL,
    nombre varchar(30)  NOT NULL,
    tipo varchar(80)  NOT NULL,
    CONSTRAINT pk_tipo_comprobante PRIMARY KEY  (id_tcomp)
);

-- Table: Turno
CREATE TABLE Turno (
    id_turno int  NOT NULL,
    id_lugar int  NOT NULL,
    id_persona int  NOT NULL,
    desde timestamp  NOT NULL,
    hasta timestamp  NULL,
    dinero_inicio numeric(18,3)  NOT NULL,
    dinero_fin numeric(18,3)  NULL,
    CONSTRAINT Turno_pk PRIMARY KEY  (id_turno)
);

-- views
-- View: v_menurol
CREATE view v_menurol as
select m.id_menu, 
 m.nombre menu, 
    r.id_rol, 
    r.nombre rol, 
 coalesce((select count(*) from RolMenu rm where r.id_rol = rm.id_rol and m.id_menu = rm.id_menu), 0) esta
from menu m, rol r
;


-- foreign keys
-- Reference: Equipo_Servicio (table: Equipo)
ALTER TABLE Equipo ADD CONSTRAINT fk_Equipo_Servicio
    FOREIGN KEY (id_servicio)
    REFERENCES Servicio (id_servicio);

-- Reference: fk_Barrio_Ciudad (table: Barrio)
ALTER TABLE Barrio ADD CONSTRAINT fk_Barrio_Ciudad
    FOREIGN KEY (id_ciudad)
    REFERENCES Ciudad (id_ciudad);

-- Reference: fk_Direccion_Barrio (table: Direccion)
ALTER TABLE Direccion ADD CONSTRAINT fk_Direccion_Barrio
    FOREIGN KEY (id_barrio)
    REFERENCES Barrio (id_barrio);

-- Reference: fk_Persona_Rol (table: Persona)
ALTER TABLE Persona ADD CONSTRAINT fk_Persona_Rol
    FOREIGN KEY (id_rol)
    REFERENCES Rol (id_rol);

-- Reference: fk_RolMenu_Menu (table: RolMenu)
ALTER TABLE RolMenu ADD CONSTRAINT fk_RolMenu_Menu
    FOREIGN KEY (id_menu)
    REFERENCES Menu (id_menu);

-- Reference: fk_RolMenu_Rol (table: RolMenu)
ALTER TABLE RolMenu ADD CONSTRAINT fk_RolMenu_Rol
    FOREIGN KEY (id_rol)
    REFERENCES Rol (id_rol);

-- Reference: fk_Turno_Lugar (table: Turno)
ALTER TABLE Turno ADD CONSTRAINT fk_Turno_Lugar
    FOREIGN KEY (id_lugar)
    REFERENCES Lugar (id_lugar);

-- Reference: fk_comprobante_lugar (table: Comprobante)
ALTER TABLE Comprobante ADD CONSTRAINT fk_comprobante_lugar
    FOREIGN KEY (id_lugar)
    REFERENCES Lugar (id_lugar);

-- Reference: fk_comprobante_persona (table: Comprobante)
ALTER TABLE Comprobante ADD CONSTRAINT fk_comprobante_persona
    FOREIGN KEY (id_persona)
    REFERENCES Persona (id_persona);

-- Reference: fk_comprobante_tipocomprobante (table: Comprobante)
ALTER TABLE Comprobante ADD CONSTRAINT fk_comprobante_tipocomprobante
    FOREIGN KEY (id_tcomp)
    REFERENCES TipoComprobante (id_tcomp);

-- Reference: fk_direccion (table: Direccion)
ALTER TABLE Direccion ADD CONSTRAINT fk_direccion
    FOREIGN KEY (id_persona)
    REFERENCES Persona (id_persona);

-- Reference: fk_equipo_persona (table: Equipo)
ALTER TABLE Equipo ADD CONSTRAINT fk_equipo_persona
    FOREIGN KEY (id_persona)
    REFERENCES Persona (id_persona);

-- Reference: fk_lc_comprobante (table: LineaComprobante)
ALTER TABLE LineaComprobante ADD CONSTRAINT fk_lc_comprobante
    FOREIGN KEY (id_comp,id_tcomp)
    REFERENCES Comprobante (id_comp,id_tcomp);

-- Reference: fk_mail (table: Mail)
ALTER TABLE Mail ADD CONSTRAINT fk_mail
    FOREIGN KEY (id_persona)
    REFERENCES Persona (id_persona);

-- Reference: fk_servicio_categoria (table: Servicio)
ALTER TABLE Servicio ADD CONSTRAINT fk_servicio_categoria
    FOREIGN KEY (id_cat)
    REFERENCES Categoria (id_cat);

-- Reference: fk_telefono (table: Telefono)
ALTER TABLE Telefono ADD CONSTRAINT fk_telefono
    FOREIGN KEY (id_persona)
    REFERENCES Persona (id_persona);

-- Reference: fk_turno_persona (table: Turno)
ALTER TABLE Turno ADD CONSTRAINT fk_turno_persona
    FOREIGN KEY (id_persona)
    REFERENCES Persona (id_persona);

-- Reference: kf_comprobante_turno (table: Comprobante)
ALTER TABLE Comprobante ADD CONSTRAINT kf_comprobante_turno
    FOREIGN KEY (id_turno)
    REFERENCES Turno (id_turno);

-- End of file.
