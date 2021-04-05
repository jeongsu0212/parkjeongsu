package kr.or.test;

public class CodingTest {

	public static void main(String[] args) {
		String w = ")(";
		String u = w.substring(1,2);
		System.out.println("w를 u와 분v로 분리할때u는"+u);
		CodingTest cd = new CodingTest();
		boolean balanceCheck = cd.isValiString(")(");
	}

}
