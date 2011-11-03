<html>
  <head>
    <meta name='layout' content='main'/>
    <title><g:message code="springSecurity.login.title"/></title>

  </head>
  <body>
    <div id="pageBody">
      <table border="1" width="50%">
        <colgroup>
          <col width="100">
          <col width="100">
        </colgroup>
        <tr>
          <th>${enemy.id} ${enemy.username}</th>
        </tr>
        <tr>
          <td>Unitscount: ${enemy.unitcount}</td>
          <td><g:link action="fightquestion" params="[enemyid:enemy.id]">Fight him!</g:link></td>
        </tr>
      </table>
      <br>
    </div>
</html>