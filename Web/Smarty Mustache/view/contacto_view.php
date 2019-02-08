<?php
include_once "view/view.php";
class ContactoView extends View {

  function mostrar(){
    $this->smarty->display('contacto.tpl');
  }

}


?>
