<?php
include_once "model.php";
class NoticiaModel extends Model{

  function getNoticia($id_noticia){
    try{
      $noticia = array();
      $consulta = $this->db->prepare("SELECT * FROM noticia WHERE id_noticia=?");
      $consulta->execute(array($id_noticia));
      while($noti = $consulta->fetch()){
        $consulta1 = $this->db->prepare("SELECT * FROM imagen where fk_id_noticia=?");
        $consulta1->execute(array($noti['id_noticia']));
        $imagenes = $consulta1->fetchAll();
        $noti['imagenes'] = $imagenes;
        $noticia[] = $noti;
      }
      return $noticia;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  function getNoticiaUnica($id){
    try{
      $consulta = $this->db->prepare("SELECT * FROM noticia WHERE id_noticia=?");
      $consulta->execute(array($id));
      return $consulta->fetch();
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  function getTodasNoticias(){
    try{
      $consulta = $this->db->prepare("SELECT * FROM noticia order by fecha desc");
      $consulta->execute();
      return $consulta->fetchAll();
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

  function agregarNoticia($titulo, $contenido, $files,$lista){
    try{
      $rutas = $this->subirArchivos($files);
      $consulta = $this->db->prepare('INSERT INTO noticia(titulo,contenido,fk_nombre_categoria) VALUES(?,?,?)');
      $consulta->execute(array($titulo,$contenido,$lista));
      $id_noticia = $this->db->lastInsertId();
      foreach ($rutas as $ruta) {
        $imagenes = $this->db->prepare('INSERT INTO imagen(fk_id_noticia,ruta) VALUES(?,?)');
        $imagenes->execute(array($id_noticia, $ruta));
      }
      return $id_noticia;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  function modificarNoticia($titulo, $contenido, $files,$lista,$id){
    try{
      $rutas = $this->subirArchivos($files);
      $consulta = $this->db->prepare('UPDATE noticia set titulo = ? , contenido = ?, fk_nombre_categoria = ? WHERE id_noticia=?');
      $consulta->execute(array($titulo,$contenido,$lista,$id));
      $consulta2 = $this->db->prepare('DELETE FROM imagen WHERE fk_id_noticia=?');
      $consulta2->execute(array($id));
      foreach ($rutas as $ruta) {
        $imagenes = $this->db->prepare('INSERT INTO imagen(fk_id_noticia,ruta) VALUES(?,?)');
        $imagenes->execute(array($id, $ruta));
      }
      return $id;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  function borrarNoticia($id){
    try{
      $consulta = $this->db->prepare('DELETE FROM noticia WHERE id_noticia=?');
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

}
?>
