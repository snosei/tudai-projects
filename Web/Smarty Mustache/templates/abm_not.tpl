<div class="row separacion ABMNot">
  <div class="col-md-6">
    <form enctype="multipart/form-data">
      <h2 class="titulo" >Noticia</h2>
      <div class="form-group">
        <label for="titulo">Titulo</label>
        <input type="text" class="form-control" id="titulo" name="titulo" maxlength="50" placeholder="Titulo de la noticia">
      </div>
      <div class="form-group">
        <label for="contenido">Contenido</label>
        <textarea rows="10" type="text" class="form-control" id="contenido" name="contenido" placeholder="Contenido de la noticia" required></textarea>
      </div>
      <div class="form-group">
        <label for="imagenes">Imagenes</label>
        <input class="navbar-inverse" type="file" name="imagenes" id="imagenesNoticia" multiple/>
      </div>
      <div class="form-group">
        <label for="lista">Categorias</label>
        <select class="selectpicker dropCategoria" data-style="btn-inverse" name="lista" id="listaCategoria">

        </select>
      </div>
      <button type="submit" class="btn btn-default" id="btnAgregarNoticia">Agregar Noticia</button>
      <button type="submit" class="btn btn-default" id="btnModificarNoticia" idNot="">Modificar Noticia</button>
    </form>
  </div>
  <div class="col-md-6">
    <h2 class="titulo" >Noticias ya Cargadas</h2>
    <p></p>
    <div class="scroll">
      <ul class="list-group noticia">

      </ul>
    </div>
  </div>
</div>
