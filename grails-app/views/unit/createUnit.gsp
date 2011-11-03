

<%@ page import="com.bgame.Unit" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
   
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'unit.label', default: 'Unit')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        
        <div id="body1">
          <h1><g:message code="default.create.label" args="[entityName]" /></h1>
          </br>
          <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${unitInstance}">
            <div class="errors">
                <g:renderErrors bean="${unitInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="saveunit" >
                <div id="table">
                    <table width="200px">
                        <tbody>
                        
                            <tr>
                                <td valign="top" class="name" width="30px">
                                    <label for="name"><g:message code="unit.name.label" default="Name" /></label>
                                </td>
                                <td width="30px valign="top" class="value ${hasErrors(bean: unitInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${unitInstance?.name}" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                
                    <span class="button"><g:submitButton name="create" class="saveunit" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>