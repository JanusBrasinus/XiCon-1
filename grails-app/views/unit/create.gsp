

<%@ page import="com.bgame.Unit" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'unit.label', default: 'Unit')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${unitInstance}">
            <div class="errors">
                <g:renderErrors bean="${unitInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="unit.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: unitInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${unitInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="curhp"><g:message code="unit.curhp.label" default="Curhp" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: unitInstance, field: 'curhp', 'errors')}">
                                    <g:textField name="curhp" value="${fieldValue(bean: unitInstance, field: 'curhp')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="exp"><g:message code="unit.exp.label" default="Exp" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: unitInstance, field: 'exp', 'errors')}">
                                    <g:textField name="exp" value="${fieldValue(bean: unitInstance, field: 'exp')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="main"><g:message code="unit.main.label" default="Main" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: unitInstance, field: 'main', 'errors')}">
                                    <g:checkBox name="main" value="${unitInstance?.main}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="maxhp"><g:message code="unit.maxhp.label" default="Maxhp" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: unitInstance, field: 'maxhp', 'errors')}">
                                    <g:textField name="maxhp" value="${fieldValue(bean: unitInstance, field: 'maxhp')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="str"><g:message code="unit.str.label" default="Str" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: unitInstance, field: 'str', 'errors')}">
                                    <g:textField name="str" value="${fieldValue(bean: unitInstance, field: 'str')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
