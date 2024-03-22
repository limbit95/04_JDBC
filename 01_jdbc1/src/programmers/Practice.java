package programmers;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Arrays;

public class Practice {

	public static void main(String[] args) {
		
		
		System.out.println(solution(new String[] {"a","bc","d","efg","hi"}));
		
	}
	
	
	public static int solution(String[] strArr) {
        int answer = 0;
        
        int idx = 0;
        while(strArr.length != idx) {
        	for(String s : strArr) {
        		if(strArr[idx].length() == s.length()) {
        			
        		}
        	}
        }
        
        return answer;
    }
}