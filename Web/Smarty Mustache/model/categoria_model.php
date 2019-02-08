<?php
include_once "model.php";
class CategoriaModel extends Model{

  function getCategorias(){
    try{
      $consulta = $this->db->prepare("SELECT * FROM categoria");
      $consulta->execute();
      return $consulta->fetchAll();
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  function getCategoria($nombre){
    try{
      $consulta = $this->db->prepare("SELECT * FROM categoria WHERE nombre=?");
      $consulta->execute(array($nombre));
      return $consulta->fetch();
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  function agregarCategoria($nombre, $detalle){
    try{
      $consulta = $this->db->prepare('INSERT INTO categoria(nombre,detalle) VALUES(?,?)');
      $consulta->execute(array($nombre,$detalle));
    }
    catch (PDOException $e){
      echo ("Ya existe la categoria a agregar, elija otro nombre");
    }
  }

  function borrarCategoria($nombre){
    try{
      $consulta = $this->db->prepare('DELETE FROM categoria WHERE nombre=?');
      $consulta->execute(array($nombre));
      if($consulta->rowCount() > 0)
        return 'Categoria borrada con exito';
      else
        return 'No se pudo borrar la categoria';
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  function modificarCategoria($nombre, $detalle, $id){
    try{
      $consulta = $this->db->prepare("UPDATE categoria set nombre = ? , detalle = ? WHERE nombre=?");
      $consulta->execute(array($nombre, $detalle, $id));
      if($consulta->rowCount() > 0){
        return 'Categoria borrada con exito';
      }
      else{
        return 'No se pudo borrar la categoria';
      }
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

}
?>
