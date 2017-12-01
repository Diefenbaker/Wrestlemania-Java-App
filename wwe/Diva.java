package ie.wit.wwe;

import java.io.Serializable;
import java.util.Comparator;

import ie.wit.wweAbstract.Wrestler;

/*The Diva class inherits all but two of its attributes from the Wrestler class.
 * It contains all getter and setter methods for Diva objects, as well as the toString,
 * totaled Attributes and Comparators.
 */

@SuppressWarnings("serial")
public class Diva extends Wrestler implements Serializable{

	
	private int totalAttributes = (strength + speed + luck);
	
	private String gender;

	public Diva(int id, String name, int titles, int age, int strength,
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
		return "\nDiva - ID: " +id+
				" Name: "+name+
				" Age: "+age+
				" Titles Won: "+titles+
				" Strength: "+strength+
				" Speed: "+speed+
				" Luck: "+luck; 
	}

	

	
	public static Comparator<Diva> sortName = new Comparator<Diva>() {
		public int compare(Diva first, Diva second) {
			return first.name.compareTo(second.name);
		}
	};

	public static Comparator<Diva> sortAge = new Comparator<Diva>() {
		public int compare(Diva first, Diva second) {
			return Integer.compare(first.getAge(), second.getAge());
		}
	};

	public static Comparator<Diva> sortTitles = new Comparator<Diva>() {
		public int compare(Diva first, Diva second) {
			return Integer.compare(first.getTitles(), second.getTitles());
		}
	};

	public static Comparator<Diva> sortStrength = new Comparator<Diva>() {
		public int compare(Diva first, Diva second) {
			return Integer.compare(first.getStrength(), second.getStrength());
		}
	};

	public static Comparator<Diva> sortSpeed = new Comparator<Diva>() {
		public int compare(Diva first, Diva second) {
			return Integer.compare(first.getSpeed(), second.getSpeed());
		}
	};

	public static Comparator<Diva> sortLuck = new Comparator<Diva>() {
		public int compare(Diva first, Diva second) {
			return Integer.compare(first.getLuck(), second.getLuck());
		}
	};

	public static Comparator<Diva> sortID = new Comparator<Diva>() {
		public int compare(Diva first, Diva second) {
			return Integer.compare(first.getId(), second.getId());
		}
	};



	

	
	
	
	
	
	
	
	
	
	
	


	}