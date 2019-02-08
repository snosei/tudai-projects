<?php
include_once "view.php";
class CategoriaView extends View{

  function mostrar($categorias){
    $this->smarty->assign('categorias', $categorias);
    $this->smarty->display('categorias.tpl');
  }


}
?>
