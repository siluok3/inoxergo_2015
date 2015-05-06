<%--
  Created by IntelliJ IDEA.
  User: eugene
  Date: 27 Μαϊ 2010
  Time: 1:01:04 μμ
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.apache.log4j.Logger" %>
<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="css/slider/style.css" type="text/css" />
<script type="text/javascript"> var _siteRoot='profil.jsp',_root='profil.jsp';</script>
<script type="text/javascript" src="js/slider/jquery.js"></script>
<script type="text/javascript" src="js/slider/scripts.js"></script>


<!--<link rel="stylesheet" href="css/prettyGallery.css" type="text/css" media="screen" charset="utf-8" />-->

<!--<script src="js/jquery.prettyGallery.js" type="text/javascript" charset="utf-8"></script>-->

<%--<script type="text/javascript" charset="utf-8">--%>
	<%--$(document).ready(function(){--%>
		<%--$("ul.gallery").prettyGallery({--%>
			<%--itemsPerPage : 2,--%>
			<%--animationSpeed : 'normal', /* fast/normal/slow */--%>
			<%--navigation : 'top',  /* top/bottom/both */--%>
			<%--of_label: ' of ', /* The content in the page "1 of 2" */--%>
			<%--previous_title_label: 'Previous page', /* The title of the previous link */--%>
			<%--next_title_label: 'Next page', /* The title of the next link */--%>
			<%--previous_label: 'Previous', /* The content of the previous link */--%>
			<%--next_label: 'Next' /* The content of the next link */--%>
		<%--});--%>
	<%--});--%>
<%--</script>--%>

<html>
  <head>
      <title>Εγκαταστάσεις</title>
  </head>                                                            
  <body>
  <div class="centercol">
      <h3>Εγκαταστάσεις</h3>
      <p>
          Οι σύγχρονες εγκαταστάσεις μας , με εξοπλισμό που αποτελεί την τελευταία λέξη της τεχνολογίας στο χώρο,
          όπως <span class="greentext">κοπή με πλάσμα</span> και <span class="greentext">υδροκοπή</span>, αποτελούν το χώρο στον οποίο δημιουργούμε όλα μας τα έργα.
          Στον ίδιο χώρο υπάρχει η αποθήκη πρώτης ύλης, καθώς και τα γραφεία μας.
          <img src="images/ktiria1.jpg" width="388" height="180" alt="T 1" class="myimgfirst"/>

          <!--<ul class="gallery">-->
            <!--<li><img src="images/stor_1.jpg" width="300" height="225" alt="T 5" /></li>-->
            <!--<li><img src="images/stor_2.jpg" width="300" height="225" alt="T 6" /></li>-->
            <!--<li><img src="images/ktiria4.jpg" width="300" height="225" alt="T 3" /></li>            -->
            <!--<li><img src="images/ktiria2.jpg" width="300" height="225" alt="T 1" /></li>-->
            <!--<li><img src="images/stor_3.jpg" width="300" height="225" alt="T 7" /></li>-->
            <!--<li><img src="images/ktiria3.jpg" width="300" height="225" alt="T 2" /></li>-->
            <!--<li><img src="images/stor_5.jpg" width="300" height="225" alt="T 9" /></li>-->
            <!--<li><img src="images/ktiria5.jpg" width="300" height="225" alt="T 4" /></li>            -->
            <!--<li><img src="images/stor_4.jpg" width="300" height="225" alt="T 8" /></li>-->

        <!--</ul>-->

          <div id="header1">
              <div class="wrap">
              <div id="slide-holder">
               <div id="slide-runner">
               <a href="#"><img id="slide-img-1" src="css/slider/images/ktiria1.jpg" class="slide" alt="" /></a>
               <a href="#"><img id="slide-img-2" src="css/slider/images/ktiria2.jpg" class="slide" alt="" /></a>
               <a href="#"><img id="slide-img-3"  src="css/slider/images/ktiria3.jpg"class="slide" alt="" /></a>
               <a href="#"><img id="slide-img-4"  src="css/slider/images/ktiria4.jpg" class="slide" alt="" /></a>
               <a href="#"><img id="slide-img-5" src="css/slider/images/ktiria5.jpg" class="slide" alt="" /></a>

               <div id="slide-controls">
                <p id="slide-client" class="text"><strong> </strong><span></span></p>
                <p id="slide-desc" class="text"></p>
                <p id="slide-nav"></p>
               </div>

           </div>

               <!--content featured gallery here -->
              </div>
              <script type="text/javascript">
               if(!window.slider) var slider={};slider.data=[{"id":"slide-img-1","client":"","desc":""},{"id":"slide-img-2","client":"","desc":""},{"id":"slide-img-3","client":"","desc":""},{"id":"slide-img-4","client":"","desc":""},{"id":"slide-img-5","client":"","desc":""}];
              </script>
             </div></div><!--/header-->


      </p>
  </div>
  </body>
</html>