package algo.stack;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReversePolish {

    private static final String P_INT = "^-?[1-9]\\d*$";

    private static int icp(String operator) {
        switch (operator) {
            case "(":
                return 5;
            case "*":
            case "/":
                return 4;
            case "+":
            case "-":
                return 2;
            case ")":
                return 1;
        }
        return -1;
    }

    private static int isp(String operator) {
        switch (operator) {
            case "(":
                return 1;
            case "*":
            case "/":
                return 4;
            case "+":
            case "-":
                return 2;
            case ")":
                return 5;
        }
        return -1;
    }

    public static String intExpr(String expr) {
        final StringJoiner sj = new StringJoiner(" ");
        final String[] tokens = Arrays.stream(expr.split(" "))
                .filter(s -> s.length() != 0)
                .toArray(size -> new String[size+1]);
        final Stack<String> operator = new Stack<>(); operator.push("(");
        tokens[tokens.length-1] = ")";
        for (int i = 0; i < tokens.length; ) {
            final String token = tokens[i];
            if (Pattern.matches(P_INT, token)) {
                sj.add(token); i++;
            } else {
                if (icp(token) > isp(operator.peek())) {
                    operator.push(token);
                    i++;
                }
                else {
                    if (")".equals(token)) {
                        // clear stack until '(' occur
                        for (;;) {
                            if (operator.isEmpty()) break;
                            final String tos = operator.pop();
                            if (tos.equals("(")) break;
                            sj.add(tos);
                        }
                        i++;
                    } else {
                        sj.add(operator.pop());
                    }
                }
            }
        }
        return sj.toString();
    }

    public static void main(String[] args) {
        System.out.println(intExpr("1 + 2 - 1 * ( ( 3 + 4 ) / 5 - 6 ) + 7"));
    }
}
