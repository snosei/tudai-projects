$(document).ready(function() {

    var instrucciones = false;

    $(".protoman-menu").css("left", ($(window).width() / 2) - 50);
    $(".opcion1").css("left", ($(window).width() / 2) - 400);
    $(".opcion2").css("left", ($(window).width() / 2) - 400);
    $(".back_menu").css("left", ($(window).width() / 2) - 400);    

    function menuInicio() {
        audioIntro.play();

        function teclasMenu(e) {
            if ([32, 37, 38, 39, 40].indexOf(e.keyCode) > -1) {
                e.preventDefault();
            }
            if (e.keyCode == 13) {
                if ($("#game").find(".selected").hasClass("opcion1")) {
                    $("#intro").css("display", "none");
                    $("#proto-animado").css("display", "none");
                    $("#option-menu1").css("display", "none");
                    $("#option-menu2").css("display", "none");
                    window.removeEventListener("keydown", teclasMenu);
                    juego();
                } else if ($("#game").find(".selected").hasClass("opcion2")) {
                    $("#instrucciones").css("display", "block");
                    $(".back_menu").css("display", "block");
                    $(".opcion2").removeClass("selected");
                    $(".back_menu").addClass("selected");
                    instrucciones = true;
                } else if ($("#game").find(".selected").hasClass("back_menu")) {
                    $("#instrucciones").css("display", "none");
                    $(".back_menu").css("display", "none");
                    $(".opcion1").addClass("selected");
                    instrucciones = false;
                }
            }
            if (e.keyCode == 38) {
                if (instrucciones == false) {
                    $(".opcion2").removeClass("selected");
                    $(".opcion1").addClass("selected");
                }
            }
            if (e.keyCode == 40) {
                if (instrucciones == false) {
                    $(".opcion1").removeClass("selected");
                    $(".opcion2").addClass("selected");
                }
            }
        }

        window.addEventListener("keydown", teclasMenu);

    }

    menuInicio();
});
