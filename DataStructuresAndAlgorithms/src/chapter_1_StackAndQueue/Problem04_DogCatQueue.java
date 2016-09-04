package chapter_1_StackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列，实现队列基本操作之外分别判断猫狗队列的情况
 * @author pen
 *
 */
public class Problem04_DogCatQueue {
	//实体类，具体类，父类
	protected static class Pet {
		private String petType;

		public String getPetType() {
			return petType;
		}

		public Pet(String petType) {
			this.petType = petType;
		}

	}
	//实体类，具体类，子类
	public static class Dog extends Pet {
		public Dog() {
			super("dog");
		}

	}
	//实体类，具体类，子类
	public static class Cat extends Pet {
		public Cat() {
			super("cat");
		}

	}
	//实体类，具体类，作为对象存到队列中的
	public static class PetEntery {
		//类变量
		private long count;
		private Pet pet;
		//构造方法，初始化对象
		public PetEntery(long count, Pet pet) {
			this.count = count;
			this.pet = pet;
		}
		//成员方法
		public long getCount() {
			return count;
		}

		public Pet getPet() {
			return pet;
		}

		/**
		 * 猫狗队列
		 * 
		 * @author pen 1.add 2.pollAll 3.pollCat 4.pollDog 5.isEmpty
		 *         6.isDogEmpty 7.isCatEmpty
		 */
		public static class DogCatQueue {
			//成员变量
			private Queue<PetEntery> dogQueue;
			private Queue<PetEntery> catQueue;
			private long count;
			//构造方法，初始化对象
			public DogCatQueue() {
				dogQueue = new LinkedList<>();
				catQueue = new LinkedList<>();
				count = 0;
			}
			//成员方法
			/*
			 * 将传进来的对象分两个队列进行存储
			 */
			public void add(Pet pet) {
				if (pet.getPetType().equals("dog")) {
					dogQueue.add(new PetEntery(this.count++, pet));
				} else if (pet.getPetType().equals("cat")) {
					catQueue.add(new PetEntery(count++, pet));
				} else {
					throw new RuntimeException("error,no cat or dog!");
				}
			}
			/*
			 * 根据存入时count的大小来判别存进来时的顺序，再按该顺序将对象取出
			 */
			public Pet pollAll() {
				if (!catQueue.isEmpty() && !dogQueue.isEmpty()) {
					if (catQueue.peek().count > dogQueue.peek().count)
						return dogQueue.poll().getPet();
					else
						return catQueue.poll().getPet();
				} else if (!dogQueue.isEmpty())
					return dogQueue.poll().getPet();
				else if (!catQueue.isEmpty())
					return catQueue.poll().getPet();
				else
					throw new RuntimeException("err, queue is empty!");
			}
			
			public Cat pollCat() {
				if (!catQueue.isEmpty())
					return (Cat) catQueue.poll().getPet();
				else
					throw new RuntimeException("CatQueue is Empty!");

			}

			public Dog pollDog() {
				if (!dogQueue.isEmpty())
					return (Dog) dogQueue.poll().getPet();
				else
					throw new RuntimeException("DogQueue is Empty!");

			}

			public boolean isEmpty() {
				return dogQueue.isEmpty() && catQueue.isEmpty();
			}

			public boolean isDogEmpty() {
				return dogQueue.isEmpty();
			}

			public boolean isCatEmpty() {
				return catQueue.isEmpty();
			}
		}

		public static void main(String[] args) {
			DogCatQueue test =new DogCatQueue();
			
			Pet dog1 = new Dog();
			Pet cat1 = new Cat();
			Pet dog2 = new Dog();
			Pet cat2 = new Cat();
			Pet dog3 = new Dog();
			Pet cat3 = new Cat();
			
			test.add(dog1);
			System.out.println(test.isCatEmpty());
			System.out.println(test.isDogEmpty());
			test.pollCat();//为空，抛异常
			test.add(cat1);
			test.add(dog2);
			test.add(cat2);
			test.add(dog3);
			test.add(cat3);

			test.add(dog1);
			test.add(cat1);
			test.add(dog2);
			test.add(cat2);
			test.add(dog3);
			test.add(cat3);

			test.add(dog1);
			test.add(cat1);
			test.add(dog2);
			test.add(cat2);
			test.add(dog3);
			test.add(cat3);
			while (!test.isDogEmpty()) {
				System.out.println(test.pollDog().getPetType());
			}
			while (!test.isEmpty()) {
				System.out.println(test.pollAll().getPetType());
			}
		}

	}
}
