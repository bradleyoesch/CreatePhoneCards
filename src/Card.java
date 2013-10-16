/**
 * Card object for the CreateVCards class. Holds all the information about the
 * person for easy adding to final vCard file.
 * 
 * @author Bradley Oesch
 * @version 1.0 September 2013
 */
public class Card {
	
	private Boolean brother;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String initials;
	
	public Boolean getBrother() {
		return brother;
	}
	
	public void setBrother(Boolean brother) {
		this.brother = brother;
		if (!brother) this.initials = null;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getInitials() {
		return (brother) ? initials : null;
	}
	
	public void setInitials(String initials) {
		if (brother) this.initials = initials;
	}
}
