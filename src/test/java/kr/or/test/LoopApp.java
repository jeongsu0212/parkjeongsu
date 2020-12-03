package kr.or.test;

public class LoopApp {

	public static void main(String[] args) {
		// 반복문중에 for문
		//메서드에서 사용하는 변수 아래2줄
		int[] numbers = {1,2,3,4,5,6,7,8,9,10};//배열변수 선언
		int cnt;//반복횟수 카운트(세는)하는 역할
		for(cnt=0;cnt<10;cnt++) {//cnt++ => cnt=cnt+1; 1씩 증가한다는 의미
			System.out.println("현재 카운터는" + numbers[cnt]);
			//println함수는 한번 출력후 newline 엔터 치는 역할.
			//10번을 반복을 하는데, numbers[0], numbers[1],..... numbers[9] 출력
		}

	}

}
