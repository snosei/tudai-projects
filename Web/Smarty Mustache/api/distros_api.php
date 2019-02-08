<?php
require_once 'api_base.php';
include_once '../model/distros_model.php';

class DistrosApi extends ApiBase {
  private $model;

  function __construct($request){
    parent::__construct($request);
    $this->model = new DistrosModel();
  }

  function distros(){
    switch ($this->method) {
      case 'GET':
        if(count($this->args) > 0){
          return $this->model->getDistro($this->args[0]); //utilizado para retornar los valores correspondientes a la distro a modificar
        }
        else{
          return $this->model->getTabla(); //utilizado para visualizar las distros en el ABM
        }
        break;
      case 'DELETE':
        if(count($this->args) > 0) return $this->model->borrarDistro($this->args[0]);
        break;
      case 'POST':
      // Por problemas desconocidos con el formData y las imagenes traidas desde ajax al enviar como PUT, decidimos cambiar el metodo PUT por una condicion
      // de POST que decidirá dependiendo los argumentos si dirigir al agregarNoticia o al modificarNoticia (En el cual hacemos un UPDATE correctamente)
        if(isset($_REQUEST['id_distro'])){
          return $this->model->modificarDistro($_FILES,$_REQUEST['ldistro'],$_REQUEST['nombD'],$_REQUEST['arqui'],$_REQUEST['ram'],$_REQUEST['disco'],$_REQUEST['ldescarga'],$_REQUEST['id_distro']);
        }
        else{
          return $this->model->agregarDistro($_FILES,$_REQUEST['ldistro'],$_REQUEST['nombD'],$_REQUEST['arqui'],$_REQUEST['ram'],$_REQUEST['disco'],$_REQUEST['ldescarga']);
        }
        break;
      default:
        return 'Verbo no soportado';
        break;
    }
  }
}

?>
