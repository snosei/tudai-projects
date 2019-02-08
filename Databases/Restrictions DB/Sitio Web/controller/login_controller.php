<?php
include_once 'controller/controller.php';
include_once 'view/login_view.php';
include_once 'model/login_model.php';
class LoginController extends Controller{

  function __construct() {
    $this->model = new LoginModel();
    $this->view = new LoginView();
  }


  function mostrarLogin(){
    $this->view->mostrar();
  }

  function logout(){
    session_start();
    session_destroy();
  }

  function login(){
    $data = json_decode(file_get_contents('php://input'), true);
    $email = $data['mail'];
    $pass = $data['pass'];
    $respuesta = $this->model->getPassword($email);

    if (( $respuesta != null ) & (password_verify($pass, $respuesta[0]))){
      session_start();
      $_SESSION["email"] = $email;
    }
    else{
      $this->view->mostrarError("Usuario y/o password invalidos");
      $this->view->mostrar();
    }
  }
}

?>
