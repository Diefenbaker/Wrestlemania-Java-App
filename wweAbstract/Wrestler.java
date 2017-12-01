package ie.wit.wweAbstract;

import java.io.Serializable;


@SuppressWarnings("serial")
public abstract class Wrestler implements Serializable {

	public int id, titles, age, strength, speed, luck;

	public String name;

	public Wrestler(int id, String name, int titles, int age, int strength,
			int speed, int luck) {
		super();
		this.id = id;
		this.name = name;
		this.titles = titles;
		this.age = age;
		this.strength = strength;
		this.speed = speed;
		this.luck = luck;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTitles() {
		return titles;
	}

	public void setTitles(int titles) {
		this.titles = titles;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	@Override
	public String toString() {
		return "Wrestler [id=" + id + ", name=" + name + ", titles=" + titles
				+ ", age=" + age + ", strength=" + strength + ", speed="
				+ speed + ", luck=" + luck + "]";
	}

}