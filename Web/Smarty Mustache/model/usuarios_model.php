<?php
include_once "model/model.php";

class UsuariosModel extends Model {

  function getUsuario($email){
    try{
      $consulta = $this->db->prepare("SELECT * FROM usuario WHERE email = ?");
      $consulta->execute(array($email));
      return $consulta->fetch();
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

}
?>
