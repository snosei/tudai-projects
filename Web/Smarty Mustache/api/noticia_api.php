<?php
require_once 'api_base.php';
include_once '../model/noticia_model.php';

class NoticiaApi extends ApiBase {
  private $model;

  function __construct($request){
    parent::__construct($request);
    $this->model = new NoticiaModel();
  }

  function noticia(){
    switch ($this->method) {
      case 'GET':
        if(count($this->args) > 0){
          return $this->model->getNoticiaUnica($this->args[0]); //utilizado para retornar los valores correspondientes a la noticia a modificar
        }
        else{
          return $this->model->getTodasNoticias(); //utilizado para visualizar las noticias en el ABM
        }
        break;
      case 'DELETE':
        if(count($this->args) > 0) return $this->model->borrarNoticia($this->args[0]);
        break;
      case 'POST':
      // Por problemas desconocidos con el formData y las imagenes traidas desde ajax al enviar como PUT, decidimos cambiar el metodo PUT por una condicion
      // de POST que decidirá dependiendo los argumentos si dirigir al agregarNoticia o al modificarNoticia (En el cual hacemos un UPDATE correctamente)
        if(isset($_REQUEST['id_noticia'])){
          return $this->model->modificarNoticia($_REQUEST['titulo'], $_REQUEST['contenido'], $_FILES, $_REQUEST['lista'],$_REQUEST['id_noticia']);
        }
        else{
          return $this->model->agregarNoticia($_REQUEST['titulo'], $_REQUEST['contenido'], $_FILES, $_REQUEST['lista']);
        }
        break;
      default:
            return 'Verbo no soportado';
        break;
    }
  }
}

?>
