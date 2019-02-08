<?php
  include_once "model/model.php";
  class MenuModel extends Model {

    function getOpciones(){
      try{
        $consulta = $this->db->prepare("SELECT * from gr01_menu");
        $consulta->execute(array());
        return $consulta->fetchAll();
      }
      catch (PDOException $e){
        echo $e->getMessage();
      }
    }

  }
  ?>
