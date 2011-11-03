<!DOCTYPE html>
<html>
  <head>
    <title><g:layoutTitle default="Grails" /></title>
    <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
    <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
  <g:layoutHead />
  <g:javascript library="application" />
</head>


<body>

  <div id="spinner" class="spinner" style="display:none;">
    <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
  </div>
  <div id="banner"><a href="http://pr0gramm.com"><img src="${resource(dir:'images',file:'banner.jpg')}" alt="Grails" border="0" /></a></div>

  <div id="nav">
    <div class="homePagePanel">
      <div class="panelTop"></div>
      <div class="panelBody">
        </br>
       </br>
       </br>
       </br>
       </br>
       </br>
      
       
       </br>
  <center>
        <ul>
          
          
          <g:link controller='unit' action='index'>Login</g:link>
          <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
            <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
          </g:each>
        </ul>
  </center>

      </div>
      <div class="panelBtm"></div>
    </div>
  </div>

<g:layoutBody />
</body>
</html>