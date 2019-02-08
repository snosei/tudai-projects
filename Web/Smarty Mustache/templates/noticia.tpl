{foreach $noticia as $noti}
<div class="panel">
    <div class="panel-heading">
        <div class="text-center">
            <div class="row">
                <div class="col-sm-9">
                  <h2 class="titulo">{$noti["titulo"]}</h2>
                </div>
                <div class="col-sm-3">
                  <h4>
                  <small><em>{$noti["fecha"]|date_format:"%A %e, %B, %Y"}</em></small>
                  </h4>
                </div>
            </div>
        </div>
    </div>
    <div class="panel-body">
      <p> {$noti["contenido"]}</p>
      <p> </p>
      <div class="container-fluid">
      <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
          <!-- Indicators -->
          <ol class="carousel-indicators">
            {foreach $noti["imagenes"] as $imagen}
              <li data-target="#carousel-example-generic" data-slide-to="{$imagen[id]}" ></li> <!--class="active"-->
            {/foreach}
          </ol>
          <!-- Wrapper for slides -->
          <div class="carousel-inner" role="listbox">
            {foreach $noti["imagenes"] as $imagen}
            <div class="item"> <!--active-->
              <img class="img-noticia" src="{$imagen['ruta']}" />
            </div>
            {/foreach}
          </div>
          <!-- Controls -->
          <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
          <span class="sr-only">Anterior</span>
          </a>
          <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
          <span class="sr-only">Siguiente</span>
          </a>
        </div>
      </div>
    </div>
    </div>
  </div>


    <div class="panel-footer">
      <span class="label label-default">Noticias</span> <span class="label label-default">></span> <span class="label label-default">{$noti["fk_nombre_categoria"]}</span>
    </div>
</div>
{/foreach}
