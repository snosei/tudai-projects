{foreach $categorias as $categoria}
  <li class="subdrop" href="index.php?action=listar_noticias&fk_nombre_categoria={$categoria['nombre']}"><a href="#">{$categoria['nombre']}</a></li>
{/foreach}
<li class="divider"></li>
<li class="subdrop" href="index.php?action=listar_noticias"><a href="#">Todas las Noticias</a></li>
