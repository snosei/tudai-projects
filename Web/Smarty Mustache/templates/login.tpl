{if $email == ""}
<div class="col-lg-12 login">
  <div class="log text-center"><h3><b>Login</b></h3></div>
  <form class="form-signin" method="POST" action="index.php?action=login">
    <div class="form-group">
      <label class = "log" for="txtEmail">Email</label>
      <input type="email" name="txtEmail" id="txtEmail" class="form-control" placeholder="Email" required autofocus>
    </div>
    <div class="form-group">
      <label class = "log" for="txtPassword">Password</label>
      <input type="password" name="txtPassword" id="txtPassword" class="form-control" placeholder="Password" required>
    </div>

    <div class="form-group">
      <button class="btn btn-gradiente" id="btnLogueo" type="submit">Acceder</button>
    </div>
    {if count($errores) gt 0}
      <div class="alert alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error:</span>
        {foreach $errores as $error}
          {$error}
        {/foreach}
      </div>
    {/if}
  </form>
</div>
{else}
  <div class="log text-center titulo"><h4><b>Bienvenido {$email}</b></h4></div>
  <li class="text-center subdrop abm" href="index.php?action=ver_abm"><a href="#"><b>CRUD</b></a></li>
  <div class=text-center ><button class="btn btn-gradiente" id="btnLogout">Salir</button></div>
{/if}
