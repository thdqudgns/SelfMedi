<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/layout/headerbs.jsp" />

<script type="text/javascript">
	window.onload = function() {
		//이벤트리스너 등록 - addEventListener
		//이벤트리스너 해제 - removeEventListener

		//--------------------------------------------------------

		btnTargetCate.addEventListener("click", targetCateClicked)
		btnSymptomCate.addEventListener("click", SymptomCateClicked)
		btnNutCate.addEventListener("click", NutCateClicked)

		//--------------------------------------------------------

	} //window.onload end

	function targetCateClicked() {
		targetCate.style.display = "block";
		symptomCate.style.display = "none";
		nutCate.style.display = "none";
	}
	function SymptomCateClicked() {
		targetCate.style.display = "none";
		symptomCate.style.display = "block";
		nutCate.style.display = "none";
	}
	function NutCateClicked() {
		targetCate.style.display = "none";
		symptomCate.style.display = "none";
		nutCate.style.display = "block";
	}
</script>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
}
.tg td {
	border-color: #ccc;
	border-style: solid;
	border-width: 1px;
	font-family: Arial, sans-serif;
	font-size: 14px;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
}
.tg th {
	border-color: #ccc;
	border-style: solid;
	border-width: 1px;
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	overflow: hidden;
	padding: 10px 5px;
	word-break: normal;
}
.tg .tg-baqh {
	text-align: center;
	vertical-align: top
}
.category {
	display: none;
}
.productNav {
	width: 500px;
	margin: auto;
}
.wrapper {
	box-sizing: border-box;
	margin: 0 auto;
	width: 1200px;
	height: 1200px;
	padding: 0 124px;
}
.productBox {
	position: relative;
	width: 470px;
	height: 400px;
	padding: 10px 10px 10px 10px;
	border-radius: 8px;
/* 	-webkit-box-shadow: 0 2px 4px 0 rgb(0 0 0/ 6%); */
/* 	box-shadow: 0 2px 4px 0 rgb(0 0 0/ 6%); */
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	background-repeat: no-repeat;
	background-position: right 20px top 20px;
	background-size: 130px;
	margin: 0;
	float: left;
}
#baskethr{border-color:#345EE6; width:1200px;text-align: center;margin: 0 auto;}
.button{
	magin: auto;
	color: blue;
	padding: 15px 30px;
}
.tg-baqh-word{}
.container-pro{width:100%;}

</style>


<div class="container-pro">

	<hr>
	<div class="productNav">
	<div style="text-align:center;">
		<div style="text-align:center;">
		<h1 style="display: inline-block;color:black;font-weight:bold;">상품 목록</h1>
		</div>
		
		<br>
		
		<div class="search">
		<form action="/product/display" method="get"> 
			<input type="text" name="search" size="50" maxlength="30" placeholder="상품을 검색해보세요."><input type="submit"
				value="검색" />
		</form>
		</div>
		<div class="button" size="width: 33.33%;">
		<button id="btnTargetCate">대상별</button>
		<button id="btnSymptomCate">증상별</button>
		<button id="btnNutCate">영양소별</button>
		</div>
		
		<div id="targetCate" class="category">
			<button>
				<a href="/product/display?targetId=1">청소년</a>
			</button>
			<button>
				<a href="/product/display?targetId=2">성인</a>
			</button>
			<button>
				<a href="/product/display?targetId=3">중장년</a>
			</button>
		</div>
	</div>

		<div id="symptomCate" class="category">
			<c:forEach items="${symptomList }" var="symptomCode">
				<button>
					<a href="/product/display?symptomId=${symptomCode.symptomId }">${symptomCode.symptomName }</a>
				</button>
			</c:forEach>
		</div>

		<div id="nutCate" class="category">
			<c:forEach items="${nutrientList }" var="NutrientKind">
				<button>
					<a href="/product/display?nutId=${NutrientKind.nutId }">${NutrientKind.nutKind }</a>
				</button>
			</c:forEach>
		</div>
	</div>

	<br><br>
	<hr id="baskethr">

	<div class="wrapper">
		<c:forEach items="${productList }" var="productInfo">
			<div class="productBox" style="text-align: center;">
				<table class="tg" style="table-layout: fixed; width: 450px">
					<colgroup>
						<col style="width: 300px">
						<col style="width: 150px">
					</colgroup>
					<thead>
						<tr>
							<th class="tg-baqh" colspan="2"><a
								href="/product/detail?productId=${productInfo.productId }">
									<img src="${productInfo.image }" style="object-fit:contain;width:400px;height:300px;"> 
							</a></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="tg-baqh-word" style="font-family:'notoRegular';font-weight:bold;background-color:rgba(52, 152, 219, 0.3)">${productInfo.productName }</td>
							<td class="tg-baqh-word" style="font-family:'notoRegular';font-weight:bold;">${productInfo.starScore }점</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:forEach>
	</div>


	<!-- .container -->
</div>
<div id="result"></div>
<c:import url="/WEB-INF/views/layout/pagingProduct.jsp" />

<c:import url="/WEB-INF/views/layout/footer.jsp" />
