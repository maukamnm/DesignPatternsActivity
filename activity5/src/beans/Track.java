package beans;

public class Track {
	private String name;
	private int number;
	
	public Track() {
		name = "";
		number = 0;
	}
	
	public Track(String title, int number) {
		this.name = title;
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}