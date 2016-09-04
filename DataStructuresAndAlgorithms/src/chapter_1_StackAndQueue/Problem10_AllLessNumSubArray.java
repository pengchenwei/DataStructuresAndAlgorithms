package chapter_1_StackAndQueue;

import java.util.LinkedList;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem10_AllLessNumSubArray.java
 * @Description: 最大值减去最小值小于等于num的子数组数量
 * @CreatTime: 2016年9月4日 下午3:56:21
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem10_AllLessNumSubArray {
	/**
	 * @MethodName: getNum
	 * @Description: 最大值减去最小值小于等于num的子数组数量
	 * @param arr
	 *            原始数组
	 * @param num
	 * @return res
	 * @author pen
	 * @CreatTime: 2016年9月4日 下午4:18:23
	 */
	private static int getNum(int[] arr, int num) {
		if (arr.length == 0 || arr == null || num < 0) {
			return 0;
		}
		/**
		 * 两个双端队列用来存储滑动窗口中最大元素下标和最小元素下标
		 * i：子数组的起点元素在arr数组中的下标值
		 * j：子数组的终点元素在arr数组中的下标值
		 */
		LinkedList<Integer> qMin = new LinkedList<>();
		LinkedList<Integer> qMax = new LinkedList<>();
		int i = 0;
		int j = 0;
		int res = 0;
		while (i < arr.length) {
			/**
			 * 子数组起点：滑动窗口的左边界是以i为下标的元素
			 * 第二次循环后，j是第一次循环之后继续累加的，因为只用判断以arr[j]结尾的子数组是否满足要求，即可断定以arr[j-1]
			 * 为结尾的子数组 是否满足要求
			 */
			while (j < arr.length) {
				/**
				 * 子数组终点：活动窗口的右边界是以j为下标的元素
				 * 取得窗口中最大(小)元素的下标，存入qMax(qMin)中
				 * qMax(qMin)中队列头的“下标”对应的arr元素是该窗口的最大(小)值
				 */
				while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[j])
					qMin.pollLast();
				qMin.add(j);
				while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[j])
					qMax.pollLast();
				qMax.add(j);
				if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num)
					break;// 不满足题目条件即开始新的循环
				j++;
			}
			/**
			 * 由于已经不满足max-min<num的条件了，需要更新队列头的“下标”，开始新的循环
			 */
			if (qMin.peekFirst() == i)
				qMin.pollFirst();
			if (qMax.peekFirst() == i)
				qMax.pollFirst();
			res += j - i;
			i++;
		}
		return res;
	}

	// for test
	public static int[] getRandomArray(int len) {
		if (len < 0) {
			return null;
		}
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * 10);
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[] arr = getRandomArray(30);
		int num = 5;
		printArray(arr);
		System.out.println(getNum(arr, num));

	}

}
