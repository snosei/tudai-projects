<?php
class Model{

  protected $db;

  function __construct() {
    try{
      $this->db = new PDO('pgsql:host=dbases.exa.unicen.edu.ar;port=6432;dbname=cursada;user=unc_247736;password=247736');
      $this->db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }
}

?>
