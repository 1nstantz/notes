package com.qinhao.feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qinhao
 * @date 2022/2/11 - 15:42
 */
public class NewFeature {
    public static void main(String[] args) {
        String s = "hello java9";
        //java10加入var
        var s1 = "hello java10";
        //当编译器无法推断出正确的变量类型时，也不允许使用var
        //Map <String，List <Integer >>类型，可以将其简化为单个var关键字，从而避免大量样板代码
        var list = new ArrayList<List<Integer>>();
        for (var integers : list) {
            System.out.println(integers);
        }

        //11字符串加强
        // 判断字符串是否为空白
        " ".isBlank();                // true
        // 去除首尾空格
        " Javastack ".strip();          // "Javastack"
        // 去除尾部空格
        " Javastack ".stripTrailing();  // " Javastack"
        // 去除首部空格
        " Javastack ".stripLeading();   // "Javastack "
        // 复制字符串
        "Java".repeat(3);             // "JavaJavaJava"
        // 行数统计
        "A\nB\nC".lines().count();    // 3

    }
}
