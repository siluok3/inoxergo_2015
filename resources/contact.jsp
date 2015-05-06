<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 27 Μαϊ 2010
  Time: 1:01:33 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Επικοινωνία</title>
      <!--//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  -->
       <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAAZ5AbsIqBIFmjkCh0oq455xQvwEa-wfW8F8ZtXMC9T2i2j-4c9xTcVN620gCeCAKHIvq6MUzsYo0YlA" type="text/javascript">
       </script>
       <script type="text/javascript">

          function initialize() {
            if (GBrowserIsCompatible()) {
              var map = new GMap2(document.getElementById("map_canvas"));
              map.setCenter(new GLatLng(38.403237, 23.807576), 13);
              map.setUIToDefault();
            }
          }

          </script>

      <!--//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  -->

  </head>
  <body onload="initialize()" onunload="GUnload()">
  <div class="centercol">
      <h3>Επικοινωνία</h3>
      <p>
         Σας ευχαριστούμε για το ενδιαφέρον σας παρακαλούμε επικοινωνήστε μαζί μας με έναν από τους παρακάτω τρόπους.<br/><br/>
          <span class="greentext">Τηλέφωνο:</span> (2310) 905.655-6 <br/>
          <span class="greentext">Fax:</span> 22290-60877  <br/>
          <span class="greentext">Διεύθυνση:</span> 22o χλμ Χαλκίδας - Αλιβερίου | Αγ.Γεώργιος,  Ερέτρια, 34008 Εύβοια  <br/>
          <span class="greentext">E-mail:</span><a href="mailto:info@inoxergo.gr" class="simple"> info@inoxergo.gr</a> <br/> <br/>
          Βρείτε μας και στο χάρτη (Πατήστε το + στα αριστερά)               </p>
      <%--<div id="map_canvas" style="width: 500px; height: 300px"></div>--%>
     <iframe width="425" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=el&amp;geocode=&amp;q=38.403237,23.807576&amp;sll=38.403282,23.80777&amp;sspn=0.002733,0.004823&amp;ie=UTF8&amp;ll=38.403188,23.806504&amp;spn=0.01093,0.01929&amp;t=h&amp;z=14&amp;output=embed"></iframe><br />
      <%--<small>--%>
      <%--<a href="http://maps.google.com/maps?f=q&amp;source=embed&amp;hl=el&amp;geocode=&amp;q=38.40272260429585,+23.81059169769287&amp;sll=37.0625,-95.677068&amp;sspn=47.033113,79.013672&amp;ie=UTF8&amp;t=h&amp;z=14&amp;ll=38.402822,23.81162" style="color:#0000FF;text-align:left">Προβολή μεγαλύτερου χάρτη</a></small>--%>

 
      <a href="http://localhost:8080/inoxergo/login.jsp" class="simple" >Σύνδεση</a>
  </div>


  <script type="text/javascript">
 var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
 document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
 </script>
 <script type="text/javascript">
 try {
 var pageTracker = _gat._getTracker("UA-15972981-1");
 pageTracker._trackPageview();
 } catch(err) {}</script>

  </body>
</html>