<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<html>
 <head>
  <title>enter your name page</title>
 </head>
 <body>
   <f:view>
     <h1>
      <h:outputText value="enter your name"/>
     </h1>
     <h:form id="helloForm">
      <h:outputText value="enter username"/>
      <h:inputText value="#{userBean.username}" />
      <h:commandButton action="greeting" value="hello" />
     </h:form>
   </f:view>
 </body>
</html>  