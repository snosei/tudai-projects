<div class="row separacion">
  <div class="col-md-6">
    <form enctype="multipart/form-data">
      <h2 class="titulo" >Distribucion</h2>
      <div class="form-group">
        <label for="nombreD">Nombre</label>
        <input type="text" class="form-control" id="nombD" name="nombD" placeholder="Nombre de La Distribucion">
      </div>
      <div class="form-group">
        <label for="link_distro">Link del Sitio Oficial</label>
        <input type="url" class="form-control" id="ldistro" name="ldistro" placeholder="link del sitio oficial de la distribucion">
      </div>
      <div class="form-group">
        <label for="arqui">Arquitectura</label>
        <select class="selectpicker" name="arqui" id="listaArqui">
          <option value="x86">Arquitectura de x86 (32 bits)</option>
          <option value="x64">Arquitectura de x64</option>
        </select>
      </div>
      <div class="form-group">
        <label for="logo">Logo</label>
        <input class="navbar-inverse" type="file" name="logo" id="logo" multiple/>
      </div>
      <div class="form-group">
        <label for="nombre">Requisito de Memoria RAM</label>
        <input type="text" class="form-control" id="ram" name="ram" placeholder="Memoria Ram Necesaria: ¿? MB">
      </div>
      <div class="form-group">
        <label for="nombre">Requisito de Memoria de Disco</label>
        <input type="text" class="form-control" id="disco" name="disco" placeholder="Memoria de Disco Necesaria: ¿? GB">
      </div>
      <div class="form-group">
        <label for="link_distro">Link de Descarga</label>
        <input type="url" class="form-control" id="ldescarga" name="ldescarga" placeholder="link de descarga">
      </div>
      <button type="submit" class="btn btn-default" id="btnAgregarDistro">Agregar Distribucion</button>
      <button type="submit" class="btn btn-default" id="btnModificarDistro" idDist="">Modificar Distribucion</button>
    </form>
  </div>
  <div class="col-md-6">
    <h2 class="titulo" >Distribuciones ya Cargadas</h2>
    <p></p>
    <div class="scroll">
      <ul class="list-group distros">

      </ul>
    </div>
  </div>
</div>
