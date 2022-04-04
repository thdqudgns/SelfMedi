# :pushpin: 1. SelfMedi
Spring을 사용하지 않고 Servlet/JSP 기반으로 개발했습니다.

### 본인 담당 코드
- [자가진단](https://github.com/thdqudgns/SelfMedi/tree/master/SelfMedi/src/com/fulldoping/selftest)
- [자가진단 view](https://github.com/thdqudgns/SelfMedi/tree/master/SelfMedi/WebContent/WEB-INF/views/selftest)
- [에러페이지](https://github.com/thdqudgns/SelfMedi/tree/master/SelfMedi/WebContent/WEB-INF/views/error)
- [필터링](https://github.com/thdqudgns/SelfMedi/tree/master/SelfMedi/src/com/fulldoping/filter)
- [UI-header](https://github.com/thdqudgns/SelfMedi/blob/master/SelfMedi/WebContent/WEB-INF/views/layout/header.jsp)
- [UI-MainPage](https://github.com/thdqudgns/SelfMedi/blob/master/SelfMedi/WebContent/WEB-INF/views/main.jsp)
- [UI-footer](https://github.com/thdqudgns/SelfMedi/blob/master/SelfMedi/WebContent/WEB-INF/views/layout/footer.jsp)

### :bulb: 개요 
보다 쉽고 간편하게 **건강기능식품**을 추천받아 제품을 구매하고 복용하여 건강관리에 도움을 주는 서비스를 제공합니다. 어떤 영양을 챙겨야할지 고민인 사용자, 부모님 연령대에 필요한 영양제가 궁금한 사용자, 온라인에 무분별한 광고로 고민인 사용자, 현재 복용 중인 영양제에 추가영양제를 먹어도 될지 고민인 사용자들을 위한 서비스를 제공합니다.

### :bulb: 구현 기능 
+ 회원가입 : 1. 일반회원 2. 사업자회원 (파일 업로드)
+ 로그인(jQuery Tab) : 1. 일반로그인 2. 사업자로그인 3. 아이디 찾기, 패스워드찾기
+ 일반 사용자 자유 게시판, 게시글 조회, 작성, 수정, 삭제, 신고, 
	- Ajax기능 이용 : 댓글작성, 댓글 수정(본인), 댓글 삭제(본인) 
+ QnA게시판 : 게시글 조회,작성, 수정, 삭제, 신고, 
	- Ajax기능 이용 : 댓글 작성(의료계 종사자만 댓글 작성 가능), 댓글 수정(본인), 댓글 삭제(본인) 
+ 공지사항 게시판
	- Ajax기능 이용 : 댓글작성, 댓글 수정(본인), 댓글 삭제(본인)
+ 자가진단 : 자가진단표 작성, 결과표 보기, 상품 추천 및 링크 연결
+ 마이페이지: 사용자 정보 확인, 자가진단 결과표 보기, 본인이 작성한 게시물 조회, 사용자 정보 수정(닉네임, 비밀번호, 이메일, 휴대전화번호)
+ 상품
	- 상품진열 : 통합검색기능, 영양제 필터 대, 중, 소분류, 별점순(jstar 라이브러리 사용) 진열기능(GET 방식 데이터 전달), 상품사진, 이름, 별점 노출
	- 상품상세 : 상품의 id로 상품의 상세정보 DB조회(영양소, 함량) (GET 방식 데이터 전달), 로그인시 비교함 추가 및 이동, 영양소 성분 표시 및 권장량 시각화, 아이콘 클릭 시 성분 정보 display toggle(영양소 상세정보)
	- 상품 비교함 : 체크박스 등록된 영양제 중 선택 가능 (2개 선택시 나머지 비활성화), 등록된 영양제 이미지 영양제 품명, 제조회사, 별점 표기
	- 상품 비교 : 영양제 이미지, 품명, 제조국, 평점, (GET 방식 데이터 전달), 포함 영양소 일일 섭취량, 권장량 표기, 섭취대상 데이터 기반 미달, 충족, 초과상태 그래프 표기, 포함량 표기 (meter태그)
- 상품 합산 : 상품 정보표기, 등록 영양제 영양소 함량 합산 그래프, 함량 숫자 표기
+ 관리자 : 회원관리, 상품관리, 신고게시판, 자유게시판, QnA 게시판, 공지사항 게시판, 게시글 조회, 작성, 수정, 삭제, 신고, 댓글 작성, 수정, 삭제
 	- 상품 목록 : 등록된 상품들의 목록을 조회, 상품명을 클릭하면 상품 상세 페이지로 이동, 상품 삭제 기능
	- 상품 등록 : 상품 추가기능, 상품의 상세 정보들을 입력(POST방식으로 데이터 전달)
	- 상품 수정: 상품을 수정하는 페이지, 수정 전, 기존의 정보가 조회, 새롭게 수정할 상품의 상세 정보들을 입력, 각 input box에 정보를 입력(POST방식으로 데이터 전달)

### :bulb: 설계의 주안점
- 일반회원, 비회원, 사업자회원, 관리자를 구분하여 각각의 정보와 서비스를 제공한다
- 상품을 진열하고 원하는 상품을 선택하여 비교, 합산으로 권장량대비 영양정보를 시각적으로 얻을 수 있다.
- 의료계 종사자만 작성할 수 있는 QnA 댓글 시스템 및 모든 사용자가 자유롭게 영양제 정보를 공유할 수 있다.
- 자가진단표를 작성하여 자신에게 필요한 영양제 추천을 받을 수 있다.

### :bulb: 사용기술 및 개발환경
- 운영체제 : Window10 64bit
- Open Source & Library : BootStrap, ojdbc6, Jstl,standard, commons-io, commons, fileupload, cos
- IDE : Eclipse, Oracle SQL Dveleoper 11g
- Server: Apache Tomcat 9.0
- Development Language : Java/Spring, HTML5 , CSS3 , JavaScript , jQuery, SQL , JSP ,servlet, Javdoc, OJDBC
- Team Coop : Github, ERDCloud, KaKaoOven, GoogleDriver, Discord, Noction

### :bulb: 팀 구성
- 팀원 1 (팀장)
	- 이름 : 
	- 역할 : 프로젝트 일정 및 전체관리, 회원가입 및 파일 첨부 기능, 로그인, 회원가입 UI
- 팀원 2
	- 이름 : 
	- 역할 : 자유게시판 , 공지사항, 게시글 신고기능, 신고게시판 , 관리자 게시판
- 팀원 3
	- 이름 : 
	- 역할 : 비교함페이지, 비교함 삭제, 합산기능, 비교기능, 관리자 상품정보 수정페이지
- 팀원 4
	- 이름 : 
	- 역할 : 상품진열 페이지, 상품상세 페이지, 관리자 상품목록페이지, 관리자 상품등록페이지, 관리자 상품삭제기능
- 팀원 5
	- 이름 : 송병훈
	- 역할 : **자가진단, 에러 페이지 처리, Filter 처리, 전반적인 UI(CSS), 오류 확인 및 해결**
- 팀원 6 
	- 이름 : 
	- 역할 : 로그인, 아이디찾기, 비밀번호찾기, 관리자 회원관리목록(수정,삭제), 회원상세보기(목록,삭제)
- 팀원 7
	- 이름 : 
	- 역할 : 댓글, QnA게시판, 마이페이지, 마이페이지 수정

### :bulb: 기여도
- 20% (구성원 7명)

### :bulb: 느낀점
첫 프로젝트라 걱정이 많았지만, 팀원들과 분업을 하며 머리를 맞대어 하나씩 해결해 나가면 된다는 것을 깨달았습니다. 팀원들과의 협력과 커뮤니케이션 및 신뢰가 중요함을 알았습니다. 그동안 배운 Servlet, jQuery, jstl, JSP기반의 MVC2 모델 흐름을 확실하게 알게 되었고, 배우지 않았던 여러 기능과 구현 방법도 검색을 통해 배웠습니다. 한편으로는 시간적 여유가 부족해 원했던 기능들을 다 넣지 못해 아쉬웠고, 모르는 것이 많음을 알게 되었습니다. 

### :bulb: 작동원리
- Servlet기반의 Controller에서는 Service파일로 이동하여 코드를 수행하거나 web page view로 이동하여 페이지를 보여주는 작동을 하고
- Service파일에서는 Dao파일로 이동하여 Dao파일 안에서 DB와 연결된 SQL문을 수행하고 eclilpse와 연동되어 java코드를 수행합니다.
- DTO클래스파일에는 필드와 getter/setter를 만들어서 서로 다른 파일에서 필드들 통해 data의 이동이 간편해집니다.
- SQL문을 수행한 이후의 정보를 객체에 담아서 view page로 전송해줍니다. 
- view page에서는 HTML, CSS, JSP, jstl, el, jQuery 등을 이용하여 Controller에서 전달받은 data를 화면에서 보여줍니다.
- Server와 Client간의 소통이 이루어집니다.
