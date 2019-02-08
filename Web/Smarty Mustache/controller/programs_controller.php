<?php
include_once 'controller/controller.php';
include_once 'view/programs_view.php';

class ProgramsController extends Controller{

  function __construct() {
    $this->view = new programsView();
    $this->checkSesion();
  }

  function mostrarPrograms(){
    $this->view->mostrar();
  }
}
?>
