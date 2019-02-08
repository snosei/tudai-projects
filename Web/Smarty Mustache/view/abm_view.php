<?php
include_once "view/view.php";
class ABMView extends View{

  function mostrar(){
    $this->smarty->display('abm.tpl');
  }
}
?>
