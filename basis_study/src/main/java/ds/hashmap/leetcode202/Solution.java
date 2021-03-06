package ds.hashmap.leetcode202;

import java.util.HashSet;

/**
 * 快乐数
 * LeetCode 202 https://mp.weixin.qq.com/s?__biz=MzUxNjY5NTYxNA==&mid=2247484202&idx=1&sn=f07d1166d61887007c2aa8c076a07365&scene=21#wechat_redirect
 *
 * @author qinhao 2021年01月11日23:55:53
 */
public class Solution {

    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (true) {
            int sum = getSum(n);
            if (sum == 1) {
                return true;
            }
            if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
            }
            n = sum;
        }
    }

    private int getSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isHappy(19));
    }
}
