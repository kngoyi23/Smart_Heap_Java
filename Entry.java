
public class Entry {

	private int key = 0; 
	private String value = "__"; //default value
	
	public Entry() {// defauult constructor
		this.key = 0; 
		this.value = "__"; // default value
	}
	
	public Entry(int key, String value) {// parameterized constructor
		this.key = key; 
		this.value = value;
	}
	
	//accessors below
	public int getKey() {return this.key; }
	public String getValue() {return this.value;}
	
	//mutators below
	public void setKey(int key) {this.key = key;}
	public void setValue(String value) {this.value = value;}
	
	public String toString() {
		return "Entry information: \n" 
				+ "Key: " + this.key + "\n"
				+ "Value: " + this.value;
	}
	
}
