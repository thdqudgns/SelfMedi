<%@page import="com.fulldoping.product.dto.NutrientInfo"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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

<script type="text/javascript">

	window.onload = function() {
		$("button[data-btn]").click(function() {
			$("#" + $(this).attr("data-btn")).toggle();
		});		
 			
		$("[name='addbasket']").click(function() {
			
			$.ajax({
				type: "post"
				, url: "/add/basket"
				, data: {
					productId: '${param.productId }'
				}
				, dataType: "json"
				, success: function(res) {
					if( confirm("비교함으로 이동하시겠습니까?") ) {
						location.href = "/basket/view";
					}
					
					console.log( res.result )
				}
				, error: function() {
					console.log("ajax error")
				}
			})
			
 		    if(confirm("비교함에 추가 하시겠습니까 ?") == true){
 		        alert("추가 되었습니다");
// 				local.href="/add/basket?productId=${productInfo.productId }";
 		    }else{
 		        return ;
 		    }
		});
		/* 
		$("[name='addbasket']").click(function() {
			if( confirm("비교함에 추가 하시겠습니까 ?") == true ) {
				$.ajax({
					//보관함 상품 확인 URL로
					//상품번호 전달
				url:"/add/basket",
				data:{ proid: "${productInfo.productId }" },
				type:"GET",
					dataType:"json"
				})
				.done(function(json) {
				})
			}else{
 		       	return false;
			}
		});
		 */
	}; //window.onload end

	// window.onload = function() {
	// 	//이벤트리스너 등록 - addEventListener
	// 	//이벤트리스너 해제 - removeEventListener
	// 	//--------------------------------------------------------
	// 	<c:forEach items="${nikList }" var="nik">
	// 	btn${nik.ni.nutId }.addEventListener("click", togleDetail${nik.ni.nutId })
	// 	</c:forEach>
	// 	//--------------------------------------------------------

	// } //window.onload end

	// <c:forEach items="${nikList }" var="nik">
	// function togleDetail${nik.ni.nutId }() {
	// 	if (detail${nik.ni.nutId }.style.display == "none"){
	// 	detail${nik.ni.nutId }.style.display = "table-row-group";
	// 	}else{
	// 		detail${nik.ni.nutId }.style.display = "none";
	// 	}

	// }
	// </c:forEach>
</script>

<style type="text/css">
.tg { border-collapse: collapse; border-spacing: 0; }

.tg td { border-color: black; border-style: solid; border-width: 1px;
	font-family: Noto Sans;
	overflow: hidden;
	padding: 10px 10px;
	word-break: normal;
}

table.tg > thead > tr > td.tg-prodctName { font-size: 24px; }

.tg-Link { font-size: 18px; }

.tg th {
	border-color: black;
	border-style: solid;
	border-width: 1px;
	font-family: Noto Sans;
	font-size: 14px;
	font-weight: normal;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
}

.tg .tg-zv4m {
	border-color: #ffffff;
	text-align: left;
	vertical-align: top
}

.tg .tg-baqh {
	text-align: center;
	vertical-align: middle;
	font-size: 14px;
	padding:10px 10px;
}

.tg .tg-8jgo {
	border-color: #ffffff;
	text-align: center;
	vertical-align: top;
}

.tg .tg-lqy6 {
	text-align: right;
	vertical-align: top;
}

.tg .tg-0lax {
	text-align: left;
	vertical-align: top;
}

table {
	margin: auto; text-align: center;
}

meter {
    -webkit-writing-mode: horizontal-tb !important;
    appearance: auto;
    box-sizing: border-box;
    display: inline-block;
    height: 3em;
    width: 25em;
    vertical-align: -0.2em;
    -webkit-user-modify: read-only !important;
}

tfoot.detail {
	display: none;
	text-align: left;

}

.section_view {
    width: 1050px;
    margin: 0 auto;
    padding-top: 20px;
    padding-bottom: 150px;
}

table.tg {undefined;table-layout: fixed; width: 1050px;border:1px solid #ccc;}
table.tg > thead > tr {border:1px solid #ccc;}
table.tg > thead > tr > th {border:1px solid #ccc;}
table.tg > thead > tr > td {border:1px solid #ccc;}

table.tg > tbody > tr {border:1px solid #ccc;}
table.tg > tbody > tr > th {border:1px solid #ccc;}
table.tg > tbody > tr > td {border:1px solid #ccc;}

table.tg > tfoot > tr {border:1px solid #ccc;}
table.tg > tfoot > tr > th {border:1px solid #ccc;}
table.tg > tfoot > tr > td {border:1px solid #ccc;}

table.tg.detail > tfoot > tr {border:1px solid #ccc;}
table.tg.detail > tfoot > tr > th {border:1px solid #ccc;}
table.tg.detail > tfoot > tr > td {border:1px solid #ccc;}

table.tg > tfoot > tr > td.tg-detail {border:1px solid #ccc;	vertical-align:middle;text-align: left;}


button.addbasket {
 	border: 1px solid #345FF6;
    background-color: #345FF6;  
    color: #fff;
    width: 200px;
    height: 40px;
    font-size: 18px;
    border-radius: 3px;
    font-weight: 700;
}

button.detail {
    border: 1px solid #345FF6;
    background-color: #fff;  
    color: #345FF6;
    width: 100px;
    height: 30px;
    font-size: 14px;
    border-radius: 3px;
    font-weight: 700;
}
</style>



<div class="section_view">
<div >
<div class="">

<!-- <h1 style="text-align: center;color:#345EE6;font-weight: bold;">디테일 페이지</h1> -->
<!-- <hr> -->

<table class="tg">
	<colgroup>
		<col style="width: 500px">
		<col style="width: 100px">
		<col style="width: 450px">
	</colgroup>
	<thead>
		<tr>
			<td class="tg-productImg" rowspan="7"><img src="${productInfo.image }" style="max-width:100%;height:auto;"></td><!-- 상품이미지 -->
			<td class="tg-prodctName" colspan="2" style="font-weight: bold;">${productInfo.productName }</td><!-- 상품명 -->
		</tr>
		<tr>
			<td style="background-color: #EEE;font-weight: bold;">별점</td>
			<td class="tg-starCore"><p class="jstars" data-value="${productInfo.starScore }" data-size="15px" data-color="blue"><p>(${productInfo.starScore }점)
			</td><!-- 별점 점수 -->
		</tr>

		<tr>
			<td style="background-color: #EEE;font-weight: bold;">제조사</td>
			<td class="tg-manuCom">${productInfo.manuCom }</td><!-- 제조사 -->
		</tr>
		<tr>
			<td style="background-color: #EEE;font-weight: bold;">타입</td>
			<td class="tg-type">${productInfo.type }</td><!-- 타입 -->
		</tr>
		<tr>
			<td class="tg-Link" colspan="2"><a href="${productInfo.purchaseLink }" style="font-weight: bold;">구매하러가기</a></td><!-- 구매 링크 -->
		</tr>
		<tr>
			<c:if test="${not empty login and login}">
			<td class="tg-basket" colspan="2"><button class="addbasket" name="addbasket">비교함 추가</button></td><!-- 비교함 추가 -->
			<%-- <a href="/add/basket?productId=${productInfo.productId }">비교함추가</a> --%>
			</c:if>
		</tr>
	</thead>
</table>


<hr>


<table class="tg" style="undefined;table-layout: fixed; width: 1050px">
	<colgroup>
		<col style="width: 120px">
		<col style="width: 400px">
		<col style="width: 130px">
		<col style="width: 130px">
		<col style="width: 130px">	
	</colgroup>
	<c:forEach items="${nikList }" var="nik">
		<thead>
			<tr>
				<td style="background-color: #EEE;font-weight: bold;">함유 영양소</td>
				<td  colspan="2" style="background-color: #EEE;font-weight: bold;" >권장량 그래프</td>
				<td style="background-color: #EEE;font-weight: bold;" >함량</td>
				<td style="background-color: #EEE;font-weight: bold;">권장량</td>
				<td style="background-color: #EEE;font-weight: bold;">상세</td>
			</tr>
		</thead>
		
		<tbody>
			<tr>
				<td class="tg-Kind">${nik.nk.nutKind }</td>
				<%--     <th class="tg-baqh" colspan="2">${nik.ni.nutContent }/ ${nik.nk.rcmAdult }</th> --%>

				<%--     <% pageContext.setAttribute("nutContentValue", ((NutrientInfo)((Map)pageContext.getAttribute("nik")).get("ni")).getNutContent().replaceAll("[^0-9]", "") ); %> --%>
				<%--     <th class="tg-baqh" colspan="2">${nutContentValue }/ ${nik.nk.rcmAdult }</th> --%>

				<%--     <th class="tg-baqh" colspan="2">${nik.ni.nutContent.replaceAll("[^0-9]", "") /${nik.nk.rcmAdult.replaceAll("[^0-9]", "") }</th>--%>
				<td class="tg-recommend" colspan="2" >
				
				<meter
						low='${nik.nk.rcmAdult.replaceAll("[^0-9]", "")*0.85 }'
						high='${nik.nk.rcmAdult.replaceAll("[^0-9]", "")*1.15 }' min='0'
						max='${nik.nk.rcmAdult.replaceAll("[^0-9]", "")*1.3 }'
						optimum='${nik.nk.rcmAdult.replaceAll("[^0-9]", "") }'
						value='${nik.ni.nutContent.replaceAll("[^0-9]", "") }'> </meter></td><!-- 권장량 그래프 -->
		
				<th class="tg-baqh">${nik.ni.nutContent }</th><!-- 함량 -->
				<th class="tg-baqh">${nik.nk.rcmAdult }</th><!-- 권장량 -->
				<th class="tg-baqh"><button name="detail" class="detail" data-btn="detail${nik.ni.nutId }">상세보기</button></th>
				<%--     <th class="tg-8jgo"><button id = "btn${nik.ni.nutId }">상세</button></th> --%>
			</tr>
		</tbody>
		
		<tfoot id="detail${nik.ni.nutId }" class="detail">
			<tr>
				<td class="tg" style="width:80px;background-color: #EEE;font-weight: bold;">기능 / 특성</td>
				<td class="tg-detail" colspan="5">${nik.nk.effect }</td>
			</tr>
			<tr>
				<td class="tg" style="width:80px;background-color: #EEE;font-weight: bold;">결핍증</td>
				<td class="tg-detail" colspan="5">${nik.nk.deficiency }</td>
			</tr>
			<tr>	
				<td class="tg" style="width:80px;background-color: #EEE;font-weight: bold;">과잉증</td>
				<td class="tg-detail" colspan="5">${nik.nk.hyperact }</td>
			</tr>
		</tfoot>
		
	</c:forEach>
</table>

</div>
</div>
</div>

<c:import url="/WEB-INF/views/layout/footer.jsp" />