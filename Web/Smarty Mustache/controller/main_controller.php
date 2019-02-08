<?php
include_once 'controller/controller.php';
include_once 'view/main_view.php';

class MainController extends Controller{

  function __construct() {
    $this->view = new MainView();
    $this->checkSesion();
  }

  function mostrarMain(){
    $this->view->mostrar();
  }

}

?>
