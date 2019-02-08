<?php
include_once 'controller/controller.php';
include_once 'view/abm_view.php';

class ABMController extends Controller {

 function __construct() {
    $this->view = new ABMView();
  }

  function mostrarABM(){
    $this->view->mostrar();
  }
}
?>
