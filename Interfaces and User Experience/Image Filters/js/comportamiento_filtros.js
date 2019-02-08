$(document).ready(function() {
    var canvasMuestra = $('#canvasMuestra')[0];
    var ctxMuestra = canvasMuestra.getContext("2d");
    var canvasFinal = $('#canvasFinal')[0];
    var ctxFinal = canvasFinal.getContext("2d");
    var canvasOriginal = $('#canvasOriginal')[0];
    var canvasFiltrado = $('#canvasFiltrado')[0];
    var ctxOriginal = canvasOriginal.getContext("2d");
    var ctxFiltrado = canvasFiltrado.getContext("2d");
    ctxOriginal.fillStyle = '#FFFFFF';
    ctxOriginal.fillRect(0, 0, canvasOriginal.width, canvasOriginal.height);
    ctxFiltrado.fillStyle = '#FFFFFF';
    ctxFiltrado.fillRect(0, 0, canvasFiltrado.width, canvasFiltrado.height);

    function filtroGris() {
        desactivarSliders();
        imageData = ctxOriginal.getImageData(0, 0, canvasOriginal.width, canvasOriginal.height);
        for (x = 0; x < imageData.width; x++) {
            for (y = 0; y < imageData.height; y++) {
                var grey = (getRed(imageData, x, y) + getGreen(imageData, x, y) + getBlue(imageData, x, y)) / 3;
                setPixel(imageData, x, y, grey, grey, grey, 255);
            }
        }
        ctxFiltrado.putImageData(imageData, 0, 0);

    }

    function filtroRojo() {
        desactivarSliders();
        imageData = ctxOriginal.getImageData(0, 0, canvasOriginal.width, canvasOriginal.height);
        for (x = 0; x < imageData.width; x++) {
            for (y = 0; y < imageData.height; y++) {
                var rojo = (getRed(imageData, x, y) + getGreen(imageData, x, y) + getBlue(imageData, x, y)) / 3;
                setPixel(imageData, x, y, rojo, 0, 0, 255);
            }
        }
        ctxFiltrado.putImageData(imageData, 0, 0);

    }

    function filtroVerde() {
        desactivarSliders();
        imageData = ctxOriginal.getImageData(0, 0, canvasOriginal.width, canvasOriginal.height);
        for (x = 0; x < imageData.width; x++) {
            for (y = 0; y < imageData.height; y++) {
                var verde = (getRed(imageData, x, y) + getGreen(imageData, x, y) + getBlue(imageData, x, y)) / 3;
                setPixel(imageData, x, y, 0, verde, 0, 255);
            }
        }
        ctxFiltrado.putImageData(imageData, 0, 0);

    }

    function filtroAzul() {
        desactivarSliders();
        imageData = ctxOriginal.getImageData(0, 0, canvasOriginal.width, canvasOriginal.height);
        for (x = 0; x < imageData.width; x++) {
            for (y = 0; y < imageData.height; y++) {
                var azul = (getRed(imageData, x, y) + getGreen(imageData, x, y) + getBlue(imageData, x, y)) / 3;
                setPixel(imageData, x, y, 0, 0, azul, 255);
            }
        }
        ctxFiltrado.putImageData(imageData, 0, 0);

    }

    function filtroBinario() {
        var threshold = parseInt(this.value);
        imageData = ctxOriginal.getImageData(0, 0, canvasOriginal.width, canvasOriginal.height);
        for (x = 0; x < imageData.width; x++) {
            for (y = 0; y < imageData.height; y++) {
                var red = getRed(imageData, x, y) * 0.2126;
                var green = getGreen(imageData, x, y) * 0.7152;
                var blue = getBlue(imageData, x, y) * 0.0722;
                var binario = ((red + blue + green) >= threshold) ? 255 : 0;
                setPixel(imageData, x, y, binario, binario, binario, 255);
            }
        }
        ctxFiltrado.putImageData(imageData, 0, 0);

    }

    function filtroSepia() {
        desactivarSliders();
        imageData = ctxOriginal.getImageData(0, 0, canvasOriginal.width, canvasOriginal.height);
        for (x = 0; x < imageData.width; x++) {
            for (y = 0; y < imageData.height; y++) {
                var red = (getRed(imageData, x, y) * 0.393) + (getGreen(imageData, x, y) * 0.769) + (getBlue(imageData, x, y) * 0.189);
                var green = (getRed(imageData, x, y) * 0.349) + (getGreen(imageData, x, y) * 0.686) + (getBlue(imageData, x, y) * 0.168);
                var blue = (getRed(imageData, x, y) * 0.272) + (getGreen(imageData, x, y) * 0.534) + (getBlue(imageData, x, y) * 0.131);
                setPixel(imageData, x, y, red, green, blue, 255);
            }
        }
        ctxFiltrado.putImageData(imageData, 0, 0);

    }

    function filtroInvertido() {
        desactivarSliders();
        imageData = ctxOriginal.getImageData(0, 0, canvasOriginal.width, canvasOriginal.height);
        for (x = 0; x < imageData.width; x++) {
            for (y = 0; y < imageData.height; y++) {
                setPixel(imageData, x, y, 255 - getRed(imageData, x, y), 255 - getGreen(imageData, x, y), 255 - getBlue(imageData, x, y), 255);
            }
        }
        ctxFiltrado.putImageData(imageData, 0, 0);

    }

    function filtroGama() {
        var valRed = parseInt(document.getElementById("slide-gama-red").value);
        var valGreen = parseInt(document.getElementById("slide-gama-green").value);
        var valBlue = parseInt(document.getElementById("slide-gama-blue").value);
        imageData = ctxOriginal.getImageData(0, 0, canvasOriginal.width, canvasOriginal.height);
        for (x = 0; x < imageData.width; x++) {
            for (y = 0; y < imageData.height; y++) {
                var red = valRed + getRed(imageData, x, y);
                var green = valGreen + getGreen(imageData, x, y);
                var blue = valBlue + getBlue(imageData, x, y);
                setPixel(imageData, x, y, red, green, blue, 255);
            }
        }
        ctxFiltrado.putImageData(imageData, 0, 0);

    }

    function filtroBrillo() {
        var valor = parseInt(this.value);
        imageData = ctxOriginal.getImageData(0, 0, canvasOriginal.width, canvasOriginal.height);
        for (x = 0; x < imageData.width; x++) {
            for (y = 0; y < imageData.height; y++) {
                var red = valor + getRed(imageData, x, y);
                var green = valor + getGreen(imageData, x, y);
                var blue = valor + getBlue(imageData, x, y);
                setPixel(imageData, x, y, red, green, blue, 255);
            }
        }
        ctxFiltrado.putImageData(imageData, 0, 0);

    }

    function filtroCorrect() {
        var valor = parseInt(this.value);
        imageData = ctxOriginal.getImageData(0, 0, canvasOriginal.width, canvasOriginal.height);
        for (x = 0; x < imageData.width; x++) {
            for (y = 0; y < imageData.height; y++) {
                var red = Math.pow(getRed(imageData, x, y) / 255, valor) * 255;
                var green = Math.pow(getGreen(imageData, x, y) / 255, valor) * 255;
                var blue = Math.pow(getBlue(imageData, x, y) / 255, valor) * 255;
                setPixel(imageData, x, y, red, green, blue, 255);
            }
        }
        ctxFiltrado.putImageData(imageData, 0, 0);

    }

    function filtroTransparencia() {
        var factor = parseInt(this.value);
        imageData = ctxOriginal.getImageData(0, 0, canvasOriginal.width, canvasOriginal.height);
        for (x = 0; x < imageData.width; x++) {
            for (y = 0; y < imageData.height; y++) {
                var red = getRed(imageData, x, y);
                var green = getGreen(imageData, x, y);
                var blue = getBlue(imageData, x, y);
                var total = imageData.height;
                setPixel(imageData, x, y, red, green, blue, factor + 255 * (total - y) / total);
            }
        }
        ctxFiltrado.putImageData(imageData, 0, 0);

    }

    function filtroClonar() {
        desactivarSliders();
        imageData = ctxOriginal.getImageData(0, 0, canvasOriginal.width, canvasOriginal.height);
        for (x = 0; x < (imageData.width) / 2; x++) {
            for (y = 0; y < imageData.height; y++) {
                var red = getRed(imageData, x, y);
                var green = getGreen(imageData, x, y);
                var blue = getBlue(imageData, x, y);
                setPixel(imageData, imageData.width - x, y, red, green, blue, 255);
            }
        }
        ctxFiltrado.putImageData(imageData, 0, 0);
    }

    function setPixel(imageData, x, y, r, g, b, a) {
        index = (x + y * imageData.width) * 4;
        imageData.data[index + 0] = r;
        imageData.data[index + 1] = g;
        imageData.data[index + 2] = b;
        imageData.data[index + 3] = a;
    }

    function getRed(imageData, x, y) {
        index = (x + y * imageData.width) * 4;
        return (imageData.data[index + 0]);
    }

    function getGreen(imageData, x, y) {
        index = (x + y * imageData.width) * 4;
        return (imageData.data[index + 1]);
    }

    function getBlue(imageData, x, y) {
        index = (x + y * imageData.width) * 4;
        return (imageData.data[index + 2]);
    }

    function cargarImagen(e) {
        var reader = new FileReader();
        reader.onload = function(event) {
            var img = new Image();
            img.crossOrigin = "Anonymous";
            img.onload = function() {
                var Cwidth = this.width;
                var Cheight = this.height;
                canvasOriginal.width = Cwidth;
                canvasOriginal.height = Cheight;
                canvasFiltrado.width = Cwidth;
                canvasFiltrado.height = Cheight;
                canvasMuestra.width = Cwidth;
                canvasMuestra.height = Cheight;
                canvasFinal.width = Cwidth;
                canvasFinal.height = Cheight;
                ctxMuestra.drawImage(img, 0, 0, canvasOriginal.width, canvasOriginal.height);
                ctxOriginal.drawImage(img, 0, 0, canvasOriginal.width, canvasOriginal.height);
                ctxFiltrado.fillStyle = '#FFFFFF';
                ctxFiltrado.fillRect(0, 0, canvasFiltrado.width, canvasFiltrado.height);
                ctxFinal.fillStyle = '#FFFFFF';
                ctxFinal.fillRect(0, 0, canvasFiltrado.width, canvasFiltrado.height);
            };
            img.src = event.target.result;
        };
        reader.readAsDataURL(e.target.files[0]);
        habilitarBotones();
    }

    function guardarImagenPNG() {
        var dataURL = canvasFiltrado.toDataURL("image/png");
        document.getElementById("btn-guardarPNG").href = dataURL;
    }

		function guardarImagenJPG() {
				var dataURL = canvasFiltrado.toDataURL("image/jpeg",0.7);
				document.getElementById("btn-guardarJPG").href = dataURL;
		}

    function mensajeAlerta() {
        alert("Primero cargue una imagen para realizar esta acción");
    }

    function activarSliderBrillo() {
        desactivarSliders();
        document.getElementById("boxBrillo").style.display = "block";
    }

    function activarSliderBinario() {
        desactivarSliders();
        document.getElementById("boxBinario").style.display = "block";
    }

    function activarSliderGama() {
        desactivarSliders();
        document.getElementById("boxGama").style.display = "block";
    }

    function activarSliderGamaCorrect() {
        desactivarSliders();
        document.getElementById("boxGamaCorrect").style.display = "block";
    }

    function activarSliderTransparencia() {
        desactivarSliders();
        document.getElementById("boxTransparencia").style.display = "block";
    }

    function desactivarSliders() {
        document.getElementById("boxBinario").style.display = "none";
        document.getElementById("boxGama").style.display = "none";
        document.getElementById("boxBrillo").style.display = "none";
        document.getElementById("boxGamaCorrect").style.display = "none";
        document.getElementById("boxTransparencia").style.display = "none";
    }

    function removerMensajeAlerta() {
        $("#filtro-gris").off();
        $("#filtro-sepia").off();
        $("#filtro-invertido").off();
        $("#filtro-brillo").off();
        $("#btn-guardar").off();
        $("#filtro-clonar").off();
        $("#filtro-binario").off();
        $("#filtro-gama").off();
        $("#filtro-rojo").off();
        $("#filtro-verde").off();
        $("#filtro-azul").off();
        $("#filtro-transparencia").off();
        $("#filtro-gama-correct").off();
    }

    function habilitarSliders() {
        $("#slide-brillo").on("change", filtroBrillo);
        $("#slide-binario").on("change", filtroBinario);
        $("#slide-gama-red").on("change", filtroGama);
        $("#slide-gama-green").on("change", filtroGama);
        $("#slide-gama-blue").on("change", filtroGama);
        $("#slide-gama-correct").on("change", filtroCorrect);
        $("#slide-transparencia").on("change", filtroTransparencia);
    }

    function habilitarBotones() {
        removerMensajeAlerta();
        $("#filtro-brillo").on("click", activarSliderBrillo);
        $("#filtro-binario").on("click", activarSliderBinario);
        $("#filtro-gama").on("click", activarSliderGama);
        $("#filtro-gama-correct").on("click", activarSliderGamaCorrect);
        $("#filtro-transparencia").on("click", activarSliderTransparencia);
        $("#filtro-invertido").on("click", filtroInvertido);
        $("#filtro-sepia").on("click", filtroSepia);
        $("#filtro-gris").on("click", filtroGris);
        $("#filtro-clonar").on("click", filtroClonar);
        $("#filtro-rojo").on("click", filtroRojo);
        $("#filtro-verde").on("click", filtroVerde);
        $("#filtro-azul").on("click", filtroAzul);
        $("#btn-guardarPNG").on("click", guardarImagenPNG);
				$("#btn-guardarJPG").on("click", guardarImagenJPG);
        habilitarSliders();
    }

    $("#btn-guardar").off().on("click", mensajeAlerta);
    $("#filtro-brillo").off().on("click", mensajeAlerta);
    $("#filtro-invertido").off().on("click", mensajeAlerta);
    $("#filtro-sepia").off().on("click", mensajeAlerta);
    $("#filtro-gris").off().on("click", mensajeAlerta);
    $("#filtro-clonar").off().on("click", mensajeAlerta);
    $("#filtro-binario").off().on("click", mensajeAlerta);
    $("#filtro-gama").off().on("click", mensajeAlerta);
    $("#filtro-rojo").off().on("click", mensajeAlerta);
    $("#filtro-verde").off().on("click", mensajeAlerta);
    $("#filtro-azul").off().on("click", mensajeAlerta);
    $("#filtro-gama-correct").off().on("click", mensajeAlerta);
    $("#filtro-transparencia").off().on("click", mensajeAlerta);
    $("#btn-imagen").off().on("change", cargarImagen);
    $("#pasoTres").on("click", function() {
        imageData = ctxFiltrado.getImageData(0, 0, canvasFiltrado.width, canvasFiltrado.height);
        ctxFinal.putImageData(imageData, 0, 0);
    });

});
