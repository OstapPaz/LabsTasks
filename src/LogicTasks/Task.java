package LogicTasks;

import java.util.Scanner;

public class Task {
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		String string = sc.nextLine();
		sc.close();
		
		System.out.println(marichka(N, string));

	}
	
	public static char[] marichka(int N, String string) {
		
		int K = 0;
		for(int i = 0; i <= 10; i++) {
			if (i * i == N) {
				K = i;
				break;
			} 
		}
		
		char[][] li = new char[K][K];
		
		int b = 0;
		for(int i = 0; i < K; i++) {
			for(int j = 0; j < K; j++) {
				li[i][j] = string.charAt(b);
				b += 1;
			}
		}
		char[] msg = new char[N];
		
		int k = 1;
		msg[0] = li[0][0];
		msg[N - 1] = li[K - 1][K - 1];
		for (int i = 1; i < K + K; i++)
		 {
		  if (k == N - 1) break;
		  for (int j = i - K + 1 > 0 ? i - K + 1 : 0; j < Math.min(K, i + 1); j++)
		  {
		   
		   msg[k] = li[j][i - j];
		   k++;
		   if (k == N - 1) break;
		  }
		 }
		
		/*char[] a = string.toCharArray();
		Arrays.sort(a);*/
		return msg;
		
	}

}
