<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 10 Μαϊ 2010
  Time: 3:05:46 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="mybeans.Pageimg" %>
<%@ page import="mybeans.Pagetext" %>
<%@ page import="java.util.Vector" %>
<jsp:useBean id="user" class="mybeans.User" scope="session" />
<jsp:useBean id="pagemanagement" class="engine.PageManagement" scope="page" />

<html>
  <head><title>Υπόστεγα Inoxergo</title>
<%
         String link = request.getParameter("link");
         if ((link == null) || (link.equals("")) || (link.equals("null"))) {
             link = "ypostega";
         }

         org.apache.log4j.Logger cat = org.apache.log4j.Logger.getLogger(link + ".jsp");
         String path[] = new String[10];
         String title[] = new String[10];
         String description[] = new String[10];
         Pageimg[] imgsource = new Pageimg[10];

         for (int k = 1; k < 11; k++) {
             path[k-1]="";
             title[k-1]="";
             description[k-1]="";

             Vector fotovec = pagemanagement.getAllPageimg(link, k);


             int vctsize = fotovec.size();
             cat.info("size=" + vctsize);
             if (vctsize >= 1) {
                 for (int i = 0; i < vctsize; i++) {

                     Pageimg pimg = (Pageimg) fotovec.elementAt(i);

                     if (i == vctsize - 1) {
                         path[k-1] = path[k-1].concat("'" + pimg.getImgpath() + "'");
                         title[k-1] = title[k-1].concat("'" + pimg.getTitle() + "'");
                         description[k-1] = description[k-1].concat("'" + pimg.getDescription() + "'");
                         cat.info("To path" + k + " einai" + path[k-1]);
                     } else {
                         path[k-1] = path[k-1].concat("'" + pimg.getImgpath() + "',");
                         title[k-1] = title[k-1].concat("'" + pimg.getTitle() + "',");
                         description[k-1] = description[k-1].concat("'" + pimg.getDescription() + "',");
                         cat.info("To path" + k + " einai" + path[k-1]);
                     }
                 }
             } else {
                 path[k-1] = "";
                 title[k-1] = "";
                 description[k-1] = "";
                 cat.info("To path" + k + " einai" + path[k-1]);
             }

         }

     %>




     <script type="text/javascript">
        <% for (int l = 0; l < 10; l++) {
                if (!path[l].equals("")){ %>
         var gallery_images<%=l%> = [<%=path[l]%>] ;
         var gallery_titles<%=l%> = [<%=title[l]%>];
         var gallery_descriptions<%=l%> = [<%=description[l]%>];
        <% }}  %>
     </script>
  </head>


  <body>
  <div class="leftcol">
      <h3>Υπόστεγα</h3>
      <% Pagetext ptxt=pagemanagement.getPagetext(link,1);
         String textdata = ptxt.getText().replace("\n","<br/>");
         for (int l = 1; l < 11; l++) {
         imgsource[l-1]=pagemanagement.getExoimg(link,l);
       }  %>
      <p><%=textdata%></p>
  </div>
  <div class="rightcol">
      <% for (int l = 1; l < 11; l++) {
          String check = imgsource[l - 1].getImgpath();
          cat.info("To path gia check einai " + check);
          if (!(check==null)) {%>
  <a href="#" onclick="$.prettyPhoto.open(gallery_images<%=l-1%>,gallery_titles<%=l-1%>,gallery_descriptions<%=l-1%>);" ><img src="<%=imgsource[l-1].getImgpath()%>" alt="<%=imgsource[l-1].getTitle()%>" title="<%=imgsource[l-1].getTitle()%>" width="80" height="80"></a>
      <% }}  %>
  </div>
  </body>
</html>