package com.conjane.algo.stack;

/**
 * 括号匹配算法
 * {[()]}
 */
public class BracketsMatch {

    /**
     * 将包含{},[],()字符，替换为空白字符，直到出现的都被替换为空白字符
     * 最后为空白字符，则认为括号是匹配的
     * @param str
     * @return
     */
    public static boolean match1(String str) {
        while (str.contains("{}") || str.contains("[]") || str.contains("()")) {
            str = str.replace("{}", "");
            str = str.replace("[]", "");
            str = str.replace("()", "");
        }
        return str.isEmpty();
    }

    public static boolean match2(String str) {
        char[] charArray = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (Character ch : charArray) {
            if (ch == '{' || ch == '[' || ch == '('){
                stack.push(ch);
            }else {
                if (stack.isEmpty()) return false;
                Character leftBracket = stack.pop();
                if (leftBracket == '{' && ch != '}') return false;
                if (leftBracket == '[' && ch != ']') return false;
                if (leftBracket == '(' && ch != ')') return false;
            }
        }
        if (stack.isEmpty()){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(BracketsMatch.match2("[][]()"));
    }
}
