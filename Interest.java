/**
 * Interest.java
 * @author Khang Nguyen
     * @author Surafel Abebe
     * @author Hazel
     * @author Rong Jin
     * @author Kang Su
     * @author Berenice Castro
     * CIS 22C Final Project
 */
public class Interest implements Comparable <Interest> {
    
	private String interest;
	private int index;
	
	
	public Interest(String interest) {
		this.interest = interest;
		 switch(interest) {
         case "Pop": 
        	 this.index = 1;
        	 break;
         case "Pop rock": 
        	 this.index = 2;
        	 break;
         case "Soft rock":
        	 this.index = 3;
        	 break;
         case "Hard rock":
        	 this.index = 4;
        	 break;
         case "Traditional pop":
        	 this.index = 5;
        	 break;
         case "Disco":
        	 this.index = 6;
        	 break;
         case "Country":
        	 this.index =7;
        	 break;
         case "Country rock":
        	 this.index = 8;
        	 break;
         case "R&B" :
        	 this.index =  9;
        	 break;
         case "Soul":
        	 this.index = 10;
        	 break;
         default:
        	 this.interest = "Unknown interest";
        	 break;
        	
        }
	}
    
	public Interest(int interest) {
		index = interest;
        switch(interest) {
         case 1: 
        	 this.interest = "Pop";
        	 break;
         case 2: 
        	 this.interest = "Pop rock";
        	 break;
         case 3:
        	 this.interest = "Soft rock";
        	 break;
         case 4:
        	 this.interest = "Hard rock";
        	 break;
         case 5:
        	 this.interest = "Traditional pop";
        	 break;
         case 6:
        	 this.interest = "Disco";
        	 break;
         case 7:
        	 this.interest = "Country";
        	 break;
         case 8:
        	 this.interest = "Country rock";
        	 break;
         case 9 :
        	 this.interest = "R&B";
        	 break;
         case 10:
        	 this.interest = "Soul";
        	 break;
         default:
        	 this.interest = "Unknown interest";
        	 break;
        	
        }


    }

    // Accessors /

    public String getInterest() {
        return interest;
    }
    

    // Mutators /

    public void setInterest(String interest) {
        this.interest = interest;
    }



    // Additional Operations **/

    public String toString() {
        return   this.interest +"\n";

    }
    @Override public boolean equals(Object o) {
        if(this == o) {
            return true;
        }else if(!(o instanceof Interest)) {
            return false;
        }else {
            Interest temp = (Interest) o;
            return this.interest.equals(temp.interest);
        }
    }
    @Override
    public int compareTo(Interest o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    public int hashCode() {
    	String key = interest; //this is the key. The element is the Butterfly Object
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += (int) key.charAt(i);
        }
        return sum;
    }
    
    public int returnIndex() {
    	return index;
    	
    }

}