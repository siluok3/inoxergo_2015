<%--
  Created by IntelliJ IDEA.
  User: evgenia
  Date: 16 Φεβ 2010
  Time: 4:28:26 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Vector" %>
<%@ page import="mybeans.Pageimg" %>
<%@ page import="mybeans.Pagetext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="usermanagement" class="engine.UserManagement" scope="session"/>
<jsp:useBean id="pagemanagement" class="engine.PageManagement" scope="page" />
<jsp:useBean id="user" class="mybeans.User" scope="session" />
<%@ page import="org.apache.log4j.Logger" %>

<html>
  <head><title>Σκάλες Σελίδα Διαχείρισης</title>
        <link href="../reset.css" rel="stylesheet" type="text/css" />
        <link href="../cssstyle.css" rel="stylesheet" type="text/css" />
      <script type="text/javascript" src="../js/jquery-1.2.6.min.js"></script>
        <link href="../jquery.linkselect.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="../js/jquery.linkselect.js"></script>


      <script type="text/javascript">
        $(document).ready(function (){
         $("select.linkselect1").linkselect({
            change: function (li, value, text){
              $('input#isjsp').val(value);
              $('#selectform').submit();
             }
             });
             });
      </script>

  </head>
  <body>

  <%
      org.apache.log4j.Logger cat = org.apache.log4j.Logger.getLogger("admin/skales.jsp");
       HttpSession session1 = request.getSession();




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      String link =request.getParameter("isjsp");

      if((link!=null)&&(!(link.equals("null")))&&(!(link.equals("")))) {
               session1.removeAttribute("pagename");
              session1.setAttribute("pagename", link);

      }  else{
           session1.setAttribute("pagename", "skales");
          link="skales";
      }
     cat.info("pagename einai "+link);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Pagetext ptxt=pagemanagement.getPagetext(link,1);
    int id = 0;
      
   String pressbuttonForInsert = request.getParameter("TextSubmit");
   cat.info(pressbuttonForInsert);
   cat.info("se poia selida to keimeno???"+link);

    int parid = 1;

        String textdata = ptxt.getText().replace("<br/>","\n");

    if ((pressbuttonForInsert != null)) {
        textdata = request.getParameter("textdata");
        cat.info("textdata="+textdata);
       ptxt.setText(textdata);
      textdata = ptxt.getText().replace("<br/>", "\n");
        try {
            id = pagemanagement.updatePagetext(parid,link,textdata);
            out.println(" <br/><b> Το κείμενό σας ενημερώθηκε επιτυχώς.</b> <br/> ");

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    Pagetext brabeiatext=pagemanagement.getPagetext(link,2);
//    int idbrabeia = 0;
//   String pressbuttonBrabeia = request.getParameter("BrabeiaSubmit");
//   cat.info(pressbuttonBrabeia);

//    int paridbrabeia = 2;

//        String textdatabrabeia = brabeiatext.getText().replace("<br/>","\n");

//    if ((pressbuttonBrabeia != null)) {
//        textdatabrabeia = request.getParameter("textdatabrabeia");
//        cat.info("textdatabrabeia="+textdatabrabeia);
//       brabeiatext.setText(textdatabrabeia);
//      textdatabrabeia = brabeiatext.getText().replace("<br/>", "\n");
//        try {
//            idbrabeia = pagemanagement.updatePagetext(paridbrabeia,link,textdatabrabeia);

//        } catch (Exception e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//       }
//    }
///////////////////////////////////////////////////////////////////////////////////////////////////

   String delsubmit = request.getParameter("delSubmit");

    if ((delsubmit != null)&&(!(delsubmit.equals("null")))&&(!(delsubmit.equals("")))) {


 String delupfile1 = request.getParameter("delupfile1");
 String delid = request.getParameter("delid");

 boolean delupf= pagemanagement.deleteImg(Integer.parseInt(delid),delupfile1);

   if (!delupf) {
      out.println(" <br/><b> Η εικόνα: " + delupfile1 + " διαγράφηκε επιτυχώς.</b> <br/> ");
  }else{
      out.println(" <br/><b> Η εικόνα δεν διαγράφηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
   }
  }
///////////////////////////////////////////////////////////////////////////////////////////////////

   String delsubmit2 = request.getParameter("delSubmit2");

    if ((delsubmit2 != null)&&(!(delsubmit2.equals("null")))&&(!(delsubmit2.equals("")))) {


 String delupfile2 = request.getParameter("delupfile2");
 String delid2 = request.getParameter("delid2");

 boolean delupf= pagemanagement.deleteImg(Integer.parseInt(delid2),delupfile2);

   if (!delupf) {
      out.println(" <br/><b> Η εικόνα: " + delupfile2 + " διαγράφηκε επιτυχώς.</b> <br/> ");
  }else{
      out.println(" <br/><b> Η εικόνα δεν διαγράφηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
   }
  }
///////////////////////////////////////////////////////////////////////////////////////////////////

   String delsubmit3 = request.getParameter("delSubmit3");

    if ((delsubmit3 != null)&&(!(delsubmit3.equals("null")))&&(!(delsubmit3.equals("")))) {


 String delupfile3 = request.getParameter("delupfile3");
 String delid3 = request.getParameter("delid3");

 boolean delupf= pagemanagement.deleteImg(Integer.parseInt(delid3),delupfile3);

   if (!delupf) {
      out.println(" <br/><b> Η εικόνα: " + delupfile3 + " διαγράφηκε επιτυχώς.</b> <br/> ");
  }else{
      out.println(" <br/><b> Η εικόνα δεν διαγράφηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
   }
  }
///////////////////////////////////////////////////////////////////////////////////////////////////

   String delsubmit4 = request.getParameter("delSubmit4");

    if ((delsubmit4 != null)&&(!(delsubmit4.equals("null")))&&(!(delsubmit4.equals("")))) {


 String delupfile4 = request.getParameter("delupfile4");
 String delid4 = request.getParameter("delid4");

 boolean delupf= pagemanagement.deleteImg(Integer.parseInt(delid4),delupfile4);

   if (!delupf) {
      out.println(" <br/><b> Η εικόνα: " + delupfile4 + " διαγράφηκε επιτυχώς.</b> <br/> ");
  }else{
      out.println(" <br/><b> Η εικόνα δεν διαγράφηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
   }
  }
///////////////////////////////////////////////////////////////////////////////////////////////////

   String delsubmit5 = request.getParameter("delSubmit5");

    if ((delsubmit5 != null)&&(!(delsubmit5.equals("null")))&&(!(delsubmit5.equals("")))) {


 String delupfile5 = request.getParameter("delupfile5");
 String delid5 = request.getParameter("delid5");

 boolean delupf= pagemanagement.deleteImg(Integer.parseInt(delid5),delupfile5);

   if (!delupf) {
      out.println(" <br/><b> Η εικόνα: " + delupfile5 + " διαγράφηκε επιτυχώς.</b> <br/> ");
  }else{
      out.println(" <br/><b> Η εικόνα δεν διαγράφηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
   }
  }

///////////////////////////////////////////////////////////////////////////////////////////////////

   String delsubmit6 = request.getParameter("delSubmit6");

    if ((delsubmit6 != null)&&(!(delsubmit6.equals("null")))&&(!(delsubmit6.equals("")))) {


 String delupfile6 = request.getParameter("delupfile6");
 String delid6 = request.getParameter("delid6");

 boolean delupf= pagemanagement.deleteImg(Integer.parseInt(delid6),delupfile6);

   if (!delupf) {
      out.println(" <br/><b> Η εικόνα: " + delupfile6 + " διαγράφηκε επιτυχώς.</b> <br/> ");
  }else{
      out.println(" <br/><b> Η εικόνα δεν διαγράφηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
   }
  }

///////////////////////////////////////////////////////////////////////////////////////////////////

   String delsubmit7 = request.getParameter("delSubmit7");

    if ((delsubmit7 != null)&&(!(delsubmit7.equals("null")))&&(!(delsubmit7.equals("")))) {


 String delupfile7 = request.getParameter("delupfile7");
 String delid7 = request.getParameter("delid7");

 boolean delupf= pagemanagement.deleteImg(Integer.parseInt(delid7),delupfile7);

   if (!delupf) {
      out.println(" <br/><b> Η εικόνα: " + delupfile7 + " διαγράφηκε επιτυχώς.</b> <br/> ");
  }else{
      out.println(" <br/><b> Η εικόνα δεν διαγράφηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
   }
  }

///////////////////////////////////////////////////////////////////////////////////////////////////

   String delsubmit8 = request.getParameter("delSubmit8");

    if ((delsubmit8 != null)&&(!(delsubmit8.equals("null")))&&(!(delsubmit8.equals("")))) {


 String delupfile8 = request.getParameter("delupfile8");
 String delid8 = request.getParameter("delid8");

 boolean delupf= pagemanagement.deleteImg(Integer.parseInt(delid8),delupfile8);

   if (!delupf) {
      out.println(" <br/><b> Η εικόνα: " + delupfile8 + " διαγράφηκε επιτυχώς.</b> <br/> ");
  }else{
      out.println(" <br/><b> Η εικόνα δεν διαγράφηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
   }
  }

///////////////////////////////////////////////////////////////////////////////////////////////////

   String delsubmit9 = request.getParameter("delSubmit9");

    if ((delsubmit9 != null)&&(!(delsubmit9.equals("null")))&&(!(delsubmit9.equals("")))) {


 String delupfile9 = request.getParameter("delupfile9");
 String delid9 = request.getParameter("delid9");

 boolean delupf= pagemanagement.deleteImg(Integer.parseInt(delid9),delupfile9);

   if (!delupf) {
      out.println(" <br/><b> Η εικόνα: " + delupfile9 + " διαγράφηκε επιτυχώς.</b> <br/> ");
  }else{
      out.println(" <br/><b> Η εικόνα δεν διαγράφηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
   }
  }

///////////////////////////////////////////////////////////////////////////////////////////////////

   String delsubmit10 = request.getParameter("delSubmit10");

    if ((delsubmit10 != null)&&(!(delsubmit10.equals("null")))&&(!(delsubmit10.equals("")))) {


 String delupfile10 = request.getParameter("delupfile10");
 String delid10 = request.getParameter("delid10");

 boolean delupf= pagemanagement.deleteImg(Integer.parseInt(delid10),delupfile10);

   if (!delupf) {
      out.println(" <br/><b> Η εικόνα: " + delupfile10 + " διαγράφηκε επιτυχώς.</b> <br/> ");
  }else{
      out.println(" <br/><b> Η εικόνα δεν διαγράφηκε. Παρακαλούμε ξαναδοκιμάστε. </b> <br/>");
   }
  }
    %>
  <div id="formes">

    <div id="selectjsp">
     <p>Η τρέχουσα σελίδα για αλλαγές είναι: </p>
    <form name="selectform" id="selectform" action="skales.jsp" method="post" style="margin-top:10px">
      <input type="hidden" id="isjsp" name="isjsp" value="" />

      <select id="selectpage" name="selectpage" title="Επιλέξτε μια σελίδα" class="linkselect1">
          <%if((link!=null)&&(link.equals("kagkela"))){ cat.info("to link prepei na einai kagkela..."+link); %>
          <option value="kagkela" selected>Κάγκελα</option>
          <%}else{%>
          <option value="kagkela">Κάγκελα</option>
          <%}%>

          <%if((link!=null)&&(link.equals("ypostega"))){%>
          <option value="ypostega" selected>Υπόστεγα</option>
          <%}else{%>
          <option value="ypostega">Υπόστεγα</option>
          <%}%>

          <%if((link!=null)&&(link.equals("klimakostasia"))){%>
          <option value="klimakostasia" selected>Κλιμακοστάσια-Κουπαστές</option>
          <%}else{%>
          <option value="klimakostasia">Κλιμακοστάσια-Κουπαστές</option>
          <%}%>

          <%if((link!=null)&&(link.equals("skales"))){%>
          <option value="skales" selected>Σκάλες</option>
          <%}else{%>
          <option value="skales">Σκάλες</option>
          <%}%>

          <%if((link!=null)&&(link.equals("portes"))){%>
          <option value="portes" selected>Πόρτες</option>
          <%}else{%>
          <option value="portes">Πόρτες</option>
          <%}%>

          <%if((link!=null)&&(link.equals("ependyseis"))){%>
          <option value="ependyseis" selected>Επενδύσεις</option>
          <%}else{%>
          <option value="ependyseis">Επενδύσεις</option>
          <%}%>

          <%if((link!=null)&&(link.equals("kataskeyes"))){%>
          <option value="kataskeyes" selected>Μεταλλικές Κατασκευές</option>
          <%}else{%>
          <option value="kataskeyes">Μεταλλικές Κατασκευές</option>
          <%}%>

          <%if((link!=null)&&(link.equals("emporio"))){%>
          <option value="emporio" selected>Εμπόριο Σιδήρου</option>
          <%}else{%>
          <option value="emporio">Εμπόριο Σιδήρου</option>
          <%}%>

          <%if((link!=null)&&(link.equals("metala"))){%>
          <option value="metala" selected>Επεξεργασία Μετάλλων & Ελλασμάτων</option>
          <%}else{%>
          <option value="metala">Επεξεργασία Μετάλλων & Ελλασμάτων</option>
          <%}%>
      </select>
        <!--<INPUT type="submit" value="Καταχώρηση" id="selectjspbtn" name="selectjspbtn">-->
    </form>
    </div>
      
  <div id="formdiv3">
       <span class=h4> Εισάγετε Kείμενο </span><br/>
       <form action="skales.jsp" method="post" id="form3" name="form3" style="margin-top:10px">
       <input type="hidden" id="textisjsp" name="isjsp" value="<%=link%>" />

     <p class="PageText">
        <input name="elemid" id="elemid" type="hidden" value=""/>
        <textarea name="textdata" id="textdata" rows="8" cols="80"><%=textdata%></textarea><br/>
       <input  class="gSubmitButtons" type="submit" name="TextSubmit" id="TextSubmit" value="Υποβολή" />
        &nbsp;  &nbsp;

     </p>
       </form>
   </div>


  <div class="formdivbr">

     <form name="myform" action="<%=response.encodeRedirectURL("../uploadImgServletOinos?pagename="+link+"&imgid=1")%>" method="post"  enctype="multipart/form-data" >
       <br/> <br />
       <p><span class="h4">Ανεβάστε εικόνα για το 1<sup>o</sup> σετ</span> (Επιτρεπόμενο μέγεθος <1Μb)</p>
       <input type="file" name="myfile" id="myimg" style="margin-top:10px" />
       <input type="radio" name="pou" value="mesa" checked /> Εσωτερική
       <input type="radio" name="pou" value="eksw" /> Εξωτερική <br /> <br />
       <p><span class="h4">Δώστε τίτλο για την εικόνα</span></p>
          <textarea name="title" id="title1" rows="1" cols="55" style="margin-top:10px" onblur="setbg2('white')" onfocus="this.value=''; setbg2('#dfe5fa');"></textarea> <br /> <br />
         <p> <span class="h4">Δώστε περιγραφή για την εικόνα</span></p>
          <textarea name="text" id="text1" rows="1" cols="55" style="margin-top:10px" onblur="setbg('white')" onfocus="this.value=''; setbg('#dfe5fa');"></textarea>
       &nbsp;  &nbsp; <input  class="gSubmitButtons" type="submit" name="upload" id="upload1" value="Υποβολή"/>
     </form>

   </div>

  <div class="formdivbr">

     <form name="myform" action="<%=response.encodeRedirectURL("../uploadImgServletOinos?pagename="+link+"&imgid=2")%>" method="post"  enctype="multipart/form-data" >
       <br/> <br />
       <p><span class="h4">Ανεβάστε εικόνα για το 2<sup>o</sup> σετ</span><span class="greentext"> (Επιτρεπόμενο μέγεθος <1Μb)</span></p>
       <input type="file" name="myfile" id="myimg2" style="margin-top:10px" />
       <input type="radio" name="pou" value="mesa" checked /> Εσωτερική
       <input type="radio" name="pou" value="eksw" /> Εξωτερική <br /> <br />
       <p><span class="h4">Δώστε τίτλο για την εικόνα</span></p>
       <textarea name="title" id="title2" rows="1" cols="55" style="margin-top:10px" onblur="setbg2('white')" onfocus="this.value=''; setbg2('#dfe5fa');"></textarea> <br /> <br />
       <p> <span class="h4">Δώστε περιγραφή για την εικόνα</span></p>
       <textarea name="text" id="text2" rows="1" cols="55" style="margin-top:10px" onblur="setbg('white')" onfocus="this.value=''; setbg('#dfe5fa');"></textarea>
       &nbsp;  &nbsp; <input  class="gSubmitButtons" type="submit" name="upload" id="upload2" value="Υποβολή"/>
     </form>

   </div>

  <div class="formdivbr">

     <form name="myform" action="<%=response.encodeRedirectURL("../uploadImgServletOinos?pagename="+link+"&imgid=3")%>" method="post"  enctype="multipart/form-data" >
       <br/> <br />
       <p><span class="h4">Ανεβάστε εικόνα για το 3<sup>o</sup> σετ</span> (Επιτρεπόμενο μέγεθος <1Μb)</p>
       <input type="file" name="myfile" id="myimg3" style="margin-top:10px" />
       <input type="radio" name="pou" value="mesa" checked /> Εσωτερική
       <input type="radio" name="pou" value="eksw" /> Εξωτερική<br /> <br />
       <p><span class="h4">Δώστε τίτλο για την εικόνα</span></p>
       <textarea name="title" id="title3" rows="1" cols="55" style="margin-top:10px" onblur="setbg2('white')" onfocus="this.value=''; setbg2('#dfe5fa');"></textarea> <br /> <br />
       <p> <span class="h4">Δώστε περιγραφή για την εικόνα</span></p>
       <textarea name="text" id="text3" rows="1" cols="55" style="margin-top:10px" onblur="setbg('white')" onfocus="this.value=''; setbg('#dfe5fa');"></textarea>
 &nbsp;  &nbsp; <input  class="gSubmitButtons" type="submit" name="upload" id="upload3" value="Υποβολή"/>
     </form>

   </div>

  <div class="formdivbr">

     <form name="myform" action="<%=response.encodeRedirectURL("../uploadImgServletOinos?pagename="+link+"&imgid=4")%>" method="post"  enctype="multipart/form-data" >
       <br/> <br />
       <p><span class="h4">Ανεβάστε εικόνα για το 4<sup>o</sup> σετ</span> (Επιτρεπόμενο μέγεθος <1Μb)</p>
       <input type="file" name="myfile" id="myimg4" style="margin-top:10px" />
       <input type="radio" name="pou" value="mesa" checked /> Εσωτερική
       <input type="radio" name="pou" value="eksw" /> Εξωτερική<br /> <br />
       <p><span class="h4">Δώστε τίτλο για την εικόνα</span></p>
       <textarea name="title" id="title4" rows="1" cols="55" style="margin-top:10px" onblur="setbg2('white')" onfocus="this.value=''; setbg2('#dfe5fa');"></textarea> <br /> <br />
       <p> <span class="h4">Δώστε περιγραφή για την εικόνα</span></p>
       <textarea name="text" id="text4" rows="1" cols="55" style="margin-top:10px" onblur="setbg('white')" onfocus="this.value=''; setbg('#dfe5fa');"></textarea>       &nbsp;  &nbsp;
         <input  class="gSubmitButtons" type="submit" name="upload" id="upload4" value="Υποβολή"/>
     </form>

   </div>

  <div class="formdivbr">

     <form name="myform" action="<%=response.encodeRedirectURL("../uploadImgServletOinos?pagename="+link+"&imgid=5")%>" method="post"  enctype="multipart/form-data" >
       <br/> <br />
       <p><span class="h4">Ανεβάστε εικόνα για το 5<sup>o</sup> σετ</span> (Επιτρεπόμενο μέγεθος <1Μb)</p>
       <input type="file" name="myfile" id="myimg5" style="margin-top:10px" />
       <input type="radio" name="pou" value="mesa" checked /> Εσωτερική
       <input type="radio" name="pou" value="eksw" /> Εξωτερική<br /> <br />
       <p><span class="h4">Δώστε τίτλο για την εικόνα</span></p>
       <textarea name="title" id="title5" rows="1" cols="55" style="margin-top:10px" onblur="setbg2('white')" onfocus="this.value=''; setbg2('#dfe5fa');"></textarea> <br /> <br />
       <p> <span class="h4">Δώστε περιγραφή για την εικόνα</span></p>
       <textarea name="text" id="text5" rows="1" cols="55" style="margin-top:10px" onblur="setbg('white')" onfocus="this.value=''; setbg('#dfe5fa');"></textarea>
     &nbsp;  &nbsp; <input  class="gSubmitButtons" type="submit" name="upload" id="upload5" value="Υποβολή"/>
     </form>

   </div>

  <div class="formdivbr">

     <form name="myform" action="<%=response.encodeRedirectURL("../uploadImgServletOinos?pagename="+link+"&imgid=6")%>" method="post"  enctype="multipart/form-data" >
       <br/> <br />
       <p><span class="h4">Ανεβάστε εικόνα για το 6<sup>o</sup> σετ</span> (Επιτρεπόμενο μέγεθος <1Μb)</p>
       <input type="file" name="myfile" id="myimg6" style="margin-top:10px" />
       <input type="radio" name="pou" value="mesa" checked /> Εσωτερική
       <input type="radio" name="pou" value="eksw" /> Εξωτερική<br /> <br />
       <p><span class="h4">Δώστε τίτλο για την εικόνα</span></p>
       <textarea name="title" id="title6" rows="1" cols="55" style="margin-top:10px" onblur="setbg2('white')" onfocus="this.value=''; setbg2('#dfe5fa');"></textarea> <br /> <br />
       <p> <span class="h4">Δώστε περιγραφή για την εικόνα</span></p>
       <textarea name="text" id="text6" rows="1" cols="55" style="margin-top:10px" onblur="setbg('white')" onfocus="this.value=''; setbg('#dfe5fa');"></textarea>
     &nbsp;  &nbsp; <input  class="gSubmitButtons" type="submit" name="upload" id="upload6" value="Υποβολή"/>
     </form>

   </div>
  <div class="formdivbr">

     <form name="myform" action="<%=response.encodeRedirectURL("../uploadImgServletOinos?pagename="+link+"&imgid=7")%>" method="post"  enctype="multipart/form-data" >
       <br/> <br />
       <p><span class="h4">Ανεβάστε εικόνα για το 7<sup>o</sup> σετ</span> (Επιτρεπόμενο μέγεθος <1Μb)</p>
       <input type="file" name="myfile" id="myimg7" style="margin-top:10px" />
       <input type="radio" name="pou" value="mesa" checked /> Εσωτερική
       <input type="radio" name="pou" value="eksw" /> Εξωτερική<br /> <br />
       <p><span class="h4">Δώστε τίτλο για την εικόνα</span></p>
       <textarea name="title" id="title7" rows="1" cols="55" style="margin-top:10px" onblur="setbg2('white')" onfocus="this.value=''; setbg2('#dfe5fa');"></textarea> <br /> <br />
       <p> <span class="h4">Δώστε περιγραφή για την εικόνα</span></p>
       <textarea name="text" id="text7" rows="1" cols="55" style="margin-top:10px" onblur="setbg('white')" onfocus="this.value=''; setbg('#dfe5fa');"></textarea>
     &nbsp;  &nbsp; <input  class="gSubmitButtons" type="submit" name="upload" id="upload7" value="Υποβολή"/>
     </form>

   </div>
  <div class="formdivbr">

     <form name="myform" action="<%=response.encodeRedirectURL("../uploadImgServletOinos?pagename="+link+"&imgid=8")%>" method="post"  enctype="multipart/form-data" >
       <br/> <br />
       <p><span class="h4">Ανεβάστε εικόνα για το 8<sup>o</sup> σετ</span> (Επιτρεπόμενο μέγεθος <1Μb)</p>
       <input type="file" name="myfile" id="myimg8" style="margin-top:10px" />
       <input type="radio" name="pou" value="mesa" checked /> Εσωτερική
       <input type="radio" name="pou" value="eksw" /> Εξωτερική<br /> <br />
       <p><span class="h4">Δώστε τίτλο για την εικόνα</span></p>
       <textarea name="title" id="title8" rows="1" cols="55" style="margin-top:10px" onblur="setbg2('white')" onfocus="this.value=''; setbg2('#dfe5fa');"></textarea> <br /> <br />
       <p> <span class="h4">Δώστε περιγραφή για την εικόνα</span></p>
       <textarea name="text" id="text8" rows="1" cols="55" style="margin-top:10px" onblur="setbg('white')" onfocus="this.value=''; setbg('#dfe5fa');"></textarea>
     &nbsp;  &nbsp; <input  class="gSubmitButtons" type="submit" name="upload" id="upload8" value="Υποβολή"/>
     </form>

   </div>
  <div class="formdivbr">

     <form name="myform" action="<%=response.encodeRedirectURL("../uploadImgServletOinos?pagename="+link+"&imgid=9")%>" method="post"  enctype="multipart/form-data" >
       <br/> <br />
       <p><span class="h4">Ανεβάστε εικόνα για το 9<sup>o</sup> σετ</span> (Επιτρεπόμενο μέγεθος <1Μb)</p>
       <input type="file" name="myfile" id="myimg9" style="margin-top:10px" />
       <input type="radio" name="pou" value="mesa" checked /> Εσωτερική
       <input type="radio" name="pou" value="eksw" /> Εξωτερική<br /> <br />
       <p><span class="h4">Δώστε τίτλο για την εικόνα</span></p>
       <textarea name="title" id="title9" rows="1" cols="55" style="margin-top:10px" onblur="setbg2('white')" onfocus="this.value=''; setbg2('#dfe5fa');"></textarea> <br /> <br />
       <p> <span class="h4">Δώστε περιγραφή για την εικόνα</span></p>
       <textarea name="text" id="text9" rows="1" cols="55" style="margin-top:10px" onblur="setbg('white')" onfocus="this.value=''; setbg('#dfe5fa');"></textarea>
     &nbsp;  &nbsp; <input  class="gSubmitButtons" type="submit" name="upload" id="upload9" value="Υποβολή"/>
     </form>

   </div>
  <div class="formdivbr">

     <form name="myform" action="<%=response.encodeRedirectURL("../uploadImgServletOinos?pagename="+link+"&imgid=10")%>" method="post"  enctype="multipart/form-data" >
       <br/> <br />
       <p><span class="h4">Ανεβάστε εικόνα για το 10<sup>o</sup> σετ</span> (Επιτρεπόμενο μέγεθος <1Μb)</p>
       <input type="file" name="myfile" id="myimg10" style="margin-top:10px" />
       <input type="radio" name="pou" value="mesa" checked /> Εσωτερική
       <input type="radio" name="pou" value="eksw" /> Εξωτερική<br /> <br />
       <p><span class="h4">Δώστε τίτλο για την εικόνα</span></p>
       <textarea name="title" id="title10" rows="1" cols="55" style="margin-top:10px" onblur="setbg2('white')" onfocus="this.value=''; setbg2('#dfe5fa');"></textarea> <br /> <br />
       <p> <span class="h4">Δώστε περιγραφή για την εικόνα</span></p>
       <textarea name="text" id="text10" rows="1" cols="55" style="margin-top:10px" onblur="setbg('white')" onfocus="this.value=''; setbg('#dfe5fa');"></textarea>
     &nbsp;  &nbsp; <input  class="gSubmitButtons" type="submit" name="upload" id="upload10" value="Υποβολή"/>
     </form>

   </div>



  </div>
  <div id="existing">
      <div class="existing">

        <p align="center"><span class="h4">Υπάρχουσες Εικόνες για το 1o σετ</span></p>

        <%  Vector fotovec = pagemanagement.getAllPageimg(link, 1);
            int vctsize = fotovec.size();
             for (int i = 0; i < vctsize; i++) {
           Pageimg  pimg =(Pageimg) fotovec.elementAt(i); %>

            <div class="prosforestable">
                <div class="thumb">
                    <img src="<%=pimg.getImgpath() %>" alt="Εικόνα"  width="150px" height="150px"/>
                </div>

                <div class="del">
                    <form action="skales.jsp" method="post">
                        <input type="hidden" value="<%=pimg.getId()%>" name="delid" id="delid"/>
                        <input type="hidden" value="<%=pimg.getImgpath()%>" name="delupfile1" id="delupfile1"/>
                        <input class="gSubmitButtons" type=submit name="delSubmit" id="delSubmit" value="Διαγραφή" />
                    </form>
                </div>
            </div>
        <%}%>
   </div>
      <div class="existing">

        <p align="center"><span class="h4">Υπάρχουσες Εικόνες για το 2o σετ</span></p>

        <%  Vector fotovec2 = pagemanagement.getAllPageimg(link, 2);
            int vctsize2 = fotovec2.size();
             for (int i = 0; i < vctsize2; i++) {
           Pageimg  pimg =(Pageimg) fotovec2.elementAt(i); %>

            <div class="prosforestable">
                <div class="thumb">
                    <img src="<%=pimg.getImgpath() %>" alt="Εικόνα"  width="150px" height="150px"/>
                </div>

                <div class="del">
                    <form action="skales.jsp" method="post">
                        <input type="hidden" value="<%=pimg.getId()%>" name="delid2" id="delid2"/>
                        <input type="hidden" value="<%=pimg.getImgpath()%>" name="delupfile2" id="delupfile2"/>
                        <input class="gSubmitButtons" type=submit name="delSubmit2" id="delSubmit2" value="Διαγραφή" />
                    </form>
                </div>
            </div>
        <%}%>
   </div>
      <div class="existing">

        <p align="center"><span class="h4">Υπάρχουσες Εικόνες για το 3o σετ</span></p>

        <%  Vector fotovec3 = pagemanagement.getAllPageimg(link, 3);
            int vctsize3 = fotovec3.size();
             for (int i = 0; i < vctsize3; i++) {
           Pageimg  pimg =(Pageimg) fotovec3.elementAt(i); %>

            <div class="prosforestable">
                <div class="thumb">
                    <img src="<%=pimg.getImgpath() %>" alt="Εικόνα"  width="150px" height="150px"/>
                </div>

                <div class="del">
                    <form action="skales.jsp" method="post">
                        <input type="hidden" value="<%=pimg.getId()%>" name="delid3" id="delid3"/>
                        <input type="hidden" value="<%=pimg.getImgpath()%>" name="delupfile3" id="delupfile3"/>
                        <input class="gSubmitButtons" type=submit name="delSubmit3" id="delSubmit3" value="Διαγραφή" />
                    </form>
                </div>
            </div>
        <%}%>
   </div>
      <div class="existing">

        <p align="center"><span class="h4">Υπάρχουσες Εικόνες για το 4o σετ</span></p>

        <%  Vector fotovec4 = pagemanagement.getAllPageimg(link, 4);
            int vctsize4 = fotovec4.size();
             for (int i = 0; i < vctsize4; i++) {
           Pageimg  pimg =(Pageimg) fotovec4.elementAt(i); %>

            <div class="prosforestable">
                <div class="thumb">
                    <img src="<%=pimg.getImgpath() %>" alt="Εικόνα"  width="150px" height="150px"/>
                </div>

                <div class="del">
                    <form action="skales.jsp" method="post">
                        <input type="hidden" value="<%=pimg.getId()%>" name="delid4" id="delid4"/>
                        <input type="hidden" value="<%=pimg.getImgpath()%>" name="delupfile4" id="delupfile4"/>
                        <input class="gSubmitButtons" type=submit name="delSubmit4" id="delSubmit4" value="Διαγραφή" />
                    </form>
                </div>
            </div>
        <%}%>
   </div>
      <div class="existing">

        <p align="center"><span class="h4">Υπάρχουσες Εικόνες για το 5o σετ</span></p>

        <%  Vector fotovec5 = pagemanagement.getAllPageimg(link, 5);
            int vctsize5 = fotovec5.size();
             for (int i = 0; i < vctsize5; i++) {
           Pageimg  pimg =(Pageimg) fotovec5.elementAt(i); %>

            <div class="prosforestable">
                <div class="thumb">
                    <img src="<%=pimg.getImgpath() %>" alt="Εικόνα"  width="150px" height="150px"/>
                </div>

                <div class="del">
                    <form action="skales.jsp" method="post">
                        <input type="hidden" value="<%=pimg.getId()%>" name="delid5" id="delid5"/>
                        <input type="hidden" value="<%=pimg.getImgpath()%>" name="delupfile5" id="delupfile5"/>
                        <input class="gSubmitButtons" type=submit name="delSubmit5" id="delSubmit5" value="Διαγραφή" />
                    </form>
                </div>
            </div>
        <%}%>
   </div>
      <div class="existing">

        <p align="center"><span class="h4">Υπάρχουσες Εικόνες για το 6o σετ</span></p>

        <%  Vector fotovec6 = pagemanagement.getAllPageimg(link, 6);
            int vctsize6 = fotovec5.size();
             for (int i = 0; i < vctsize6; i++) {
           Pageimg  pimg =(Pageimg) fotovec6.elementAt(i); %>

            <div class="prosforestable">
                <div class="thumb">
                    <img src="<%=pimg.getImgpath() %>" alt="Εικόνα"  width="150px" height="150px"/>
                </div>

                <div class="del">
                    <form action="skales.jsp" method="post">
                        <input type="hidden" value="<%=pimg.getId()%>" name="delid6" id="delid6"/>
                        <input type="hidden" value="<%=pimg.getImgpath()%>" name="delupfile6" id="delupfile6"/>
                        <input class="gSubmitButtons" type=submit name="delSubmit6" id="delSubmit6" value="Διαγραφή" />
                    </form>
                </div>
            </div>
        <%}%>
   </div>
      <div class="existing">

        <p align="center"><span class="h4">Υπάρχουσες Εικόνες για το 7o σετ</span></p>

        <%  Vector fotovec7 = pagemanagement.getAllPageimg(link, 7);
            int vctsize7 = fotovec7.size();
             for (int i = 0; i < vctsize7; i++) {
           Pageimg  pimg =(Pageimg) fotovec7.elementAt(i); %>

            <div class="prosforestable">
                <div class="thumb">
                    <img src="<%=pimg.getImgpath() %>" alt="Εικόνα"  width="150px" height="150px"/>
                </div>

                <div class="del">
                    <form action="skales.jsp" method="post">
                        <input type="hidden" value="<%=pimg.getId()%>" name="delid7" id="delid7"/>
                        <input type="hidden" value="<%=pimg.getImgpath()%>" name="delupfile7" id="delupfile7"/>
                        <input class="gSubmitButtons" type=submit name="delSubmit7" id="delSubmit7" value="Διαγραφή" />
                    </form>
                </div>
            </div>
        <%}%>
   </div>

      <div class="existing">

        <p align="center"><span class="h4">Υπάρχουσες Εικόνες για το 8o σετ</span></p>

        <%  Vector fotovec8 = pagemanagement.getAllPageimg(link, 8);
            int vctsize8 = fotovec8.size();
             for (int i = 0; i < vctsize8; i++) {
           Pageimg  pimg =(Pageimg) fotovec8.elementAt(i); %>

            <div class="prosforestable">
                <div class="thumb">
                    <img src="<%=pimg.getImgpath() %>" alt="Εικόνα"  width="150px" height="150px"/>
                </div>

                <div class="del">
                    <form action="skales.jsp" method="post">
                        <input type="hidden" value="<%=pimg.getId()%>" name="delid8" id="delid8"/>
                        <input type="hidden" value="<%=pimg.getImgpath()%>" name="delupfile8" id="delupfile8"/>
                        <input class="gSubmitButtons" type=submit name="delSubmit8" id="delSubmit8" value="Διαγραφή" />
                    </form>
                </div>
            </div>
        <%}%>
   </div>
      <div class="existing">

        <p align="center"><span class="h4">Υπάρχουσες Εικόνες για το 9o σετ</span></p>

        <%  Vector fotovec9 = pagemanagement.getAllPageimg(link, 9);
            int vctsize9 = fotovec9.size();
             for (int i = 0; i < vctsize9; i++) {
           Pageimg  pimg =(Pageimg) fotovec9.elementAt(i); %>

            <div class="prosforestable">
                <div class="thumb">
                    <img src="<%=pimg.getImgpath() %>" alt="Εικόνα"  width="150px" height="150px"/>
                </div>

                <div class="del">
                    <form action="skales.jsp" method="post">
                        <input type="hidden" value="<%=pimg.getId()%>" name="delid9" id="delid9"/>
                        <input type="hidden" value="<%=pimg.getImgpath()%>" name="delupfile9" id="delupfile9"/>
                        <input class="gSubmitButtons" type=submit name="delSubmit9" id="delSubmit9" value="Διαγραφή" />
                    </form>
                </div>
            </div>
        <%}%>
   </div>
      <div class="existing">

        <p align="center"><span class="h4">Υπάρχουσες Εικόνες για το 10o σετ</span></p>

        <%  Vector fotovec10 = pagemanagement.getAllPageimg(link, 10);
            int vctsize10 = fotovec10.size();
             for (int i = 0; i < vctsize10; i++) {
           Pageimg  pimg =(Pageimg) fotovec10.elementAt(i); %>

            <div class="prosforestable">
                <div class="thumb">
                    <img src="<%=pimg.getImgpath() %>" alt="Εικόνα"  width="150px" height="150px"/>
                </div>

                <div class="del">
                    <form action="skales.jsp" method="post">
                        <input type="hidden" value="<%=pimg.getId()%>" name="delid10" id="delid10"/>
                        <input type="hidden" value="<%=pimg.getImgpath()%>" name="delupfile10" id="delupfile10"/>
                        <input class="gSubmitButtons" type=submit name="delSubmit10" id="delSubmit10" value="Διαγραφή" />
                    </form>
                </div>
            </div>
        <%}%>
   </div>

       </div>

  </body>
</html>