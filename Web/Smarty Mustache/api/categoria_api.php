<?php
require_once 'api_base.php';
include_once '../model/categoria_model.php';

class CategoriaApi extends ApiBase {
  private $model;

  function __construct($request){
    parent::__construct($request);
    $this->model = new CategoriaModel();
  }

  function categoria(){
    switch ($this->method) {
      case 'GET':
        if(count($this->args) > 0){
          return $this->model->getCategoria($this->args[0]); //utilizado para retornar los valores correspondientes a la categoria a modificar
        }
        else{
          return $this->model->getCategorias(); //utilizado para visualizar las categorias en el ABM
        }
        break;
      case 'DELETE':
        if(count($this->args) > 0) return $this->model->borrarCategoria($this->args[0]);
        break;
      case 'POST':
        $data = json_decode(file_get_contents('php://input'), true);
        return $this->model->agregarCategoria($data['nombre'], $data['detalle']);
        break;
      case 'PUT':
        $data = json_decode(file_get_contents('php://input'), true);
        return $this->model->modificarCategoria($data['nombre'], $data['detalle'], $data['idCat']);
        break;
      default:
            return 'Verbo no soportado';
        break;
    }
  }
}

?>
