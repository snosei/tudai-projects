<?php

class Controller{

  protected $model;
  protected $view;

  function checkSesion(){
    $time = 600;
    session_start();
    if(isset($_SESSION["email"])){
      if(isset($_SESSION["expire"]) && time() > $_SESSION["expire"] + $time){
        session_destroy();
      }
      else{
        $_SESSION["expire"] = time();
      }
    }
  }

}

?>
