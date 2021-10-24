<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<style type="text/css">
div.container{ padding-top: 50px; width:1200px; margin:auto; height:1500px;}
.producttb{width:50%;margin:0;float:left;}
.nutwrap1{width:50%;margin:0;float:left;}
.nutwrap2{width:50%;margin:0;float:left;}
.nutdiv{width:50%;margin:0;float:left; padding-top:30px;}
td.typroducttdpe{border: 1px solid #ccc;}
td.score{border: 1px solid #ccc;}
.producttd{border: 1px solid #ccc; height:50px;vertical-align: middle;}
.title{text-align: center; background-color: #345EE6; color: #fff;padding:20px 0px;}

td.nut{border: 1px solid #ccc;}
td.nutkind{border: 1px solid #ccc;}
td.nutgraf{border-bottom: 1px solid #ccc;}
td.nutcontent{border-bottom: 1px solid #ccc;}
td.rcm{border-bottom: 1px solid #ccc; border-right:1px solid #ccc;}
meter {
    -webkit-writing-mode: horizontal-tb !important;
    appearance: auto;
    box-sizing: border-box;
    display: inline-block;
    height: 3em;
    width: 30em;
    vertical-align: -0.2em;
    -webkit-user-modify: read-only !important;
}
</style>

</head>
<body id="merge">
<script type="text/javascript">
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-36251023-1']);
_gaq.push(['_setDomainName', 'jqueryscript.net']);
_gaq.push(['_trackPageview']);


(function ($) {
	    $.fn.jstars = function (options) {
	        options = options || {};

	        var defaults = {
	            size: '1.5rem',
	            value: 4.5,
	            stars: 5,
	            color: '#4285F4',
	            emptyColor: '#dddddd'
	        };
	        var settings = $.extend(defaults, options);

	        var unselectable = function ($element) {
	            $element.css('user-select', 'none')
	                .css('-moz-user-select', 'none')
	                .css('-khtml-user-select', 'none')
	                .css('-webkit-user-select', 'none')
	                .css('-o-user-select', 'none');
	        };

	        var repeat = function (str, total) {
	            var last = '';
	            for (var i = 0; i < total; i++) last += str;
	            return last;
	        }; 

	        this.each(function () {
	            var $container = $(this);
	            var value = $container.data('value') || settings.value;
	            var totalStars = $container.data('total-stars') || settings.stars;
	            var color = $container.data('color') || settings.color;
	            var emptyColor = $container.data('empty-color') || settings.emptyColor;
	            var size = $container.data('size') || settings.size;

	            var $emptyStars = $(document.createElement('div'))
	                .addClass('jstars-empty')
	                .css('position', 'relative')
	                .css('display', 'inline-block')
	                .css('font-size', size)
	                .css('line-height', size)
	                .css('color', emptyColor)
	                .append(repeat("&starf;", totalStars));

	            unselectable($emptyStars);

	            var $filledStars = $(document.createElement('div'))
	                .addClass('jstars-filled')
	                .css('top', 0)
	                .css('left', 0)
	                .css('position', 'absolute')
	                .css('display', 'inline-block')
	                .css('font-size', size)
	                .css('line-height', size)
	                .css('width', ((value / totalStars) * 100) + '%')
	                .css('overflow', 'hidden')
	                .css('white-space', 'nowrap')
	                .css('color', color)
	                .append(repeat("&starf;", totalStars));

	            unselectable($filledStars);

	            $emptyStars.append($filledStars);
	            $container.append($emptyStars);
	        });
	        return this;
	    };
	    $(function () {
	        $('.jstars').jstars();
	    });
	})(jQuery);
</script>
<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<div class="container">
	<h3 class="title"><b>제품 합산</b></h3>
	<hr>
	<c:forEach items="${productInfo }" var="productInfo">
		<div class="producttb">
			<table class="product"  style="cellspacing: 0; border-collapse: collapse;text-align:center;" >
				<thead>
					<tr>
						<td class="image" rowspan="3" colspan="5"><img src="${productInfo.image }"
							width="585" height="300" style="object-fit:contain"></td>
					</tr>
				</thead>
					<tr>
						<td class="producttd" colspan="5" style="font-weight:bold;">${productInfo.productName }</td>
					</tr>
					<tr>
						<td class="producttd" style="width:146.25px;background-color: #EEE;font-weight:bold;">제조사 </td>
						<td class="producttd" colspan="4">${productInfo.manuCom }</td>
					</tr>
					<tr>
						<td class="producttd" colspan="1" style="width: 146.25px;background-color: #EEE;font-weight:bold;">타입</td>
						<td class="typroducttdpe" colspan="1" style="width: 146.25px;">${productInfo.type }</td>
						<td class="score" style="width:146.25px;background-color: #EEE;font-weight:bold;">별점</td>
						<td class="producttd" colspan="1" style="width: 146.25px;">
						<div style="vertical-align:middle;">
						<div style="display:inline-block;" class="jstars" data-value="${productInfo.starScore }" data-size="14px" data-color="blue"></div>
						<div>${productInfo.starScore }점</div>
						</div>
						</td>
					</tr>
			</table>
		</div>
	</c:forEach>

	<div class="nutdiv"  style="width:1170px;">
		<table class="nutheadtb" style="cellspacing: 0; border-collapse: collapse;text-align:center;">
			<tr class="nuttr" style="height:50px;">
				<td class="nut" style="width:80px;background-color: #EEE;font-weight:bold;">함유 영양소</td>
				<td class="nut" colspan="2" style="width:600px;background-color: #EEE;font-weight:bold;">권장량 비교 그래프</td>
				<td class="nut" style="width:260px;background-color: #EEE;font-weight:bold;">함량</td>
				<td class="nut" style="width:260px;background-color: #EEE;font-weight:bold;">권장량</td>	
			</tr>

<!-- 		<table class="nuttb" style="cellspacing: 0; border-collapse: collapse;"> -->
			<c:forEach items="${nutList }" var="nutlist">
					<tr style="height: 60px;">
						<td class="nutkind" style="width:80px;">${nutlist.nk.nutKind }</td>
						<td class="nutgraf" colspan="2" style="width:600px;"><meter
								low='${nutlist.nk.rcmAdult.replaceAll("[^0-9]", "")*0.6 }'
								high='${nutlist.nk.rcmAdult.replaceAll("[^0-9]", "")*1.5 }' 
								min='0'
								max='${nutlist.nk.rcmAdult.replaceAll("[^0-9]", "")*2 }'
								optimum='${nutlist.nk.rcmAdult.replaceAll("[^0-9]", "") }'
								value='${nutlist.ni.nutContent.replaceAll("[^0-9]", "") }'></meter></td>
		
						<td class="nutcontent" style="width:260px;">${nutlist.ni.nutContent }</td>
						<td class="rcm" style="width:260px;">${nutlist.nk.rcmAdult }</td>
					</tr>
			</c:forEach>
		</table>
	</div>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />