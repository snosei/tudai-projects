<div class="row">
  <h2 class="text-center">Comprobantes</h2>
<h3 class="text-left">SALDO CTA CTE: {$saldo[0]}</h3>



{if count($comprobantes) == 0}
<div class="row">
  <div class="text-center">
    <h1>No hay comprobantes cargadas</h1>
  </div>
</div>
{else}
  <div class="row table-responsive text-center" >
    <table class="table table-inverse table-responsive">
      <thead>
        <tr>
          <th></th>
          <th>Fecha de Emicion     </th>
          <th>Fecha de Vencimiento      </th>
          <th>Ciudad               </th>
          <th>Estado               </th>
          <th>Comentario           </th>
          <th>Tipo de Pago         </th>
          <th>Monto                </th>


        </tr>
      </thead>
      <tbody>

    {foreach $comprobantes as $comp}

        <tr>
          <th class="glyphicon glyphicon-eye-open rowLinea" scope="row" idcomp="{$comp['id_comp']}"  idtcomp="{$comp['id_tcomp']}" href="#"><a href="#">a</a></th>
          <td>{$comp['fecha']|date_format:"%A %e, %B, %Y"}</td>
          <td>{$comp['fecha_vencimiento']|date_format:"%A %e, %B, %Y"}</td>
          <td>{$comp['nombre']}</td>
          <td>{$comp['estado']}</td>
          <td>{$comp['comentario']}</td>
          <td>{$comp['tipo_pago']}</td>
          <td>{$comp['importe_total']}</td>

        </tr>
      {/foreach}
    </tbody>
    </table>
  </div>
  {/if}


  <div class="row" id="AjaxModal">


  </div>


</div>
</div>
</div>
