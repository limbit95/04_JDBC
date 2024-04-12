package programmers;

import java.util.Arrays;

public class Practice {

	public static void main(String[] args) {
		
		
		System.out.println(solution("baxbc"));
		
	}
	
	
	public static String[] solution(String myString) {
        String[] answer = {};
        
        answer = myString.split("x");
        
        int[] intArr = new int[answer.length];

        for(int i = 0; i < answer.length; i++) {
        	int sum = 0;
        	
        	for(int x = 0; x < answer[i].length(); x++) {
        		if(x == 0) {
        			sum = answer[i].charAt(x);
        		} else if(answer[i].charAt(x-1) < answer[i].charAt(x)){
        			continue;
        		} else if(answer[i].charAt(x-1) >= answer[i].charAt(x)) {
        			sum += answer[i].charAt(x);
        		} else {
        			sum += answer[i].charAt(x);
        		}
        	}
        	intArr[i] = sum;
        }
        
        for(int i = 0; i < intArr.length; i++) {
        	for(int x = i + 1; x < intArr.length; x++) {
        		if(intArr[x] < intArr[i]) {
        			int temp = intArr[i];
        			intArr[i] = intArr[x];
        			intArr[x] = temp;
        			
        			String tempStr = answer[i];
        			answer[i] = answer[x];
        			answer[x] = tempStr;
        		}
        	}
        }
        
        System.out.println(Arrays.toString(intArr));
        System.out.println(Arrays.toString(answer));
        
        return answer;
    }
	
}