<div class="row table-responsive text-center" >
  <table class="table table-inverse table-responsive">
    <thead>
      <tr>
        <th>#</th>
        <th>descripcion</th>
        <th>cantidad</th>
        <th>importe</th>
      </tr>
    </thead>
    <tbody>
      {foreach $lineas as $l}
      <tr>
        <th scope="row">{$l['nro_linea']}</th>
        <td>{$l['descripcion']}</td>
        <td>{$l['cantidad']}</td>
        <td>{$l['importe']}</td>
      </tr>
      {/foreach}

      </tbody>
    </table>
  </div>
