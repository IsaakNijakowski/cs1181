import java.util.ArrayList;

public class ThreadingDivisors {

	public static void main(String[] args) throws InterruptedException {

		long start = System.currentTimeMillis();
        
        ArrayList<DivisorThread> threadList = new ArrayList<>();
        int calcSize = 100000;
        int threads = 4;

        int maxDivisors = 0;
		int answer = 0;

        for (int i = 0; i < threads; i++) {
            threadList.add(new DivisorThread((calcSize/threads)*i, (calcSize/threads)*(i+1)));
            threadList.get(i).start();
        }
        for (int i = 0; i < threads; i++) {
            threadList.get(i).join();
        }
		for (int i = 0; i < threads; i++) {
            if (threadList.get(i).getDivisors() > maxDivisors) {
                answer = threadList.get(i).getAnswer();
                maxDivisors = threadList.get(i).getDivisors();
            }
        }
		
		System.out.println(answer + " has the most divisors (" + 
				maxDivisors + ")");
		
		long end = System.currentTimeMillis();
		System.out.println(end - start + " milliseconds");
	}
}
