package chapter_2_ListProblem;

import java.util.HashMap;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem09_CopyListWithRandom.java
 * @Description: TODO
 * @CreatTime: 2016年9月6日 下午7:17:30
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem09_CopyListWithRandom {
	public static class Node {
		Node next;
		Node rand;
		int value;

		public Node(int data) {
			this.value = data;
		}

	}

	/**
	 * @MethodName: copyListWithRand1
	 * @Description: 原链表的全拷贝(值和关联关系)
	 * @param head头节点
	 * @return 拷贝链表的头节点
	 * @author pen
	 * @CreatTime: 2016年9月6日 下午10:47:01
	 */
	private static Node copyListWithRand1(Node head) {
		// 将原链表的节点值复制到map中，下一步再确定关系
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Node cur = head;
		while (cur != null) {
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		// 调整每个复制节点的关系
		cur = head;
		while (cur != null) {
			map.get(cur).next = map.get(cur.next);
			map.get(cur).rand = map.get(cur.rand);
			cur = cur.next;
		}
		return map.get(head);
	}

	/**
	 * @MethodName: copyListWithRand2
	 * @Description: 原链表的全拷贝(值和关联关系)
	 * @param head原链表的头节点
	 * @return 拷贝链表的头节点
	 * @author pen
	 * @CreatTime: 2016年9月6日 下午10:48:48
	 */
	private static Node copyListWithRand2(Node head) {
		// 拷贝节点放在拷贝源的下一个位置，并用next连接起来，形如【1>1`>2>2`>3>3`>4...】
		Node cur = head;
		Node next = null;// 辅助变量
		while (cur != null) {
			next = cur.next;// 保存下一节点信息，循环动力
			cur.next = new Node(cur.value);// 拷贝一份当前节点的值，不包括关联关系(next和rand)
			cur.next.next = next;// 把新建的节点插在下一节点之前
			cur = next;// while循环动力
		}
		// 设置rand关系
		cur = head;
		Node copyCur = null;
		while (cur != null) {
			next = cur.next.next; // 每次移动两个步幅
			copyCur = cur.next; // 复制链表的头节点
			/*
			 * copyCur节点的rand信息在其前一个节点(cur)中找，找到rand指向的节点后还要向后移一位才能对应到该位置的拷贝节点
			 */
			copyCur.rand = cur.rand != null ? cur.rand.next : null;// 设置拷贝节点的rand指向
			cur = next;
		}
		// 将拷贝链表分离出来
		Node res = head.next;
		cur = head;
		while (cur != null) {
			next = cur.next.next;// 设定每次移动的步长
			copyCur = cur.next;// 拷贝的链表的头节点
			cur.next = next;// 设置原链表的next指向
			copyCur.next = next != null ? next.next : null;// 设置拷贝节点的next指向
			cur = next;// 循环动力
		}
		return res;
	}

	public static void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = null;
		Node res1 = null;
		Node res2 = null;
		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		// res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.rand = head.next.next.next.next.next; // 1 -> 6
		head.next.rand = head.next.next.next.next.next; // 2 -> 6
		head.next.next.rand = head.next.next.next.next; // 3 -> 5
		head.next.next.next.rand = head.next.next; // 4 -> 3
		head.next.next.next.next.rand = null; // 5 -> null
		head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

	}
}
