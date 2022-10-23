package algo.misc;

public class StringMatch {

    public static int bf(String src, String tpl) {
        int left = 0, i = 0, j = 0, sLen = src.length(), tLen = tpl.length();
        for (; i < sLen && j < tLen; ) {
            if (src.charAt(i) == tpl.charAt(j)) { i++; j++; }
            else {
                left++; i = left; j = 0;
            }
        }
        return j == tLen ? left : -1;
    }
}
