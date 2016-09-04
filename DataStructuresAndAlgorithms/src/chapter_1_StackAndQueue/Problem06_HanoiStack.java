package chapter_1_StackAndQueue;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem06_HanoiStack.java
 * @Description: 汉诺塔问题，要求只能相邻跳跃
 * @CreatTime: 2016年9月3日 下午11:20:03
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 */
public class Problem06_HanoiStack {

	/**
	 * @MethodName: HanoiProblem
	 * @Description: TODO
	 * @param num
	 *            圆盘数
	 * @param left
	 *            左塔
	 * @param mid
	 *            中塔
	 * @param right
	 *            右塔
	 * @return 需要移动的总步数
	 * @author pen
	 * @CreatTime: 2016年9月3日 下午11:22:59
	 */
	public static int HanoiProblem(int num, String left, String mid, String right) {
		if (num < 0) { // 圆盘数不能小于零
			return 0;
		}
		return process(num, left, mid, right, left, right);

	}

	/**
	 * @MethodName: process
	 * @Description: 具体的移动过程
	 * @param num
	 *            圆盘总数
	 * @param left
	 *            左塔
	 * @param mid
	 *            中塔
	 * @param right
	 *            右塔
	 * @param from
	 *            来源塔
	 * @param to
	 *            目标塔
	 * @return 移动总步数
	 * @author pen
	 * @CreatTime: 2016年9月3日 下午11:40:54
	 */
	private static int process(int num, String left, String mid, String right, String from, String to) {
		if (num == 1) {// 最顶端最后一个圆盘的移动只能有两种走法，递归的终止条件
			if (from.equals(mid) || to.equals(mid)) { // 来源或者目标是中塔，只能从中塔到目标塔
				System.out.println("move 1 from " + from + " to " + to);
				return 1;
			} else { // 来源或者目标不是中塔，只能从来源塔到中塔，再从中塔到目标塔，分两步！！
				System.out.println("move 1 from " + from + " to " + mid);
				System.out.println("move 1 from " + mid + "to " + right);
				return 2;
			}
		}
		// 除最顶端的圆盘上面已经解决，剩下的圆盘(num-1)下面解决
		if (from.equals(mid) || to.equals(mid)) { // 根据中塔来分情况
			// 起点是中塔，根据目标塔来确定中间桥梁塔another
			String another = (from.equals(left) || to.equals(left)) ? right : left;
			/**
			 * 从中塔到桥梁塔，因为num个圆盘中最大的圆盘要移到目标塔之前（partA的目的）需要将目标塔空出来，所以先要把num-
			 * 1个小圆盘移到桥梁塔，再（partB的目的） 把大圆盘从中塔移到目标塔，最后再（partC的目的）把桥梁塔的圆盘移回来到目标盘上
			 */
			int partA = process(num - 1, left, mid, right, from, another);
			int partB = 1;
			System.out.println("move " + num + " from " + from + " to " + to);
			int partC = process(num - 1, left, mid, right, another, to);
			return partA + partB + partC;
		} else {
			// 现在这num-1个圆盘不在中塔上的情况，及所有圆盘在左塔或者右塔上，
			/*
			 * 先（partA目的）把num-1个圆盘移到目标塔：因为最大的圆盘必然要先移到中塔才能移到目标塔，所以第一步就是把中塔空出来，
			 * 接下来就是（partB）把num中最大的圆盘移到中塔，然后（partC目的）需要把num-1个圆盘移回到来源塔：因为要目标塔空出来，
			 * 这样（partD）刚刚移到中塔的最大的圆盘才能顺利移到目标塔，最后需要（partE）把剩下的num-1个圆盘移到目标盘
			 */
			int partA = process(num - 1, left, mid, right, from, to);
			int partB = 1;
			System.out.println("move " + num + " from " + from + " to " + mid);
			int partC = process(num - 1, left, mid, right, to, from);
			int partD = 1;
			System.out.println("move " + num + " from " + mid + " to " + to);
			int partE = process(num - 1, left, mid, right, from, to);
			return partA + partB + partC + partD + partE;
		}
	}

	public static void main(String[] args) {
		int num = 4;

		// solution 1
		int steps1 = HanoiProblem(num, "left", "mid", "right");
		System.out.println("It will move " + steps1 + " steps.");
		System.out.println("===================================");
	}

}
