package org.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.edu.dao.IF_MemberDAO;
import org.edu.vo.MemberVO;
import org.springframework.stereotype.Service;

/**
 * MemberServiceImpl는 DAO 메서드 호출구현
 * @author 박정수
 *
 */
@Service
public class MemberServiceImpl implements IF_MemberService{

	@Inject
	IF_MemberDAO memberDAO;//IF_MemberDAO 주입받아서 memberDAO 오브젝트 생성.
	
	@Override
	public List<MemberVO> selectMember(String search_type,String search_keyword) throws Exception {
		// 회원정보 조회 DAO 메서드 호출(아래)
		return memberDAO.selectMember(search_type,search_keyword);
	}

}
