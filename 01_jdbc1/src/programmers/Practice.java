package programmers;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Arrays;

public class Practice {

	public static void main(String[] args) {
		
		
		System.out.println(solution("abstract algebra"));
		
	}
	
	
	public static String solution(String myString) {
        String answer = "";
        
        for(int i = 0; i < myString.length(); i++) {
        	if(myString.charAt(i) == 'a') {
        		answer += myString.toUpperCase().charAt(i);
        	} else {
        		answer += myString.toLowerCase().charAt(i);
        	}
        }
        
        return answer;
    }
}