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
            <col width="100">
          </colgroup>
          <tr>
            <th>${unit.name}</th>
          </tr>
          <tr>
            <td>St&auml;rke: ${unit.str}</td>
            <td>Hp: ${unit.curhp}/${unit.maxhp}</td>
            <td>Exp: ${unit.exp}</td>
          </tr>
        </table>
        <br>
      
    </div>  
  </body>
</html>