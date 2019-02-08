<?php
include_once 'controller/controller.php';
include_once 'view/categoria_view.php';
include_once 'model/categoria_model.php';

class CategoriaController extends Controller {

 function __construct() {
    $this->model = new CategoriaModel();
    $this->view = new CategoriaView();
    $this->checkSesion();
  }

  function mostrarCategorias(){
    $this->view->mostrar($this->model->getCategorias());
  }

  function agregarCategoria(){
    // La siguiente linea agarrará el objeto JSON pasado por AJAX y lo decodificará para poder usar sus datos dentro del PHP
    $data = json_decode(file_get_contents('php://input'), true);
    $this->model->agregarCategoria($data['nombre'], $data['detalle']);
  }

}
?>
