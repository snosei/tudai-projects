<?php
include_once 'controller/controller.php';
include_once 'view/index_view.php';
class IndexController extends Controller{

  function __construct() {
    $this->view = new indexView();
  }

  function mostrarHome(){
    $this->view->mostrar();


  }
}
?>
