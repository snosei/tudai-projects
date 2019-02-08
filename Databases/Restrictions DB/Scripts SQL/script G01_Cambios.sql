--Realizado una sobreescritura de los nombres en G01_Creacion.sql, colocandoles nuestro nombre de grupo a las tablas, vistas y restricciones

--MODIFICACION DE COLUMNAS

--Agregamos el campo password en la tabla Persona para que puedan tener una forma para ingresar en la web
ALTER TABLE GR01_Persona ADD COLUMN password varchar(100) NOT NULL;

--Agregamos el campo tipo_pago en la tabla Comprobante para establecer la funcionalidad de credito o debito
ALTER TABLE GR01_Comprobante ADD COLUMN tipo_pago varchar(50) NOT NULL;

--Agregamos el campo importe_total en la tabla Comprobante para llevar el total del mismo
ALTER TABLE GR01_Comprobante ADD COLUMN importe_total numeric(18,5) NOT NULL;

--FIN MODIFICACION


--RESTRICCIONES

--1) Hay dos tipos de personas en el sistema los clientes cuyo rol es “C” y los empleados cuyo rol es “E”.
--Restriccion de tipo Atributo sobre el atributo nombre en la tabla gr01_rol

ALTER TABLE gr01_rol add constraint ck_gr01_nombre check (nombre in ('C','E'));

--ERROR:  new row for relation "gr01_rol" violates check constraint "ck_gr01_nombre"

--2) Los turnos están asignados a personas cuyo rol es empleado “E”.

--TRIGGER
create or replace function trfn_gr01_verificarturnoempleado_turno()
returns trigger as $$
declare
	cant integer;
begin
	select count(*) into cant
	from gr01_turno t
	join gr01_persona p on (t.id_persona = p.id_persona)
	join gr01_rol r on (p.id_rol = r.id_rol)
	where (r.nombre like 'E') and (t.id_persona = new.id_persona);

	if cant=0 and not exists(
		select 1
		from gr01_persona p
		join gr01_rol r on (p.id_rol = r.id_rol)
		where (r.nombre like 'E') and (p.id_persona = new.id_persona)
	) then
		RAISE EXCEPTION 'Turno no debe estar asignado a una persona que no sea empleado';
	end if;

	return new;
end$$
language 'plpgsql';

create trigger tr_gr01_turno_turnoempleado
before insert or update of id_persona on gr01_turno
for each row execute procedure trfn_gr01_verificarturnoempleado_turno();

create or replace function trfn_gr01_verificarturnoempleado_persona()
returns trigger as $$
declare
	cant integer;
begin
	if(old.id_persona != new.id_persona or old.id_rol != new.id_rol) then
		select count(*) into cant
		from gr01_turno t
		join gr01_persona p on (t.id_persona = p.id_persona)
		join gr01_rol r on (p.id_rol = r.id_rol)
		where (p.id_persona = old.id_persona) and (p.id_rol = old.id_rol);

		if cant>0 and not exists(
			select 1
			from gr01_turno t
			join gr01_persona p on (t.id_persona = p.id_persona)
			join gr01_rol r on (p.id_rol = r.id_rol)
			where (r.nombre like 'E') and (p.id_persona = new.id_persona) and (p.id_rol = new.id_rol)
		) then
			RAISE EXCEPTION 'Turno no debe estar asignado a una persona que no sea empleado';
		end if;
end if;
	return new;
end$$
language 'plpgsql';

create trigger tr_gr01_persona_turnoempleado
before update of id_persona,id_rol on gr01_persona
for each row execute procedure trfn_gr01_verificarturnoempleado_persona();

create or replace function trfn_gr01_verificarturnoempleado_rol()
returns trigger as $$
declare
	cant integer;
begin
	if(old.id_rol != new.id_rol or old.nombre not like new.nombre) then
		select count(*) into cant
		from gr01_turno t
		join gr01_persona p on (t.id_persona = p.id_persona)
		join gr01_rol r on (p.id_rol = r.id_rol)
		where (r.id_rol = old.id_rol) and (r.nombre like old.nombre);

		if cant>0 and not exists(
			select 1
			from gr01_turno t
			join gr01_persona p on (t.id_persona = p.id_persona)
			join gr01_rol r on (p.id_rol = r.id_rol)
			where (r.nombre like new.nombre) and (r.id_rol = new.id_rol)) then
			RAISE EXCEPTION 'Turno no debe estar asignado a una persona que no sea empleado';
		end if;
		end if;
	return new;
end$$
language 'plpgsql';

create trigger tr_gr01_rol_turnoempleado
before update on gr01_rol
for each row execute procedure trfn_gr01_verificarturnoempleado_rol();
--ERROR:  Turno no debe estar asignado a una persona que no sea empleado

--3) Se debe controlar que un comprobante pertenezca o bien a un turno (de un
--empleado) o a un cliente, no se debe dar la situación que ambos estén ausentes o ambos presentes.
--Restriccion de tipo Tupla sobre los atributos id_persona e id_turno en la tabla gr01_comprobante
ALTER TABLE gr01_comprobante add constraint ck_gr01_turnoopersona check ((id_persona is not null and id_turno is null)or(id_persona is null and id_turno is not null));
--ERROR:  new row for relation "gr01_comprobante" violates check constraint "ck_gr01_turnoopersona"

--Fin restricciones

--Servicios
--1) Se debe mantener actualizado el saldo de la cuenta corriente de cada cliente cada vez que
--se genera un comprobante ya sea de débito (suma al saldo, ej. factura) o de crédito (resta al saldo, ej pago).
create or replace function trfn_gr01_actualizarsaldo()
returns trigger as $$
declare
begin

	if(TG_OP = 'INSERT') then
		if(new.tipo_pago = 'Debito') then
			update gr01_persona set saldo = saldo + new.importe_total where id_persona = new.id_persona;
		else
			update gr01_persona set saldo = saldo - new.importe_total where id_persona = new.id_persona;
		end if;
	end if;

	if(TG_OP = 'UPDATE') then
		if(new.tipo_pago = 'Debito') then
			update gr01_persona set saldo = saldo - old.importe_total where id_persona = old.id_persona;
			update gr01_persona set saldo = saldo + new.importe_total where id_persona = new.id_persona;
		else
			update gr01_persona set saldo = saldo + old.importe_total where id_persona = old.id_persona;
			update gr01_persona set saldo = saldo - new.importe_total where id_persona = new.id_persona;
		end if;
	end if;

	if(TG_OP = 'DELETE') then
		if(new.tipo_pago = 'Debito') then
			update gr01_persona set saldo = saldo - old.importe_total where id_persona = old.id_persona;
		else
			update gr01_persona set saldo = saldo + old.importe_total where id_persona = old.id_persona;
		end if;
	end if;

	return new;
end$$
language 'plpgsql';

create trigger tr_gr01_comprobante_saldoactualizado
after insert or update or delete on gr01_comprobante
for each row execute procedure trfn_gr01_actualizarsaldo();

--2) Se debe mantener actualizado el importe total de cada comprobante en función de la
--suma de los débitos o créditos que registre cada línea
create or replace function trfn_gr01_actualizarimporte()
returns trigger as $$
declare
begin

	if(TG_OP = 'INSERT') then
		update gr01_comprobante set importe_total = importe_total + new.importe where (id_comp = new.id_comp) and (id_tcomp = new.id_tcomp);
	end if;

	if(TG_OP = 'UPDATE') then
		update gr01_comprobante set importe_total = importe_total - old.importe where (id_comp = old.id_comp) and (id_tcomp = old.id_tcomp);
		update gr01_comprobante set importe_total = importe_total + new.importe where (id_comp = new.id_comp) and (id_tcomp = new.id_tcomp);
	end if;

	if(TG_OP = 'DELETE') then
		update gr01_comprobante set importe_total = importe_total - old.importe where (id_comp = old.id_comp) and (id_tcomp = old.id_tcomp);
	end if;

	return new;
end$$
language 'plpgsql';

create trigger tr_gr01_lineacomprobante_importeactualizado
after insert or update or delete on GR01_LineaComprobante
for each row execute procedure trfn_gr01_actualizarimporte();

--FUNCIONES

-- Creacion de la funcion
CREATE OR REPLACE FUNCTION ​FN_GR01_buscar_pass(correo varchar(120))
RETURNS varchar(100) AS
$BODY$
DECLARE
	pass varchar(100);
BEGIN
	select p.password into pass
	from gr01_persona p
	join gr01_mail m on (p.id_persona = m.id_persona)
	where m.mail like correo;

	RETURN pass;
END;
$BODY$
LANGUAGE plpgsql;

-- ejecutar la función select ​FN_GR01_buscar_pass(mail);

-- Creacion de la funcion
CREATE OR REPLACE FUNCTION FN_GR01_verificar_permiso(correo varchar(120),opcion integer)
RETURNS boolean AS
$BODY$
DECLARE
	resp boolean;
BEGIN

	select true into resp
	from gr01_mail m
	join gr01_persona p on(m.id_persona = p.id_persona)
	join gr01_rolmenu r on (p.id_rol = r.id_rol)
	where m.mail like correo and r.id_menu = opcion;

	IF (resp is null) THEN
		RAISE Exception '<h1>No tiene permisos para acceder a esta funcionalidad</h1>';
	end if;

	 RETURN resp;

END;
$BODY$
LANGUAGE plpgsql;

-- ejecutar la función select FN_GR01_verificar_permiso(mail,idmenu);

--FIN FUNCIONES

--INICIO INSERT'S

--Insercion en la tabla Ciudad
insert into gr01_ciudad values(1,'Tandil');
insert into gr01_ciudad values(2,'Necochea');
insert into gr01_ciudad values(3,'Bolivar');
insert into gr01_ciudad values(4,'Olavarria');
insert into gr01_ciudad values(5,'Cordoba');

--Insercion en la tabla Barrio
insert into gr01_barrio values(1,'Diamante',3);
insert into gr01_barrio values(2,'Las Flores',3);
insert into gr01_barrio values(3,'La movediza',1);
insert into gr01_barrio values(4,'Metalurgica',1);
insert into gr01_barrio values(5,'San Cayetano',1);

--Insercion en la tabla Menu
insert into gr01_menu values(1,'Ver Comprobantes');
insert into gr01_menu values(2,'Generar Comprobante');
insert into gr01_menu values(3,'Eliminar Comprobante');
insert into gr01_menu values(4,'Eliminar Persona');

--Insercion en la tabla Rol
insert into gr01_rol values(1,'C');
insert into gr01_rol values(2,'E');

--Insercion en la tabla RolMenu
insert into gr01_rolmenu values(1,1);
insert into gr01_rolmenu values(2,2);

--Insercion en la tabla Persona
insert into gr01_persona values(1,'Cliente','DNI','36.906.146','Santiago','Nosei','08-18-1992',0,NULL,1,NULL,FALSE,'$2y$10$VgTsMbRWxSHXCVYiqTzVs.q6uFPHlGkU6nq7TEDF.YGKAnc/RQdiW');
insert into gr01_persona values(2,'Cliente','DNI','38.165.672','Victor','Gonzalez','05-06-1993',0,NULL,1,NULL,FALSE,'$2y$10$EATIOVB5a4rpI63/Dw7PJecvDpI.hHyVjbsXWKss7uIlnpEFs492C');
insert into gr01_persona values(3,'Cliente','DNI','10.100.100','Bulbasaur','Bob','01-01-1980',0,NULL,1,NULL,FALSE,'$2y$10$2MNqACh7HU36oyWdtVqyPuF4t0F5Eib3IAnb1KDFyNwyxPgA4l52q');
insert into gr01_persona values(4,'Empleado','DNI','11.111.111','Arthur','Freezer','11-11-1989',NULL,NULL,2,'20-11111111-2',FALSE,'$2y$10$.ovlvE7j8uVU24yunX/09.oDDQz7qYMzDdSbPPVEJSwvJc/B2m9/.');
insert into gr01_persona values(5,'Admin','DNI','22.222.222','Alcachofa','Gonzalez','01-01-1893',NULL,NULL,NULL,NULL,FALSE,'$2y$10$hZsMZQ0I1GFOdASYO/bipOeizawabGeCuvI6MW158LfRvmaGVbPXC');

--Insercion en la tabla Mail
insert into gr01_mail values(1,'snosei92@gmail.com','Gmail');
insert into gr01_mail values(1,'soushimayuy@gmail.com','Gmail');
insert into gr01_mail values(2,'g.victor.juan@gmail.com','Gmail');
insert into gr01_mail values(3,'bolbybolby@live.com','Live');
insert into gr01_mail values(4,'arthur@yahoo.com.ar','Yahoo');
insert into gr01_mail values(5,'admin@gmail.com','Gmail');

--Insercion en la tabla Telefono
insert into gr01_telefono values(1,249,4367281,'Fijo');
insert into gr01_telefono values(2,249,4367280,'Fijo');
insert into gr01_telefono values(3,249,4367282,'Fijo');
insert into gr01_telefono values(4,249,4367279,'Fijo');
insert into gr01_telefono values(5,249,4367278,'Fijo');

--Insercion en la tabla Direccion
insert into gr01_direccion values(1,1,'Las Heras',378,NULL,NULL,'Casa',1);
insert into gr01_direccion values(1,2,'Velez Sarsfierld',73,NULL,NULL,'Casa',2);
insert into gr01_direccion values(1,3,'Alberdi',234,NULL,NULL,'Casa',3);
insert into gr01_direccion values(1,4,'Necochea',135,NULL,NULL,'Casa',4);
insert into gr01_direccion values(1,5,'Colombia',2290,NULL,NULL,'Casa',5);

--Insercion en la tabla Categoria
insert into gr01_categoria values(1,'Internet');
insert into gr01_categoria values(2,'Mantenimiento');

--Insercion en la tabla Servicio
insert into gr01_servicio values(1,'Internet 30Mb',TRUE,300.000,NULL,NULL,TRUE,1);
insert into gr01_servicio values(2,'Mantenimiento',FALSE,150.000,NULL,NULL,TRUE,2);

--Insercion en la tabla Equipo
insert into gr01_equipo values(1,1,'TP','00:00:00:00:00:01',NULL,NULL,1);
insert into gr01_equipo values(2,2,'RD','00:00:00:00:00:02',NULL,NULL,1);
insert into gr01_equipo values(3,3,'FK','00:00:00:00:00:03',NULL,NULL,1);

--Insercion en la tabla TipoComprobante
insert into gr01_tipocomprobante values(1,'Periodico','Periodico');
insert into gr01_tipocomprobante values(2,'No Periodico','No Periodico');

--Insercion en la tabla Lugar
insert into gr01_lugar values(1,'Tandil');
insert into gr01_lugar values(2,'Necochea');

--Insercion en la tabla Turno
insert into gr01_turno values(1,1,4,'01-25-2016',NULL,300.000,NULL);
insert into gr01_turno values(2,2,4,'02-13-2016','02-18-2016',150.000,200.000);

--Insercion en la tabla Comprobante
insert into gr01_comprobante values(1,1,'02-29-2016',1,'Primer Comprobante','Falta pagar','03-15-2016',1,null,'Debito',0.00000);
insert into gr01_comprobante values(1,2,'02-29-2016',2,'Comprobante Instalacion','Pagado',null,1,null,'Credito',0.00000);

--Insercion en la tabla LineaComprobante
insert into gr01_lineacomprobante values(1,1,1,'Servicio wifi 30Mb',1,300.00000);
insert into gr01_lineacomprobante values(2,1,1,'Impuesto ley 24637',1,100.00000);
insert into gr01_lineacomprobante values(1,1,2,'Instalacion modem Wifi',1,150.00000);

--FIN INSERT'S
