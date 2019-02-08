$(document).ready(function(){
"use strict";


function InitMenuEvt(){
		$("ul .opcion").off().on("click",ActualizarContenido);
		$("ul .logout").off().on("click",logout);
}

function getLineaCom(){
		var idc = $(this).attr('idcomp');
		var idtc = $(this).attr('idtcomp');
		var datos = {idcomp: idc,idtcomp: idtc}
			$.ajax({
				method: "POST",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(datos),
				cache: false,
				url:"index.php?action=ver_lineas",
				success: function(data){
					$("#AjaxModal").html(data);
				},
				error: function(data)
        {$("#AjaxModal").text("Hubo un error en la red.");}
			});
    }



function ActualizarContenido(ev){
			var pagina = $(this).attr("href");
			var user = $(".usuario").attr("user");
			var opt = $(this).attr("idopcion");
			var datos = {usuario: user,opcion: opt}
			$.ajax({
				method: "POST",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(datos),
				cache: false,
				url: pagina,
				success: function(data){
					$("#AjaxContent").html(data);
					if($("div").hasClass("ABM")){
						$('#btnAgregarComprobante').off().on('click',agregarComprobante);
					}
					$('.rowLinea').off().on('click',getLineaCom);
				},
				error: function(data){alert("Ha habido un error con la carga del contenido, vuelva a intentarlo.");}
			});
			ev.preventDefault();
}

function agregarComprobante(ev){
	var tipoc = $('#listaTipo :selected').attr('idtipoc');
	var fec = $('#fecha').val();
	var lug = $('#listaLugar :selected').attr('idlugar');
	var com = $('#comentario').val();
	var est = $('#estado').val();
	var fecv = $('#fechavenc').val();
	var per = $('#listapersona :selected').attr('idpersona');
	var tipop = $('#listatipop :selected').text();
	var datos = {tipocomp: tipoc,fecha: fec,lugar: lug,comentario: com,estado: est,fechavenc: fecv,persona: per,tipopago:tipop};
	$.ajax({
		method: "POST",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(datos),
		cache: false,
		url: "index.php?action=agregar_comprobante",
		success: function(data){
			alert('Comprobante generado con exito');
		},
		error: function(data){alert("Hubo un problema al generar el comprobante, vuelva a intentarlo");}
	});
	ev.preventDefault();
}

function CargarMenu(){
			$.ajax({
				url:"index.php?action=ver_menu",
				dataType: "html",
				success: function(data){
					$("#ajaxMenu").html(data);
					InitMenuEvt()
				},
				error: function(data)
        {$("#ajaxMenu").text("Hubo un error en la red.");}
			});
    }



		function mostrarLogin(ev){
					$.ajax({
						url:"index.php?action=ver_login",
						dataType: "html",
						success: function(data){
							$("#AjaxContent").html(data);
							$(".btnlogin").on("click",login);
						},
						error: function(data){$("#AjaxContent").text("Hubo un error en la red.");}
					});
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
				$("#AjaxContent").html(data);
				if(!($("#AjaxContent span").hasClass("error"))){
					CargarMenu();
				}
				$(".btnlogin").on("click",login);
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
				window.location.href = "index.php";
				alert("Ha salido con exito.");
			},
			error: function(data){alert("Ha habido un error al salir, vuelva a intentarlo.");}
		});
		ev.preventDefault();
}
//




$("ul li").on("click",mostrarLogin);


});
