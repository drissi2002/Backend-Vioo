package tn.wevioo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DocumentServiceApplicationTests {

	Calculator underTest = new Calculator();

	@Test
	void itShouldAddTwoNumbers() {
		//given
		int n =20;
		int n1 = 30;

		//when
		int result = underTest.add(n,n1);

		//then
		int expected =50;
		assertThat(result).isEqualTo(expected);
	}

	class Calculator{
		int add(int a , int b){
			return  a + b;
		}
	}

}
