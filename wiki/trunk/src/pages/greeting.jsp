<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<html>
  <head>
   <title>greeting page</title>
  </head>    
  <body>
     <f:view>
     	<h3>
 	 <h:outputText value="hi" />,
 	 <h:outputText value="#{userBean.username}" />
         <h:outputText value="foo" />
    	</h3>
     </f:view>
 </body>	
</html> 