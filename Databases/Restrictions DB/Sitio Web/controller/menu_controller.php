<?php
include_once 'controller/controller.php';
include_once 'view/menu_view.php';
include_once 'model/menu_model.php';
class MenuController extends Controller{

  function __construct() {
    $this->view = new MenuView();
    $this->model = new MenuModel();
  }

  function mostrarMenu(){
    session_start();
    $this->view->mostrar($this->model->getOpciones(),$_SESSION["email"]);
  }


}
?>
