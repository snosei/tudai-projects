const DIFICULTAD_BEBE = 2;
const DIFICULTAD_FACIL = 4;
const DIFICULTAD_MEDIA = 8;
const DIFICULTAD_DIFICIL = 16;
const INTERVALO_DIBUJO = 30;
const CANVAS_WIDTH = $( window ).width();
const CANVAS_HEIGHT = $( window).height()-200 ;


var timerInstance = new Timer();
var anchoGrilla;
var altoGrilla;
var posX;
var posY;

var canvas = document.getElementById('canvasOriginal');
var ctx = canvas.getContext('2d');
canvas.width = CANVAS_WIDTH;
canvas.height = CANVAS_HEIGHT;

var canvasGhost = document.getElementById('canvasGhost');
var ctxGhost = canvasGhost.getContext("2d");
canvasGhost.width = CANVAS_WIDTH;
canvasGhost.height = CANVAS_HEIGHT;


function EstadoCanvas(canvas, grillaP, grillaR) {
    this.canvas = canvas;
    this.width = canvas.width;
    this.height = canvas.height;
    this.ctx = canvas.getContext('2d');

    this.valid = false; // Cuando sea falso se redibujara todo el canvas
    this.formas = []; // Las cosas a dibujar
    this.celdas = [];
    this.dragged = false; // Si un objeto fue agarrado o no
    this.selection = null;
    this.grillaPuzzle = grillaP;
    this.grillaResuelto = grillaR;

    var miEstado = this; // Referencia a la instancia del canvas

    canvas.onmousedown = function(e) {
        var px = e.offsetX; //Firefox, para chrome es clientX
        var py = e.offsetY;
        var formas = miEstado.formas;
        var l = formas.length;

        for (var i = l - 1; i >= 0; i--) {
            if (formas[i].check(px, py) === true) {
                var forma = formas[i];


                var celdas = miEstado.celdas;
                var encontrado = false;
                var j = 0;
                while ((j < celdas.length) && (encontrado === false)) {
                    if (celdas[j].id != -1) {
                        if (celdas[j].id == forma.id) {
                            celdas[j].id = -1;
                            encontrado = true;
                        }
                    }
                    j++;
                }

                miEstado.dragged = true;
                miEstado.selection = forma;

                miEstado.selection.posX = px - ((miEstado.selection.width) / 2);
                miEstado.selection.posY = py - ((miEstado.selection.height) / 2);
                miEstado.valid = false; // Para que redibuje
                return;
            }
        }

    };

    canvas.onmousemove = function(e) {
        if (miEstado.dragged) {
            var px = e.offsetX;
            var py = e.offsetY;
            miEstado.selection.posX = px - ((miEstado.selection.width) / 2);
            miEstado.selection.posY = py - ((miEstado.selection.height) / 2);
            miEstado.valid = false;
        }
    };

    canvas.onmouseup = function(e) {
        if (miEstado.dragged) {
            var forma = miEstado.selection;
            miEstado.dragged = false;
            var l = miEstado.celdas.length;
            var colocado = false;
            var i = 0;
            while ((i < l) && (colocado === false)) {
                var caja = miEstado.celdas[i];
                if ((caja.id == -1) && (caja.checkContiene(forma))) {
                    caja.id = forma.id;
                    forma.posX = caja.posX;
                    forma.posY = caja.posY;
                    miEstado.valid = false;
                    colocado = true;

                }
                i++;
            }
        }
    };

    canvas.onmouseout = function(e) { // Usado para cuando el mouse deja el canvas
        miEstado.dragged = false;
    };

    this.interval = INTERVALO_DIBUJO;
    setInterval(function() {
        miEstado.render();
    }, miEstado.interval);
}

EstadoCanvas.prototype.addFormas = function(desordenado, cantCeldas) {
    var cont = 0;
    var width = this.grillaPuzzle.width / cantCeldas;
    var height = this.grillaPuzzle.height / cantCeldas;
    for (var i = 0; i < cantCeldas; i++) {
        for (var j = 0; j < cantCeldas; j++) {
            desordenado[cont].addPos(this.grillaPuzzle.posX + (width * j), this.grillaPuzzle.posY + (height * i))
            this.formas.push(desordenado[cont]);
            cont++;
        }
    }
    this.valid = false;

};

EstadoCanvas.prototype.addCelda = function(celda) {
    this.celdas.push(celda);
};

EstadoCanvas.prototype.clear = function() {
    this.ctx.clearRect(0, 0, this.width, this.height);
};

// Se llamara tantas veces se cumpla el tiempo del intervalo
// Pero solo hara algo si la variable valid paso de true a false
EstadoCanvas.prototype.render = function() {
    if (!(this.valid)) {

        var ctx = this.ctx;
        var formas = this.formas;
        this.clear();

        if (this.selection != null) {
            removerPieza(this.formas, "id", this.selection.id);
            this.formas.push(this.selection);
        }

        this.grillaResuelto.render(ctx);

        var l = formas.length;
        for (var i = 0; i < l; i++) {
            formas[i].render(ctx);
        }

        this.valid = true;

        if (this.grillaResuelto.checkWin()) {
            var mensaje = "¡Felicitaciones! Ha resuelto el puzzle en " +
                timerInstance.getTimeValues().hours + " horas " +
                timerInstance.getTimeValues().minutes + " minutos " +
                timerInstance.getTimeValues().seconds + " segundos";
            alert(mensaje);
            timerInstance.pause();
        }

    }
};

var removerPieza = function(arr, attr, value) {
    var i = arr.length;
    while (i--) {
        if (arr[i] &&
            arr[i].hasOwnProperty(attr) &&
            (arr[i][attr] === value)) {
            arr.splice(i, 1);
        }
    }
    return arr;
}

function seleccionarDificultad(dif) {
    if (dif == 0) {
        $("#txt-dificultad").text("BEBE");
        return DIFICULTAD_BEBE;
    } else if (dif == 1) {
        $("#txt-dificultad").text("FACIL");
        return DIFICULTAD_FACIL;
    } else if (dif == 2) {
        $("#txt-dificultad").text("MEDIA");
        return DIFICULTAD_MEDIA;
    } else {
        $("#txt-dificultad").text("DIFICIL");
        return DIFICULTAD_DIFICIL;
    }
}

function calcularValores(img){
  if (img.width > img.height) {
      anchoGrilla = (CANVAS_WIDTH/3)+100;
      altoGrilla = CANVAS_HEIGHT-50;
      posX = CANVAS_WIDTH/12;
      posY = CANVAS_HEIGHT/18;
  } else {
      anchoGrilla = (CANVAS_WIDTH/3)-100;
      altoGrilla = CANVAS_HEIGHT-50;
      posX = CANVAS_WIDTH/5;
      posY = CANVAS_HEIGHT/18;
  }
}

function inicializarContador(){
  timerInstance.start();
  timerInstance.addEventListener('secondsUpdated', function(e) {
      $('#txt-tiempo').html(timerInstance.getTimeValues().toString());
  });
}

function inicializar(dificultad, imagen) {

    var cantCeldas = seleccionarDificultad(dificultad);

    var img = new Image();
    img.crossOrigin = "Anonymous";
    img.onload = function() {

        calcularValores(img);

        var g1 = new Grilla(posX, posY, anchoGrilla, altoGrilla, cantCeldas);
        var g2 = new Grilla(posX+anchoGrilla+(posX/2), posY, anchoGrilla, altoGrilla, cantCeldas);

        var c1 = new EstadoCanvas(canvas, g1, g2);

        ctxGhost.drawImage(img, 0, 0, anchoGrilla, altoGrilla);

        var valores = [];
        for (var i = 0; i < (cantCeldas * cantCeldas); i++) {
            valores.push(i);
        }

        var id = 0;
        var desordenado = [];
        var width = g1.width / cantCeldas;
        var height = g1.height / cantCeldas;

        for (var i = 0; i < cantCeldas; i++) {
            for (var j = 0; j < cantCeldas; j++) {
                c1.addCelda(g2.celdas[i][j]);

                var clipX = width * j;
                var clipY = height * i;

                var p = new Pieza(width, height, canvasGhost, id, clipX, clipY);
                var rand = Math.floor((Math.random() * valores.length));
                var idAleatorio = valores[rand];
                valores.splice(rand, 1);
                desordenado.splice(idAleatorio, 0, p);

                id++;
            }
        }
        c1.addFormas(desordenado, cantCeldas);
    }
    img.src = imagen;

    inicializarContador();

}
