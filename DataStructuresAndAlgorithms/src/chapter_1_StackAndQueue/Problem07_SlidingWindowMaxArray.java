package chapter_1_StackAndQueue;

import java.util.LinkedList;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem07_SlidingWindowMaxArray.java
 * @Description: TODO
 * @CreatTime: 2016年9月4日 下午2:06:54
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem07_SlidingWindowMaxArray {
	/**
	 * @MethodName: getMaxWindow
	 * @Description: 滑动宽度为w的窗口，每滑动一次取一次窗口中的最大值
	 * @param arr
	 *            取值数组
	 * @param w
	 *            滑动窗口宽度
	 * @return 最后得到的每个窗口最大值组成的数组
	 * @author pen
	 * @CreatTime: 2016年9月4日 下午2:29:04
	 */
	private static int[] getMaxWindow(int[] arr, int w) {
		/**
		 * 条件验算
		 */
		if (w < 1 || arr == null || w > arr.length)
			return null;
		/**
		 * 满足条件时需要进行接下来的操作
		 */
		LinkedList<Integer> qMax = new LinkedList<Integer>(); // 用来存放数组下标
		int[] res = new int[arr.length - w + 1]; // 用来存放每个滑动窗口最大值元素
		int index = 0;// 记录res的下标
		/*
		 * arr的每个元素都要被遍历一遍，时间复杂度至少为O(n)，队列qMax始终是按照从大到小的顺序排列
		 */
		for (int i = 0; i < arr.length; i++) {// 遍历arr中的每一个元素
			while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]) {// qMax不为空时，判断qMax队尾存放的下标对应的arr中元素值大小和当前arr[i]的大小
				qMax.pollLast();// 始终把滑动窗口框到的最大的arr元素放到qMax的最前列，比新框到的元素小的元素下标弹出
			}
			qMax.add(i);// 确保qMax始终按照从大到小的顺序排列
			if (i - w == qMax.peekFirst())// 窗口长度只能为w，所以必须把不在窗口框住范围的元素下标给踢出去
				qMax.pollFirst();// 同时qMax的队头元素仍然是窗口框住元素中的最大元素的下标
			if (i >= w - 1)// 从什么时候开始取窗口框住的最大元素，最后必然取得Max(i)-(w-1),即arr.length-w+1
				res[index++] = arr[qMax.peekFirst()];
		}
		return res;
	}

	/**
	 * @MethodName: printArray
	 * @Description: 打印数组元素
	 * @param arr:数组
	 * @author pen
	 * @CreatTime: 2016年9月4日 下午2:50:58
	 */
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
		int w = 3;
		printArray(getMaxWindow(arr, w));

	}

}
