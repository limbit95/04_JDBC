package programmers;

import java.util.Arrays;

public class Practice {

	public static void main(String[] args) {
		
		System.out.println(solution(new int[] {0, 1, 2, 3, 4, 5}, new int[] {4, 1, 2} ));
		
	}
	
	
    public static int[] solution(int[] arr, int[] query) {
    	int[] answer;    	
    	
		for(int i = 0; i < query.length; i++) {
			if(query[i] % 2 == 0) {
				answer = new int[arr.length - (arr.length - query[i])];
				for(int x = 0; x <= query[i]; i++) {
					
				}
			}
		}
		
        return arr;
    }
	
	
}