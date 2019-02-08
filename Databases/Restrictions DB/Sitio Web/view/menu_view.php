<?php
include_once "view/view.php";
class MenuView extends View {

  function mostrar($opciones,$mail){
    $this->smarty->assign('opciones', $opciones);
    $this->smarty->assign('mail', $mail);
    $this->smarty->display('menu.tpl');
  }

}
?>
