<html>
  <head>
    <title>Welcome to Xiconis da Game</title>
    <meta name="layout" content="main" />

  </head>
  <body>
    <div id="body1">
      Hey ${user.username}, you surely wanna fight ${enemy.username}?
      <br>
      <g:link action="fight" params="[enemyid:enemy.id]">DO IT!!!</g:link>

    </div>
  </body>
</html>
