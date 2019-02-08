<?php
include_once 'controller/controller.php';
include_once 'view/contacto_view.php';
include_once 'model/contacto_model.php';

class ContactoController extends Controller{

  function __construct() {
    $this->model = new ContactoModel();
    $this->view = new ContactoView(); //genricos hay que especificarlos bien
    $this->checkSesion();
  }

  function mostrarContacto(){
    $this->view->mostrar();
  }

  function enviarMensaje(){
    // La siguiente linea agarrará el objeto JSON pasado por AJAX y lo decodificará para poder usar sus datos dentro del PHP
    $data = json_decode(file_get_contents('php://input'), true);
    $this->model->enviarMensaje($data['nombre'],$data['mail'],$data['mensaje']);
  }
}
?>
