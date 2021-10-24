<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/WEB-INF/views/admin/layout/header.jsp" />

<script type="text/javascript">
   $(document).ready(function() {
      $("input[name=targetCode]:checkbox").change(
         function() {// 체크박스들이 변경됬을때
            var cnt = 1;
            if (cnt == $("input[name=targetCode]:checkbox:checked").length) {
                  $("input[name=targetCode]:checkbox:not(:checked)").attr("disabled", "disabled");
            } else {
                  $("input[name=targetCode]:checkbox").removeAttr("disabled");
            }
});

$("#selCnt").change(function() {
   $("input[name=targetCode]:checkbox").removeAttr("checked");
   $("input[name=targetCode]:checkbox").removeAttr("disabled");
});

//페이지 첫 접속 시 입력창으로 포커스 이동
$("input").eq(0).focus();

               

$("[name='addProductBtn']").click(function() {
      
   
   var p1 = document.getElementById('productName').value;
   var p2 = document.getElementById('manuCom').value;
   var p3 = document.getElementById('type').value;
   var p4 = document.getElementById('image').value;
   var p5 = document.getElementById('purchaseLink').value;
      if (p1 == "" || p2 == ""|| p3 == "" || p4 == ""|| p5 == "") {
         
         alert('필수항목 누락')
         return false;
      }else{
         $(this).parents("form").submit();
		
      }
      /*    console.log( $(this).prev().prev()); */
		
});

               
});
      
</script>

<style type="text/css">
.page_aticle {
   width: 1000px;
   margin: 0 auto;
   background: #fff;
/*    text-align: center; */
}

.member_join {
   width: 640px;
   margin: 0 auto;
   background: #fff;
}

form {
   width: 500px;
   margin: 0 auto;
}


.icon {
   color: red;
   font-size:
}

input[type=text] {
   width: 60%;
   height: 44px;
   padding: 0 14px;
   border: 1px solid #ccc;
   font-size: 14px;
   color: #333;
   line-height: 20px;
   border-radius: 3px;
   background: #fff;
   outline: none;
   vertical-align: top;
}

input[type=text]:focus, input[type=password]:focus {
   border-color: dodgerBlue;
   box-shadow: 0 0 8px 0 dodgerBlue;
}

button.btnAdd {
    border: 1px solid #345FF6;
    background-color: #345FF6;
    color: #fff;
    width: 200px;
    height: 40px;
    font-size: 16px;
    border-radius: 3px;
    font-weight: 700;
    vertical-align: middle;
}
</style>

<!-- ------------------------------------------------------------------ -->

<hr>
<div id="maincontent">
<div id="productcontainer">

   <div class="page_aticle">
      <div class="type_form member_join ">
      <h2 class="text-center" >신규 상품 등록</h2>
         <p class="page_sub">
            <span class="icon">* </span>필수입력사항
         </h6>
         <form id="form" name="productInfo" action="/ad/product/add"
            method="post">
            <div>
               <label for="productName">상품 명 <span class="icon">*</span>
               </label>
               <div>
                  <input type="text" id="productName" name="productName" placeholder="상품명을 입력해주세요"/>
               </div>
            </div>

            <div>
               <label for="productId">상품번호 <span class="icon">*</span>
               </label>
               <div>
                  <input type="text" id="productId" name="productId" placeholder="상품번호를 입력하세요(숫자)"/>
               </div>
            </div>

            <div>
               <label for="manuCom">제조사 <span class="icon">*</span>
               </label>
               <div>
                  <input type="text" id="manuCom" name="manuCom" placeholder="제조사를 입력하세요"/>
               </div>
            </div>
            
            <br>

            <div>
               <label for="type">타입/성상 <span class="icon">*</span>
               </label>
               <div>
                  <input type="text" id="type" name="type" placeholder="타입/성상을 입력하세요"/>
               </div>
            </div>

            <div>
               <label for="image">이미지 URL <span class="icon">*</span>
               </label>
               <div>
                  <input type="text" id="image" name="image" placeholder="이미지 URL을 입력하세요"/>
               </div>
            </div>

            <div>
               <label for="purchaseLink">구매 링크 <span class="icon">*</span>
               </label>
               <div>
                  <input type="text" id="purchaseLink" name="purchaseLink" placeholder="구매링크 URL을 입력하세요"/>
               </div>
            </div>
            
			<br>
            
            <div>
               <label for="allergyInfo">알러지 정보 </label>
               <div>
                  <input type="text" id="allergyInfo" name="allergyInfo" placeholder="알러지 반응을 입력하세요(선택)"/>
               </div>
            </div>

			<br>

            <div>
               <label for="targetCode">대상 </label>
               <div id="targetdiv">
                  <c:forEach items="${targetCode }" var="target">
                     <div>
                        <input type="checkbox" value="${target.targetId }"
                           name="targetCode" /> ${target.targetName }
                     </div>
                  </c:forEach>
               </div>
            </div>

			<br>

            <div>
               <label for="symptomCode">증상 </label>
               <div>
                  <c:forEach items="${symptomCode }" var="symptom">
                     <div>
                        <input type="checkbox" value="${symptom.symptomId }"
                           name="symptomCode" /> ${symptom.symptomName }
                     </div>
                  </c:forEach>
               </div>
            </div>
			<br>
            <div id="nutrient">
               <div class="nutrientItem" data-no="1">
                  <label for="nutirentInfo">영양소 </label>
                  <div>
                     <select name="nutirentInfo">
                        <c:forEach items="${nutrientKind }" var="nutrient">
                           <option value="${nutrient.nutId }">${nutrient.nutKind }</option>
                        </c:forEach>
                     </select> <input type="text" name="nutrientContent" placeholder="영양소 함량을 입력하세요"/>
                  </div>
               </div>
               <!-- <div id="test"></div> -->
            </div>
            <button type="button" onclick="nutInfoAppend()">+</button>
            <button type="button" onclick="nutInfoRemove()">-</button>
            <!--          <button type="button" onclick="$('#test').html(nutItemHtml());">TEST</button> -->

            <script type="text/javascript">
               var no = 1;

               function nutItemHtml() {
                  var html = "";
                  html += '<div class="nutrientItem" data-no="'+ ++no +'">';
                  html += '   <label for="nutirentInfo">영양소 </label>';
                  html += '   <div>';
                  html += '      <select name="nutirentInfo">';
                  <c:forEach items="${nutrientKind }" var="nutrient">
                  html += '         <option value="${nutrient.nutId }">${nutrient.nutKind }</option>';
                  </c:forEach>
                  html += '      </select>';
                  html += '      <input type="text" name="nutrientContent" placeholder="영양소 함량을 입력하세요"/>';
                  //    html += '      <button type="button" onclick="nutInfoAppend(' + no + ')"> + </button> <button type="button" onclick="nutInfoRemove('+ no++ +')"> - </button>';
                  html += '   </div>';
                  html += '</div>';

                  return html;
               }

               function nutInfoAppend() {
                  console.log("nutInfoAppend call", no)

                  if (no >= Number('${nutrientKind.size() }')) {
                     alert('더 이상 영양소 항목을 추가할 수 없습니다')
                     return false;
                  }

                  $("#nutrient").append($(nutItemHtml()))

                  console.log("nutInfoAppend after", no)
               }
               function nutInfoRemove() {
                  console.log("nutInfoRemove call", no)

                  if (no <= 1) {
                     alert('영양소 항목을 최소 1개가 필요합니다')
                     return false;
                  }
                  $(".nutrientItem[data-no='" + no-- + "']").remove();

                  console.log("nutInfoRemove after", no)
               }
            </script>

            <div>
               <label for="addProductBtn"></label>
               <div>
                  <button name="addProductBtn" class="btnAdd">등록하기</button>
               </div>
               <div>
               ㅤ
               </div>
            </div>
         </form>
      </div>
      <!-- .type_form member_join  -->

   </div>
   <!-- .#container -->
</div>
</div>
<c:import url="/WEB-INF/views/admin/layout/footer.jsp" />