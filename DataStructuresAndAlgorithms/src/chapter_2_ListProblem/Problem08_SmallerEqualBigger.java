package chapter_2_ListProblem;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem08_SmallerEqualBigger.java
 * @Description: TODO
 * @CreatTime: 2016年9月6日 下午4:07:48
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem08_SmallerEqualBigger {
	public static class Node {
		Node next;
		int value;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: listPartition1
	 * @Description: 按给定正整数将链表分为小中大三个部分（无序）
	 * @param head
	 * @param pivot
	 * @return
	 * @author pen
	 * @CreatTime: 2016年9月6日 下午6:32:10
	 */
	private static Node listPartition1(Node head, int pivot) {
		// 得到链表长度并创建Node[]数组，将节点依次放入数组
		if (head == null)
			return null;
		// 获得长度
		int i = 0;

		Node cur = head;
		while (cur != null) {
			cur = cur.next;
			i++;
		}
		// 放入数组
		Node[] nodeArr = new Node[i];
		cur = head;
		for (i = 0; i < nodeArr.length; i++) {
			nodeArr[i] = cur;
			cur = cur.next;
		}
		// 调整元素顺序
		arrPartition(nodeArr, pivot);
		// 把每一个节点关联起来
		for (i = 0; i < nodeArr.length - 1; i++)
			nodeArr[i].next = nodeArr[i + 1];
		nodeArr[i].next = null;// 末节点置空
		return nodeArr[0];
	}

	/**
	 * @MethodName: arrPartition
	 * @Description: 比较节点大小，按要求排序
	 * @param nodeArr数组
	 * @param pivot基准值
	 * @author pen
	 * @CreatTime: 2016年9月6日 下午5:36:10
	 */
	private static void arrPartition(Node[] nodeArr, int pivot) {
		int index = 0;
		int small = -1;
		int big = nodeArr.length;
		/*
		 * 本循环最多只遍历一次数组，每次判断大小是与额外的pivot比较
		 */
		while (index != big) {
			if (nodeArr[index].value < pivot)// 比pivot小的节点从头开始放到数组中
				swap(nodeArr, ++small, index++);// index循环动力
			else if (nodeArr[index].value == pivot)
				index++;
			else
				// 把大的数交换到前面index位置后又要重新比较该位置的节点与pivot的大小
				swap(nodeArr, index, --big);// “--big”有两个作用：1.减少循环的末端边界2.比pivot大的节点从末位开始放到数组中
		}
	}

	// 交换节点位置
	private static void swap(Node[] nodeArr, int a, int b) {
		Node temp = nodeArr[a];
		nodeArr[a] = nodeArr[b];
		nodeArr[b] = temp;
	}

	/**
	 * @MethodName: listPartition2
	 * @Description: 按给定正整数将链表分为小中大三个部分（有序）
	 * @param head头节点
	 * @param pivot给定基准值
	 * @return 新链表头节点
	 * @author pen
	 * @CreatTime: 2016年9月6日 下午6:31:51
	 */
	private static Node listPartition2(Node head, int pivot) {
		Node sH = null;
		Node sT = null;
		Node eH = null;
		Node eT = null;
		Node bH = null;
		Node bT = null;
		Node next = null;// 辅助变量
		while (head != null) {
			next = head.next;
			head.next = null;// 把当前节点的next都置为空，到下面再来定义他们之间的关系
			if (head.value < pivot) {
				if (sH == null) {// small部分第一个节点的处理
					sH = head;
					// sT = head;
				} else {
					sT.next = head;// small部分第一个以后节点的处理
				}
				sT = head;
			} else if (head.value == pivot) {
				if (eH == null) {
					eH = head;
					// eT = head;
				} else {
					eT.next = head;
				}
				eT = head;
			} else {
				if (bH == null) {
					bH = head;
					// bT = head;
				} else {
					bT.next = head;
				}
				bT = head;
			}
			head = next;// while循环动力
		}
		// 将三个部分连接起来
		if (sT != null) {
			sT.next = eH;
			eT = eT == null ? sT : eT;//如果equal部分为空直接将small部分的末节点sT连到big部分的头节点bH
		}
		if (eT != null) {//如果equal部分不为空，将equal的末节点
			eT.next = bH;
		}
		// 确定新链表的头节点并返回
		return sH != null ? sH : eH != null ? eH : bH;
	}

	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head1 = new Node(7);
		head1.next = new Node(9);
		head1.next.next = new Node(1);
		head1.next.next.next = new Node(8);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(2);
		head1.next.next.next.next.next.next = new Node(5);
		printLinkedList(head1);
		// head1 = listPartition1(head1, 10);
		head1 = listPartition2(head1, 5);
		printLinkedList(head1);

	}

}
