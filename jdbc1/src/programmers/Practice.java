package programmers;

import java.util.Arrays;

public class Practice {

	public static void main(String[] args) {
		
		System.out.println(solution(new int[] {0, 1, 2, 3, 4, 5}, new int[] {4, 1, 2} ));
		
	}
	
	
    public static int[] solution(int[] arr, int[] query) {
    	
		for(int i = 0; i < query.length; i++) {
			if(query[i] == 0) {
				continue;
			} else if(query[i] % 2 == 0) {
				int[] arr1 = Arrays.copyOfRange(arr, 0, query[i] + 1);
				arr = arr1;
			} else if(query[i] % 2 != 0) {
				int[] arr1 = Arrays.copyOfRange(arr, query[i], arr.length);
				arr = arr1;
			} 
		}
		
        return arr;
    }
	
	
}