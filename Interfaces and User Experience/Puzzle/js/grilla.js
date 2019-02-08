	function Grilla(x, y, w, h, cantCeldas) {
	    this.posX = x;
	    this.posY = y;
	    this.width = w;
	    this.height = h;
	    this.celdas = new Array(cantCeldas);
	    for (var i = 0; i < cantCeldas; i++) {
	        this.celdas[i] = new Array(cantCeldas);
	        var width = this.width / cantCeldas;
	        var height = this.height / cantCeldas;
	        for (var j = 0; j < cantCeldas; j++) {
	            this.celdas[i][j] = new Celda((this.posX) + (width * j), (this.posY) + (height * i), width, height);
	        }
	    }
	}

	Grilla.prototype.render = function(ctx) {
	    var cantCeldas = this.celdas.length;
	    for (var i = 0; i < cantCeldas; i++) {
	        for (var j = 0; j < cantCeldas; j++) {
	            this.celdas[i][j].render(ctx);
	        }
	    }
	};
	
	Grilla.prototype.checkWin = function() {
		var solucion = true;
		var i = 0;
		var id = 0;
	    var cantCeldas = this.celdas.length;
	    while ( (i < cantCeldas)&&(solucion === true) ) {
			var j = 0;
	        while (( j < cantCeldas)&&(solucion === true)) {
	            if(this.celdas[i][j].id != id){
					solucion = false;
				}
				id++;
				j++;
	        }
			i++;
	    }
		return solucion;
	};
