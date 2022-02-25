package com.qinhao.stream;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author qinhao
 * @date 2022/2/25 - 16:16
 */
public class UseStream {
    private static List<Person> personList = new ArrayList();

    static {
        personList.add(new Person("Tom", 8900, 11, "male", "New York"));
        personList.add(new Person("Jack", 7000, 11, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 18, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 18, "female", "New York"));
        personList.add(new Person("Owen", 9500, 19, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 20, "female", "New York"));
    }

    public static void main(String[] args) {
        //几种创建流的方式
        int[] array = {1, 3, 5, 6, 8};
        IntStream stream = Arrays.stream(array);

        Stream<Integer> stream0 = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(2);
        stream2.forEach(System.out::println);

        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);

        //并行流list.parallelStream()就是多线程的流，数据多的时候用
        stream.parallel().filter(x -> x > 6).findFirst();
    }

    @Test
    public void match() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        // 遍历输出符合条件的元素
        list.stream().filter(s -> s > 6).forEach(System.out::println);

        //取第一个 findAny()取任意一个
        Optional<Integer> first = list.stream().filter(s -> s > 6).findFirst();
        if (first.isPresent()) {
            System.out.println(first.get());
        }

        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x > 6);
        System.out.println(anyMatch);
    }

    @Test
    public void compare() {
//        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
//        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
//        System.out.println("最长的字符串：" + max.get());

        List<Integer> list = Arrays.asList(7, 6, 9, 4, 11, 6);

        //计算Integer集合中大于6的元素的个数。
        long count = list.stream().filter(x -> x > 6).count();
        // 自然排序
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        // 自定义排序
        Optional<Integer> max2 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("自然排序的最大值：" + max.get());
        System.out.println("自定义排序的最大值：" + max2.get());

        //获取工资最多的
        Person person = personList.stream().max(Comparator.comparing(Person::getSalary)).get();
        System.out.println(person.getSalary());
    }

    /**
     * 规约：把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
     */
    @Test
    public void reduce() {
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        // 求和方式1
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        // 求和方式3
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        // 求最大值方式1
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer max2 = list.stream().reduce(1, Integer::max);
        System.out.println("list求和：" + sum.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求和：" + max.get() + "," + max2);
    }
}
