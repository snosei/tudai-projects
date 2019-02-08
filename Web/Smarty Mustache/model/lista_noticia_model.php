<?php
include_once "model/model.php";
class ListaNoticiaModel extends Model{

  function getNoticias($categoria){
    //Al agregar "order by fecha desc" podre ordenarlo por fecha, teniendo en cuenta que el ultimo siempre va a ser el mas actual
    try{
      $noticias = array();
      $consulta = $this->db->prepare("SELECT * FROM noticia WHERE fk_nombre_categoria=? order by fecha desc");
      $consulta->execute(array($categoria));
      while($noticia = $consulta->fetch()){
        $consulta1 = $this->db->prepare("SELECT * FROM imagen where fk_id_noticia=?");
        $consulta1->execute(array($noticia['id_noticia']));
        $imagenes = $consulta1->fetchAll();
        $noticia['imagenes'] = $imagenes;
        $noticias[] = $noticia;
      }
      return $noticias;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

  function getTotalNoticias(){
    try{
      $noticias = array();
      $consulta = $this->db->prepare("SELECT * FROM noticia order by fecha desc");
      $consulta->execute();
      while($noticia = $consulta->fetch()){
        $consulta1 = $this->db->prepare("SELECT * FROM imagen where fk_id_noticia=?");
        $consulta1->execute(array($noticia['id_noticia']));
        $imagenes = $consulta1->fetchAll();
        $noticia['imagenes'] = $imagenes;
        $noticias[] = $noticia;
      }
      return $noticias;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }
}
?>
