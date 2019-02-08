$(document).ready(function() {
    "use strict";

    var imagen;

    $(".fancybox").fancybox({
        openEffect: "none",
        closeEffect: "none"
    });

    function ActualizarContenido(ev) {
        var zona = "#zona-" + $(this).attr("id");
        var back = "#zona-" + $(this).attr("back");
        $(zona).removeClass();
        $(zona).addClass("animated fadeIn");
        $(zona).css("display", "block");
        $(back).css("display", "none");
    }

    function IniciarRompecabezas(ev) {
        $("#zona-jugar-galeria #imagen-popup").attr("src", imagen);
        timerInstance.stop();
        var zona = "#zona-" + $(this).attr("id");
        $(zona).removeClass();
        $(zona).addClass("animated zoomInDown");
        var back = "#zona-" + $(this).attr("back");
        $(zona).css("display", "block");
        $(back).css("display", "none");
        var dificultad = $("#zona-jugar-dificultad").find(".active").attr("dificultad");
        inicializar(dificultad, imagen);

    }

    function mostrarMensaje(ev) {
        var dif = $(this).attr("dificultad");
        if (dif == 0) {
            $("#txt-ayuda").text("BEBE: Recomendado para los que recien comienzan");
        } else if (dif == 1) {
            $("#txt-ayuda").text("FACIL: Recomendado para jugadores casuales");
        } else if (dif == 2) {
            $("#txt-ayuda").text("MEDIA: Recomendado para los que quieren un desafio");
        } else {
            $("#txt-ayuda").text("DIFICIL: Recomendado para los que quieren pasarse horas resolviendo puzzles");
        }
    }

    function guardarDireccionImagen() {
        $("#jugar-dificultad").off("click", mensajeAlerta);
        $("#zona-jugar-galeria a").removeClass("active");
        $(this).addClass("active");
        var src = $(this).find("img").css("background-image");
        src = /^url\((['"]?)(.*)\1\)$/.exec(src);
        src = src ? src[2] : "";
        imagen = src;
        $("#jugar-dificultad").on("click", ActualizarContenido);
    }

    function mostrarAyuda() {
        $("#zona-jugar-galeria #imagen-popup").css("width",anchoGrilla);
        $("#zona-jugar-galeria #imagen-popup").css("height",altoGrilla);
        $("#zona-jugar-galeria #imagen-popup").css("background-size","100%");
        var popup = $("#imagen-popup").fancybox();
        $(popup).trigger("click");
    };

    function mensajeAlerta(){
      alert("Antes de continuar elija un rompecabezas");
    }

    $("#zona-rompecabezas #imagen-ayuda").on("click", mostrarAyuda);

    $("#zona-jugar-galeria #imagen_1").on("click", guardarDireccionImagen);
    $("#zona-jugar-galeria #imagen_2").on("click", guardarDireccionImagen);
    $("#zona-jugar-galeria #imagen_3").on("click", guardarDireccionImagen);
    $("#zona-jugar-galeria #imagen_4").on("click", guardarDireccionImagen);
    $("#zona-jugar-galeria #imagen_5").on("click", guardarDireccionImagen);
    $("#zona-jugar-galeria #imagen_6").on("click", guardarDireccionImagen);
    $("#zona-jugar-galeria #imagen_7").on("click", guardarDireccionImagen);
    $("#zona-jugar-galeria #imagen_8").on("click", guardarDireccionImagen);

    $("#zona-jugar-dificultad .btn-primary").on("click", mostrarMensaje);
    $("#zona-jugar-dificultad .btn-primary").trigger("click");
    $("#zona-jugar-dificultad .btn-danger").on("click", mostrarMensaje);
    $("#zona-jugar-dificultad .btn-warning").on("click", mostrarMensaje);
    $("#zona-jugar-dificultad .btn-success").on("click", mostrarMensaje);

    $("#instrucciones").on("click", ActualizarContenido);
    $("#zona-inicio #jugar-galeria").on("click", ActualizarContenido);
    $("#zona-jugar-dificultad #jugar-galeria").on("click", ActualizarContenido);
    $("#zona-instrucciones #inicio").on("click", ActualizarContenido);
    $("#zona-jugar-galeria #inicio").on("click", ActualizarContenido);
    $("#zona-rompecabezas #inicio").on("click", ActualizarContenido);
    $("#rompecabezas").on("click", IniciarRompecabezas);
    $("#jugar-dificultad").on("click", mensajeAlerta);

});
