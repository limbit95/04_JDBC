package programmers;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Arrays;

public class Practice {

	public static void main(String[] args) {
		
		
		System.out.println(solution("18446744073709551615", "287346502836570928366"));
		
	}
	
	
	public static String solution(String a, String b) {
		StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1, j = b.length() - 1;
        
        

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;

            if (i >= 0) sum += a.charAt(i--) - '0';
            System.out.println(sum);
            if (j >= 0) sum += b.charAt(j--) - '0';
//            System.out.println(sum);
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }
}