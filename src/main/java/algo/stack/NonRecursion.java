package algo.stack;

public class NonRecursion {

    static int p(int n, int x) {
        if (n == 0) return 0;
        if (n == 1) return 2*x;
        final int[] stack = new int[n+1];
        stack[0] = 0; stack[1] = 2 * x;
        for (int i = 2; i <= n; i++) {
            stack[i] = 2 * x * stack[i-1] + 2 * (n - 1) * stack[i-2];
        }
        return stack[n];
    }

    public static void main(String[] args) {
        //System.out.println(p(0, 1));
        //System.out.println(p(1, 1));
        System.out.println(p(3, 1));
    }
}
