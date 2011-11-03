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
  
  
  
  <g:render template="/layouts/header" /> 
  <g:render template="/layouts/nav" />
  <g:layoutBody />
  <g:render template="/layouts/footer" />
</body>
</html>