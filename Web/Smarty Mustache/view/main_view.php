<?php
include_once "view/view.php";
class MainView extends View {

  function mostrar(){
    $this->smarty->display('main.tpl');
  }
}
?>
