package org.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.edu.vo.MemberVO;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements IF_MemberDAO{
	@Inject
	private SqlSession sqlSession;

	public List<MemberVO> selectMember() throws Exception {
		// mapper.xml에 접근하는 방법(아래)
		//오브젝트의 메서드를 실행할때,
		//.입력힌트가 나오지 않으면, ctrl+space키를 눌러서 메서드키 나오게하기.
		return sqlSession.selectList("memberMapper.selectMember") ;
		//sqlSession템플릿의 selectList메서드를 실행하면, memberMapper.selectMember
	}

	@Override
	public void insertMember(MemberVO memberVO) throws Exception {
		// mapper.xml에 접근하는 방법(아래)
		sqlSession.insert("memberMapper.insertMember", memberVO);
	}

	@Override
	public void deleteMember(String user_id) throws Exception {
		// mapper.xml에 접근하는 방법(아래)
		sqlSession.delete("memberMapper.deleteMember", user_id);
		
	}

	@Override
	public MemberVO readMember(String user_id) throws Exception {
		// mapper.xml에 접근하는 방법(아래) .selectOne() sql세션템플릿안 메서드.
		return sqlSession.selectOne("memberMapper.readMember", user_id);
	}

}
