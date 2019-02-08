<?php
include_once 'config/config_app.php';
include_once 'controller/index_controller.php';
include_once 'controller/menu_controller.php';
include_once 'controller/login_controller.php';
include_once 'controller/contenido_controller.php';

if(!array_key_exists(ConfigApp::$ACTION, $_REQUEST) ||
$_REQUEST[ConfigApp::$ACTION] == ConfigApp::$ACTION_DEFAULT)
{
  $IndexController = new IndexController();
  $IndexController->mostrarHome();
}
else {
  switch ($_REQUEST[ConfigApp::$ACTION]) {
    case ConfigApp::$ACTION_VER_MENU:
      $MenuController = new MenuController();
      $MenuController->mostrarMenu();
      break;
    case ConfigApp::$ACTION_VER_LOGIN:
      $LoginController = new LoginController();
      $LoginController->mostrarLogin();
      break;
    case ConfigApp::$ACTION_VER_CONTENIDO:
      $ContenidoController = new ContenidoController();
      $ContenidoController->verificiarPermiso();
      break;
    case ConfigApp::$ACTION_LOGIN:
      $LoginController = new LoginController();
      $LoginController->login();
      break;
    case ConfigApp::$ACTION_LOGOUT:
      $LoginController = new LoginController();
      $LoginController->logout();
      break;
    case ConfigApp::$ACTION_VER_LINEAS:
      $LineaController = new ContenidoController();
      $LineaController->getLinea();
      break;
    case ConfigApp::$ACTION_AGREGAR_COMPROBANTE:
      $AComprobanteController = new ContenidoController();
      $AComprobanteController->agregarComprobante();
      break;

    default:
      echo 'Pagina no encontrada';
      break;
  }
}
?>
