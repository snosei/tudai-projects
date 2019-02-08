<?php
include_once 'controller/controller.php';
include_once 'view/noticia_view.php';
include_once 'model/noticia_model.php';

class NoticiaController extends Controller{

  function __construct() {
    $this->model = new NoticiaModel();
    $this->view = new NoticiaView();
    $this->checkSesion();
  }

  function mostrarNoticia(){
    $this->view->mostrar($this->model->getNoticia($_REQUEST["id_noticia"]));
  }

}

?>
