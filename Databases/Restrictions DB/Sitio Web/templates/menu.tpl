<ul  class="nav navbar-nav navbar-left">

  {if count($opciones) gt 0}
    {foreach $opciones as $opcion}
      <li class="opcion" idopcion ="{$opcion['id_menu']}" href="index.php?action=ver_contenido&opcion={$opcion['nombre']|lower|replace:" ":"_"}"><a href="*">
        {$opcion['nombre']}
      </a></li>
    {/foreach}
  {/if}

</ul>
<ul class="nav navbar-nav navbar-right">


  <div class="navbar-header"><a class="navbar-brand usuario" user ={$mail} href="#">{$mail}</a></div>
  <li class="logout" href="index.php?action=logout"><a alt="Salir" title="Salir" href="*" class="glyphicon glyphicon-off"></a></li>

</ul>
