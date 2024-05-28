/**
 * Main.java
 * @author Khang Nguyen
     * @author Surafel Abebe
     * @author Hazel
     * @author Rong Jin
     * @author Kang Su
     * @author Berenice Castro
     * CIS 22C Final Project
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static BST<User> bstUser;
	private static ArrayList<User> userArrayList;
	private static HashTable<User> hashTableInterest;
	private static HashTable<User> hashTableLogin;
	private static Graph graphFriendRecommendation;

	public static void readFileIO() throws FileNotFoundException {

		Scanner input = new Scanner(new File("database.txt"));
		int id, numFriend, numInterest;
		String firstName, lastName, userName, password, city;
		userArrayList = new ArrayList<>();
		hashTableInterest = new HashTable<User>(11);
		graphFriendRecommendation = new Graph(50);
		while (input.hasNextLine()) {

			id = Integer.parseInt(input.nextLine());
			firstName = input.next();
			lastName = input.next();

			input.nextLine();

			userName = input.nextLine();
			password = input.nextLine();

			User user = new User(id, firstName, lastName, userName, password);
			numFriend = Integer.parseInt(input.nextLine());

			for (int i = 0; i < numFriend; i++) {
				input.nextLine();
			}

			city = input.nextLine();
			user.setCity(city);

			numInterest = Integer.parseInt(input.nextLine());

			for (int i = 0; i < numInterest; i++) {
				Interest interest = new Interest(input.nextLine());
				user.addInterests(interest);
				hashTableInterest.insertInterest(interest.returnIndex(), user);
				//graphFriendRecommendation.insertUser(user);

			}

			userArrayList.add(user);
			if (input.hasNextLine()) {
				input.nextLine();
			}

		}
		User[] userArray = new User[userArrayList.size()];
		userArray = userArrayList.toArray(userArray);

		bstUser = new BST<User>(userArray);
		hashTableLogin = new HashTable<User>(bstUser.getSize() * 2);
		input.close();

		Scanner input1 = new Scanner(new File("database.txt"));
		int friendId;

		while (input1.hasNextLine()) {
			id = Integer.parseInt(input1.nextLine());
			firstName = input1.next();
			lastName = input1.next();
			input1.nextLine();
			input1.nextLine();
			input1.nextLine();

			numFriend = Integer.parseInt(input1.nextLine());
			User friend = null;
			for (int i = 0; i < numFriend; i++) {//id of r
				friendId = Integer.parseInt(input1.nextLine());
				for (int j = 0; j < userArray.length; j++) {
					if (userArray[j].getId() == friendId) {
						friend = userArray[j];
						break;
					}
				}
				userArrayList.get(id - 1).addFriends(friend);
				graphFriendRecommendation.addDirectedEdge(id, friend.getId());

			}
			hashTableLogin.insert(bstUser.searchByName(firstName, lastName));
			input1.nextLine();
			numInterest = Integer.parseInt(input1.nextLine());
			for (int i = 0; i < numInterest; i++) {
				input1.nextLine();
			}
			if (input1.hasNextLine()) {
				input1.nextLine();
			}
		}
		
		input1.close();
	}
	

	public static void writeToFile() throws FileNotFoundException {
		PrintWriter output = new PrintWriter(new File("database.txt"));
		for (int i = 0; i < userArrayList.size(); i++) {
			User user = userArrayList.get(i);
			output.write(user.getId() + "\n" + user.getFirstName() + " " + user.getLastName() + "\n"
					+ user.getUserName() + "\n" + user.getPassWord() + "\n" + user.getFriends().getSize() + "\n"
					+ graphFriendRecommendation.getStringList(i + 1) + user.getCity() + "\n"
					+ user.getInterest().getLength() + "\n" + user.getInterest().toString().strip() + "\n");
			if (i != userArrayList.size() - 1) {
				output.write("\n");
			}
		}
		output.close();
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		readFileIO();
		int choice = 3;
		while (choice != 0) {

			System.out.println("Welcome to 'Social Media'\n" + "Enter 1 to Log-in with an existing account\n"
					+ "Enter 2 to Create new Account" +"\nEnter 0 to quit");

			Scanner keyboard = new Scanner(System.in);

			// System.out.println(graphFriendRecommendation.toString());

			System.out.print("\nChoice: ");
			choice = keyboard.nextInt();
			keyboard.nextLine();
			if (choice == 1) {
				User temp = new User();

				while (hashTableLogin.searchAccount(temp) == null) {
					System.out.println("\nPlease enter your log in informations!");

					System.out.print("Username: ");
					String user = keyboard.nextLine();
					temp.setUserName(user);

					System.out.print("Password: ");
					String passWord = keyboard.nextLine();
					temp.setPassWord(passWord);
					
					if (hashTableLogin.searchAccount(temp) == null) {
						System.out.println("invalid username or password");
					} else {
						User currentUser = hashTableLogin.searchAccount(temp);
						System.out.println("\n******Login Success!********\n");
						int menu = 50;
						while (menu != 0) {
							System.out.println("\n" + "1.View friends(sorted by name, show in list)\r\n"
									+ "2.View this Friend's Full Profile\n" + "3.Make New Friends(search by name)\r\n"
									+ "4.Add friend\r\n" + "0.quit\n");
							System.out.print("Choice: ");
							menu = keyboard.nextInt();
							keyboard.nextLine();
							if (menu == 1) {
								System.out.print("\n1.View friend sorted by name\n2.Search by friend name\n\nChoice: ");
								int subMenu = keyboard.nextInt();
								keyboard.nextLine();

								if (subMenu == 1) {
									if (currentUser.friendIsEmpty()) {
										System.out.println("\nYou have no friend in your list yet");
									} else {
										System.out.println(currentUser.getFriends().FriendListInorderToString());
									}
								}

								else if (subMenu == 2) {
									if (currentUser.friendIsEmpty()) {
										System.out.println("\nYou have no friend in your list yet");
									} else {
									System.out.print("\nFull name of friend: ");
									String firstName = keyboard.next();
									String lastName = keyboard.next();
									keyboard.nextLine();
									User friend = new User(firstName, lastName);
									if (currentUser.friendIsEmpty()) {
										System.out.println("\nYou have no friends!");
									} else if (!currentUser.getFriends().search(friend)) {

										System.out.println(firstName + " " + lastName + " is not in your friend list");

									} else {
										System.out.println(
												"\n" + currentUser.getFriends().searchByName(firstName, lastName));
									}
								}
								}
							} else if (menu == 2) {
								if (currentUser.friendIsEmpty()) {
									System.out.println("\nYou have no friend in your list yet");
								} else {
									System.out.print("\nEnter friend's full name: ");
									String firstN = keyboard.next();
									String lastN = keyboard.next();
									LinkedList<User> friendSearch = currentUser.getFriends().makeNewFriend(firstN, lastN);
									keyboard.nextLine();
									
									if (friendSearch == null) {
										System.out.println("\n" + firstN + " " + lastN + " is not in your friend list");
									} else {
										System.out.print("\n" + friendSearch.toString());
										System.out.print("Which friend would you like to remove? Please Enter the ID\n" + "\nChoice:");
										int userID = keyboard.nextInt();
										keyboard.nextLine();
										
										System.out.print("\nWould you like to remove this friend(y\\n)?\nChoice: " );
										String removeFriend = keyboard.nextLine();
										
										if (removeFriend.equalsIgnoreCase("y")) {
											
											currentUser.removeFriend(userArrayList.get(userID -1));
											userArrayList.get(userID -1).removeFriend(currentUser);
											graphFriendRecommendation.removeFriend(currentUser.getId(), userArrayList.get(userID -1).getId());
											graphFriendRecommendation.removeFriend(userArrayList.get(userID -1).getId(),currentUser.getId() );
											System.out.println("\nFriend has been successfully removed!");
	
										} else if (removeFriend.equalsIgnoreCase("n")) {
											System.out.println("Friend not removed");
										} else {
											System.out.println("Invalid input!");
										}
									}
								}
							} else if (menu == 3) {
								System.out.println("\nSearch by name \n");
								System.out.print("Please enter person's full name: ");
								String firstName = keyboard.next();
								String lastName = keyboard.next();
								LinkedList<User> friendSearch = bstUser.makeNewFriend(firstName, lastName);

								if (friendSearch == null) {
									System.out.println(
											"\n" + firstName + " " + lastName + " has not registered an account!");
								} else {
									System.out.println("\n" + friendSearch.toString());
									keyboard.nextLine();
									System.out.print("Enter the ID of the friend you wish to add:");
									int userID = keyboard.nextInt();
									keyboard.nextLine();
									if(currentUser.friendExist(firstName, lastName)) {
										System.out.println(firstName + " " + lastName + " this person is already your friend!");
										
									} else {
									System.out.print("Do you want to add this friend?(Y/N)\n" + "\nChoice: ");
									String addFriend = keyboard.nextLine();
									if (addFriend.equalsIgnoreCase("y")) {
										
										currentUser.addFriends(userArrayList.get(userID-1));
										userArrayList.get(userID-1).addFriends(currentUser);
										graphFriendRecommendation.addDirectedEdge(currentUser.getId(), userID);
										graphFriendRecommendation.addDirectedEdge(userID, currentUser.getId());

										System.out.println(firstName + " "+ lastName + " has been successfully Added!");

									} else if (addFriend.equalsIgnoreCase("n")) {
										System.out.println("Friend not added");
									} else {
										System.out.println("Invalid input!");
									}
								}
								}
							}
							else if (menu == 4) {

								System.out.print(
										"\n1. Search by interest\n" + "2. Get Friend recommendations\n\n" + "Choice: ");
								int subMenuChoice = keyboard.nextInt();

								if (subMenuChoice == 1) {
									System.out.print("\nAdd Friend!\n" + "\nSearch by Interest!:\n" + "\nPop\r\n"
											+ "Pop rock\r\n" + "Soft rock\r\n" + "Hard Rock\r\n" + "Traditional pop\r\n"
											+ "Disco\r\n" + "Country\r\n" + "Country rock\r\n" + "R&B\r\n"
											+ "Soul\r\n");
									keyboard.nextLine();
                                  while(true) {
									System.out.print("\nInterest: ");
									String interests = keyboard.nextLine();
                                 
									if (interests.equalsIgnoreCase("Pop")) {
										System.out.println("These people shares the same interests as you!");
										hashTableInterest.printBucket(1);
										break;
									} else if (interests.equalsIgnoreCase("Pop rock")) {
										System.out.println("These people shares the same interests as you!");
										hashTableInterest.printBucket(2);
										break;
									} else if (interests.equalsIgnoreCase("Soft rock")) {
										System.out.println("These people shares the same interests as you!");
										hashTableInterest.printBucket(3);
										break;
									} else if (interests.equalsIgnoreCase("Hard Rock")) {
										System.out.println("These people shares the same interests as you!");
										hashTableInterest.printBucket(4);
										break;
									} else if (interests.equalsIgnoreCase("Traditional pop")) {
										System.out.println("These people shares the same interests as you!");
										hashTableInterest.printBucket(5);
										break;
									} else if (interests.equalsIgnoreCase("Disco")) {
										System.out.println("These people shares the same interests as you!");
										hashTableInterest.printBucket(6);
										break;
									} else if (interests.equalsIgnoreCase("Country")) {
										System.out.println("These people shares the same interests as you!");
										hashTableInterest.printBucket(7);
										break;
									} else if (interests.equalsIgnoreCase("Country rock")) {
										System.out.println("These people shares the same interests as you!");
										hashTableInterest.printBucket(8);
										break;
									} else if (interests.equalsIgnoreCase("R&B")) {
										System.out.println("These people shares the same interests as you!");
										hashTableInterest.printBucket(9);
										break;
									} else if (interests.equalsIgnoreCase("Soul")) {
										System.out.println("These people shares the same interests as you!");
										hashTableInterest.printBucket(10);
										break;
									} else {
										System.out.println("no such interest");
									
									}
                                  }

									System.out.print("Who would you like to add to your friend list!\n"
											+ "Please enter their full name!\n" + "Name: ");
									String tempName = keyboard.next();
									String tempName2 = keyboard.next();
									keyboard.nextLine();
									if (currentUser.friendExist(tempName, tempName2)) {
										System.out.println(tempName + " " + tempName2 + " is already your friend!");
									} else {
										currentUser.addFriends(bstUser.searchByName(tempName, tempName2));
										bstUser.searchByName(tempName, tempName2).addFriends(currentUser);
										graphFriendRecommendation.addDirectedEdge(currentUser.getId(), bstUser.searchByName(tempName, tempName2).getId());
										graphFriendRecommendation.addDirectedEdge(bstUser.searchByName(tempName, tempName2).getId(), currentUser.getId());
										System.out.println(tempName + " is succesfully added to your friend list");
									}

								} else if (subMenuChoice == 2) {
									System.out.println("Recomending Friends with mutual friends as you!");
								  
									LinkedList<Interest> userInterests = currentUser.getInterest();
									System.out.println(userInterests.toString());
									graphFriendRecommendation.BFS(currentUser.getId());
									LinkedList<Integer> integer = graphFriendRecommendation.recommendFriends(currentUser);
									
									integer.positionIterator();
									while(!integer.offEnd()) {
										int id = integer.getIterator();
										System.out.println(userArrayList.get(id-1));
										integer.advanceIterator();
									}
									
									System.out.print("Who would you like to add to your friend list!\n"
											+ "Please enter their full name!\n" + "Name: ");
									String tempName = keyboard.next();
									String tempName2 = keyboard.next();
									keyboard.nextLine();
									if (currentUser.friendExist(tempName, tempName2)) {
										System.out.println(tempName + " " + tempName2 + " is already your friend!");
									} else {
										currentUser.addFriends(bstUser.searchByName(tempName, tempName2));
										bstUser.searchByName(tempName, tempName2).addFriends(currentUser);
										graphFriendRecommendation.addDirectedEdge(currentUser.getId(), bstUser.searchByName(tempName, tempName2).getId());
										graphFriendRecommendation.addDirectedEdge(bstUser.searchByName(tempName, tempName2).getId(),currentUser.getId() );
										System.out.println(tempName + " is succesfully added to your friend list");
									}

									
									

								}
							} else if (menu == 0) {
								choice = 0;
								writeToFile();
								break;
							} else {
								System.out.println("Invalid input");
							}
						}
					}
				}

				

			}

			else if (choice == 2) {
				System.out.println("\nCreate your Social Media account!");

				System.out.print("\nFirst name: ");
				String fName = keyboard.nextLine();

				System.out.print("Last name: ");
				String lName = keyboard.nextLine();

				boolean usernameUsed;

				System.out.print("Username: ");
				String user = keyboard.nextLine();

				System.out.print("Password: ");
				String passWord = keyboard.nextLine();

				System.out.print("City: ");
				String city = keyboard.nextLine();

				User newUser = new User(fName, lName, user, passWord, city);
				bstUser.insert(newUser);
				hashTableLogin.insert(newUser);// 1: 23455
				userArrayList.add(newUser);
				
				
				System.out.print("\nWhat are your interets? \n"
						+ "Choose the corresponding index to add interest(s) or enter '0' to finish adding \r\n"
						+ "Please choose at least one from below!\n\n" + "1.Pop\r\n" + "2.Pop rock\r\n"
						+ "3.Soft rock\r\n" + "4.Hard Rock\r\n" + "5.Traditional pop\r\n" + "6.Disco\r\n"
						+ "7.Country\r\n" + "8.Country rock\r\n" + "9.R&B\r\n" + "10.Soul\r\n"
						+ "\nEtner \"0\" to Exit." + "\n\nChoice: ");
				int interest = keyboard.nextInt();
				keyboard.nextLine();
				while (interest != 0) {
					System.out.println("\nEnter more interest or \"0\" to Exit\n");
					System.out.print("Choice: ");
					interest = keyboard.nextInt();
					keyboard.nextLine();
					
					if(interest >= 1 && interest<=10) {
						Interest temp = new Interest(interest);
						newUser.addInterests(temp);
						System.out.println();
					}

				}
			
		   
			}
			else if(choice==0) {
				System.out.println("Quiting the program");
				writeToFile();
				break;
			}
			else {
				System.out.println("Invalid input!");
			}

		}

	}
}