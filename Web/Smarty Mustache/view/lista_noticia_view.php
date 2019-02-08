<?php
include_once "view/view.php";
class ListaNoticiasView extends View {

  function mostrar($noticias){
    $this->smarty->assign("noticias",$noticias);
    $this->smarty->display('lista_noticias.tpl');
  }

}


?>
