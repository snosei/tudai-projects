	function Pieza(w, h, imagen, id,cX,cY) {
	    this.posX = 0;
	    this.posY = 0;
	    this.width = w;
	    this.height = h;
	    this.imagen = imagen;
	    this.id = id;
	    this.clipX = cX;
	    this.clipY = cY;
	}

	Pieza.prototype.render = function(ctx) {
		ctx.drawImage(this.imagen,this.clipX, this.clipY, this.width, this.height ,this.posX, this.posY, this.width, this.height );
	};

	Pieza.prototype.addPos = function(x,y) {
		this.posX = x;
	    this.posY = y;
	};

	function Celda(x, y, w, h) {
	    this.posX = x;
	    this.posY = y;
	    this.width = w;
	    this.height = h;
	    this.id = -1;
	}

	Celda.prototype.render = function(ctx) {
	    ctx.lineWidth = 0.5;
	    ctx.strokeRect(this.posX, this.posY, this.width, this.height);
	};

	Pieza.prototype.check = function(ptoX, ptoY) {
	    return (((ptoX >= this.posX) && (ptoX <= (this.posX + this.width))) && ((ptoY >= this.posY) && (ptoY <= (this.posY + this.height))));
	};

	Celda.prototype.checkContiene = function(forma) {
	    return (forma.check(this.posX + (this.width / 2), this.posY + (this.height / 2)));
	};

	Celda.prototype.check = function(ptoX, ptoY) {
	    return (((ptoX >= this.posX) && (ptoX <= (this.posX + this.width))) && ((ptoY >= this.posY) && (ptoY <= (this.posY + this.height))));
	};
