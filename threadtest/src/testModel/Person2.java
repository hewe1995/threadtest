package testModel;

import java.util.HashMap;
import java.util.Map;

public class Person2 {
	private String id;
	private String name;

	private int sumQuantity;
	private float sumPrice;

	private Map<Integer, Head> map = new HashMap<Integer, Head>();

	public Person2() {
	}

	public Person2(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "person : " + this.getId() + "," + this.getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSumQuantity() {
		return sumQuantity;
	}

	public void setSumQuantity(int sumQuantity) {
		this.sumQuantity = sumQuantity;
	}

	public float getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(float sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Head head(int key, int q, float p) {
		if (this.map.containsKey(key)) {
			return map.get(key);
		} else {
			Head head = new Head(q, p);
			map.put(key, head);
			return head;
		}
	}

	public class Head {
		private int quantity;
		private float price;

		private Head(int q, float p) {
			this.quantity = q;
			this.price = p;
			sumPrice += p;
			sumQuantity += q;
		}

		public int getQuantity() {
			return quantity;
		}

		public float getPrice() {
			return price;
		}

		public void increment(int num) {
			this.quantity += num;
			sumQuantity += num;
		}
	}
}
