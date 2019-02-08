--Insercion en la tabla Ciudad
insert into g01_ciudad values(1,'Tandil');
insert into g01_ciudad values(2,'Necochea');
insert into g01_ciudad values(3,'Bolivar');
insert into g01_ciudad values(4,'Olavarria');
insert into g01_ciudad values(5,'Cordoba');

--Insercion en la tabla Menu
insert into g01_menu values(1,'Alta Usuario');
insert into g01_menu values(2,'Baja Usuario');
insert into g01_menu values(3,'Consultar Factura');
insert into g01_menu values(4,'Alta Servicio');
insert into g01_menu values(5,'Consultar Historial');

--Insercion en la tabla Rol
insert into g01_rol values('CL','Cliente');
insert into g01_rol values('AD','Administrador');
insert into g01_rol values('VE','Ventas');
insert into g01_rol values('MA','Marketing');
insert into g01_rol values('SO','Soporte');

--Insercion en la tabla RolXMenu
insert into g01_rolxmenu values('AD',1);
insert into g01_rolxmenu values('AD',2);
insert into g01_rolxmenu values('AD',3);
insert into g01_rolxmenu values('AD',4);
insert into g01_rolxmenu values('AD',5);
insert into g01_rolxmenu values('CL',3);
insert into g01_rolxmenu values('CL',5);
insert into g01_rolxmenu values('VE',4);

--Insercion en la tabla Usuario
insert into g01_usuario values(1,'Victor','Gonzalez','Calle falsa 123','05-19-1993',37085941,0,1,'CL');
insert into g01_usuario(id_usuario,nombre,apellido,direccion_real,fecha_nacimiento,dni,id_ciudad,id_rol) values(2,'Victor','Victory','Calle falsa 654','05-19-1975',30015941,1,'AD');
insert into g01_usuario values(3,'Lucia','Betelu','Calle falsa 2189','03-05-1993',37789125,0,1,'CL');
insert into g01_usuario values(4,'Santiago','Nosei','Calle falsa 871','08-18-1992',36035241,0,2,'CL');
insert into g01_usuario(id_usuario,nombre,apellido,direccion_real,fecha_nacimiento,dni,id_ciudad,id_rol) values(5,'Rogelio','Barazibal','Calle falsa 666','02-15-1932',17082941,5,'VE');

--Insercion en la tabla Telefono
insert into g01_telefono values(1,1,'Fijo',02494,541472);
insert into g01_telefono values(2,3,'Fijo',02494,541473);
insert into g01_telefono values(3,3,'Celular',02494,15541072);
insert into g01_telefono values(4,3,'Celular',02494,15541272);
insert into g01_telefono values(5,4,'Fijo',02262,541474);

--Insercion en la tabla Direccion Servicio
insert into g01_direccion_servicio values(1,1,'Calle falsa 123',1);
insert into g01_direccion_servicio values(2,3,'New calle falsa 2189',1);
insert into g01_direccion_servicio values(3,3,'Nueva calle falsa 2189',2);
insert into g01_direccion_servicio values(4,3,'Neo calle falsa 2189',3);
insert into g01_direccion_servicio values(5,4,'Gugure Machine 678',1);

--Insercion en la tabla Punto Conexion
insert into g01_punto_conexion values('00:00:00:00:00:01','v4','Sagemcom','IP FIJA',3,'New calle falsa 2189');
insert into g01_punto_conexion values('00:00:00:00:00:02','ah2','Gigabyte','PPTP',3,'Nueva calle falsa 2189');
insert into g01_punto_conexion values('00:00:00:00:00:03','s_2','Motorola','DHCP',3,'Neo calle falsa 2189');
insert into g01_punto_conexion values('00:00:00:00:00:04','fafa3','Exo','PPTP',1,'Calle falsa 123');
insert into g01_punto_conexion values('00:00:00:00:00:05','astek','Lenovo','DHCP',4,'Gugure Machine 678');

--Insercion en la tabla Historial Transaccion
insert into g01_historial_transaccion values(1,1,'Debe','03-05-2016',300);
insert into g01_historial_transaccion values(2,3,'Debe','08-21-2016',125);
insert into g01_historial_transaccion values(3,3,'Paga','08-21-2016',125);
insert into g01_historial_transaccion values(4,3,'Debe','10-05-2016',1800);
insert into g01_historial_transaccion values(5,3,'Paga','10-21-2016',1800);
insert into g01_historial_transaccion values(6,3,'Debe','10-27-2016',50);
insert into g01_historial_transaccion values(7,4,'Debe','01-02-2016',420);

--Insercion en la tabla Servicio
insert into g01_servicio values(1,'Velocidad 6 megas',200,'P','Internet con velocidad hasta 6 mbps');
insert into g01_servicio values(2,'Velocidad 12 megas',400,'P','Internet con velocidad hasta 12 mbps');
insert into g01_servicio values(3,'Velocidad 300 megas',850,'P','Internet con velocidad hasta 300 mbps');
insert into g01_servicio values(4,'Mantenimiento conexion',50,'D','Mantenimiento general de la conexion a internet');
insert into g01_servicio values(5,'Instalacion conexion',125,'D','Instalacion de la conexion a internet');

--Insercion en la tabla ServicioXUsuario
insert into g01_servicioxusuario values(1,1,'03-05-2016');
insert into g01_servicioxusuario values(3,3,'08-21-2016');
insert into g01_servicioxusuario values(4,3,'10-27-2016');
insert into g01_servicioxusuario values(5,3,'11-27-2016');
insert into g01_servicioxusuario values(2,4,'01-02-2016');

--Insercion en la tabla ServicioXPuntoconexion
insert into g01_servicioxpuntoconexion values(1,'00:00:00:00:00:04');
insert into g01_servicioxpuntoconexion values(3,'00:00:00:00:00:01');
insert into g01_servicioxpuntoconexion values(1,'00:00:00:00:00:02');
insert into g01_servicioxpuntoconexion values(2,'00:00:00:00:00:03');
insert into g01_servicioxpuntoconexion values(2,'00:00:00:00:00:05');

--Insercion en la tabla Factura
insert into g01_factura values(1,1,'Calle falsa 123',300,'A','03-05-2016','04-05-2016');
insert into g01_factura values(2,3,'Calle falsa 2189',125,'A','08-21-2016','08-21-2016');
insert into g01_factura values(3,3,'Calle falsa 2189',1800,'A','10-05-2016','11-05-2016');
insert into g01_factura values(4,3,'Calle falsa 2189',50,'A','10-27-2016','10-27-2016');
insert into g01_factura values(5,4,'Calle falsa 871',420,'A','01-02-2016','02-02-2016');
insert into g01_factura values(6,3,'Calle falsa 2189',50,'A','11-27-2016','11-27-2016');
insert into g01_factura values(7,1,'Calle falsa 123',50,'A','03-05-2016','03-05-2016');
insert into g01_factura values(8,4,'Calle falsa 871',50,'A','01-02-2016','01-02-2016');

--Insercion en la tabla Renglon Factura
insert into g01_renglon_factura values(1,1,'A','Velocidad 6 megas',200,'Calle falsa 123');
insert into g01_renglon_factura values(2,1,'A','IVA',100,'Calle falsa 123');
insert into g01_renglon_factura values(1,2,'A','Instalacion conexion',125,'Neo calle falsa 2189');
insert into g01_renglon_factura values(1,3,'A','Velocidad 300 megas',850,'New calle falsa 2189');
insert into g01_renglon_factura values(2,3,'A','Velocidad 6 megas',200,'Nueva calle falsa 2189');
insert into g01_renglon_factura values(3,3,'A','Velocidad 12 megas',400,'Neo calle falsa 2189');
insert into g01_renglon_factura values(4,3,'A','IVA',150,'New calle falsa 2189');
insert into g01_renglon_factura values(5,3,'A','IVA',100,'Nueva calle falsa 2189');
insert into g01_renglon_factura values(6,3,'A','IVA',100,'Neo calle falsa 2189');
insert into g01_renglon_factura values(1,4,'A','Mantenimiento',50,'Nueva calle falsa 2189');
insert into g01_renglon_factura values(1,5,'A','Velocidad 12 megas',400,'Gugure Machine 678');
insert into g01_renglon_factura values(2,5,'A','IVA',20,'Gugure Machine 678');
insert into g01_renglon_factura values(1,6,'A','Mantenimiento',50,'Nueva calle falsa 2189');
insert into g01_renglon_factura values(1,7,'A','Mantenimiento',50,'Calle falsa 123');
insert into g01_renglon_factura values(1,8,'A','Mantenimiento',50,'Calle falsa 871');
