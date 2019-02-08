<div class="row">
  <h2 class="text-center titulo">Noticias</h2>
          {if count($noticias) == 0}
          <div class="row">
            <div class="text-center">
              <h1>No hay noticias cargadas en esta categoria</h1>
            </div>
          </div>
          {/if}
            <div class="row" id="noticia">
              {foreach $noticias as $noti}
                <div class="col-md-4 col-sm-6  text-center"> <!--col-xs-3-->
                    <div class="box">
                        <div class="box-content">
                            <div>
                              <a href="index.php?action=ver_noticia&id_noticia={$noti['id_noticia']}"><h3 class="tag-title">{$noti["titulo"]}</h3></a>
                              <h5>Categoria: {$noti["fk_nombre_categoria"]}</h5>
                              <h5>{$noti["fecha"]|date_format:"%A %e, %B, %Y"}</h5>
                              <img  src="{$noti['imagenes'][0]['ruta']}" alt="imagen de la noticia" class="img-responsive thumbnail"  />
                              <p>{$noti["contenido"]|truncate:80}</p>
                            </div>
                            <a href="index.php?action=ver_noticia&id_noticia={$noti['id_noticia']}" class="btn btn-gradiente ">Leer Más</a>
                        </div>
                    </div>
                </div>
                {/foreach}
            </div>
      </div>
</div>
</div>
