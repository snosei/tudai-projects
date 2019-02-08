--implementar  script, que borre todos los objetos del esquema (tanto los creados por el script GXX_Creacion.sql como los objetos incorporados mediante el script GXX_Cambios.sql )


--borrado de tablas creadas, la vista y las funciones:

drop trigger if exists tr_gr01_turno_turnoempleado on gr01_turno;
drop function if exists trfn_gr01_verificarturnoempleado_turno();
drop trigger if exists tr_gr01_persona_turnoempleado on gr01_persona;
drop function if exists trfn_gr01_verificarturnoempleado_persona();
drop trigger if exists tr_gr01_rol_turnoempleado on gr01_rol;
drop function if exists trfn_gr01_verificarturnoempleado_rol();
drop trigger if exists tr_gr01_comprobante_saldoactualizado on gr01_comprobante;
drop function if exists trfn_gr01_actualizarsaldo();
drop trigger if exists tr_gr01_lineacomprobante_importeactualizado on gr01_lineacomprobante;
drop function if exists trfn_gr01_actualizarimporte();
drop function if exists FN_GR01_buscar_pass();
drop function if exists FN_GR01_verificar_permiso();

drop view if exists GR01_v_menurol;

drop table if exists GR01_LineaComprobante;
drop table if exists GR01_Comprobante;
drop table if exists GR01_TipoComprobante;
drop table if exists GR01_Direccion;
drop table if exists GR01_Barrio;
drop table if exists GR01_Ciudad;
drop table if exists GR01_Mail;
drop table if exists GR01_Telefono;
drop table if exists GR01_RolMenu;
drop table if exists GR01_Menu;
drop table if exists GR01_Equipo;
drop table if exists GR01_Turno;
drop table if exists GR01_Persona;
drop table if exists GR01_Rol;
drop table if exists GR01_Lugar;
drop table if exists GR01_Servicio;
drop table if exists GR01_Categoria;
