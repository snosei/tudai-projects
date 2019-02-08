<?php
require_once 'categoria_api.php';
require_once 'noticia_api.php';
require_once 'distros_api.php';

$url_elements = explode('/', rtrim($_REQUEST['parametros'], '/'));  // divido la accion con los parametros
if(count($url_elements)>0){                                         // si existen parametros actúo
  $api_name = ucfirst($url_elements[0]) . 'Api';
  if (!($api_name == 'Api') && class_exists($api_name)) { //que sea API + categoria/noticia
      $api = new $api_name($_REQUEST['parametros']); //api es un nuevo api cat según nombre parametro
      echo $api->processAPI();
      return;
  }
}

echo "No endpoint ".$url_elements[0];
?>
