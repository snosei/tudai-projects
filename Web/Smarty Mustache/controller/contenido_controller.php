<?php
include_once 'controller/controller.php';
include_once 'view/categoria_view.php';
include_once 'model/categoria_model.php';
include_once 'view/login_view.php';

class ContenidoController extends Controller{

  private $view2;

  function __construct() {
    $this->model = new CategoriaModel();
    $this->view = new CategoriaView();
    $this->view2 = new LoginView();
    $this->checkSesion();
  }

  function mostrarContenido(){
    if($_REQUEST["contenido"] == "categorias"){
      $this->view->mostrar($this->model->getCategorias());
    }
    else{
      if ($_REQUEST["contenido"] == "login"){
        if(!isset($_SESSION["email"])){
          $this->view2->mostrar("");
        }
        else{
          $this->view2->mostrar($_SESSION["email"]);
        }
      }
    }
  }
}
?>
