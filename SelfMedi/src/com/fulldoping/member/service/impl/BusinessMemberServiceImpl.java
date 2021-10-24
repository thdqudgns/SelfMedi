package com.fulldoping.member.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fulldoping.common.JDBCTemplate;
import com.fulldoping.member.dao.face.BusinessMemberDao;
import com.fulldoping.member.dao.impl.BusinessMemberDaoImpl;
import com.fulldoping.member.dto.Member;
import com.fulldoping.member.dto.MemberFile;
import com.fulldoping.member.service.face.BusinessMemberService;

public class BusinessMemberServiceImpl implements BusinessMemberService {

	//MemberDao 객체
	private BusinessMemberDao businessMemberDao = new BusinessMemberDaoImpl();
	
	@Override
	public Member getLoginBusinessMember(HttpServletRequest req) {
		
		try {
			//인코딩 처리하기
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//Member 객체 생성
		Member member = new Member();
		
		System.out.println("req.getParameter(\"userId\")========= " + req.getParameter("userId")   );
		
		//전달파라미터에서 가져오기
		member.setUserId( req.getParameter("userId") );
		member.setUserPw( req.getParameter("userPw") );
		
		//결과 반환
		return member;
	}
	
	
	@Override
	public boolean loginBusiness(Member member) {
		
		if ( businessMemberDao.selectCntBusinessMemberByUserIdUserPw(JDBCTemplate.getConnection(), member) > 0 ) {
			return true; //로그인 성공
		} else {
			return false; //로그인 실패
		}
	}

	
	@Override
	public Member infoBusiness(Member member) {
		
		return businessMemberDao.selectBusinessMemberByUserNo(JDBCTemplate.getConnection(), member);
	}

	
	@Override
	public Member getJoinBusinessMember(HttpServletRequest req) {
		try {
			//인코딩 처리하기
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		//Member 객체 생성
		Member member = new Member();

		//전달파라미터에서 가져오기
		member.setUserId(req.getParameter("userId"));
		member.setUserPw(req.getParameter("userPw"));
		member.setUserName( req.getParameter("userName"));
		member.setUserNick(req.getParameter("userNick"));
		member.setUserEm( req.getParameter("userEm"));
		member.setUserPh( req.getParameter("userPh"));
		member.setUserGen( req.getParameter("userGen"));
		member.setUserBirth( req.getParameter("userBirth"));
		member.setBusinessNo(req.getParameter("businessNo"));
		
		//결과 반환
		return member;
	}

	
	@Override
	public void joinBusiness(Member member) {
		Connection conn = JDBCTemplate.getConnection();

		if( businessMemberDao.insertBusiness(conn, member) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}


	@Override
	public void businessMemberFileUpload(HttpServletRequest req, HttpServletResponse resp) {		
		System.out.println("BusinessMemberService - businesMemberFileUpload()");
		
		//응답객체 설정하기
		resp.setContentType("text/html; charset=UTF-8");
		
		//응답 출력 스트림 객체 얻기
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//--------------------------------------------------------

		//1. 파일업로드형식이 맞는지 검사
		//	-> 요청메시지의 content-type이 multipart/form-data인지 확인한다

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		//1-1. multipart/form-data가 아니면 파일업로드 처리 중단하기
		if( !isMultipart ) {
			
			out.append("<h1>enctype이 multipart/form-data가 아닙니다</h1>");
			out.append("<a href='/commons/fileupload'>업로드페이지로 이동하기</a>");
			
			//fileupload()메소드 중단하기
			return;
		}
		
		
		//1-2. multipart/form-data로 전송되었을 경우 정상 처리하기
		//	-> 여기 이후로 작성된 코드는 파일업로드를 처리하는 코드
		//--------------------------------------------------------

		//2. 업로드된 파일을 처리하는 아이템팩토리 객체 생성하기		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//--------------------------------------------------------
		
		//3. 업로드된 파일아이템의 용량이 설정값보다 작으면 메모리에서 처리한다
		int maxMem = 1 * 1024 * 1024; //1MB
		factory.setSizeThreshold(maxMem);

		
		//4. 파일아이템의 용량이 설정값보다 크면 임시파일(HDD처리)을 만들어서 처리한다
		//	->임시파일을 저장할 폴더를 설정할 수 있다
		
		//서블릿컨텍스트 객체
		ServletContext context = req.getServletContext();
		
		//서버가 배포된(설치된) 폴더의 실제 경로에서 tmp폴더를 나타내기
		String path = context.getRealPath("tmp");
		
		//tmp폴더 경로 확인
		System.out.println(path);
		
		//임시 저장 폴더 객체
		File tmpRepository = new File(path);
		
		//임시 저장 폴더 생성
		tmpRepository.mkdir();
		
		//임시파일을 저장하는 폴더를 factory객체에 설정하기
		factory.setRepository(tmpRepository);
		
		//--------------------------------------------------------
		
		//5. 파일업로드를 수행하는 객체 생성
		//	-> 업로드된 파일이 제한 용량을 넘으면 업로드되지않도록 설정 

		//파일업로드를 수행하는 객체 생성
		//	->DiskFileItemFactory객체에 저장해놓은 설정값을 사용한다
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//최대 업로드 허용 사이즈
		int maxFile = 10 * 1024 * 1024; //10MB
		
		//파일 업로드 용량제한 사이즈 설정
		upload.setFileSizeMax(maxFile);
		//--------------------------------------------------------
		
		//----- 파일 업로드 준비 완료 -----
		
		//--------------------------------------------------------
		
		//6. 업로드된 데이터 추출하기(파싱)
		//	-> 임시 파일 업로드도 같이 수행된다
		
		List<FileItem> items = null;
		try {
			//요청객체(req)에 담겨있는 전달파라미터(multipart)들을 파싱한다
			//	->임시파일업로드까지 수행됨
			items = upload.parseRequest(req);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		

		
		
		//파일아이템 반복자
		Iterator<FileItem> iter = items.iterator();
		
				
		//회원을 저장할 DTO 객체 생성
		Member member = new Member();
		
		//회원파일을 저장할 DTO 객체 생성
		MemberFile memberFile = new MemberFile();
		
		//DB연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//
		int userNo = businessMemberDao.selectNextUserNo(conn);
		
		while( iter.hasNext() ) {
			
			FileItem item = iter.next();
			
			//--- 1) 빈 파일에 대한 처리 ---
			if( item.getSize() <= 0 ) {
				continue; //빈 파일은 무시하고 다음 FileItem처리로 넘긴다
			}
			
			//--- 2) form-data에 대한 처리 ---
			if( item.isFormField() ) {
				
				//키 추출하기
				String key = item.getFieldName();
				
				//값 추출하기
				String value = null;
				try {
					value = item.getString("UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
				member.setUserNo(userNo);
				
				//회원 가입 처리 파라미터 - member
				if( "userId".equals(key) ) {
					member.setUserId(value);
				} else if( "userPw".equals(key) ) {
					member.setUserPw(value);
				} else if( "userName".equals(key) ) {
					member.setUserName( value);
				} else if( "userNick".equals(key) ) {
					member.setUserNick(value);
				} else if( "userEm".equals(key) ) {
					member.setUserEm(value);
				} else if( "userPh".equals(key) ) {
					member.setUserPh(value);
				} else if( "userGen".equals(key) ) {
					member.setUserGen(value);
				} else if( "userBirth".equals(key) ) {
					member.setUserBirth(value);
				} else if( "businessNo".equals(key) ) {
					member.setBusinessNo(value);
				}

				//회원 파일 정보 파라미터 처리
				if( "pharmacy".equals(key) ) {
					memberFile.setPharmacy( value );
				} 
				
				//--------------------------------------------------

			}  //if( item.isFormField() ) end
			
			
			
			
			//--- 3) 파일에 대한 처리 ---
			if( !item.isFormField() ) {
				
				//UUID 생성
				UUID uuid = UUID.randomUUID(); //랜덤 UUID
				String uid = uuid.toString().split("-")[0]; //8자리 uuid
				
				//로컬 저장소의 업로드 폴더
				File upFolder = new File(req.getServletContext().getRealPath("upload"));
				upFolder.mkdir(); //폴더 생성
				
				//업로드 파일 객체
				String origin = item.getName(); //원본파일명
				String stored = origin + "_" + uid; //원본파일명_uid
				File up = new File(upFolder, stored);
				
				
				
				try {
					item.write(up); //실제 업로드(임시파일을 최종결과파일로 생성함)
					item.delete(); //임시파일을 삭제
				} catch (Exception e) {
					e.printStackTrace();
				}
								
				memberFile.setUserNo(userNo);
				
				//업로드된 파일의 정보 저장
				memberFile.setOriginName(origin);
				memberFile.setStoredName(stored);
			
			} //if( !item.isFormField() ) end
		} //while( iter.hasNext() ) end

		
		System.out.println( "member : " + member);
		
		
		int res = 0;	
		
		//회원가입 파라미터 데이터 삽입
		res = businessMemberDao.insertBusiness(conn, member);
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//회원파일 데이터 삽입
		res = businessMemberDao.insertParamFile(conn, memberFile);
		if( res > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}		
	}

	
	@Override
	public MemberFile businessFile(HttpServletRequest req) {
	
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		//MemberFile 객체 생성
		MemberFile memberFile = new MemberFile();

		
		
		////전달파라미터에서 가져오기
		memberFile.setFileNo(Integer.parseInt(req.getParameter("fileNo")));
		memberFile.setUserNo(Integer.parseInt(req.getParameter("userNo")));
		memberFile.setPharmacy(req.getParameter("pharmacy"));
		memberFile.setOriginName(req.getParameter("originName"));
		memberFile.setStoredName(req.getParameter("storedName"));
			
		return memberFile;
	}


	
	@Override
	public int insertBusinessMember(Member member, MemberFile memberFile ) {
		
		//DB에 데이터 삽입하기
		Connection conn = JDBCTemplate.getConnection();
		
			
		//회원번호를 조회하여 값을 userNo에 저장한다
		int user_no = businessMemberDao.selectNextUserNo(conn);
		
		member.setUserNo(user_no);
		
		//사업자 회원 정보
		int memberSuccess = businessMemberDao.insertBusiness(JDBCTemplate.getConnection(), member);
		
		memberFile.setUserNo(user_no);
		
		//사업자 회원 파일 정보
		int memberFileSuccess = businessMemberDao.insertParamFile(JDBCTemplate.getConnection(), memberFile);
		
		
		//사업자 회원 정보 및 사업자 회원 파일 정보가 둘다 1이면 성공
		if( memberSuccess == 1 && memberFileSuccess == 1 ) {
			
			JDBCTemplate.commit(conn);
			return 1; 	//성공
		} else {
			JDBCTemplate.rollback(conn);
			return 0;	//실패
		}
	}


	@Override
	public List<MemberFile> list() {

		return businessMemberDao.selectAll(JDBCTemplate.getConnection());

	}
	
}





