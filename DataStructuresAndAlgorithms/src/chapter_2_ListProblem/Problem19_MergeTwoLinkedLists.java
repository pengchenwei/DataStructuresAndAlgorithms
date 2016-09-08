package chapter_2_ListProblem;

/**
 * @projectName: DataStructuresAndAlgorithms
 * @className: Problem19_MergeTwoLinkedLists.java
 * @Description: 合并两个有序链表，形成新的有序链表
 * @CreatTime: 2016年9月8日 下午2:57:42
 * @Author: pen
 * @Copyright: Copyright (c) 2016, pen All Rights Reserved.
 * @See
 */
public class Problem19_MergeTwoLinkedLists {
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * @MethodName: merge
	 * @Description: 合并
	 * @param head1链表1头节点
	 * @param head2链表2头节点
	 * @return 合成链表头节点
	 * @author pen
	 * @CreatTime: 2016年9月8日 下午3:46:14
	 */
	public static Node merge(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return head1 == null ? head2 : head1;
		}
		Node head = head1.value <= head2.value ? head1 : head2;
		Node cur1 = head1 == head ? head1 : head2;// 将cur1作为基准链表，在cur2中每次取出一个节点插入cur1中的适当位置
		Node cur2 = head1 == head ? head2 : head1;
		Node pre = null;// 永远指向最小cur1和cur2中的小的一个
		Node next = null;
		while (cur1 != null && cur2 != null) {
			if (cur1.value <= cur2.value) {// 根据上面条件，下面的代码块第一次一定会执行，pre被初始化
				pre = cur1;
				cur1 = cur1.next;
			} else {// cur2比cur1小，将cur2插入到cur1指针(pre)指向的位置
				next = cur2.next;// 保存下一节点
				pre.next = cur2;// 将cur2插入到cur1中
				cur2.next = cur1;// cur2<cur1，所以插到前面
				pre = cur2;// 更新指针
				cur2 = next;// 循环动力
			}
		}
		pre.next = cur1 == null ? cur2 : cur1;// cur1短一些的话就将cur2剩余部分直接接到pre后面
		return head;
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

		Node head1 = null;
		Node head2 = null;
		Node head = merge(head1, head2);
		printLinkedList(head);

		head1 = new Node(1);
		head2 = null;
		head = merge(head1, head2);
		printLinkedList(head);

		head1 = null;
		head2 = new Node(1);
		head = merge(head1, head2);
		printLinkedList(head);

		head1 = new Node(1);
		head2 = new Node(2);
		head = merge(head1, head2);
		printLinkedList(head);

		head1 = new Node(2);
		head2 = new Node(1);
		head = merge(head1, head2);
		printLinkedList(head);

		head1 = new Node(1);
		head1.next = new Node(4);
		head2 = new Node(2);
		head2.next = new Node(3);
		head2.next.next = new Node(5);
		head = merge(head1, head2);
		printLinkedList(head);

		head1 = new Node(1);
		head1.next = new Node(3);
		head1.next.next = new Node(5);
		head1.next.next.next = new Node(7);
		head1.next.next.next.next = new Node(9);
		head2 = new Node(0);
		head2.next = new Node(6);
		head2.next.next = new Node(6);
		head2.next.next.next = new Node(7);
		head = merge(head1, head2);
		printLinkedList(head);

	}

}
