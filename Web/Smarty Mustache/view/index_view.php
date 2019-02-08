<?php
include_once "view/view.php";
class IndexView extends View {

  function mostrar($categorias){
    $this->smarty->assign('categorias',$categorias);
    $this->smarty->display('index.tpl');
  }

}
?>
