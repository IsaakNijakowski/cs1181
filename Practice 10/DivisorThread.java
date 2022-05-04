public class DivisorThread extends Thread {
    private int start;
    private int end;
    private int answer;
    public DivisorThread(int start, int end) {
        this.start = start;
        this.end = end;
    }
    private static int getNumDivisors(int n) {
		int numDivisors = 0;
		for (int i=1; i<=n; i++) {
			if (n % i == 0) {
				numDivisors++;
			}
		}
		return numDivisors;
	}
    public void run() {
        int maxDivisors = 0;
        for (int n=start; n<end; n++) {
			int divisors = getNumDivisors(n);
			if (divisors > maxDivisors) {
				maxDivisors = divisors;
				answer = n;
			}
		}
    }
    public int getAnswer() {
        return answer;
    }
    public int getDivisors() {
        return getNumDivisors(answer);
    }
}
