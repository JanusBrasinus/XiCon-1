<html>
  <head>
    <title>Welcome to Xiconis da Game</title>
    <meta name="layout" content="main" />

  </head>
  <body>
    <div id="body1">
      <g:render template="units" collection="${userunits}" var="unit"/>
      <g:link controller='unit' action='createUnit'>Create new Unit</g:link>
      </br>
      <g:link controller='unit' action='heal'>Heal your team</g:link>
    </div>
  </body>
</html>
