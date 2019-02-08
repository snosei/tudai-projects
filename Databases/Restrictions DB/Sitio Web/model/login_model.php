<?php
  include_once "model/model.php";
  class LoginModel extends Model {

    function getPassword($email){
      try{
        $consulta = $this->db->prepare("SELECT ​FN_GR01_buscar_pass(?)");
        $consulta->execute(array($email));
        return $consulta->fetch();
      }
      catch (PDOException $e){
        echo $e->getMessage();
      }
    }

  }
  ?>
