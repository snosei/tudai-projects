<?php
  include_once 'config/config_app.php';
  include_once 'controller/index_controller.php';
  include_once "controller/main_controller.php";
  include_once "controller/distros_controller.php";
  include_once "controller/programs_controller.php";
  include_once "controller/contacto_controller.php";
  include_once "controller/abm_controller.php";
  include_once "controller/lista_noticia_controller.php";
  include_once "controller/noticia_controller.php";
  include_once "controller/contenido_controller.php";
  include_once "controller/login_controller.php";

  if(!array_key_exists(ConfigApp::$ACTION, $_REQUEST) ||
  $_REQUEST[ConfigApp::$ACTION] == ConfigApp::$ACTION_DEFAULT)
  {
    $IndexController = new IndexController();
    $IndexController->mostrarHome();
  }
  else {
    switch ($_REQUEST[ConfigApp::$ACTION]) {
      case ConfigApp::$ACTION_VER_MAIN:
        $MainController = new MainController();
        $MainController->mostrarMain();
        break;
      case ConfigApp::$ACTION_VER_DISTROS:
        $DistrosController = new DistrosController();
        $DistrosController->mostrarDistros();
        break;
      case ConfigApp::$ACTION_VER_PROGRAMS:
        $ProgramsController = new ProgramsController();
        $ProgramsController->mostrarPrograms();
        break;
      case ConfigApp::$ACTION_VER_CONTACTO:
        $ContactoController = new ContactoController();
        $ContactoController->mostrarContacto();
        break;
      case ConfigApp::$ACTION_ENVIAR_MENSAJE:
        $ContactoController = new ContactoController();
        $ContactoController->enviarMensaje();
        break;
      case ConfigApp::$ACTION_LISTAR_NOTICIAS:
        $ListaNoticiaController = new ListaNoticiaController();
        $ListaNoticiaController->mostrarLista();
        break;
      case ConfigApp::$ACTION_VER_NOTICIA:
        $NoticiaController = new NoticiaController();
        $NoticiaController->mostrarNoticia();
        break;
      case ConfigApp::$ACTION_VER_ABM:
        $ABMController = new ABMController();
        $ABMController->mostrarABM();
        break;
      case ConfigApp::$ACTION_MOSTRAR_CONTENIDO:
        $ContenidoController = new ContenidoController();
        $ContenidoController->mostrarContenido();
        break;
      case ConfigApp::$ACTION_LOGIN:
        $ContenidoController = new LoginController();
        $ContenidoController->login();
        break;
      case ConfigApp::$ACTION_LOGOUT:
        $ContenidoController = new LoginController();
        $ContenidoController->logout();
        break;
      default:
        echo 'Pagina no encontrada';
        break;
    }
  }

?>
