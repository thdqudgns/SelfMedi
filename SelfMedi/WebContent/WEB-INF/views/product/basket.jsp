<%@page import="com.fulldoping.product.dto.NutrientInfo"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta charset="utf-8">
<title>: : : Self-Medi : : :</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

    
<style>
*{margin:0; padding:0;}
body {
	margin: 0;
	
	height:auto;
}

#footer {
   width:100%;
   background:#5eaeff;
  text-align: center;
  color: white;
}
.container {text-align: center; width:1050px;}

.tablebody{display:inline-block;  margin:0 auto; padding-top:20px;}

.producttitle{width:1150px; text-align: left;}
#baskethr{border-color:#345EE6;}
#baskettitle{display: inline-block; margin:0; padding: 20px 0px;}
#baskettitlewrap{width:250px;background-color: 345EE6; text-align: }
.tablebody{
	width: 1050px; margin:auto; ;
}

.tablewrap{width:50%; float: left;   /* background-color: #ccc;  border: 10px solid #ccc; */ }




.btnwrap{width: 1050px; margin:0 auto; text-align: center;}

.btn{
	box-shadow: 0px 0px 0px -7px #f0f7fa;
	background:linear-gradient(to bottom, #33bdef 5%, #019ad2 100%);
	background-color:#33bdef;
	border-radius:32px;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Arial;
	font-size:15px;
	font-weight:bold;
	padding:9px 59px;
	text-decoration:none;
	text-shadow:0px 0px 0px #5b6178;
}
.btn:hover {
	background:linear-gradient(to bottom, #019ad2 5%, #33bdef 100%);
	background-color:#019ad2;
}
.btn:active {
	position:relative;
	top:1px;
}
#compbtn{width: 300px;}
#mergebtn{width: 300px;}
.productchkbox{text-align: left;}
.productdelbtn{text-align: right;}
.blank{width:15px;}
.productname{text-align: left; width: 235px;}
.productcom{text-align: left;}
.productstar{text-align: left;width:40px;}
.productscore{text-align: left;}

button.deletebtn {
    border: 1px solid #345FF6;
    background-color: #fff;  
    color: #345FF6;
    width: 70px;
    height: 30px;
    font-size: 14px;
    border-radius: 3px;
    font-weight: 700;
}

</style>

</head>
<body>

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
  
  $(document).ready(function() {
		$("input[name=productId]:checkbox").change(function() {// 체크박스들이 변경됬을때
				var cnt = 2;
			if (cnt == $("input[name=productId]:checkbox:checked").length) {
				$(":checkbox:not(:checked)").attr("disabled","disabled");
			} else {
				$("input[name=productId]:checkbox").removeAttr("disabled");
					}
		});

		$("#selCnt").change(function() {
			$("input[name=productId]:checkbox").removeAttr("checked");
			$("input[name=productId]:checkbox").removeAttr("disabled");
		});

		
		//글쓰기 버튼 누르면 이동
		$("#compbtn").click(function() {

		/* 	//방법1. 선택된 productId들을 , 로 구분하는 한 문자열로 합쳐서 보낸다  -> 컨트롤러에서 split() 필요
			var productIds = $("input[name='productId']:checked").map(function() {
			return $(this).val();
			}).get().join();
												
			console.log("-- checked --")
			console.log(productIds)
												
			location.href="/comparison?productIds=" + productIds; */

			//방법2. form태그를 생성하고 checked된 input체크박스를 submit하는 방식
			//	-> 컨트롤러에서 req.getParameterValues("productId"); 사용
			var form = $("<form>").attr("action","/comparison").attr("method", "get");

			$("input[name='productId']:checked").each(function() {
					//			console.log( $(this).val() )
				form.append($(this)).appendTo($(document.body));
			})
				form.submit();
		});

		$("#mergebtn").click(function() {
			
			var form = $("<form>").attr("action","/merge").attr("method", "get");
			
			$("input[name='productId']:checked").each(function() {
				//			console.log( $(this).val() )
				form.append($(this)).appendTo($(document.body));
			})
				form.submit();
		});

					
		$("[name='deletebtn']").click(function() {
			
		    if(confirm("정말 삭제하시겠습니까 ?") == true){
		        alert("삭제 되었습니다");
				var form = $("<form>").attr("action","/delete/basket").attr("method", "get");
				
				form.append($(this)).appendTo($(document.body));
			
				form.submit;
				
		    }else{
		        return ;
		    }
				/* 	console.log( $(this).prev().prev()); */
		});

	});
</script>
<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

	<div class="container">
		<div class="producttitle" style="text-align:center;">
				<h1 id="baskettitle" style="font-weight:bold;color:black;">나의 비교함 목록</h1>
				<hr id="baskethr">
		</div>
			<div class="tablebody">
				<c:forEach items="${productInfo }" var="productInfo">
					<div class="tablewrap">
						<table>
							<tr>
			   					<td class="productchkbox"><input type="checkbox" value="${productInfo.productId }" name= "productId" /></td>
			    				<td class="productdelbtn" colspan="3"><button class="deletebtn" name="deletebtn" value="${productInfo.productId }">상품삭제</button></td>
			    				<%-- <td class="productdelbtn" colspan="3"><button name="deletebtn" value="${productInfo.productId }" >제거</button></td> --%>
			   				</tr>
			   				<tr>
			   					<td class="productimg" rowspan="4"><img src="${productInfo.image }" width="250" height="150" name="image"></td>
			   				</tr>
			   				<tr>
			   					<td class="blank"></td>
			   					<td class="productname" colspan="2">${productInfo.productName }</td>
			   				</tr>
			   				<tr>
			   					<td class="blank"></td>
			   					<td class="productcom" colspan="2">제조사 : ${productInfo.manuCom }</td>
			   				</tr>
			   				<tr>
			   					<td class="blank"></td>
			   					<td class="productstar"><span class="jstars" data-value="${productInfo.starScore }" data-size="10px" data-color="black"></span></td>
			   					<td class="productscore">(${productInfo.starScore }/5)</td>
		   					
		   					</tr>

	   					</table>
					</div>
				</c:forEach>
			</div>
	</div>	
	
	<br><br>
	
		<div class="btnwrap">
			<span style="padding-right:40px;"><button class="btn" id="compbtn" style="border:1px solid #CCC;font-weight: bold;">상품비교하기</button></span>
			<span style="padding-left:40px;"><button class="btn" id="mergebtn" style="border:1px solid #CCC;font-weight: bold;">상품합산하기</button></span>
		</div>
</body>
<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
</html>
