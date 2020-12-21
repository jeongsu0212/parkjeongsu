package org.edu.text;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.edu.dao.IF_MemberDAO;
import org.edu.vo.MemberVO;
import org.edu.vo.PageVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
/**
 * JUnit 자바단위테스트, DateSource 지정후 DB(Hsql,Mysql,Oracle)접속,
 * 데이터베이스 테이블에 Create, Read, Update, Delete 테스트용 클래스
 * 제이유닛4클래스를 사용.
 * 단위테스트는 톰캣이 실행되지 않아도 작동이 되야합니다.
 * 그래서, 테스트 클래스 상단에 servelet-context.xml 이러한 설정파일을 불러들여서 실행이 가능.
 * @author 박정수
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class DataSourcesTest {

	@Inject
	DataSource dataSource;//자바에서처럼 new 오브젝트를 생성하지않고 스프링에서는 @Inject로 오브젝트 생성.
	
	@Inject
	IF_MemberDAO memberDAO;
	
	@Inject//사용하면 안되는 이유: 클래스 상단에 @Controller, @Service, @Repository, Component이런 내용만 사용가능
	MemberVO memberVO;//기존 자바처럼 new MemberVO() 오브젝트를 생성하지않고 주입해서 사용
	
	//여기서 실행하면 안되 Test아니야~~~
	public String memberPrimaryKey() {
		//사용자 프라이머리키 생성하는 메서드 년월일시분초밀리초
		Date primaryKey = new Date();
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		System.out.println("프라이머리키: " + newFormat.format(primaryKey));
		return "user_" + newFormat.format(primaryKey);
	}
	
	@Test
	public void updateMember() throws Exception {
		//CRUD 중 Update 테스트 구현 특징, user_id는 프라이머리키 이기때문에 수정대상이 아님.
		//MemberVO memberVO = new MemberVO();
		memberVO.setUser_id("admin");
		memberVO.setUser_name("홍길동");
		memberVO.setUser_pw("");//암호를 수정하지않는 사람을 가정...
		memberVO.setEmail("test@test.com");
		memberVO.setPoint(100);
		memberVO.setEnabled(true);
		memberVO.setLevels("ROLE_ADMIN");
		String user_id = memberVO.getUser_id();//memberVO의 오브젝트의 데이터는 1개의 레코드이기 때문에 반환값이 1개만 반환.
		memberDAO.updateMember(memberVO);
	}
	
	@Test
	public void readMember() throws Exception {
		//CRUD 중 Read 테스트 구현
		//MemberVO memberVO = new MemberVO();
		memberVO = memberDAO.readMember("admin");
		System.out.println("admin 에 대한 상세정보 입니다.");
		System.out.println(memberVO.toString());
	}
	
	@Test
	public void deleteMember() throws Exception {
		//CRUD 중 Delete 테스트(쿼리 > DAO > memberDAO주입받은 오브젝트사용)
		memberDAO.deleteMember("user_20201215145630195");//삭제메서드 > 쿼리호출
		
	}
	
	@Test
	public void insertMember() throws Exception {
		//CRUD중 Create 테스트
		//MemberVO memberVO = new MemberVO();
		//사용자 생성 규칙: user_ 시작(prefix), suffix(접미사)는 년월일시분초
		//사용자 생성 결과 예: user_20201215142130
		String memberIdKey = memberPrimaryKey();
		memberVO.setUser_id(memberIdKey);
		memberVO.setUser_name("사용자03");
		//패스워드 암호화 처리(필수이지만, 스프링 시큐리티할때 처리 예정)
		memberVO.setUser_pw("1234");
		memberVO.setEmail("user03@abc.com");
		memberVO.setPoint(100);
		memberVO.setEnabled(true);
		memberVO.setLevels("ROLE_ADMIN");
		Date reg_date = new Date();
		memberVO.setReg_date(reg_date);//Mapper쿼리에서 처리로 대체
		memberDAO.insertMember(memberVO);
		
	}
	@Test
	public void selectMember() throws Exception{
		//"user_name","홍길동"
		PageVO pageVO = new PageVO();
		pageVO.setSearch_type("user_name");
		pageVO.setSearch_keyword("홍길동");
		//아래3개줄은 초기 페이징처리에 필요한 필수값 저장
		pageVO.setPage(1);
		pageVO.setPerPageNum(5);//리스트 하단에 보여지는 페이지번호의 개수
		pageVO.setQueryPerPageNum(10);//쿼리에서 1페이지당 보여줄 회원수 10명으로 입력해 놓음.
		
		List<MemberVO> memberList =memberDAO.selectMember(pageVO);
		System.out.println("회원리스트 테스트 입니다.");
		System.out.println(memberList.toString());
	}
	
	@Test
	public void dbConnectionTest()  throws Exception{
		try {//내부에서 {} 에러발생시 실행을 중지하고, catch{}구문이 실행 됩니다. 예외처리라고 함.
			Connection connection = dataSource.getConnection();
			System.out.println("데이터베이스 접속이 성공하였습니다.");
		} catch (SQLException e) {
			System.out.println("데이터베이스 접속에 실패하였습니다. 왜냐하면" + e);
			e.printStackTrace();
		}//DB커넥션 오브젝트 만드는 과정. new사용안함.
		//try~catch는 테스트에서만 사용하고 스프링에서는 throws Exception으로 예외를 스프링에 
		
	}
	@Test
	public void test() {
		//fail("Not yet implemented");
		System.out.println("JUnit테스트 시작 입니다.");
	}

}