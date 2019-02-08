var audioIntro = document.createElement('audio');
audioIntro.setAttribute('src', 'sounds/tema_intro.mp3');
audioIntro.loop = true;
var audioJuego = document.createElement('audio');
audioJuego.setAttribute('src', 'sounds/tema_juego.mp3');
audioJuego.loop = true;
var audioGameOver = document.createElement('audio');
audioGameOver.setAttribute('src', 'sounds/game_over.mp3');
audioGameOver.loop = true;
var audioExplosion = document.createElement('audio');
audioExplosion.setAttribute('src', 'sounds/explosion.wav');
var audioBoing = document.createElement('audio');
audioBoing.setAttribute('src', 'sounds/boing.wav');
var audioSlime = document.createElement('audio');
audioSlime.setAttribute('src', 'sounds/slime.wav');
var audioSword = document.createElement('audio');
audioSword.setAttribute('src', 'sounds/sword.wav');
var audioPickaxe = document.createElement('audio');
audioPickaxe.setAttribute('src', 'sounds/pickaxe.mp3');
var audioItem = document.createElement('audio');
audioItem.setAttribute('src', 'sounds/item-catch.wav');
var audioReady = document.createElement('audio');
audioReady.setAttribute('src', 'sounds/ready.wav');
var audioGo = document.createElement('audio');
audioGo.setAttribute('src', 'sounds/go.wav');
var audioGameOverVoice = document.createElement('audio');
audioGameOverVoice.setAttribute('src', 'sounds/game_over_voice.wav');

function juego() {

    var background = $("#background");
    var floor = $("#floor");
    var player = $("#player");
    var juegoIniciado = false;
    var cantEnemigos = 0;
    var enemigosDisponibles = ["idle-enemy-swordy", "idle-enemy-mettaur", "idle-enemy-slime", "idle-item"];
    var enemigosEnJuego = [];
    var puntaje = 0;
    var puntajeFinal = 0;
    var tiempoJuego;
    var collisionDetect;
    var tiempoSpawn;

    player.css("left", ($(window).width() / 2) - 350);

    function iniciarJuego() {
        tiempoJuego = setInterval(function() {
            puntaje++;
            $("#txt-puntaje").text(puntaje);
        }, 1000);

        collisionDetect = setInterval(function() {
            for (var i = 0; i < enemigosEnJuego.length; i++) {
                enemy = enemigosEnJuego[i];
                if (collision(player, enemy)) {
                    if (enemy.hasClass("idle-enemy-swordy")) {
                        if (player.hasClass("shoot")) {
                            enemy.removeClass();
                            enemy.addClass("enemy-explosion");
                            audioExplosion.load();
                            audioExplosion.play();
                            puntaje += 20;
                        } else {
                            audioSword.load();
                            audioSword.play();
                            playerDerrotado();
                        }
                    } else if (enemy.hasClass("idle-enemy-mettaur")) {
                        if (player.hasClass("shield")) {
                            enemy.removeClass();
                            enemy.addClass("enemy-fly");
                            audioBoing.load();
                            audioBoing.play();
                            puntaje += 15;
                        } else {
                            audioPickaxe.load();
                            audioPickaxe.play();
                            playerDerrotado();
                        }
                    } else if (enemy.hasClass("idle-enemy-slime")) {
                        audioSlime.load();
                        audioSlime.play();
                        playerDerrotado();
                    } else if (enemy.hasClass("idle-item")) {
                        audioItem.load();
                        audioItem.play();
                        enemy.removeClass();
                        puntaje += 30;
                    }
                };
            }
        }, 17);

        function enemigoRandom() {
            var rand = Math.floor(Math.random() * 4);
            return enemigosDisponibles[rand];
        }

        var enemySpawn = function() {
            var objetoElegido = enemigoRandom();
            var eCaja = "<div id='enemyBox" + cantEnemigos + "' class='move'></div>";
            var objeto;
            if (objetoElegido === "idle-item") {
                objeto = "<div id='item' class='" + objetoElegido + " objeto" + cantEnemigos + "'></div>";
            } else {
                objeto = "<div id='enemy' class='" + objetoElegido + " objeto" + cantEnemigos + "'></div>";
            }

            $("#enemyField").append(eCaja);
            $("#enemyBox" + cantEnemigos).append(objeto);
            enemigosEnJuego.push($(".objeto" + cantEnemigos));

            $("#enemyBox" + cantEnemigos + ".move").one("webkitTransitionEnd animationend oTransitionEnd msTransitionEnd transitionend",
                function(event) {
                    enemigosEnJuego.shift();
                    $("#enemyField").find(":first").remove();
                });

            cantEnemigos++;
            time = Math.floor((Math.random() * 2000) + 500);
            tiempoSpawn = setTimeout(enemySpawn, time);
        };

        var time = Math.floor((Math.random() * 3000) + 1000);
        tiempoSpawn = setTimeout(enemySpawn, time);

    }

    function accionesTeclasApretadas(e) {
        // Evita que se accionen los eventos por defecto de las flechas y la barra espaciadora
        if ([32, 37, 38, 39, 40].indexOf(e.keyCode) > -1) {
            e.preventDefault();
        }
        if (!juegoIniciado) {
            if (e.keyCode == 40) {
                audioIntro.pause();
                audioIntro.load();
                $("#cartelesInicioJuego").addClass("juego-ready");
                $("#cartelesInicioJuego.juego-ready").css("left", ($(window).width() / 2)-100);
                audioReady.play();
                console.log(audioReady.ended);
                audioReady.onended = function() {
                    $("#cartelesInicioJuego").removeClass();
                    $("#cartelesInicioJuego").addClass("juego-go");
                    $("#cartelesInicioJuego.juego-go").css("left", ($(window).width() / 2)-50);
                    audioGo.play();
                    audioGo.onended = function() {
                        $("#cartelesInicioJuego").removeClass();
                        audioJuego.play();
                        iniciarJuego();
                        juegoIniciado = true;
                        background.css("animation-play-state", "running");
                        floor.css("animation-play-state", "running");
                        background.addClass("bg-moving");
                        floor.addClass("f-moving");
                        player.removeClass();
                        player.addClass("running");
                    }
                }
            }
        }
        if (juegoIniciado) {
            if (e.keyCode == 38) {
                player.removeClass();
                player.addClass("jump");
                $(".jump").one("webkitTransitionEnd animationend oTransitionEnd msTransitionEnd transitionend",
                    function(event) {
                        player.removeClass();
                        player.addClass("running");
                    });
            }
            if (e.keyCode == 39) {
                player.removeClass();
                player.addClass("shoot");
            }
            if (e.keyCode == 37) {
                player.removeClass();
                player.addClass("shield");
            }

        }

    }


    function accionesTeclasSoltadas(e) {
        if (juegoIniciado) {
            if ((e.keyCode == 37) || (e.keyCode == 39)) {
                player.removeClass();
                player.addClass("running");
            }
        }
    }

    function collision(player, enemy) {
        var modificador = 20;
        var Px = player.position().left + modificador;
        var Py = player.position().top;
        var Pwidth = player.width() - (modificador * 2);
        var Pheight = player.height();
        var Ex = enemy.position().left + modificador;
        var Ey = enemy.position().top;
        var Ewidth = enemy.width() - (modificador * 2);
        var Eheight = enemy.height() - (modificador * 2);
        return ((Px < (Ex + Ewidth)) &&
            ((Px + Pwidth) > Ex) &&
            (Py < (Ey + Eheight)) &&
            ((Py + Pheight) > Ey));

    }

    function playerDerrotado() {
        audioJuego.pause();
        audioJuego.load();
        audioGameOver.play();
        juegoIniciado = false;
        window.removeEventListener("keydown", accionesTeclasApretadas);
        window.removeEventListener("keyup", accionesTeclasSoltadas);
        clearInterval(tiempoJuego);
        clearInterval(collisionDetect);
        clearTimeout(tiempoSpawn);
        player.removeClass();
        player.addClass("hit");
        puntajeFinal = parseInt($("#txt-puntaje-final").text());
        if (puntaje > puntajeFinal) {
            $("#txt-puntaje-final").text(puntaje);
        }
        pausarJuego();
    }

    function pausarJuego() {
        background.css("animation-play-state", "paused");
        floor.css("animation-play-state", "paused");
        audioGameOverVoice.play();
        $("#txtBox").addClass("game_over");
        $("#txtBox.game_over").css("left", ($(window).width() / 2)-250);
        $(".game_over").on("click", reiniciarJuego);
    }

    function reiniciarJuego() {
        audioGameOver.pause();
        audioGameOver.load();
        audioIntro.play();
        background.removeClass();
        floor.removeClass();
        player.removeClass();
        player.addClass("spawn");
        $("#enemyField").empty();
        $("#txtBox").removeClass();
        enemigosEnJuego = [];
        cantEnemigos = 0;
        puntaje = 0;
        salto = false;
        $("#txt-puntaje").text(puntaje);
        $(".spawn").one("webkitTransitionEnd animationend oTransitionEnd msTransitionEnd transitionend",
            function(event) {
                player.removeClass();
                player.addClass("idle");
            });
        window.addEventListener("keydown", accionesTeclasApretadas);
        window.addEventListener("keyup", accionesTeclasSoltadas);
    }

    reiniciarJuego();

}
