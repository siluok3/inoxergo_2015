
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="google-site-verification" content="CnZItlKri_gSpp_l1qZHrmXCHijgkY7K9gPn0n2HQwg" />
  <meta name="keywords" content="Ιnox, Εtalbond Ιnox, Ερέτρια, Ερέτρεια, Σκάλες, Κάγκελα, Μέταλλα, Υπόστεγα, Κλιμακοστάσια, Κουπαστές, Ελλάσματα, Ανοξείδωτες Κατασκευές, Ζαφειράτος, Αφοί Ζαφειράτου, Zafiratos, εμπόριο σιδήρου, stairs, metal, construction"/>
  <meta name="description" content="H Inox-ergo είναι μια εταιρία στην Ερέτρια Ευβοίας που έχει εδραιωθεί στον χώρο των ανοξείδωτων inox κατασκευών."/>
  <title>Inox-Ergo, Μεταλλικές Κατασκευές</title>
  <link href="reset.css" rel="stylesheet" type="text/css" />
  <link href="cssstyle.css" rel="stylesheet" type="text/css" />
  <link href="jquery.linkselect.css" rel="stylesheet" type="text/css" />
   <script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
  <script type="text/javascript" src="js/jquery.linkselect.js"></script>
	<link rel="stylesheet" href="css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />
	<script src="js/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>

  <script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>

    <link rel="shortcut icon" href="favicon.ico">
<link rel="icon" type="image/gif" href="animated_favicon1.gif">

 <script type="text/javascript" charset="utf-8">
		$(document).ready(function(){
			$("a[rel^='prettyPhoto']").prettyPhoto({
				animationSpeed: 'normal', /* fast/slow/normal */
				padding: 40, /* padding for each side of the picture */
				opacity: 0.35, /* Value betwee 0 and 1 */
				showTitle: true, /* true/false */
				allowresize: true, /* true/false */
				counter_separator_label: '/', /* The separator for the gallery counter 1 "of" 2 */
				theme: 'light_rounded', /* light_rounded / dark_rounded / light_square / dark_square */
               callback: function(){}
            });
		});


    </script>

    
</head>
<body>

<%   String link = request.getParameter("link");
    if((link==null)||(link.equals(""))||(link.equals("null"))){
        link = "default";
    } %>

     <div id="page">
         <div id="header">
                <div id="logo">
                  <a href="index.jsp?link=default"><object width="400" height="100">
            <param name="movie" value="logo.swf">
            <param name="quality" value="high">
            <param name="wmode" value="opaque">
            <embed src="logo.swf" width="400" height="100"  quality="high" class="flashy" wmode="opaque" >
            </embed>
            </object></a>
                </div>
                  <ul id="hdnav">

                    <li class="hdnavli"><a href="http://ergokamin.gr/"><img src="images/logo-ergokamin_small.png"></a></li>
                     <br/><br/><br/>
                    <li class="hdnavli"><a href="index.jsp?link=etaireia">Η Εταιρεία μας</a></li>
                    <li class="hdnavli"><a href="index.jsp?link=profil">Εγκαταστάσεις</a></li>
                    <li class="hdnavli"><a href="index.jsp?link=contact">Επικοινωνία</a></li>
                  </ul>
                  <ul id="lang">
           <li><a href="http://www.google.com/translate?sl=el&tl=en&u=http://www.inoxergo.gr"><img src="images/en.jpg" alt=""</a></li>
           <li><a href="http://www.google.com/translate?sl=el&tl=fr&u=http://www.inoxergo.gr"><img src="images/fr.jpg" alt=""</a></li>
           <li><a href="http://www.google.com/translate?sl=el&tl=de&u=http://www.inoxergo.gr"><img src="images/ge.jpg" alt=""</a></li>
           <li><a href="http://www.google.com/translate?sl=el&tl=it&u=http://www.inoxergo.gr"><img src="images/it.jpg" alt=""</a></li>
           <li><a href="http://www.google.com/translate?sl=el&tl=zh&u=http://www.inoxergo.gr"><img src="images/ch.jpg" alt=""</a></li>
                  </ul>
            </div>
            <div id="hdflash">
            <object width="965" height="250" class="flashy">
            <param name="movie" value="flashzaf.swf">
            <param name="wmode" value="opaque">
            <param name="quality" value="high">
            <embed src="flashzaf.swf" width="965" height="250"  quality="high" class="flashy" wmode="opaque" >
            </embed>
            </object>
            </div>
         <div id="main">
                <div id="mainmenu">
                <div id="menu">
                <ul id="mainnav">
                <li class="mainnavli"><a href="index.jsp?link=kagkela">Κάγκελα</a></li>
                <li class="mainnavli"><a href="index.jsp?link=ypostega">Υπόστεγα</a></li>
                <li class="mainnavli"><a href="index.jsp?link=klimakostasia">Κλιμακοστάσια-Κουπαστές</a></li>
                <li class="mainnavli"><a href="index.jsp?link=skales">Σκάλες</a></li>
                <li class="mainnavli"><a href="index.jsp?link=portes">Πόρτες</a></li>
                <li class="mainnavli"><a href="index.jsp?link=ependyseis">Επενδύσεις Etalbond Inox</a></li>
                <li class="mainnavli"><a href="index.jsp?link=kataskeyes">Μεταλλικές Κατασκευές</a></li>
                <li class="mainnavli"><a href="index.jsp?link=emporio">Εμπόριο Σιδήρου & Επεξεργασία Μετάλλων</a></li>
                <li class="mainnavli"><a href="index.jsp?link=metala">Διάφορες Κατασκευές</a></li>
                </ul>
                </div>
                <div id="pelatologio">
                    <a href="index.jsp?link=pelatologio">.</a>
                </div>
                </div>

                <div id="col">
                    <div id="textholder">
                    <jsp:include page="<%=mybeans.linkGetter.getlink(link)%>"/>
                    </div>

                </div>

            </div>
            <div id="footer">
                <div id="footcenter">
                    <p>Copyright-2010 inoxergo.gr © </p>
                  </div>
                <div id="footright">
                 <a href="http://www.freelancing.gr">Powered by freelancing.gr</a> | <a href="http://www.it-expert.gr"> Managed by It-Expert ®</a>
                 </div>
            </div>

     </div>
</body>
</html>