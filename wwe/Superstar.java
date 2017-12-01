package ie.wit.wwe;

import java.io.Serializable;
import java.util.Comparator;

import ie.wit.wweAbstract.Wrestler;

/*The Superstar class inherits all but two of its attributes from the Wrestler class.
 * It contains all getter and setter methods for Superstar objects, as well as the toString,
 * totaled Attributes and Comparators.
 */

@SuppressWarnings("serial")
public class Superstar extends Wrestler implements Serializable {

	private int totalAttributes = (strength + speed + luck);
	

	private String gender;

	public Superstar(int id, String name, int titles, int age, int strength,
			int speed, int luck, String gender) {
		super(id, name, titles, age, strength, speed, luck);
		gender = this.gender;
		// TODO Auto-generated constructor stub
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public int getTotalAttributes() {
		return totalAttributes;
	}
	
	@Override
	public String toString() {
		return "\nSuperStar - ID: "+id+
				"  Name: "+name+
				"  Age: "+age+
				"  Titles Won: "+titles+
				"  Strength: "+strength+
				"  Speed: "+speed+
				"  Luck: "+luck; 
	}
	
	

	public static Comparator<Superstar> sortName = new Comparator<Superstar>() {
		public int compare(Superstar first, Superstar second) {
			return first.name.compareTo(second.name);
		}
	};

	public static Comparator<Superstar> sortAge = new Comparator<Superstar>() {
		public int compare(Superstar first, Superstar second) {
			return Integer.compare(first.getAge(), second.getAge());
		}
	};

	public static Comparator<Superstar> sortTitles = new Comparator<Superstar>() {
		public int compare(Superstar first, Superstar second) {
			return Integer.compare(first.getTitles(), second.getTitles());
		}
	};

	public static Comparator<Superstar> sortStrength = new Comparator<Superstar>() {
		public int compare(Superstar first, Superstar second) {
			return Integer.compare(first.getStrength(), second.getStrength());
		}
	};

	public static Comparator<Superstar> sortSpeed = new Comparator<Superstar>() {
		public int compare(Superstar first, Superstar second) {
			return Integer.compare(first.getSpeed(), second.getSpeed());
		}
	};

	public static Comparator<Superstar> sortLuck = new Comparator<Superstar>() {
		public int compare(Superstar first, Superstar second) {
			return Integer.compare(first.getLuck(), second.getLuck());
		}
	};

	public static Comparator<Superstar> sortID = new Comparator<Superstar>() {
		public int compare(Superstar first, Superstar second) {
			return Integer.compare(first.getId(), second.getId());
		}
	};
	
	

}