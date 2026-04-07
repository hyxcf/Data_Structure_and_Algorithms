package com.leetcode;

import java.util.*;

public class Leetcode_207_课程表_12_24 {

    /*
        你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
        在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
    
        例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
        请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1.构建邻接表和入度数组
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        // 初始化图
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        // 建图
        for (int[] prerequisite : prerequisites) {
            int a = prerequisite[0];
            int b = prerequisite[1];
            graph.get(b).add(a);
            inDegree[a]++;
        }
        // 2.将所有入度为0的课程加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        // 3.拓扑排序
        int learned = 0;
        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            learned++;
            // 遍历该课程的所有后续课程
            for (Integer next : graph.get(course)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        // 4.判断是否学完所有课程
        return learned == numCourses;
    }

    private static class Solution {

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 1. 构建邻接表和入度数组
            List<List<Integer>> graph = new ArrayList<>();
            int[] inDegree = new int[numCourses];

            // 初始化图
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }

            // 建图：[a, b] 表示 b -> a（b 是 a 的先修）
            for (int[] pre : prerequisites) {
                int a = pre[0]; // 要学的课
                int b = pre[1]; // 先修课
                graph.get(b).add(a);      // b -> a
                inDegree[a]++;            // a 的入度 +1
            }

            // 2. 将所有入度为 0 的课程加入队列
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }

            // 3. 拓扑排序
            int learned = 0; // 已学习的课程数
            while (!queue.isEmpty()) {
                int course = queue.poll();
                learned++;

                // 遍历该课程的所有后续课程
                for (int next : graph.get(course)) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }

            // 4. 判断是否学完所有课程
            return learned == numCourses;
        }
    }

    static class Preview_2_2 {
        /*
            你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
            在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
            例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
            请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
            示例 1：
                输入：numCourses = 2, prerequisites = [[1,0]]
                输出：true
                解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
            示例 2：
                输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
                输出：false
                解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
            fixme:图论这块比较薄弱
         */
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }
            int[] degree = new int[numCourses];
            for (int[] pre : prerequisites) {
                int a = pre[0];// 要学习的课
                int b = pre[1];// 先修课
                graph.get(b).add(a);
                degree[a]++;
            }
            // 2.将所有入度为0的课程加入队列
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (degree[i] == 0) {
                    queue.offer(i);
                }
            }
            // 3.拓扑排序
            int learned = 0;
            while (!queue.isEmpty()) {
                int course = queue.poll();
                learned++;
                // 遍历该课程的所有后续课程
                for (Integer next : graph.get(course)) {
                    degree[next]--;
                    if (degree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
            return learned == numCourses;
        }
    }

    private static class Preview_2_27 {
        /*
            你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
            在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
            例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
            请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
            示例 1：
                输入：numCourses = 2, prerequisites = [[1,0]]
                输出：true
                解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
            示例 2：
                输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
                输出：false
                解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
            fixme:图论这块比较薄弱
        */
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }
            int[] degree = new int[numCourses];
            for (int[] course : prerequisites) {
                int a = course[0];
                int b = course[1];
                graph.get(b).add(a);
                degree[a]++;
            }
            // 将入度为0的加入数组
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (degree[i] == 0) {
                    queue.offer(i);
                }
            }
            // 拓扑排序
            int learned = 0;
            while (!queue.isEmpty()) {
                learned++;
                Integer polled = queue.poll();
                for (Integer next : graph.get(polled)) {
                    degree[next]--;
                    if (degree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
            return learned == numCourses;
        }
    }

    private static class Preview_3_10 {
        /*
            你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
            在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
            例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
            请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
            示例 1：
                输入：numCourses = 2, prerequisites = [[1,0]]
                输出：true
                解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
            示例 2：
                输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
                输出：false
                解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
            fixme:图论这块比较薄弱
        */
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }
            // 初始化数据
            int[] degree = new int[numCourses];
            for (int[] course : prerequisites) {
                int a = course[0];
                int b = course[1];
                graph.get(b).add(a);
                degree[a]++;
            }
            // 将入度为0的加入数组
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (degree[i] == 0) {
                    queue.offer(i);
                }
            }
            int learned = 0;
            while (!queue.isEmpty()) {
                learned++;
                Integer polled = queue.poll();
                for (Integer next : graph.get(polled)) {
                    degree[next]--;
                    if (degree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
            return learned == numCourses;
        }
    }

    private static class Preview_3_20 {
        /*
            你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
            在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
            例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
            请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
            示例 1：
                输入：numCourses = 2, prerequisites = [[1,0]]
                输出：true
                解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
            示例 2：
                输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
                输出：false
                解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
        */
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }
            // 初始化数据
            int[] degree = new int[numCourses];
            for (int[] course : prerequisites) {
                int a = course[0];
                int b = course[1];
                graph.get(b).add(a);
                degree[a]++;
            }
            // 将入度为0的加入数组
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (degree[i] == 0) {
                    queue.offer(i);
                }
            }
            int learned = 0;
            while (!queue.isEmpty()) {
                learned++;
                Integer polled = queue.poll();
                for (Integer next : graph.get(polled)) {
                    degree[next]--;
                    if (degree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
            return learned == numCourses;
        }
    }

    private static class Preview_3_30 {
        /*
            你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
            在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
            例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
            请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
            示例 1：
                输入：numCourses = 2, prerequisites = [[1,0]]
                输出：true
                解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
            示例 2：
                输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
                输出：false
                解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
        */
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<List<Integer>> graph = new ArrayList<>();
            int[] degree = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }
            for (int[] course : prerequisites) {
                int a = course[0];
                int b = course[1];
                graph.get(b).add(a);
                degree[a]++;
            }
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (degree[i] == 0) {
                    queue.offer(i);
                }
            }
            int learned = 0;
            while (!queue.isEmpty()) {
                learned++;
                Integer polled = queue.poll();
                for (Integer next : graph.get(polled)) {
                    degree[next]--;
                    if (degree[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
            return learned == numCourses;
        }
    }
}
