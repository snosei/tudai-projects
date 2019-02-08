<?php
include_once "model/model.php";
class ContactoModel extends Model{

  function enviarMensaje($nombre, $mail, $mensaje){
    try{
      $consulta = $this->db->prepare('INSERT INTO mensaje(nombre,mail,mensaje) VALUES(?,?,?)');
      $consulta->execute(array($nombre, $mail, $mensaje));
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

}
?>
