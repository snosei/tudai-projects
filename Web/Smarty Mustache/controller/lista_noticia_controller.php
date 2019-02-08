<?php
include_once 'controller/controller.php';
include_once 'view/lista_noticia_view.php';
include_once 'model/lista_noticia_model.php';

class ListaNoticiaController extends Controller{

  function __construct() {
    $this->model = new ListaNoticiaModel();
    $this->view = new ListaNoticiasView();
    $this->checkSesion();
  }

  function mostrarLista(){
    // Preguntará si lo que viene por el REQUEST es una categoria en especifica para mostrar sus noticias o mostrarlas todas
    if(isset($_REQUEST["fk_nombre_categoria"])){
      $this->view->mostrar($this->model->getNoticias($_REQUEST["fk_nombre_categoria"]));
    }
    else{
      $this->view->mostrar($this->model->getTotalNoticias());
    }
  }

}
?>
