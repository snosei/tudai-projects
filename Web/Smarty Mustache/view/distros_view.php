<?php
include_once "view/view.php";
class DistrosView extends View {

  function mostrar($distros){
    $this->smarty->assign("distros",$distros);
    $this->smarty->display('distros.tpl');
  }

}


?>
