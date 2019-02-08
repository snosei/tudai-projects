<?php
include_once "model.php";
class ContenidoModel extends Model{

  function verificiarPermiso($usuario,$opcion){
    try {
    $result=$this->db->prepare('SELECT fn_gr01_verificar_permiso(?,?)');
    $result->execute(array($usuario,$opcion));
    if ($result==true){
        return true;
    }
    else{
        throw new PDOException();
    }
    } catch (PDOException $e) {
      echo $e->getMessage();
    }
  }

  function getSaldo($usuario){
    try{

      $saldo = $this->db->prepare("SELECT p.saldo
                                    from gr01_mail m
                                    join gr01_persona p on (m.id_persona = p.id_persona)
                                    where (m.mail like (?))");
      $saldo->execute(array($usuario));
      $saldo = $saldo->fetch();
      return $saldo;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
  }

function getComprobante($usuario){
  try{
      $consulta = $this->db->prepare("SELECT c.fecha, c.fecha_vencimiento, c.comentario, l.nombre, c.tipo_pago, c.estado, c.importe_total, c.id_comp , c.id_tcomp
                                from gr01_mail m
                                join gr01_persona p on (m.id_persona = p.id_persona)
                                join gr01_comprobante c on (p.id_persona = c.id_persona)
                                join gr01_lugar l on (l.id_lugar = c.id_lugar)
                                where (m.mail like (?))
                                ORDER BY c.fecha desc");

      $consulta->execute(array($usuario));
      $consulta = $consulta->fetchAll();
      return $consulta;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
}

function getUltimoComprobante($idtcomp){
  try{
      $consulta = $this->db->prepare("SELECT max(id_comp)+1 as ultimoid
                                      from gr01_comprobante
                                      where id_tcomp = (?)");
      $consulta->execute(array($idtcomp));
      $consulta = $consulta->fetch();
      return $consulta;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
}

function agregarComprobante($idtipoc,$idcomp,$fecha,$lugar,$comentario,$estado,$fechavenc,$persona,$tipopago){
  try{
      $consulta = $this->db->prepare("INSERT into gr01_comprobante(id_comp,id_tcomp,fecha,id_lugar,comentario,estado,fecha_vencimiento,id_persona,tipo_pago,importe_total)
                                      values(?,?,?,?,?,?,?,?,?,0.00000)");
      $consulta->execute(array($idcomp['ultimoid'],$idtipoc,$fecha,$lugar,$comentario,$estado,$fechavenc,$persona,$tipopago));
      $consulta = $consulta->fetchAll();
      return $consulta;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
}

function getLinea($idcomp , $idtcomp){
  try{
      $consulta = $this->db->prepare("SELECT l.nro_linea , l.descripcion, l.cantidad, l.importe
                                      from gr01_LineaComprobante l
                                      where (l.id_comp = (?) and l.id_tcomp = (?))
                                    ");

      $consulta->execute(array($idcomp,$idtcomp));
      $consulta = $consulta->fetchAll();
      return $consulta;
    }
    catch (PDOException $e){
      echo $e->getMessage();
    }
}

function getTipoComprobante(){
  try{
    $consulta = $this->db->prepare("SELECT * from gr01_tipocomprobante");
    $consulta->execute(array());
    $consulta = $consulta->fetchAll();
    return $consulta;
  }
  catch (PDOException $e){
    echo $e->getMessage();
  }
}

function getLugar(){
  try{
    $consulta = $this->db->prepare("SELECT * from gr01_lugar");
    $consulta->execute(array());
    $consulta = $consulta->fetchAll();
    return $consulta;
  }
  catch (PDOException $e){
    echo $e->getMessage();
  }
}

function getUsuarios(){
  try{
    $consulta = $this->db->prepare("SELECT distinct(p.id_persona), p.nombre ||','|| p.apellido as nomap
                                    from gr01_mail m
                                    join gr01_persona p on (p.id_persona = m.id_persona)
                                    join gr01_rol r on (p.id_rol = r.id_rol)
                                    where r.nombre like 'C' and p.tipo like 'Cliente'");
    $consulta->execute(array());
    $consulta = $consulta->fetchAll();
    return $consulta;
  }
  catch (PDOException $e){
    echo $e->getMessage();
  }
}


}
?>
