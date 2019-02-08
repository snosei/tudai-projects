<?php
include_once "view/view.php";
class ProgramsView extends View {

  function mostrar(){
    $this->smarty->display('programs.tpl');
  }

}


?>
