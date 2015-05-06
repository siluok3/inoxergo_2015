<%--
  Created by IntelliJ IDEA.
  User: evgenia
  Date: 17 Φεβ 2010
  Time: 1:43:45 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
     <title>Login Page</title>
     <link href="reset.css" rel="stylesheet" type="text/css" />
     <link href="cssstyle.css" rel="stylesheet" type="text/css" />

  </head>
  <body>
  <div >
  <div >
  <br/><br/>
  <p>Παρακαλούμε εισάγετε username και password:</p>
  <br/>

  <form action="<%=response.encodeRedirectURL("loginServlet")%>" method="post" name="login" id="log">

    <p style="height:30px">

    <input type="hidden" name="fldDestination" id="fldDestination"/>
    <input type="hidden" name="Action" id="Action" value=""/>

    <span>Username:</span><input align="top" type="text" size="25" maxlength="75" name="username" id="username" value=""
    onFocus="if(this.value==this.defaultValue)this.value='';"/>&nbsp;&nbsp;&nbsp;&nbsp;
    <span>Password:</span><input align="top" type="password" size="15" maxlength="25" name="password" value="" id="password"
    onFocus="if(this.value==this.defaultValue)this.value='';"/> &nbsp;&nbsp;&nbsp;&nbsp;

    <input name="submitcf" id="submitcf" type="submit" class="gSubmitButtons" value="LogIn"/>

    </p>

    <br/>

    <br/>
 </form>

<p >
    <a href="#" class="forgot">forgot password?</a>
</p>
  </div>
  </div>
  </body>
</html>