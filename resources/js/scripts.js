$(document).ready( function() {
	//change product's bg
	 $("ul#items li").hover(
      function () {
        $(this).find("div.details").css('background','#a0d7f6');   //#36a9eb  #ffa1a1
        $(this).find("div.details span").css('color','#947969');   
      },
      function () {
        $(this).find("div.details").css('background','#947969');
        $(this).find("div.details span").css('color','#ffffff');   
      }
    );
	//set color active
	$('ul#color li a')
    .bind("click",
        function() {
		  $(this)
		  .addClass("activecolor")
		  .parent()
		  .siblings()
		  .find('a')
		  .removeClass("activecolor")
		  .end();
		  var getSrcColor = $(this).attr("rel");
		  $("div#product-preview a").attr("href",getSrcColor);
			$("div#product-preview img").attr("src",getSrcColor);
			  $('input#iscolor').val( $("ul#color li a.activecolor img").attr("alt"));
//			 $('input#iscolor').val( $("ul#color li a img").attr("alt"));
		  return false;
    });
	//set size active
	$('ul#size li a')
    .bind("click",
        function() {
		  $(this)
		  .addClass("activesize")
		  .parent()
		  .siblings()
		  .find('a')
		  .removeClass("activesize")
		  .end();
         $('input#issize').val( $(this).attr("title"));
  		  return false;
    });
		//check properties
	$('#add2cart')
	.bind("click",
		function() {
		var isColorSelected = $('ul#color li a').hasClass("activecolor");
		var isSizeSelected  = $('ul#size li a').hasClass("activesize");
		if (isColorSelected == false && isSizeSelected == false) {
			alert("Πρέπει να επιλέξετε χρώμα και μέγεθος");
		}
		else if ( isColorSelected == false && isSizeSelected == true ) {
			alert("Πρέπει να επιλέξετε χρώμα.");
		}
		else if ( isColorSelected == true && isSizeSelected == false ) {
			alert("Πρέπει να επιλέξετε μέγεθος.");
		}
//		if ( isSizeSelected == false ) {
//			alert("Πρέπει να επιλέξετε μέγεθος.");
//		}
		else{
           // alert("Όλα εντάξει. Προσθήκη στο καλάθι.");
            $('div.popupcart').slideToggle("slow");
            $("div.popupcart").click(function () {
                 $("div.popupcart").slideToggle("slow");
           document.getElementById('shoppingForm').submit();    });
            $("div#page").click(function () {
                 $("div.popupcart").slideToggle("slow");
           document.getElementById('shoppingForm').submit();    });
        }
         return false;
	});
    
    //switch images
	$('div#thumbs ul li a')
	.bind(" mouseover",
		function() {
		var getSrc = $(this).attr("rel");
    $("div#product-preview a").attr("href",getSrc);
		$("div#product-preview img").attr("src",getSrc);
	$("a#thelightbox").attr("href",getSrc);
            return false;
	});

    //style dropdowns
	$("select.linkselect").linkselect({
     change: function (li, value, text){
      $('input#isbrand').val(value);
      $('#brandform').submit();
     }
     });

$("select.linkselect1").linkselect({
     change: function (li, value, text){
      $('input#sort').val(value);
      $('#sortform').submit();
     }
     });

   $("select.stateselect").linkselect({
     change: function (li, value, text){
      $('input#isstate').val(value);
      $('#stateform').submit();
     }
     });

$("select.linkselect2a").linkselect({
     change: function (li, value, text){
      $('input#typeselect').val(value);
     }
     });

$("select.linkselect2b").linkselect({
     change: function (li, value, text){
      $('input#catselect').val(value);
     }
     });

$("select.linkselect2c").linkselect({
     change: function (li, value, text){
      $('input#brandselect').val(value);
     }
     });

$("select.linkselect2d").linkselect({
     change: function (li, value, text){
      $('input#stateselect').val(value);
     }
     });


$("select.linkselectinstalments").linkselect({
     change: function (li, value, text){
      $('input#areinstalments').val(value);
      $('#instalmentsform').submit();
     }
     });



    $("select.linkselectbasket").linkselect({
         change: function (li, value, text){
          $('input#paymanner').val(value);
          $('#paymannerform').submit();
         }
         });


    $("select.statusselectclass").linkselect({
    change: function (li, value, text){
    $('input#isstatus').val(value);
    $('#isstatusform').submit();
    }
    });


});







  //bubble login

    $(function () {
        $('#login').each(function () {
            var distance = 10;
            var time = 250;
            var hideDelay = 5000;

            var hideDelayTimer = null;

            var beingShown = false;
            var shown = false;
            var trigger = $('.trigger', this);
            var info = $('#dpop').css('opacity', 0);


            $([trigger.get(0), info.get(0)]).mouseover(function () {
                if (hideDelayTimer) clearTimeout(hideDelayTimer);
                if (beingShown || shown) {
                    // don't trigger the animation again
                    return;
                } else {
                    // reset position of info box
                    beingShown = true;

                    info.css({
                        top: 7,
                        left: 780,
                        display: 'block'
                    }).animate({
                        top: '-=' + distance + 'px',
                        opacity: 1
                    }, time, 'swing', function() {
                        beingShown = false;
                        shown = true;
                    });
                }

                return false;
            }).mouseout(function () {
                if (hideDelayTimer) clearTimeout(hideDelayTimer);
                hideDelayTimer = setTimeout(function () {
                    hideDelayTimer = null;
                    info.animate({
                        top: '-=' + distance + 'px',
                        opacity: 0
                    }, time, 'swing', function () {
                        shown = false;
                        info.css('display', 'none');
                    });

                }, hideDelay);

                return false;
            });
        });
    });



