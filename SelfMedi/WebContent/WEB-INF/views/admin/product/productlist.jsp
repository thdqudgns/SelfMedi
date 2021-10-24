<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<c:import url="/WEB-INF/views/admin/layout/header.jsp" />

<%-- 여기 header와 paging, footer사이가 본문코드 작성영역입니다. --%>

<script type="text/javascript">
	$(document).ready(function(){
		
		 $("[name='deleteProductBtn']").click(function() {
	         
	         if(confirm("정말 삭제하시겠습니까 ?") == true){
	            var p1 = (this).value;
	            console.log(p1);
	            alert("삭제 되었습니다");
	            location.href="/ad/product/delete?productId=" + p1;
	             
	           }else{
	               return false;
	           }
	      
	      });

 		$("[name='updateProductBtn']").click(function() {
			
 			var p2 = (this).value;
			location.href="/ad/product/update?productId=" + p2;
			
 		});	
		

	});



</script>


<style type="text/css">

.tg  {border-collapse:collapse;
	border-spacing:0;}

.tg td{ border-color:black;
	border-style:solid;
	border-width:1px;
	font-family:Arial,
	sans-serif;
	font-size:14px;
  	overflow:hidden;
  	padding:10px 20px;
  	word-break:normal;}
  
.tg th{border-color:black;
	border-style:solid;
	border-width:1px;
	font-family:Arial, sans-serif;
	font-size:14px;
  	font-weight:normal;
  	overflow:hidden;
  	padding:10px 20px;
  	word-break:normal;}
  
.tg .tg-18bt{background-color:#dae8fc;
	border-color:#dae8fc;
	text-align:left;
	vertical-align:top;
	font-style: fon}

.tg .tg-xphl{border-color:#dae8fc;
	text-align:left;
	vertical-align:top;
	color: steelblue;}

.btnBox {
	background-color:#7892c2;
	border-radius:10px;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Trebuchet MS;
	font-size:15px;
	font-weight:bold;
	padding:5px 10px;
	text-decoration:none;
}
.btnBox:hover {
	background-color:#476e9e;
}
.btnBox:active {
	position:relative;
	top:1px;
}

/* .btn{
	position : relative;
	left: 880px;
} */

</style>

<div id="maincontent">

	<div class="productcontainer">

		<h2 style="margin: 0px auto; padding-top: 30px; text-align: center; color: black;">상품 관리</h2>
		<hr>

		<table class="table table-bordered"
			style="width: 1000px; margin: 0 auto;">
			<colgroup>
				<col style="width: 10%">
				<col style="width: 20%">
				<col style="width: 38%">
				<col style="width: 10%">
				<col style="width: 12%">
			</colgroup>
			<thead>
				<tr>
					<th class="info">사진</th>
					<th class="info">상품번호</th>
					<th class="info">제품 이름</th>
					<th class="info">제조사</th>
					<th class="info">수정/삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${productList }" var="productInfo">
					<tr>
						<td class="tg-xphl"><img src="${productInfo.image }"
							width="60" height="40"></td>
						<td class="tg-xphl">${productInfo.productId }</td>
						<td class="tg-xphl"><a href="/product/detail?productId=${productInfo.productId }">${productInfo.productName }</a></td>
						<td class="tg-xphl">${productInfo.manuCom }</td>
						<td class="tg-xphl"><button id="updateProductBtn" class="btn btn-primary btn-sm"
								name="updateProductBtn" value="${productInfo.productId }">수정</button>
							<button id="deleteProductBtn" name="deleteProductBtn" class="btn btn-danger btn-sm"
								value="${productInfo.productId }">삭제</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
		<button style="margin: 0 auto;">
			<a href="<%=request.getContextPath()%>/ad/product/add">신규상품 등록</a>
		</button>

		<c:import url="/WEB-INF/views/layout/pagingAdProduct.jsp" />
		<!-- .container -->
	</div>


</div>

<c:import url="/WEB-INF/views/admin/layout/footer.jsp" />