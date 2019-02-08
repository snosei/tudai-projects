<?php
include_once 'controller/controller.php';
include_once 'view/contenido_view.php';
include_once 'model/contenido_model.php';

class ContenidoController extends Controller{


  function __construct() {
    $this->model = new ContenidoModel();
    $this->view = new ContenidoView();
  }

  function verificiarPermiso(){
    $data = json_decode(file_get_contents('php://input'), true);
    $usuario = $data['usuario'];
    $opcion = $data['opcion'];
    $resultado = $this->model->verificiarPermiso($usuario,$opcion);
    if ($resultado==true){
      $this->mostrarContenido($usuario);
    }
  }

function getLinea(){

  $data = json_decode(file_get_contents('php://input'), true);
  $idcomp = $data['idcomp'];
  $idtcomp = $data['idtcomp'];
  $resultado = $this->model->getLinea($idcomp,$idtcomp);
  $this->view->rellenarLinea($resultado);
}

  function mostrarContenido($usuario){
    if($_REQUEST["opcion"] == "ver_comprobantes"){
      $this->view->mostrarComprobantes($this->model->getSaldo($usuario),$this->model->getComprobante($usuario));
    }
    else{
      if ($_REQUEST["opcion"] == "generar_comprobante"){
        $this->view->mostrarGenerarComprobante($this->model->getTipoComprobante(),$this->model->getLugar(),$this->model->getUsuarios());
      }
    }
  }

  function agregarComprobante(){
    $data = json_decode(file_get_contents('php://input'), true);
    $idtipoc = $data['tipocomp'];
    $fecha = $data['fecha'];
    $lugar = $data['lugar'];
    $comentario = $data['comentario'];
    $estado = $data['estado'];
    $fechavenc = $data['fechavenc'];
    $persona = $data['persona'];
    $tipopago = $data['tipopago'];
    $idcomp = ($this->model->getUltimoComprobante($idtipoc));
    $this->model->agregarComprobante($idtipoc,$idcomp,$fecha,$lugar,$comentario,$estado,$fechavenc,$persona,$tipopago);
  }
}
?>
