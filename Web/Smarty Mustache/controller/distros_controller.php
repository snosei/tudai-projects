<?php
include_once 'controller/controller.php';
include_once 'view/distros_view.php';
include_once 'model/distros_model.php';

class DistrosController extends Controller{

  function __construct() {
    $this->model = new DistrosModel();
    $this->view = new DistrosView();
    $this->checkSesion();
  }

  function mostrarDistros(){
    $this->view->mostrar($this->model->getTabla());
  }
}
?>
