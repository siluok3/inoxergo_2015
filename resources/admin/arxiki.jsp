<%@ page import="mybeans.ProsforesBean" %>
<%--
  Created by IntelliJ IDEA.
  User: evgenia
  Date: 10 Μαρ 2010
  Time: 5:24:54 μμ
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="prosfmanagement" class="engine.ProsforesManagement" scope="page" />
<jsp:useBean id="pagemanagement" class="engine.PageManagement" scope="page" />
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="java.util.Vector" %>
<%@ page import="mybeans.Pagetext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="prosfora" class="mybeans.ProsforesBean" scope="session" />
<% Logger cat = Logger.getLogger("arxiki.jsp"); %>

<html>
<head>
  <title>Διαχείριση Αρχικής Σελίδας</title>
   <link href="../reset.css" rel="stylesheet" type="text/css" />
   <link href="../cssstyle.css" rel="stylesheet" type="text/css" />

    <SCRIPT TYPE="text/javascript">
           <!--
           function setbg(color)
           {
           document.getElementById("text").style.background=color
           }
           function setbg2(color)
           {
           document.getElementById("title").style.background=color
           }
           //-->
           </SCRIPT>



</head>
<body>
  <%
      HttpSession session1 = request.getSession();
      session1.setAttribute("thumbnailstate", "upload");
    cat.info( "to thumb einai "+ session1.getAttribute("thumbnailstate"));
      String fileuploaded = request.getParameter("fileuploaded");
      String isok = request.getParameter("isok");
      int reply = 0;

      String submit = request.getParameter("submit");
      cat.info("Submit " + submit);
      if ((submit != null) && (!(submit.equals("null"))) && (!(submit.equals("")))) {

          String theid = request.getParameter("theid");
          cat.info("the id " + theid);
          String title = request.getParameter("title");
          String text = request.getParameter("text");
          title = title.replace("\n", "<br/>");
          text = text.replace("\n", "<br/>");

          int edit = prosfmanagement.updateProsforaTitleText(Integer.parseInt(theid), title, text);

          if (edit != 0) {
              out.println(" <br/><b> Η προσφορά ανανεώθηκε επιτυχώς.</b> <br/> ");
          } else {
              out.println(" <br/><b> Η προσφορά δεν ανανεώθηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
          }
      }

      String editsubmit = request.getParameter("editsubmit");
      cat.info("EditSubmit " + editsubmit);
      if ((editsubmit != null) && (!(editsubmit.equals("null"))) && (!(editsubmit.equals("")))) {

          String theid = request.getParameter("prosforaid");
          cat.info("the id " + theid);
          String title = request.getParameter("edittitle");
          String text = request.getParameter("edittext");
          title = title.replace("\n", "<br/>");
          text = text.replace("\n", "<br/>");

          int edit = prosfmanagement.updateProsforaTitleText(Integer.parseInt(theid), title, text);

          if (edit != 0) {
              out.println(" <br/><b> Η προσφορά ανανεώθηκε επιτυχώς.</b> <br/> ");
          } else {
              out.println(" <br/><b> Η προσφορά δεν ανανεώθηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
          }
      }

      String delsubmit = request.getParameter("delSubmit");

      if ((delsubmit != null) && (!(delsubmit.equals("null"))) && (!(delsubmit.equals("")))) {


          String delupfile1 = request.getParameter("delupfile1");
          String delupfile2 = request.getParameter("delupfile2");
          String delid = request.getParameter("delid");

          boolean delupf = prosfmanagement.deleteProsfora(Integer.parseInt(delid), delupfile1, delupfile2);

          if (!delupf) {
              out.println(" <br/><b> Η εικόνα: " + delupfile1 + " διαγράφηκε επιτυχώς.</b> <br/> ");
          } else {
              out.println(" <br/><b> Η εικόνα δεν διαγράφηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
          }
      }

      String wrongname = request.getParameter("wrongname");
      if ((wrongname != null) && (!(wrongname.equals("null"))) && (!(wrongname.equals("")))) {

          out.println("<br/><b> Μη αποδεκτός τύπος εικόνας.  Παρακαλούμε δώστε .png, .jpg ή .ai. </b> <br/>");

      } else {

          if ((fileuploaded != null) && (!(fileuploaded.equals("null"))) && (!(fileuploaded.equals("")))) {

              prosfora.setImgpath("big/" + fileuploaded);
              prosfora.setThumbpath("small/" + fileuploaded);
              prosfora.setTitle("updating");
              prosfora.setText("updating");
              reply = prosfmanagement.insertProsfora(prosfora);
              if (reply == 0) {
                  out.println("<br/> <b>Η εικόνα δεν σώθηκε. Παρακαλούμε ξαναδοκιμάστε.</b> <br/>");
              } else if (reply == -2) {
                  out.println("<br/><b> Εικόνα με αυτό το όνομα υπάρχει ήδη.</b> <br/>");
              } else {
                  out.println("<br/><b> Η εικόνα: " + fileuploaded + " ανέβηκε επιτυχώς.</b> <br/>");
              }
          }

      }

      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      Pagetext ptxt = pagemanagement.getPagetext("default", 1);
      int id = 0;

      String pressbuttonForInsert = request.getParameter("TextSubmit");
      cat.info(pressbuttonForInsert);
      cat.info("se poia selida to keimeno??? default");

      int parid = 1;

      String textdata = ptxt.getText().replace("<br/>", "\n");

      if ((pressbuttonForInsert != null)) {
          textdata = request.getParameter("textdata");
          cat.info("textdata=" + textdata);
          ptxt.setText(textdata);
          textdata = ptxt.getText().replace("<br/>", "\n");
          try {
              id = pagemanagement.updatePagetext(parid, "default", textdata);
              out.println(" <br/><b> Το κείμενό σας ενημερώθηκε επιτυχώς.</b> <br/> ");

          } catch (Exception e) {
              e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
          }
      }
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      Pagetext ptxt2 = pagemanagement.getPagetext("default", 2);
      int id2 = 0;

      String pressbuttonForInsert2 = request.getParameter("TextSubmit2");
      cat.info(pressbuttonForInsert2);
      cat.info("se poia selida to keimeno??? default");

      int parid2 = 2;

      String textdata2 = ptxt2.getText().replace("<br/>", "\n");

      if ((pressbuttonForInsert2 != null)) {
          textdata2 = request.getParameter("textdata2");
          cat.info("textdata2=" + textdata2);
          ptxt2.setText(textdata2);
          textdata2 = ptxt2.getText().replace("<br/>", "\n");
          try {
              id2 = pagemanagement.updatePagetext(parid2, "default", textdata2);
              out.println(" <br/><b> Το κείμενό σας ενημερώθηκε επιτυχώς.</b> <br/> ");

          } catch (Exception e) {
              e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
          }
      }
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      Pagetext ptxt3 = pagemanagement.getPagetext("default", 3);
      int id3 = 0;

      String pressbuttonForInsert3 = request.getParameter("TextSubmit3");
      cat.info(pressbuttonForInsert3);
      cat.info("se poia selida to keimeno??? default");

      int parid3 = 3;

      String textdata3 = ptxt3.getText().replace("<br/>", "\n");

      if ((pressbuttonForInsert3 != null)) {
          textdata3 = request.getParameter("textdata3");
          cat.info("textdata3=" + textdata3);
          ptxt3.setText(textdata3);
          textdata3 = ptxt3.getText().replace("<br/>", "\n");
          try {
              id = pagemanagement.updatePagetext(parid3, "default", textdata3);
              out.println(" <br/><b> Το κείμενό σας ενημερώθηκε επιτυχώς.</b> <br/> ");

          } catch (Exception e) {
              e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
          }
      }
   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  %>

    <div id="formdiv1">
       <span class=h4> Εισάγετε Kείμενο για Inox </span><br/>
       <form action="arxiki.jsp" method="post" id="form" name="form" style="margin-top:10px">

     <p class="PageText">
        <input name="elemid" id="elemid" type="hidden" value=""/>
        <textarea name="textdata" id="textdata" rows="3" cols="80"><%=textdata%></textarea><br/>
       <input  class="gSubmitButtons" type="submit" name="TextSubmit" id="TextSubmit" value="Υποβολή" />
        &nbsp;  &nbsp;

     </p>
       </form>
   </div>

    <div id="formdiv2">
       <span class=h4> Εισάγετε Kείμενο για Βιομηχανικές Κατασκευές</span><br/>
       <form action="arxiki.jsp" method="post" id="form2" name="form2" style="margin-top:10px">

     <p class="PageText">
        <input name="elemid2" id="elemid2" type="hidden" value=""/>
        <textarea name="textdata2" id="textdata2" rows="3" cols="80"><%=textdata2%></textarea><br/>
       <input  class="gSubmitButtons" type="submit" name="TextSubmit2" id="TextSubmit2" value="Υποβολή" />
        &nbsp;  &nbsp;

     </p>
       </form>
   </div>

    <div id="formdiv4">
       <span class=h4> Εισάγετε Kείμενο για Αρχιτεκτονικές Επενδύσεις</span><br/>
       <form action="arxiki.jsp" method="post" id="form3" name="form3" style="margin-top:10px">

     <p class="PageText">
        <input name="elemid3" id="elemid3" type="hidden" value=""/>
        <textarea name="textdata3" id="textdata3" rows="3" cols="80"><%=textdata3%></textarea><br/>
       <input  class="gSubmitButtons" type="submit" name="TextSubmit3" id="TextSubmit3" value="Υποβολή" />
        &nbsp;  &nbsp;

     </p>
       </form>
   </div>


   <% if ((isok==null)||(isok.equals(""))||(isok.equals("null"))) {%>
    <div id="formdiv">
     <form name="myform" action="http://www.inoxergo.gr/uploadPortfolioThumbnailServlet" method="post"  enctype="multipart/form-data" >
          <br /> <br />
         <p><span class="h4">Ανεβάστε εικόνα slideshow</span></p>
          <input type="file" name="myfile" id="myimg" /> <br /> <br />
          <input type="hidden" name="thumbnailstate" id="thumbnailstate"  value="upload"/>

          &nbsp;  &nbsp; <input  class="gSubmitButtons" type="submit" name="upload" id="upload" value="Upload"/>

       </form>
       <% } else { %>
        <form name="myform2" action="arxiki.jsp" method="post">
          <input type="hidden" name="theid" id="theid"  value="<%=reply%>"/>
          <p><span class="h4">Δώστε τίτλο για την εικόνα</span></p>
          <textarea class="title" name="title" id="title" rows="1" cols="100" onblur="setbg2('white')" onfocus="this.value=''; setbg2('#dfe5fa');"></textarea> <br /> <br />
          <p><span class="h4">Δώστε περιγραφή για την εικόνα</span></p>
          <textarea class="textarea" name="text" id="text" rows="10" cols="100" onblur="setbg('white')" onfocus="this.value=''; setbg('#dfe5fa');"></textarea>  <br /> <br />

          &nbsp;  &nbsp; <input  class="gSubmitButtons" type="submit" name="submit" id="submit" value="Καταχώρηση"/>

       </form>
        <%}%>
      </div>
  <div id="existing">

      <% Vector vct = prosfmanagement.getAllProsfores();
          int vctsize = vct.size();%>

<p align="center"><span class="h5">Υπάρχουσες Εικόνες Slideshow</span></p>



    <%
        for (int i = vctsize-1; i > 0; i--) {
         prosfora =(ProsforesBean) vct.elementAt(i);
    %>
  <div class="prosforestable">
  <div class="thumb">
 <img src="../images/slides/<%=prosfora.getImgpath() %>" alt="<%=prosfora.getTitle()%>" title="<%=prosfora.getTitle()%>" width="150px" height="150px"/>
</div>

   <div id="textareas">
            <%String prosforatext=prosfora.getText();
            String prosforatitle=prosfora.getTitle();
             if ((prosforatext != null)&&(!(prosforatext.equals("null")))&&(!(prosforatext.equals("")))) {
            prosforatext=prosforatext.replace("<br/>","\n");}%>
             <form action="arxiki.jsp" method="post">
                 <input type="hidden" name="prosforaid" id="prosforaid" value="<%=prosfora.getId()%>"/>
                 <textarea rows="2" name="edittitle" id="edittitle" cols="60"><%=prosforatitle%></textarea>   <br/>
                 <textarea rows="6" name="edittext" id="edittext" cols="60"><%=prosforatext%></textarea>   <br/>
                 <input class="gSubmitButtons" type=submit name="editsubmit" id="editsubmit" value="Επεξεργασία"/>
              </form>
    </div>

      <div id="delete">
      <form action="arxiki.jsp" method="post">
          <input type="hidden" value="<%=prosfora.getId()%>" name="delid" id="delid"/>
          <input type="hidden" value="<%=prosfora.getThumbpath()%>" name="delupfile1" id="delupfile1"/>
          <input type="hidden" value="<%=prosfora.getImgpath()%>" name="delupfile2" id="delupfile2"/>
          <input class="gSubmitButtons" type=submit name="delSubmit" id="delSubmit" value="Διαγραφή" />
      </form>
    </div>
      </div>
        <%}%>



</body>
</html>