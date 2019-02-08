<?php
include_once "view/view.php";
class NoticiaView extends View {

  function mostrar($noticia){
    $this->smarty->assign("noticia",$noticia);
    $this->smarty->display('noticia.tpl');
  }

}


?>
