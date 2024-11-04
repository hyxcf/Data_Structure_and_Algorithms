package com.hyx2.basic_data_structure.tree.tips;

import java.util.List;

/**
 * @version 0.1
 * @Author hyx
 * @className Code04_MaxHappy
 * @Date 2024/11/4  21:15
 * @description
 * @since jdk 11
 */

/*
    公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、没有环的多叉树。树的头节点是公司唯一的老板。
    除老板之外的每个员工都有唯一的直接上级。叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
    这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，
    规则:
        1. 如果某个员工来了，那么这个员工的所有直接下级都不能来
        2. 派对的整体快乐值是所有到场员工快乐值的累加
        3. 你的目标是让派对的整体快乐值尽量大
    给定一棵多叉树的头节点boss，请返回派对的最大快乐值。

    情况：
    假设 x 节点下有 a，b，c 三个直接下属。
        1. 如果 x 来，一定获得 x.happy，那么 a、b、c 一定不来，返回 a、b、c 不来时整棵树的最大快乐值。
        2. 如果 x 不来，那么 a、b、c 可能来也可能不来，
            返回 a、b、c 来时整棵树的最大快乐值和 不来时整棵树的最大快乐值的中的最大值的和。
 */

public class Code04_MaxHappy {


    public static Info process2(Employee x) {
        if (x.nexts.isEmpty()) {
            return new Info(x.happy, 0);
        }
        int yes = x.happy;
        int no = 0;
        for (Employee next : x.nexts) {
            Info nextInfo = process2(next);
            yes += nextInfo.no;
            no += Math.max(nextInfo.yes, nextInfo.no);
        }
        return new Info(yes, no);
    }

    public static class Info {
        private int yes; // 头节点在来时的最大快乐值
        private int no; // 头节点在不来时的最大快乐值

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    static class Employee {
        public int happy; //这名员工可以带来的快乐值
        List<Employee> nexts; //这名员工有哪些直接下级
    }
}








































