<?php
include_once "view/view.php";
class IndexView extends View {

  function mostrar(){
    $this->smarty->display('index.tpl');
  }

}
?>
