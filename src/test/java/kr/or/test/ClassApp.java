package kr.or.test;
//오리지날 자바프로그램 형식
public class ClassApp {

	public static void main(String[] args) {
		// 이 클래스의 집입메서드 main 실행
		Circle c = new Circle(5);//c라는 인스턴스(오브젝트 클래스)변수 c 생성 new키워드 생성circle(반지름값 5)
		//위에서 new 키워드는 프로그램 실행공간을 메모리에 새로 할당한다는 이야기 입니다.
		Circle c2 = new Circle(10);
		//Circle클래스를 c 이름으로 prefix해서 사용하는겁니다.
		//자바프로그램의 특징은 OOP프로그래밍의 대표적인 사례, Object=위에서 new키워드로 생성한 c가 오브젝트,
		//자바에서 OOP를 사용하는 방식을 설명하는 예. 스프링에서는 new키워드대신 @Inject를 주로 사용
		double print_result = c.getjeongsu();
		//System.out.println("첫번째 원의 넓이는" + print_result + " 입니다.");
		double print_result2 = c2.getjeongsu();
		//System.out.println("두번째 원의 넓이는" + print_result2 + " 입니다.");
		//아래 new 키워드 생성자를 이용해서 오브젝트를 2개 만들었음.
		Employee employee = new Employee();
		Salesman salesman = new Salesman();
		Development development = new Development();
		//employee.dojob();
		//salesman.dojob();
		//development.dojob();
		GraghicObject graghicObject = new Triangle();
		graghicObject.draw();//클래스형 변수명을 지정할때, 카멜표기를 적용.
	}

}

//오버로드(한개의 클래스에서 메서드이름은 고유값이라서 중복사용 불가. 단, 매개변수(인자값)가 다르면 가능함.)
//추상클래스에 대한 연습(아래)
//추상클래스 또는 인터페이스를 사용하는 목적은 뚜거운책에서 목차를 만드는 것과 똑같은 역할.
abstract class GraghicObject {
	int x, y;//그래픽오브젝트 클래스 멤버변수 선언
	abstract void draw();//명세표만 있고 구현내용이 없는(인터페이스) 추상메서드 선언
	//다른 해석하면, 책에서 목차만 있고, 내용이 없는 구조. 내용은 별도의 클래스에 있음.
}
 class Triangle extends GraghicObject {
	//메서드 오버라이드(메서드 이름이 같은것을 상속관계에서 실행)=부모 메서드 재정의, 대표적인 다형성을 구현.
	 @Override
	 void draw( ) {
		 System.out.println("  *");
		 System.out.println(" * *");
		 System.out.println("*****");
	 }
}

//클래스 상속에 대한연습(아래)
class Employee {//회사의 모든직원들 클래스
	int nSalary;//회사직원 봉급 멤버변수
	String szDept;//회사직원의 부서명 멤버변수
	public void dojob() {
		System.out.println("환영합니다. 직원분들!");
	}
}
class Salesman extends Employee {
	public Salesman( ) {//클래스명과 똑같은 매서드를 생성자 매서드입니다.
		szDept = "판매 부서";//부모클래스 Employee의 szDept멤버변수를 자식 클래스에서도 사용가능 
	}
	public void dojob() {
		System.out.println("환영합니다. " + szDept + " 입니다.");
	}
	
}
//오브젝트생성과 new키워드사용 연습(아래)
class Circle {
	int r;//멤버변수 반지름
	public Circle(int a) {//생성자 매서드 a인자=매개변수=파라미터 값은 new키워드로 오브젝트 만들어질때 발생
		r = a;
	}
	public double getjeongsu() {
		return r*r*3.14;//원의 넓이를 반환
	}
}
class Development extends Employee {
	public Development() {szDept="개발 부서";}
	public void dojob() {
		System.out.println("환영합니다. " + szDept + " 입니다.!");
	}
}