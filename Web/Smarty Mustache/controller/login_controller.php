<?php
include_once 'controller/controller.php';
include_once 'view/login_view.php';
include_once 'model/usuarios_model.php';

class LoginController extends Controller {

  function __construct() {
    $this->model = new UsuariosModel();
    $this->view = new LoginView();
  }

  function logout(){
    session_start();
    session_destroy();
    $this->view->mostrar("");
  }

  function login(){
    $data = json_decode(file_get_contents('php://input'), true);
    $email = $data['mail'];
    $pass = $data['pass'];
    $usuario = $this->model->getUsuario($email);
    if(password_verify($pass, $usuario['password'])){
      session_start();
      $_SESSION["email"] = $email;
      $this->view->mostrar($_SESSION["email"]);
    }
    else{
      $this->view->mostrarError("Usuario y/o password invalidos");
      $this->view->mostrar("");
    }
  }
}

?>
