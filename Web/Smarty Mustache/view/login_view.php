<?php
include_once "view/view.php";
class LoginView extends View {

  function mostrar($mail){
    $this->smarty->assign('email', $mail);
    $this->smarty->assign('errores', $this->errores);
    $this->smarty->display('login.tpl');
  }
}
?>
