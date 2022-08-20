package algo.stack;

import ds.stack.IStack;
import ds.stack.impl.LinkedStack;

import java.util.Objects;
import java.util.function.Function;

/**
 * 使用栈实现括号匹配算法
 *  1. 输入为包含目标括号对的字符串
 *  2. 检测的括号对包括：[], {}, ()三种
 */
public class BracketMatch implements Function<String, Boolean> {

    @Override
    public Boolean apply(String s) {
        // 使用链式栈实现
        final IStack<Character> chStack = new LinkedStack<>();
        if (s == null || s.length() == 0) return Boolean.TRUE;
        for (final char ch : s.toCharArray()) {
            switch (ch) {
                case '(': case '[': case '{':
                    chStack.push(ch);
                    break;
                case ')':
                    if (chStack.isEmpty()) return Boolean.FALSE;
                    if (!Objects.equals(chStack.top(), '(')) return Boolean.FALSE;
                    chStack.pop();
                    break;
                case ']':
                    if (chStack.isEmpty()) return Boolean.FALSE;
                    if (!Objects.equals(chStack.top(), '[')) return Boolean.FALSE;
                    chStack.pop();
                    break;
                case '}':
                    if (chStack.isEmpty()) return Boolean.FALSE;
                    if (!Objects.equals(chStack.top(), '{')) return Boolean.FALSE;
                    chStack.pop();
                    break;
            }

        }
        return chStack.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
    }
}
