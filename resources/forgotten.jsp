<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<jsp:useBean id="user" class="mybeans.User" scope="session" />




<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">




<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>freelancing.gr - freeCMS</title>
    <link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<% HttpSession session1 = request.getSession();

    String relang = request.getParameter("lang");
    if (relang != null) {
        session1.setAttribute("lang", relang);
    }
    String lang = (String) session1.getAttribute("lang");
    if (lang == null) {
        lang = "gr";
        session1.setAttribute("lang", lang);
    }

 String recountry = request.getParameter("country");
    if (recountry != null) {
        session1.setAttribute("country", recountry);
    }
    String country = (String) session1.getAttribute("country");
    if (country == null) {
        country = "GR";
        session1.setAttribute("country", country);
    }


    String path =request.getRequestURI();  //takes jsp
    String params =request.getQueryString(); //takes parameters
    if ((params!=null)&&(!(params.equals(""))) && (!(params.equals("null")))) {
    path =path+"?"+params;
     }


    Locale currentLocale;
    ResourceBundle messages;

    String locallang=lang;
    if(lang.equals("gr")){locallang="el";}
    currentLocale = new Locale(locallang, country);

    messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);

     String sentmessage=(String)session1.getAttribute("precover");
%>



  <body>

   <div id="page">

    <div id="language_selection">

 			<form name="grform" id="grform" method="post" action="<%=response.encodeRedirectURL("invalidateServlet")%>" >
				 <input type="hidden" name="lang" value="gr">
                 <input type="hidden" name="path" value="<%=path%>"/>
				 <input type="hidden" name="country" value="GR">
			</form>

			<form name="enform" id="enform" method="post" action="<%=response.encodeRedirectURL("invalidateServlet")%>" >
				<input type="hidden" name="lang" value="en"/>
                <input type="hidden" name="path" value="<%=path%>"/>
				<input type="hidden" name="country" value="US"/>
			</form>

        <a  href="#" onclick="document['grform'].submit()">Ελληνικά</a> |
        <a href="#" onclick="document['enform'].submit()">English</a>

        </div>

   <div id="content">

       <br/>    <br/>

       <div class="PageTitle"><a href="index.jsp"><%=messages.getString("btback")%></a></div>

       <br/>    <br/>

          <h3><span><b><%
                if(sentmessage!=null){
                    if(sentmessage.equals("emailsent")){
               out.println(" <br/> <p class=\"PageText\"><b>"+messages.getString("emailsent")+" "+messages.getString("checkyouremails")+"</b></p>");
                    }else  if(sentmessage.equals("notamamber")){
                String precoveremail=(String)session1.getAttribute("precoveremail");
                out.println(" <br/> <p class=\"PageText\"><b>"+messages.getString("userwithemail")+" "+precoveremail+" "+messages.getString("notamamber")+"</b></p>");
                    }
             session1.removeAttribute("precover");
                }%>
               <br/>
        </b></span> </h3>
        <br/>      <br/>

        <h3><span><b><%=messages.getString("passwordrecovery")%>
        </b></span> </h3>

        <p class="info-page"><%=messages.getString("emailtoyou")%><br/>
       <%=messages.getString("passwordemaillogin")%><br/>
       <%=messages.getString("passwordemailtojunk")%>
      <br/> <br/> <br/>  </p>

                   <form method="post" action="<%=response.encodeRedirectURL("forgotPasswordServlet")%>" name="signupForm" id="signupForm">


                       <p class="info-page">
                           E-mail   &#160;   &#160;

                      <input class="regInput" type="text" id="email" name="email" size="25" maxlength="75" value="" />

                        &#160;   &#160;

                       <input class="gSubmitButtons" type="submit" name="cmdSubmit" id="cmdSubmit" value="<%=messages.getString("btsubmit")%>" />

                       </p>
                   </form>


    </div>

       <div id="footer">

                 <p id="powered"><a href="http://www.freelancing.gr">Powered by freelancing.gr</a></p>

                    </div>


  </div>


  </body>
</html>