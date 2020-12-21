package org.edu.vo;
/**
 * PageVO클래스로서 회원, 게시판 공통으로 사용.
 * 1페이지당 보여줄 개수를 이용해서 전체데이터를 분할해서 보여주는 역할
 * 왜필요한가? 회원또는 게시물이 10만건을 한꺼번에 보여주면, 조회속도가 느리게됩니다.
 * 1페이지당 10개 또는 20개, 30개 등 제한을 두어서, 속도향상및 사용자화면을 개선시킴.
 * 아래 계산식에서 []부분이 변수로 생성해야 할 부분.
 * 1페이지계산 10[1페이지당출력할개수]x(1[몇번째페이지번호]-1 =0 1페이지일떄
 * 2페이지계산 10x(2-1) = 10[계산결과나온 시작페이지번호] 2페이지일때
 * SELECT * FROM tbl_board order by bno desc limit 10, 5;# 10-시작인덱스,10-출력할 개수
 * @author 박정수
 *
 */
public class PageVO {
	//예를 들면 변수중에 boolean(일반형데이터형 변수)-null값이들어가면 에러 / boolean(대문자로시작 클래스형 변수-Null로 입력되었을때 처리하는 로직이 들어있음.)
	private int perPageNum;//페이징리스트 목록의 개수변수(리스트 하단에 보이는 번호의 개수)
	private int queryPerPageNum;//쿼리에서 사용하는 1페이지당 출력할 개수를 나타내는 변수
	private Integer page;//jsp에서 선택한 페이지 번호 변수
	private int queryStartNo;//쿼리에서 사용되는 시작인덱스값 변수
	private boolean prev;//[계산식]페이징에서 이전 번호가 있을때 표시값이 들어가는 변수
	private boolean next;//[계산식]페이징에서 이후 번호가 있을때 표시값이 들어가는 변수
	//위에 생성 프리뷰, 넥스트 변수값이 있는지 없는지 확인하려면, [계산식]이 필요합니다. 계산할때 필요한 변수 3개가 필요(아래)
	private int totalCount;//회원[게시물] 전체의 갯수값이 들어가는 변수
	private int startPage;//jsp화면에서 보여주는 페이징 리스트의 시작번호
	private int endPage;//jsp화면에서 보여주는 페이징 리스트의 끝번호
	//start페이지와 end페이지 변수가 필요한 이유?

	//검색에 필요한 변수 2개도 포함시켜서, 컨트롤러에서 매개변수 사용을 축소하게 됨.
	private String search_type;//검색조건
	private String search_keyword;//검색어
	
	//전체 클래스에서 [계산식]이 4개가 필요. 개발자가 만들어야 합니다.
	//계산식4개로 반환되는 값 startPage(11), endPage(20), prev(true), next(false)
	//아래 메서드에서 사용되는 totalCount변수는 컨트롤러에서 쿼리문을 통해서 전송받습니다.,
	private void calcPage() {
		//page변수는 현재 jsp에서 클릭한 페이지번호를 받아서 아래 계산식에서 사용
		//(int)형변환 : 2.1, 2.8 다 출력할때는 2로 반환이 됨.
		//ceil메서드: 천장값을 반환 1.1 => 2, 2.3 =>3  
		//floor메서드: 바닥값을 반환 1.1 =>1, 2.3 =>2
		//ceil(1/10) => 1.0, 0.9, 0.8,... 0.0 ,-0.1, -0.2 => 1
		//ceil(1/10)*10 => 10페이지
		int tempEnd = (int)(
				Math.ceil(page/(double)this.perPageNum)*this.perPageNum
				);
		//jsp에서 클릭한 페이지 번호 예로 1을 기준으로 끝 페이지를 계산한다.(위)
		//예) < 1 2 3 4 5 6 7 8 9 10 > 페이징 리스트의 시작과 끝 값이 바뀌게 됨. 
		//예) ㄴ < 11 12 13 14 15 16 17 18 19 20 > 시작11과 끝 20
		this.startPage = (tempEnd - this.perPageNum) + 1; 
		//jsp에서 11을 클릭했을때 (20-10) + 1 = 11 스타트 페이지 값(위)
		//(아래) 20x10 =200의 레코드(회원[게시물])
		//만약 회원[게시물] 195개 일 경우가 있습니다.
		if(tempEnd*this.queryPerPageNum > this.totalCount) {//200>195 일 경우
			//임시끝페이지x쿼리에서 1페이지당 출력할 개수 > 실제전체개수
			//클릭한 page번호로 계산된 게시물수가 실제 게시물수(totalCount) 보다 클때
			this.endPage = (int)Math.ceil(
					this.totalCount/(double)this.queryPerPageNum
					);// 195/10 = 20 19.9 19.8 ...19.5 결과적으로 20이 출력
		} else {
			//전체 회원[게시물]수가 195일때 page 1을 클릭한 경우 100 > 195
			//결과가 195/10 = 20 잘못됨, 다음처럼 나와서 위 조건을 타면 안되고 else 문을 진입하게됨. 
			this.endPage = tempEnd;//tempEnd가 10이니깐 endPage가 10
		}
		//=============여기까지가 endPage를 구하는 계산식=====================
		//아래는 prev, next 구하는 계산식
		this.prev = (this.startPage != 1);//예) 스타트페이지 11 결과값은 true
		//시작페이지가 1보다 크면 무조건 이전 페이지가 있다고 봄.(위)
		this.next = (this.endPage*this.queryPerPageNum < this.totalCount);
		//20x10 < 195 결과는 false 이기 때문에 jsp에서 > 안보이게 처리함.
		//예)  < 11 12 13 14 15 16 17 18 19 20(tempPage) [>] 시작11과 끝 20
	}
	
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		//perPageNum = 10;//강제로 1페이지당 보여줄 개수 10개로 지정
		this.perPageNum = perPageNum;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getQueryStartNo() {
		//DB쿼리에서 사용 결과값은 시작 인덱스번호(0)를 구하는 계산식(아래)
		//계산식 = (jsp에서 클릭한 페이지번호 -1)*페이지당 보여지는 개수
		//1페이지계산 [1페이지당출력할개수]x10(1[몇번째페이지번호]-1 =0 1페이지일떄
		//2페이지계산 10x(2-1) = 10[계산결과나온 시작페이지번호] 2페이지일때
		queryStartNo = queryPerPageNum*(this.page-1);//개발자가 추가한 계산식//queryPerPageNum=5
		return queryStartNo;
	}
	public void setQueryStartNo(int queryStartNo) {
		this.queryStartNo = queryStartNo;
	}
	public boolean getPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean getNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//totalCount변수값이 만들어지는 순간 calcPage메서드가 실행되면 4개의 변수값 반환.
		calcPage();//totalCount변수값이 필수로 필요한 메서드.

	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	public String getSearch_keyword() {
		return search_keyword;
	}
	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}

	public int getQueryPerPageNum() {
		return queryPerPageNum;
	}

	public void setQueryPerPageNum(int queryPerPageNum) {
		this.queryPerPageNum = queryPerPageNum;
	}
	
}
