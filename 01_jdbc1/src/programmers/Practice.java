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
        
        for(int i = 0; i < strArr.length; i++) {
        	int cnt = 0;
        	for(int x = 0; x < strArr.length; x++) {
        		if(strArr[i].length() == strArr[x].length()) {
        			cnt++;
        		}
        	}
        	
        	if(cnt > answer) {
        		answer = cnt;
        	}
        }
        
        return answer;
    }
}