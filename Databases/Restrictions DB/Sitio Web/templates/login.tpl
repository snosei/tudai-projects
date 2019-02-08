
    <h1> Login</h1>
    <form class="form-signin" method="POST" action="index.php?action=login">
        <input type="email" name="txtEmail" id="txtEmail" class="username" placeholder="Email" required autofocus>
        <input type="password" name="txtPassword" id="txtPassword" placeholder="Password" required>
        <button type="submit" class="btnlogin">Sign me in</button>
    {if count($errores) gt 0}
        <div class="alert alert-danger" role="alert">
        <span class="error" >+</span>
        {foreach $errores as $error}
          {$error}
        {/foreach}
        </div>
    {/if}
   </form>
