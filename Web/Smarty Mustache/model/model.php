<?php
class Model{

  protected $db;

  function __construct() {
    try{
      $this->db = new PDO('mysql:host=localhost;dbname=linuxdatabase;charset=utf8', 'root', '');
      $this->db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

}

?>
