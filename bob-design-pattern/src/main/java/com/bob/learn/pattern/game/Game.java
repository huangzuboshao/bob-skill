package com.bob.learn.pattern.game;

/**
 * <a href="https://mp.weixin.qq.com/s?__biz=MzI1MTIzMzI2MA==&mid=2650583493&idx=2&sn=77bfa87421533f22bf97109c9717e9c6&chksm=f1fe0746c6898e5084eeb1703da55b61a921acd55bfa8d748efe8dbcc5e13752c966dcffb694&mpshare=1&scene=1&srcid=1010BJsssd6iQnOlPe6bzq80&sharer_sharetime=1665370472698&sharer_shareid=142d7750dd483cb2ff2a0f3edc12ebb5&version=4.0.18.6008&platform=win&from_wecom=1#rd">链接</a>
 * 在[0, m * n)中选取k个随机数;很难保证随机数不重复。如果出现重复的随机数，你就得再随机选一次，直到找到k个不同的随机数
 * <p>
 * k比较小,m * n比较大 还好满足
 * </p>
 * <p>
 * 1、将二维数组坐标(x, y)转化成一维数组索引的技巧，那么你是否有办法把三维坐标(x, y, z)转化成一维数组的索引呢？
 * 2、如何对带有权重的样本进行加权随机抽取？比如给你一个数组w，每个元素w[i]代表权重，请你写一个算法，按照权重随机抽取索引。比如w = [1,99]，算法抽到索引 0 的概率是 1%，抽到索引 1 的概率是 99%。
 * 3、实现一个生成器类，构造函数传入一个很长的数组，请你实现randomGet方法，每次调用随机返回数组中的一个元素，多次调用不能重复返回相同索引的元素。要求不能对该数组进行任何形式的修改，且操作的时间复杂度是 O(1)
 * </p>
 *
 * @author bob
 * @date 2022/10/10
 */
public class Game {
    /**
     * m * n 棋盘
     */
    int m, n;
    /**
     * 棋盘
     */
    boolean[] board;

    /**
     * 降维编码
     *
     * @param x
     * @param y
     * @return
     */
    int encode(int x, int y) {
        return x * n + y;
    }

    /**
     * 升维解码
     * 将一维数组中的索引转化为二维数组中的坐标 (x, y)
     *
     * @param index
     * @return
     */
    int[] decode(int index) {
        return new int[]{index / n, index % n};
    }
}
