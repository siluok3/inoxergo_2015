
<%--
  Created by IntelliJ IDEA.
  User: evgenia
  Date: 17 Φεβ 2010
  Time: 2:45:16 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<jsp:useBean id="usermanagement" class="engine.UserManagement" scope="session"/>
<jsp:useBean id="pagemanagement" class="engine.PageManagement" scope="page" />
<jsp:useBean id="user" class="mybeans.User" scope="session" />

<% HttpSession session1 = request.getSession();

    String pagename=request.getParameter("pagename");
    if (pagename == null) {
        pagename = "default";
        session1.setAttribute("pagename", pagename);
    }

//
//    String path = request.getRequestURI();  //takes jsp
//    String params = request.getQueryString(); //takes parameters
//    if ((!(params.equals(""))) && (!(params.equals("null")))) {
//        path = path + "?" + params;
//    }

    String link = request.getParameter("link");
    if ((link == null) || (link.equals("")) || (link.equals("null"))) {
        link = "default";
    }

%>

<html>

  <head>
      <title>InoxErgo Administrator Page</title>

     <link href="../reset.css" rel="stylesheet" type="text/css" />
     <link href="../cssstyle.css" rel="stylesheet" type="text/css" />
      
   <script type="text/javascript">
   function loadLink(name, url) {
    if ( window.frames[name] ) {
        parent.window.frames[name].location = url;
        return false;
    }
    return false;
    }
  </script>

  <script type="text/javascript">
      if (/MSIE/i.test(navigator.userAgent)) {
        var jmi = document.getElementById("my_iframe");
        var c = document.getElementById("content");
        // account for the sidebar
//        jmi.style.width = ( c.parentNode.clientWidth -220) + "px";
      }
        var originalURL = decodeURIComponent(window.location.href);

        // look for the params
        if (originalURL.indexOf("#") != -1) {
            var qString = originalURL.split('#')[1];
             if (!qString.trim() == '') loadLink('_main', qString);
        }
  </script>

  </head>

  <body>

  <% if(user.getEmail()!= null){%>

<div id="page">
    <div id="top">
     <div id="header">
        <div id="logo">
          <object width="400" height="100">
    <param name="movie" value="../logo.swf">
    <param name="quality" value="high">
    <param name="wmode" value="opaque">
    <embed src="../logo.swf" width="400" height="100"  quality="high" class="flashy" wmode="opaque" >
    </embed>
    </object>
        </div>
          <ul id="hdnav">
            <li class="hdnavli"><a href="http://localhost:8080/inoxergo/logoutServlet">Αποσύνδεση</a></li>
<%--<input class=gSubmitButtons type="button" onClick="window.open('http://localhost:8080/inoxergo/logoutServlet','_self')" value="Αποσύνδεση"/>--%>
          </ul>
       </div>
       <div id="adminmenu">
       <ul id="adminnav">

       <li><a    href="#"  onclick="loadLink('_main', 'arxiki.jsp')">Αρχική</a></li>    
      
       <li><a    href="#"  onclick="loadLink('_main', 'skales.jsp')">Σελίδες</a></li>

       </ul>
       </div> <!-- div id="adminmenu" -->
       </div>
      <div id="adminmain">


     <% String spagename=request.getParameter("pagename");
        if (spagename!=null){  %>
          <iframe id="my_iframe" frameborder="0" src="<%=spagename%>.jsp" name="_main" class="contentFrame"></iframe>
    <% }else{ %>
         <iframe id="my_iframe" frameborder="0" src="default.jsp" name="_main" class="contentFrame"></iframe>
     <%}%>

    </div>
    </div>

   <%}else{%>

    <div id="logoutdiv" align="center" style="height:768px; padding-left:200px; padding-top:100px">

    <img alt="Inoxergo Administrator"  align="middle" width=370 height=80 src="../images/logo.gif" />

    <br/><br/><br/>

    <p>
    <input class=eSubmitButtons type="button" onClick="window.open('http://localhost:8080/inoxergo/login.jsp','_self')" value="Παρακαλούμε συνδεθείτε πρώτα. Please log in."/><br/>
    <br/>Η πρόσβαση επιτρέπεται μόνο σε διαχειριστές του συστήματος.<br/>Only administrators can view this page. <br/><br/>
    </p>

    </div> <!-- logoutdiv -->

  <%}%>

  </body>

</html>