/**
 * User.java
 * @author Khang Nguyen
     * @author Surafel Abebe
     * @author Hazel
     * @author Rong Jin
     * @author Kang Su
     * @author Berenice Castro
     * CIS 22C Final Project
 */
public class User implements Comparable<User>{
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private String city;
    private BST<User> friends;
    private LinkedList<Interest> interests;
    private static int numUser = 0;
    //constructor
   
   
    public User(String firstName, String lastName) {
    	// this constructor is just used to create the temporary object that needs to help for searching
    	// no need to increase numUser
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.userName = "Unkown userName";
    	this.passWord = "Unknown password";
    	this.city = "Unknown city";
    	this.friends = new BST<>();
    	interests = new LinkedList<>();
    
    }
    public User() {
    	this.firstName = "Unknown fisrtName";
    	this.lastName = "Unknwon lastName";
    	this.userName = "Unkown userName";
    	this.passWord = "Unknown password";
    	this.city = "Unknown city";
    	this.friends = new BST<>();
    	interests = new LinkedList<>();
    }
    public User (int id, String f, String l, String uName, String pWord) {
       
        firstName = f;
        lastName = l;
        this.id = id;
        userName = uName;
        passWord = pWord;
        city = "Unknown city";
        friends = new BST<>();
        interests = new LinkedList<>();
        numUser++;
    
    }
    public User(String firstName, String lastName, String user,String pass, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = user;
        this.passWord = pass;
        this.id = ++numUser;
        this.city = city;
        friends = new BST<>();
        interests = new LinkedList<>();
        numUser++;
        
        
    }
    
    public User(User user) {
    	if(user == null) {
    		return;
    	}else {
    		this.id = user.id;
    		this.firstName = user.firstName;
    		this.lastName = user.lastName;
    		this.userName = user.userName;
    		this.passWord = user.passWord;
    		this.city = user.city;
    		friends = new BST<User>(user.friends);
    		interests = new LinkedList<Interest>(user.interests);
    		numUser++;
    	}
    	
    }
    
    //accessor 
    public int getId() {
    	return id;
    }
    public String getUserName() {
        return userName;
    }
    
    public String getPassWord() {
        return passWord;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getCity() {
        return city;
    }
    
    public BST<User> getFriends() {
    	return friends;
    } 
    

    public LinkedList<Interest> getInterest(){
    	return interests;
    }
    public boolean friendExist(String first, String last) {
    	if(friends.searchByName(first, last) != null) {
    		return true;
    	}
    	return false;
    }
   
    //mutator
    
    public void setFirstName(String fName) {
        this.firstName = fName;
    }
    
    public void setLastName(String lName) {
        this.lastName = lName;
    }
    
    public void setUserName(String uName) {
        this.userName = uName;
    }
    
    public void setPassWord(String pWord) {
        this.passWord = pWord;
    }
    
    public void setCity(String c) {
        this.city = c;
    }
    
    public void addFriends(User user) {
    	if(user == null) {
    		return;
    	}
    	friends.insert(user);
    }
    public void removeFriend(User user) {
    	if(user == null) {
    		return;
    	}
    	friends.remove(user);
    }
    public void addInterests(Interest interest) {
    	if(interest == null) {
    		return;
    	}
    	if(interests.linearSearch(interest) == -1) {
    		interests.addLast(interest);
    	}       
    }
    //additional methods
    public int hashCode() {
    	String key = userName + passWord; //this is the key. The element is the Butterfly Object
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += (int) key.charAt(i);
        }
        return sum;
    }
    
    public String toString() {
        
        return "Id:            " + id + "\nFirst name:    " + firstName 
        		+ "\nLast Name:     " + lastName + "\n" + "City:          " + city
        		+ "\n\nFriend List:    " + friends.FriendListInorderToString() + "\n\nInterest: \n" + interests + "\n";
    }
    
    public boolean friendIsEmpty() {
    	if(friends.isEmpty()) {
    		return true;
    	}
    	return false;
    }
    @Override public boolean equals(Object o) {
    	if(this == o) {
    		return true;
    	}else if(! (o instanceof User)) {
    		return false;
    	}else {
    		User temp = (User) o;
    		return this.firstName.equals(temp.firstName) && this.lastName.equals(temp.lastName);
    	}
    }
   
    @Override public int compareTo(User o) {
        if(o==null) {
            return 0;
        }
        else if(this.equals(o)) {
            return 0;
        }
        else if(!this.getFirstName().equals(o.getFirstName())){
            return this.getFirstName().compareTo(o.getFirstName());        
        }
        else if(!this.getLastName().equals(o.getLastName())){
            return this.getLastName().compareTo(o.getLastName());        
        }
        else {
            return 0;
        }
    }
	
	
    
}