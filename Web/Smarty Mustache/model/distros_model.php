<?php
include_once "model.php";
class DistrosModel extends Model{

  function getTabla(){
    try{
      $consulta = $this->db->prepare("SELECT * FROM distro");
      $consulta->execute();
      return $consulta->fetchAll();
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  function getDistro($id){
    try{
      $consulta = $this->db->prepare("SELECT * FROM distro WHERE id_distro=?");
      $consulta->execute(array($id));
      return $consulta->fetch();
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  private function subirArchivos($files){
    try{
      $destinos = array();
      foreach ($files as $file){
        $destino = "uploaded/images/".uniqid().$file["name"];
        move_uploaded_file($file["tmp_name"],"../".$destino);
        $destinos[]=$destino;
      }
      return $destinos;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  function agregarDistro($logo,$ldistro,$nombre,$arqui,$ram,$disco,$ldescarga){
    try{
      $ruta = $this->subirArchivos($logo);
      $consulta = $this->db->prepare('INSERT INTO distro(logo,link_distro,nombre,arquitectura,requisito_memoria,requisito_disco,link_descarga) VALUES(?,?,?,?,?,?,?)');
      $consulta->execute(array($ruta[0],$ldistro,$nombre,$arqui,$ram,$disco,$ldescarga));
      $id = $this->db->lastInsertId();
      return $id;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  function borrarDistro($id){
    try{
      $consulta = $this->db->prepare('DELETE FROM distro WHERE id_distro=?');
      $consulta->execute(array($id));
      if($consulta->rowCount() > 0)
        return 'Noticia borrada con exito';
      else
        return 'No se pudo borrar la noticia';
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  function modificarDistro($logo,$ldistro,$nombre,$arqui,$ram,$disco,$ldescarga,$id){
    try{
      $ruta = $this->subirArchivos($logo);
      $consulta = $this->db->prepare('UPDATE distro set logo = ?, link_distro = ? , nombre = ?, arquitectura = ?, requisito_memoria = ?, requisito_disco = ?, link_descarga = ? WHERE id_distro=?');
      $consulta->execute(array($ruta[0],$ldistro,$nombre,$arqui,$ram,$disco,$ldescarga,$id));
      return $id;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }
  
}
?>
