package kr.or.test;

public class chap01 {

	public static void main(String[] args) {
		int score = 76;
		if(score>=90) {
			if(score >= 95) {
				System.out.println("A+등급입니다.");
			}else {
				System.out.println("A-등급입니다.");
			}
			
		}else if(score>=80) {
			if(score >= 85) {
				System.out.println("B+등급입니다.");
			}else {
				System.out.println("B-등급입니다.");
			}
		}else if(score>=70) {
			if(score >= 75) {
				System.out.println("C+등급입니다.");
			}else {
				System.out.println("C-등급입니다.");
			}
		}else if(score>=60) {
			if(score >= 65) {
				System.out.println("D+등급입니다.");
			}else {
				System.out.println("D-등급입니다.");
			}
		}else if(score>=50) {
			if(score >= 55) {
				System.out.println("E+등급입니다.");
			}else {
				System.out.println("E-등급입니다.");
			}
		}else{
			if(score >= 0) {
				System.out.println("F등급입니다.");
			}
		}

	}
}
