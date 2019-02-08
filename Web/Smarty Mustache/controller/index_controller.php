<?php
include_once 'controller/controller.php';
include_once 'view/index_view.php';
include_once 'model/categoria_model.php';

class IndexController extends Controller{

  function __construct() {
    $this->model = new CategoriaModel();
    $this->view = new indexView();
    $this->checkSesion();
  }

  function mostrarHome(){
    $this->view->mostrar($this->model->getCategorias());
  }
}
?>
