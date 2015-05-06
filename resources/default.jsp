<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 19 Απρ 2010
  Time: 4:24:14 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Vector" %>
<%@ page import="mybeans.ProsforesBean" %>
<%@ page import="mybeans.Pagetext" %>
<jsp:useBean id="prosfmanagement" class="engine.ProsforesManagement" scope="page" />
<jsp:useBean id="pagemanagement" class="engine.PageManagement" scope="page" />
<jsp:useBean id="prosfora" class="mybeans.ProsforesBean" scope="session" />

<html>
  <head>
      <title>Inoxergo Μεταλλικές Εφαρμογές</title>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
      <link href="reset.css" rel="stylesheet" type="text/css" />
      <link href="cssstyle.css" rel="stylesheet" type="text/css" />
      <link type="text/css" rel="stylesheet" href="galleryview.css" />
      <script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
      <script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
      <script type="text/javascript" src="js/jquery.galleryview-2.0.js"></script>
      <script type="text/javascript" src="js/jquery.timers-1.1.2.js"></script>


      <!-- InstanceBeginEditable name="head" -->
      <style type="text/css">

      /*a:link,a:visited {*/
          /*color: #ddd !important;*/
          /*text-decoration: underline;*/
      /*}*/
      /*a:hover {*/
          /*text-decoration: none;*/
      /*}*/
      h3 {
          border-bottom-color: white;
      }
      </style>
      <script type="text/javascript">
          $(document).ready(function(){
              $('#photos').galleryView({
                  panel_width: 300,
                  panel_height: 225,
                  frame_width: 30,
                  frame_height: 23,
                  overlay_color: '#222',
                  overlay_text_color: 'white',
                  caption_text_color: '#222',
                  background_color: 'transparent',
                  border: 'none',
                  nav_theme: 'light',
                  easing: 'easeInOutQuad',
                  pause_on_hover: true
              });
          });
      </script>

  </head>

  <body>
  <% Vector vct = prosfmanagement.getAllProsfores();
    int vctsize = vct.size();%>
     <div class="centercold">
         <h3>Καλώς Ήρθατε</h3>
      <p class="pcentercold">
          Η εταιρεία <span class="greentext">“Αφοι Ι.Ζαφειράτου Ο.Ε.” </span>έχει μια μακρόχρονη παρουσία και εμπειρία στο χώρο των
          μεταλλικών κατασκευών από το <span class="greentext">1965</span>, με ειδίκευση στις ανοξείδωτες κατασκευές. <br/><br/>

          <span class="greentext">Για τον άνθρωπο που θέλει να συνδυάζει την ποιότητα με την καλαισθησία,
          η λύση είναι η “Αφοι Ι.Ζαφειράτου Ο.Ε.”.</span>

      </p>

         <% Pagetext ptxt = pagemanagement.getPagetext("default", 1);
           String textdata = ptxt.getText().replace("\n", "<br/>");%>
    <p class="mystyle" style="padding:10px 7px 0 50px;border-right:3px double #6D7D8C"><%=textdata%></p>
         <% Pagetext ptxt2 = pagemanagement.getPagetext("default", 2);
         String textdata2 = ptxt2.getText().replace("\n", "<br/>");%>
      <p class="mystyle" style="padding:10px 0 0 18px"><%=textdata2%></p>
       
         
      <div id="slideshow">
        <div id="photos" class="galleryview">

         <%  for (int i = vctsize-1 ; i > 0; i--) {
             prosfora = (ProsforesBean) vct.elementAt(i);  %>

           <div class="panel">
             <img src="images/slides/<%=prosfora.getImgpath() %>" alt="<%=prosfora.getTitle()%>" title="<%=prosfora.getTitle()%>"/>
             <div class="panel-overlay">
             <h2><%=prosfora.getTitle()%></h2>
             <p><%=prosfora.getText()%></p>
             </div>
          </div>
         <%}%>

      <ul class="filmstrip">
          <%  for (int i = vctsize-1 ; i > 0; i--) {
             prosfora = (ProsforesBean) vct.elementAt(i);
         %>
        <li><img src="images/slides/<%=prosfora.getThumbpath() %>" alt="<%=prosfora.getTitle()%>" title="<%=prosfora.getTitle()%>" /></li>
       <% } %>
      </ul>
      </div>
    </div>
    </div>

  </body>
</html>