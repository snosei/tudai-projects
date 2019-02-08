<div class="col-md-12 ABM">
  <form enctype="multipart/form-data">
    <h2 class="titulo" >Comprobante</h2>
    <div class="form-group">
      <label for="listatipo">Tipo Comprobante</label>
      <select class="selectpicker tipocomp" data-style="btn-inverse" name="listatipo" id="listaTipo" required>
        {if count($tipocomprobante) gt 0}
          {foreach $tipocomprobante as $tipoc}
          <option idtipoc={$tipoc['id_tcomp']} value="{$tipoc['id_tcomp']}">{$tipoc['tipo']}</option>
          {/foreach}
        {/if}
      </select>
    </div>
    <div class="form-group">
      <label for="fecha">Fecha</label>
      <input type="text" class="form-control input-append date" data-date-format='mm-dd-yyyy' id="fecha" name="fecha" maxlength="50" placeholder="Fecha de Emision del comprobante" required>
    </div>
    <div class="form-group">
      <label for="listalugar">Lugar</label>
      <select class="selectpicker lugar" data-style="btn-inverse" name="listalugar" id="listaLugar" required>
        {if count($lugares) gt 0}
          {foreach $lugares as $lug}
          <option idlugar={$lug['id_lugar']} value="{$lug['id_lugar']}">{$lug['nombre']}</option>
          {/foreach}
        {/if}
      </select>
    </div>
    <div class="form-group">
      <label for="comentario">Comentario</label>
      <textarea rows="5" type="text" class="form-control" id="comentario" name="comentario" placeholder="Comentario del comprobante" required></textarea>
    </div>
    <div class="form-group">
      <label for="estado">Estado</label>
      <input type="text" class="form-control" id="estado" name="estado" maxlength="50" placeholder="Estado del comprobante" required>
    </div>
    <div class="form-group">
      <label for="fechavenc">Fecha Vencimiento</label>
      <input type="text" class="form-control" id="fechavenc" name="fechavenc" maxlength="50" placeholder="Fecha de Vencimiento del comprobante" required>
    </div>
    <div class="form-group">
      <label for="persona">Persona a agregar el comprobante</label>
      <select class="selectpicker persona" data-style="btn-inverse" name="listapersona" id="listapersona" required>
        {if count($usuarios) gt 0}
          {foreach $usuarios as $us}
          <option idpersona={$us['id_persona']} value="{$us['id_persona']}">{$us['nomap']}</option>
          {/foreach}
        {/if}
      </select>
    </div>
    <div class="form-group">
      <label for="tipop">Tipo Pago</label>
      <select class="selectpicker tipop" data-style="btn-inverse" name="listatipop" id="listatipop" required>
          <option idtipop=1 value="1">Credito</option>
          <option idtipop=2 value="2">Debito</option>
      </select>
    </div>
    <button type="submit" class="btn btn-default" id="btnAgregarComprobante">Agregar Comprobante</button>
  </form>
</div>
