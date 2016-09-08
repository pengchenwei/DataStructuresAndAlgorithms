package chapter_2_ListProblem;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem16_ListSelectionSort.java
 * @Description: 选择排序法将链表排序
 * @CreatTime: 2016年9月8日 上午10:00:57
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem16_ListSelectionSort {
	public static class Node {
		Node next;
		int value;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: selectionSort
	 * @Description: 在剩下的节点中找到最小节点并删除，将找到的最小节点连接到已经排好序的链表末端
	 * @param head头节点
	 * @return 最后排序好的头节点
	 * @author pen
	 * @CreatTime: 2016年9月8日 下午1:58:45
	 */
	private static Node selectionSort(Node head) {
		Node small = head;
		Node cur = head;
		Node smallPre = null;
		Node tail = null;
		while (cur != null) {
			small = cur;
			smallPre = getSmallestPreNode(cur);
			if (smallPre != null) {
				small = smallPre.next;// 拿到本次循环得到得最小节点
				smallPre.next = small.next;// 删除最小节点
			}
			// 上面的smallPre==null，说明cur就是这些节点中的small
			cur = cur == small ? cur.next : cur;// 如果当前节点就是剩下这些节点中的最小节点，那么就进入下一个步骤
			if (tail == null) {
				head = small;// 设置排序后链表的头节点
			} else {
				tail.next = small;// 将找到的最小节点依次连接起来
			}
			tail = small;// 更新已经排好序的链表末节点
		}
		return head;
	}

	/**
	 * @MethodName: getSmallestPreNode
	 * @Description: 找到最小节点的前一个节点，要得到最小节点的同时还要保持剩下链表的结构不能变
	 * @param head剩下节点的头节点
	 * @return 最小节点的前一个节点
	 * @author pen
	 * @CreatTime: 2016年9月8日 下午2:00:49
	 */
	private static Node getSmallestPreNode(Node head) {
		Node small = head;// 第一次选择头节点为最小节点
		Node smallPre = null;
		Node pre = head;
		Node cur = head.next;
		while (cur != null) {
			if (cur.value < small.value) {
				smallPre = pre;// 上次循环的指针
				small = cur;// 更新最小值
			}
			pre = cur;// 更新指针
			cur = cur.next;// 循环动力
		}
		return smallPre;// 如果smallPre为null，那么传进来的头节点就是最小节点

	}

	public static void printLinkedList(Node head) {
		System.out.print("Linked List: ");
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = null;
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(1);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(2);
		head.next = new Node(1);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(1);
		head.next = new Node(3);
		head.next.next = new Node(2);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(2);
		head.next = new Node(1);
		head.next.next = new Node(3);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(2);
		head.next = new Node(3);
		head.next.next = new Node(1);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(3);
		head.next = new Node(1);
		head.next.next = new Node(2);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(3);
		head.next = new Node(2);
		head.next.next = new Node(1);
		head = selectionSort(head);
		printLinkedList(head);

		head = new Node(3);
		head.next = new Node(1);
		head.next.next = new Node(4);
		head.next.next.next = new Node(2);
		head = selectionSort(head);
		printLinkedList(head);

	}
}
