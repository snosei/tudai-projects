$(document).ready(function(){
"use strict";
var archivos; // Variable que usaremos para cargar las imagenes

function limpiarCampos(){
	// Función que se encargará de limpiar los campos a la hora de agregar noticias, etc
		$("#Nombre").val("");
		$("#Mail").val("");
		$("#Texto").val("");
		$("#nombre").val("");
		$("#detalle").val("");
		$("#titulo").val("");
		$("#contenido").val("");
		$("#nombD").val("");
		$("#ldistro").val("");
		$("#ram").val("");
		$("#disco").val("");
		$("#ldescarga").val("");
}

//Funciones que inicializan los eventos que afectaran a los contenidos cargados con partial render luego de su carga
function InitEvt(){
		$("#btnEnviarMensaje").off().on("click",EnviarMensaje);
		$("tr").filter(":odd").addClass("ResaltarTabla").end();
		$("#noticia a").off().on("click", MostrarNoticia);
		$("#categorias .subdrop").off().on("click", ActualizarContenido);
}
function InitNoticiaEvt(){
		$(".carousel-indicators li:first").addClass("active");
		$(".carousel-inner .item:first").addClass("active");
}
function InitLoginEvt(){
		$("#btnLogueo").off().on("click",login);
		$("#btnLogout").off().on("click",logout);
		$("#login .subdrop").off().on("click", ActualizarContenido);
}
function InitABMEvt(){
		$("#btnAgregarCategoria").off().on("click",AgregarCategoria);
		$("#btnAgregarNoticia").off().on("click",AgregarNoticia);
		$("#btnAgregarDistro").off().on("click",AgregarDistro);
		$("#btnModificarCategoria").off().on("click",modificarCategoria);
		$("#btnModificarNoticia").off().on("click",modificarNoticia);
		$("#btnModificarDistro").off().on("click",modificarDistro);
		$("#imagenesNoticia").off().on("change",tomarImagenes);
		$("#logo").off().on("change",tomarImagenes);

		$("#btnModificarCategoria").hide();
		$("#btnModificarNoticia").hide();
		$("#btnModificarDistro").hide();
}
//


//Inicializadores de eventos por filtro
$('body').on('click','a.borrarCat', function(ev){
	ev.preventDefault();
	borrarCategoria(this.getAttribute('nombre'));
});
$('body').on('click','a.borrarNot', function(ev){
	ev.preventDefault();
	borrarNoticia(this.getAttribute('id'));
});
$('body').on('click','a.borrarDist', function(ev){
	ev.preventDefault();
	borrarDistro(this.getAttribute('id'));
});
$('body').on('click','a.modificarCat', function(ev){
	ev.preventDefault();
	getValoresCategoria(this.getAttribute('nombre'));
	$("#btnModificarCategoria").show();
});
$('body').on('click','a.modificarNot', function(ev){
	ev.preventDefault();
	getValoresNoticia(this.getAttribute('id'));
	$("#btnModificarNoticia").show();
});
$('body').on('click','a.modificarDist', function(ev){
	ev.preventDefault();
	getValoresDistro(this.getAttribute('id'));
	$("#btnModificarDistro").show();
});
//

function tomarImagenes(ev){// Función que ira almacenando las imagenes en una variable para su uso
	archivos = ev.target.files;
};

function crearHtml(objeto,parametro){//render de mustache para cualquier api que tengamos
	$.ajax({ url: 'js/templates/'+parametro+'.mst',
		success: function(template) {
			var rendered = Mustache.render(template,objeto);
		  $('.'+parametro).append(rendered);
		}
	});
}

//Funciones de CATEGORIA
function getValoresCategoria(nombre){
		$.ajax({
			method: 'GET',
			datatype: 'JSON',
			url: "api/categoria/" + nombre,
			success: function(categoria){
				$("#nombre").val(categoria.nombre);
				$("#detalle").val(categoria.detalle);
				$("#btnModificarCategoria").attr("idCat",categoria.nombre);
			},
			error: function(data){alert("Hubo un error al traer la categoria, vuelva a intentarlo.");}
		});
}
function getCategorias(ev,cat){
		$.ajax({
			method: 'GET',
			datatype: 'JSON',
			url: "api/categoria",
			success: function(categorias){
				$('.'+cat).html('');
				categorias.forEach(function(categoria){
					 var html = crearHtml(categoria,cat);
					$('.'+cat).append(html);
				});
			},
			error: function(data){alert("Hubo un error al traer las categorias, vuelva a intentarlo.");}
		});
}
function AgregarCategoria(ev){
	// Función que guardará lo contenido en el formulario de categorias y lo enviará al servidor
	if(($("#nombre").val()!="")&&($("#detalle").val()!="")){
	var nom = $("#nombre").val();
	var det = $("#detalle").val();
	var datos = { // Objeto JSON con la informacion del formulario
		nombre: nom,
		detalle: det
	}
	$.ajax({
		method: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(datos),
		cache: false,
		url: "api/categoria",
		success: function(data){
			alert("La categoria ha sido agregada con exito.");
			limpiarCampos();
			$(".categoria").append(crearHtml(datos,"categoria"));
			$(".dropCategoria").append(crearHtml(datos,"dropCategoria"));
			$("#btnModificarCategoria").hide();
		},
		error: function(data){alert("Hubo un error al agregar la categoria, vuelva a intentarlo.");}
	});
	}
	else{
		alert("Faltan rellenar datos.")
	}
	ev.preventDefault();
}
function modificarCategoria(ev){
	if(($("#nombre").val()!="")&&($("#detalle").val()!="")){
	var nom = $("#nombre").val();
	var det = $("#detalle").val();
	var nombre = this.getAttribute('idCat');
	var datos = { // Objeto JSON con la informacion del formulario
		nombre: nom,
		detalle: det,
		idCat: nombre
	}
	$.ajax({
		method: "PUT",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(datos),
		cache: false,
		url: "api/categoria/" + nombre,
		success: function(data){
			alert("La categoria ha sido modificada con exito.");
			$("#cat"+nombre).attr("value",nom);
			$("#cat"+nombre).text(nom);
			$("#cat"+nombre).attr("id","cat"+nom);
			$("#categoria"+nombre+" span").text(nom);
			$("#categoria"+nombre+" a").attr("nombre",nom);
			$("#categoria"+nombre).attr("id","categoria"+nom);
			$("#btnModificarCategoria").hide();
			limpiarCampos();
		},
		error: function(data){alert("Hubo un error al modificar la categoria, vuelva a intentarlo.");}
	});
	}
	else{
		alert("Faltan rellenar datos.")
	}
	ev.preventDefault();
}
function borrarCategoria(nombre){
	  $.ajax({
	    method: 'DELETE',
	    url:'api/categoria/' + nombre,
	    datatype: 'JSON',
			success: function(data){
				$('#categoria'+nombre).remove();
				$("#listaCategoria option[value="+nombre+"]").remove();
				$("#btnModificarCategoria").hide();
				getNoticias();
			},
	    error: function (data) {alert("Hubo un problema al borrar la categoria");}
	  });
}
//

//Funciones de NOTICIA
function getValoresNoticia(id){
		$.ajax({
			method: 'GET',
			datatype: 'JSON',
			url: "api/noticia/" + id,
			success: function(noticia){
				$("#titulo").val(noticia.titulo);
				$("#contenido").val(noticia.contenido);
				$("#btnModificarNoticia").attr("idNot",noticia.id_noticia);
			},
			error: function(data){alert("Hubo un error al traer la noticia, vuelva a intentarlo.");}
		});
}
function getNoticias(ev){
		$.ajax({
			method: 'GET',
			datatype: 'JSON',
			url: "api/noticia",
			success: function(noticias){
				$('.noticia').html('');
				noticias.forEach(function(noticia){
					 var html = crearHtml(noticia,"noticia");
					$('.noticia').append(html);
				});
			},
			error: function(data){alert("Hubo un error al traer las noticias, vuelva a intentarlo.");}
		});
}
function AgregarNoticia(ev){
	// Función que guardará lo contenido dentro del formulario de noticia y lo agregará en el servidor
	if(($("#titulo").val()!="")&&($("#contenido").val()!="")&&(typeof(archivos) != "undefined")){
	var tit = $("#titulo").val();
	var cont = $("#contenido").val();
	var list = $('#listaCategoria :selected').text();
	var datos = new FormData(); // En vez de un JSON usamos un FormData que contendrá tanto las imagenes como los datos del formulario
	$.each(archivos, function(key, value)
	{
		datos.append(key, value);
	});
	datos.append("titulo",tit);
	datos.append("contenido",cont);
	datos.append("lista",list);
	$.ajax({
		method: "POST",
		contentType: false,
		processData: false,
		data: datos,
		cache: false,
		url: "api/noticia/",
		success: function(data){
			alert("La noticia ha sido agregada con exito.");
			limpiarCampos();
			var datosJson = {
				titulo: tit,
				id_noticia: data
			}
			$(".noticia").append(crearHtml(datosJson,"noticia"));
			$("#btnModificarNoticia").hide();
		},
		error: function(data){alert("Hubo un error al agregar la noticia, vuelva a intentarlo.");}
	});
	}
	else{
		alert("Faltan rellenar datos.")
	}
	ev.preventDefault();
}
function modificarNoticia(ev){
	// Función que guardará lo contenido dentro del formulario de noticia y lo agregará en el servidor
	if(($("#titulo").val()!="")&&($("#contenido").val()!="")&&(typeof(archivos) != "undefined")){
	var tit = $("#titulo").val();
	var cont = $("#contenido").val();
	var list = $('#listaCategoria :selected').text();
	var id = this.getAttribute('idNot');
	var datos = new FormData(); // En vez de un JSON usamos un FormData que contendrá tanto las imagenes como los datos del formulario
	$.each(archivos, function(key, value)
	{
		datos.append(key, value);
	});
	datos.append("titulo",tit);
	datos.append("contenido",cont);
	datos.append("lista",list);
	datos.append("id_noticia",id);
	$.ajax({
		method: "POST",
		contentType: false,
		processData: false,
		data: datos,
		cache: false,
		url: "api/noticia/"+id,
		success: function(data){
			alert("La noticia ha sido modificada con exito.");
			limpiarCampos();
			$("#noticia"+id+" span").text(tit);
			$("#noticia"+id+" a").attr("id",id);
			$("#noticia"+id).attr("id","noticia"+id);
			$("#btnModificarNoticia").hide();
		},
		error: function(data){alert("Hubo un error al modificar la noticia, vuelva a intentarlo.");}
	});
	}
	else{
		alert("Faltan rellenar datos.")
	}
	ev.preventDefault();
}
function borrarNoticia(id){
	  $.ajax({
	    method: 'DELETE',
	    url:'api/noticia/' + id,
	    datatype: 'JSON',
			success: function(data){
				$('#noticia'+id).remove();
				$("#btnModificarNoticia").hide();
			},
	    error: function (data) {alert("Hubo un problema al borrar la noticia");}
	  });
}

function MostrarNoticia(ev){// Función que mostrará una noticia en particular
		var pagina = $(this).attr("href");
		$.ajax({
			url:pagina,
			dataType: "html",
			success: function(data){$("#AjaxContent").html(data);$("html, body").animate({ scrollTop: 0 }, "fast");InitNoticiaEvt();},
			error: function(data){$("#AjaxContent").text("Hubo un error en la red.");}
		});
		ev.preventDefault();
}
//

//Funciones de DISTRO
function getValoresDistro(id){
		$.ajax({
			method: 'GET',
			datatype: 'JSON',
			url: "api/distros/" + id,
			success: function(distro){
				$("#nombD").val(distro.nombre);
				$("#ldistro").val(distro.link_distro);
				$("#ram").val(distro.requisito_memoria);
				$("#disco").val(distro.requisito_disco);
				$("#ldescarga").val(distro.link_descarga);
				$("#btnModificarDistro").attr("idDist",id);
			},
			error: function(data){alert("Hubo un error al traer la distro, vuelva a intentarlo.");}
		});
}
function getDistribuciones(ev){
		$.ajax({
			method: 'GET',
			datatype: 'JSON',
			url: "api/distros",
			success: function(distribuciones){
				$('.distros').html('');
				distribuciones.forEach(function(distribucion){
					 var html = crearHtml(distribucion,"distros");
					$('.distros').append(html);
				});
			},
			error: function(data){alert("Hubo un error al traer las distros, vuelva a intentarlo.");}
		});
}
function AgregarDistro(ev){
	// Función que guardará los datos del formulario de distribuciones dentro del servidor
	if(($("#nombD").val()!="")&&($("#ldistro").val()!="")&&($("#ram").val()!="")&&($("#disco").val()!="")&&($("#ldescarga").val()!="")&&(typeof(archivos) != "undefined")){
	var nom = $("#nombD").val();
	var ldist = $("#ldistro").val();
	var ram = $("#ram").val();
	var dis = $("#disco").val();
	var ldesc = $("#ldescarga").val();
	var list = $('#listaArqui :selected').val();
	var datos = new FormData();
	$.each(archivos, function(key, value)
	{
		datos.append(key, value);
	});
	datos.append("ldistro",ldist);
	datos.append("nombD",nom);
	datos.append("arqui",list);
	datos.append("ram",ram);
	datos.append("disco",dis);
	datos.append("ldescarga",ldesc);
	$.ajax({
		method: "POST",
		contentType: false,
		processData: false,
		data: datos,
		cache: false,
		url: "api/distros",
		success: function(data){alert("La distribución ha sido agregada con exito.");
		limpiarCampos();
		var datosJson = {
			nombre: nom,
			arquitectura: list,
			id_distro: data
		}
		$(".distros").append(crearHtml(datosJson,"distros"));
		$("#btnModificarDistro").hide();
		},
		error: function(data){alert("Hubo un error al agregar la distro, vuelva a intentarlo.");}
	});
	}
	else{
		alert("Faltan rellenar datos.")
	}
	ev.preventDefault();
}
function modificarDistro(ev){
	// Función que guardará los datos del formulario de distribuciones dentro del servidor
	if(($("#nombD").val()!="")&&($("#ldistro").val()!="")&&($("#ram").val()!="")&&($("#disco").val()!="")&&($("#ldescarga").val()!="")&&(typeof(archivos) != "undefined")){
	var nom = $("#nombD").val();
	var ldist = $("#ldistro").val();
	var ram = $("#ram").val();
	var dis = $("#disco").val();
	var ldesc = $("#ldescarga").val();
	var list = $('#listaArqui :selected').val();
	var datos = new FormData();
	var id = this.getAttribute('idDist');
	$.each(archivos, function(key, value)
	{
		datos.append(key, value);
	});
	datos.append("ldistro",ldist);
	datos.append("nombD",nom);
	datos.append("arqui",list);
	datos.append("ram",ram);
	datos.append("disco",dis);
	datos.append("ldescarga",ldesc);
	datos.append("id_distro",id);
	$.ajax({
		method: "POST",
		contentType: false,
		processData: false,
		data: datos,
		cache: false,
		url: "api/distros/"+id,
		success: function(data){alert("La distribución ha sido modificada con exito.");
		limpiarCampos();
		$("#distro"+id+" .priSpan").text(nom);
		$("#distro"+id+" .segSpan").text(list);
		$("#distro"+id+" a").attr("id",id);
		$("#distro"+id).attr("id","distro"+id);
		$("#btnModificarDistro").hide();
	},
		error: function(data){alert("Hubo un error al modificar la distro, vuelva a intentarlo.");}
	});
	}
	else{
		alert("Faltan rellenar datos.")
	}
	ev.preventDefault();
}
function borrarDistro(id){
	  $.ajax({
	    method: 'DELETE',
	    url:'api/distros/' + id,
	    datatype: 'JSON',
			success: function(data){
				$('#distro'+id).remove();
				$("#btnModificarDistro").hide();
			},
	    error: function (data) {alert("Hubo un problema al borrar la distribucion");}
	  });
}
//

function EnviarMensaje(ev){
	// Funcion que enviará lo introducido en el formulario de contacto al servidor
	if(($("#Nombre").val()!="")&&($("#Mail").val()!="")&&($("#Texto").val()!="")){
	var nom = $("#Nombre").val();
	var mail = $("#Mail").val();
	var txt = $("#Texto").val();
	var datos = { // Objeto JSON con la informacion del formulario
		nombre: nom,
		mail: mail,
		mensaje: txt
	}
	$.ajax({
		method: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(datos),
		cache: false,
		url: "index.php?action=enviar_mensaje",
		success: function(data){alert("Su mensaje ha sido enviado con exito. En breve le responderemos. Gracias.");limpiarCampos();},
		error: function(data){alert("Ha habido un problema al enviar su mensaje. Vuelva a intentarlo.");}
		});
	}
	else{
		alert("Faltan rellenar datos.")
		}
	ev.preventDefault();
}

function recargarContenido(){
	// Función que recargará el contenido del dropdown que contiene categorias dentro del navbar
		var pagina = $(this).attr("id");
		$.ajax({
			url: "index.php?action=mostrar_contenido&contenido="+pagina,
			dataType: "html",
			success: function(data){
				if(!($("div").hasClass("login"))){
					$("#" + pagina + " .dropdown-menu").html(data);
					InitEvt();
					InitLoginEvt();
				}
			},
			error: function(data){alert("Error de red");}
		});
}

function ActualizarContenido(ev){
	// Funcion que realizará un partial render al clickear un elemento de nuestro nav
		$("nav ul li").removeClass("active");
		if (!($(this).hasClass("dropdown"))){
			var pagina = $(this).attr("href");
			$(this).addClass("active");
			$.ajax({
				url:pagina,
				dataType: "html",
				success: function(data){ // Si la solicitud tuvo exito, mostrará el contenido en la pagina y
					$("#AjaxContent").html(data);
					if($("h1").hasClass("ABM")){
						getCategorias(ev,"categoria");
						getCategorias(ev,"dropCategoria");
						getNoticias();
						getDistribuciones();
						InitABMEvt();
					}
					else{
						InitEvt();
					};
				},
				error: function(data){$("#AjaxContent").text("Hubo un error en la red.");}
			});
		}
		ev.preventDefault();
}

//LOGIN
function login(ev){
		if(($("#txtEmail").val()!="")&&($("#txtPassword").val()!="")){
		var email = $("#txtEmail").val();
		var password = $("#txtPassword").val();
		var datos = {mail: email,pass: password}
		$.ajax({
			method: "POST",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify(datos),
			cache: false,
			url: "index.php?action=login",
			success: function(data){
				$("#login .dropdown-menu").html(data);
				InitLoginEvt();
			},
			error: function(data){alert("Ha habido un error con el logueo, vuelva a intentarlo.");}
		});
		}
		else{
			alert("Faltan rellenar datos.")
		}
		ev.preventDefault();
}
function logout(ev){
		$.ajax({
			method: "POST",
			contentType: "application/json; charset=utf-8",
			data: null,
			cache: false,
			url: "index.php?action=logout",
			success: function(data){
				$("#login .dropdown-menu").html(data);
				InitLoginEvt();
				if($("h1").hasClass("ABM")){
					window.location.href = "index.php";
				};
				alert("Ha salido con exito.");
			},
			error: function(data){alert("Ha habido un error al salir, vuelva a intentarlo.");}
		});
		ev.preventDefault();
}
//

	$("#categorias").on("click",recargarContenido); // Ejecutará un partial render
	$("#categorias").trigger("click");
	$("#login").on("click",recargarContenido); // Ejecutará un partial render
	$("#login").trigger("click");
	$("nav ul li").on("click", ActualizarContenido); // Ejecutará el partial render. Se uso el tag li por ser el que tenga la clase active
	$("nav ul li:first").trigger("click"); // Ejecuta por primera vez el evento de hacer click en "Inicio" cuando se cargue la pagina
});
