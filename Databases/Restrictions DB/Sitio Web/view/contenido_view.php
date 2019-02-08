<?php
include_once "view/view.php";
class ContenidoView extends View {

  function mostrarComprobantes($saldo,$comp){
    $this->smarty->assign('saldo',$saldo);
    $this->smarty->assign('comprobantes',$comp);
    $this->smarty->display('comprobantes.tpl');
  }

  function mostrarGenerarComprobante($tipoc,$lugar,$usuario){
    $this->smarty->assign('tipocomprobante',$tipoc);
    $this->smarty->assign('lugares',$lugar);
    $this->smarty->assign('usuarios',$usuario);
    $this->smarty->display('generar_comprobante.tpl');
  }

  function rellenarLinea($lineas){
    $this->smarty->assign('lineas',$lineas);
    $this->smarty->display('modalContenido.tpl');
  }




}
?>
