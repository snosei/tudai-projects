<div class="row separacion ABMCat">
  <div class="col-md-6">
    <form enctype="multipart/form-data">
      <h2 class="titulo" >Categoria</h2>
      <p></p>
      <div class="form-group">
        <label for="nombre">Nombre</label>
        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre de categoria">
      </div>
      <div class="form-group">
        <label for="detalle">Detalle</label>
        <input type="text" class="form-control" id="detalle" name="detalle" placeholder="Detalle de categoria">
      </div>
      <button type="submit" class="btn btn-default" id="btnAgregarCategoria">Agregar Categoria</button>
      <button type="submit" class="btn btn-default" id="btnModificarCategoria" idCat="">Modificar Categoria</button>
    </form>
  </div>
  <div class="col-md-6">
    <h2 class="titulo" >Categorias ya Cargadas</h2>
    <p></p>
    <div class="scroll">
      <ul class="list-group categoria">

      </ul>
    </div>
  </div>
</div>
