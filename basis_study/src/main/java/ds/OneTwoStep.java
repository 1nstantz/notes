package ds;

import java.util.HashMap;
import java.util.Map;

/**
 * n个台阶的走法问题，每次可以走1个台阶或者2个台阶
 * <p>
 * 核心：
 * <p>
 * 1. 递推公式： f(n) = f(n-1)+f(n-2)  因为总共的走法等于迈出1个台阶之后的走法+迈出2个台阶之后的走法
 * 2. 终止条件： f(1) = 1  f(2) = 2  走1个台阶有一种走法  走2个台阶有两种走法
 *
 * @author qinhao 2018年12月03日23:44:50
 */
public class OneTwoStep {

    private static final int THRESHOLD_VALUE = 100_000_000;
    private int deep;
    private Map<Integer, Integer> maps = new HashMap<>();

    /**
     * @param n 台阶数
     * @return 几种走法
     */
    public int oneTwoStepRecursion(int n) {
        deep++;
        if (deep == THRESHOLD_VALUE) {
            System.out.println("————————数太大了兄弟——————————");
            throw new StackOverflowError();
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (maps.containsKey(n)) {
            return maps.get(n);
        }
        int result = oneTwoStepRecursion(n - 1) + oneTwoStepRecursion(n - 2);
        maps.put(n, result);
        return result;
    }

    /**
     * @param n 台阶数
     * @return 几种走法
     */
    public int oneTwoStepNonRecursion(int n) {
        int pre = 2;
        int prepre = 1;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = prepre + pre;
            prepre = pre;
            pre = result;
        }
        return result;
    }

    public static void main(String[] args) {
        OneTwoStep oneTwoStep = new OneTwoStep();
        int n = 38;
        System.out.println("递归实现↓");
        System.out.println(n + "个台阶有" + oneTwoStep.oneTwoStepRecursion(n) + "种走法");
        System.out.println("递归调用栈的深度为：" + oneTwoStep.deep);
        System.out.println();
        System.out.println("————————————朴素的分割线————————————");
        System.out.println();
        System.out.println("非递归实现↓");
        System.out.println(n + "个台阶有" + oneTwoStep.oneTwoStepNonRecursion(n) + "种走法");
    }
}
