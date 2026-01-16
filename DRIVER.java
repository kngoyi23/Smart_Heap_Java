
import java.util.Scanner;

public class DRIVER {

	public static void main(String[] args) {
		System.out.println("----------------------------------------------------------------");
		System.out.println("	WELCOME TO KABEYA'S HEAP GENERATOR PROGRAM					");
		System.out.println("----------------------------------------------------------------");
		final int inboundAdjuster = -1;
		System.out.println("How many heaps would you like to create?: ");
		Scanner scan1 = new Scanner(System.in); 
		int numb_Of_heaps = scan1.nextInt();
		Heap [] heaps = new Heap [numb_Of_heaps];

		System.out.println("You have " + heaps.length + " Heaps.");
		System.out.println("Which Heap would you like to develop (Pick from one of the numbers below)?: ");
		for(int i = 0; i < heaps.length; i++) {
			System.out.println("Heap #" + (i + 1));
		}
		int pickedHeap = scan1.nextInt();
		pickedHeap = pickedHeap + inboundAdjuster;
		try {
			for(int i = 0; i < heaps.length; i++) {
				heaps[i] = new Heap();
			}
			heaps[pickedHeap] = new Heap();
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Out of bounds on picked heap...");
			System.out.println("Program will now terminate...");
			System.exit(0);//Terminating System
		}

		String heapMenu = "1. Insert Keys and Values in the Heap" 
				+ "\n2. Change State of Heap (from min-Heap to max-Heap or vice versa)" 
				+ "\n3. Remove the Top Entry of the Heap" 
				+ "\n4. Display the Top of the Heap" 
				+ "\n5. Remove Entrie(s) in the Heap" 
				+ "\n6. Replace an Entry's Key in the Heap" 
				+ "\n7. Replace an Entry's Value in the Heap" 
				+ "\n8. Check if Heap is Empty"
				+ "\n9. Check the size of the Heap" 
				+ "\n10. Show current heap"
				+ "\n11. Merge this Heap with another one"
				+ "\n12. Switch heap"
				+ "\n13. Exit";
		
		final int op1 = 1, op2 = 2, op3 = 3, op4 = 4, op5 = 5, op6 = 6, op7 = 7, op8 = 8, // main menu options
				op9 = 9, op10 = 10, op11 = 11, op12 = 12, op13 = 13;// main menu options
		
		int menuOption = 0;

		do {
			System.out.println("What would you like to do with your Heap?: ");
			System.out.println("-------------------------------------------");
			System.out.println(heapMenu);

			Scanner menuScan = new Scanner(System.in);
			menuOption = menuScan.nextInt();

			switch (menuOption) {
			case op1: {//1 ------------------ Insert Keys and Values in the Heap

				String keepGoing = ""; 
				boolean newKey = false;
				do {
					Scanner op1Scan = new Scanner(System.in); 
					System.out.println("Insert your key in the heap: ");
					int key = op1Scan.nextInt();
					System.out.println("Insert your value for key " + key + ": ");
					String value = op1Scan.next(); 
					System.out.println("For key " + key + " you have entered value: " + value );
					heaps[pickedHeap].insert(key, value);//*** heap insertion
					System.out.println("Would you like to enter a new key? (type \"yes\" or \"no\": ");
					keepGoing = op1Scan.next();
					if(!(keepGoing.equalsIgnoreCase("yes"))&&!(keepGoing.equalsIgnoreCase("no"))) {
						System.out.println("Invalid Entry...");
						System.out.println("You have been brought back to the main menu\n");
						break; 
					}
				} while(keepGoing.equalsIgnoreCase("yes"));

				break;
			}
			case op2:{//2 ------------------ Change State of Heap
				boolean isHeapEmpty = heaps[pickedHeap].isEmpty();
				if(isHeapEmpty) {
					System.out.println("Heap is empty...");
				}
				else {
					System.out.println("The current state of your heap is: " + heaps[pickedHeap].state());
					heaps[pickedHeap].toggle();
					System.out.println("After change, the state of your heap is: " + heaps[pickedHeap].state());
				}
				break;
			}
			case op3:{//3 ------------------ Remove the Top Entry of the Heap
				heaps[pickedHeap].removeTop();
				heaps[pickedHeap].showHeap();
				break;
			}
			case op4:{//4 ------------------ Display the Top of the Heap
				System.out.println("The top of your heap is: ");
				System.out.println(heaps[pickedHeap].top() + "\n");
				break;
			}
			case op5:{//5 ------------------ Remove Entrie(s) in the Heap

				if(heaps[pickedHeap].isEmpty()) {
					System.out.println("Your heap is currently empty");
				}
				else {
					String keepGoing = "";
					Scanner op5Scan = new Scanner(System.in);  
					do {
						heaps[pickedHeap].showHeap();
						System.out.println("Enter the Key of the Entry you wish to remove: ");
						int key = op5Scan.nextInt();
						System.out.println("Enter the Value of the Entry you wish to remove: ");
						String value = op5Scan.next();

						Entry removedEntry = new Entry(key, value);
						removedEntry = heaps[pickedHeap].remove(removedEntry);
						if(removedEntry == null) {break;}

						System.out.println("Would you like to remove another entry (type \"yes\" or \"no\")?: ");
						keepGoing = op5Scan.next();
						
						boolean invalidEntry = !keepGoing.equalsIgnoreCase("no") && !keepGoing.equalsIgnoreCase("yes");
						
						if(invalidEntry) {
							System.out.println("Invalid Entry...");
							System.out.println("You have been brought back to the main menu\n");
							break; 
						}

					} while(keepGoing.equalsIgnoreCase("yes"));
				}

				break;
			}
			case op6:{//6 ------------------ Replace an Entry's Key in the Heap
				Scanner op6Scan = new Scanner(System.in); 
				heaps[pickedHeap].showHeap(); 
				System.out.print("Which Key would you like to replace?: ");
				int replacedKey = op6Scan.nextInt();
				if(heaps[pickedHeap].entryKeyFound(replacedKey)) {
					System.out.println("Enter the new Key (type a number): ");
					int newKey = op6Scan.nextInt();
					heaps[pickedHeap].replace(replacedKey, newKey); 
					System.out.println("Key replacement was successful! ");
				}
				else {
					System.out.println("Unable to find the key...");
					System.out.println("Key replacement unsucessful! ");
				}

				break;
			}
			case op7:{//7 ------------------ Replace an Entry's Value in the Heap
				Scanner op7Scan = new Scanner(System.in); 
				heaps[pickedHeap].showHeap(); 
				System.out.print("Which Value would you like to replace?: ");
				String replacedValue = op7Scan.next();
				System.out.println("Enter your new value below: ");
				String newValue = op7Scan.next();
				String replacedDone = heaps[pickedHeap].replace(replacedValue, newValue);
				if(replacedDone == null) {
					System.out.println("Value Replacement unsuccessful!");
					System.out.println("You have been redirected to the main menu...");
				}
				else {
					System.out.println("Value Replacement successful! ");
				}
				break;
			}
			case op8:{//8 ------------------ Check if Heap is Empty
				if(heaps[pickedHeap].isEmpty()) {
					System.out.println("This Heap is empty.");
				}
				else {
					System.out.println("This Heap is not empty.");
				}
				break;
			}
			case op9:{//9 ------------------ Check the size of the Heap
				System.out.print("This heap has " + (heaps[pickedHeap].size()) + " elements.\n");
				break;
			}
			case op10:{//10 ------------------ Show current heap
				heaps[pickedHeap].showHeap();
				break;
			}
			case op11:{//11 ------------------ Merge this Heap with another one
				int adjuster = 1;
				Scanner op11Scan = new Scanner(System.in);
				System.out.println("You have " + heaps.length + " Heaps.");
				System.out.println("Which Heap two heaps would you like to merge? ");
				for(int i = 0; i < heaps.length; i++) {
					System.out.println("Heap #" + (i + 1));
				}
				System.out.println("\nYour heap is Heap#" + (pickedHeap + adjuster));
				System.out.println("Which heap would you like to merge to yours?: ");
				int mergedHeap = op11Scan.nextInt();
				mergedHeap = mergedHeap - adjuster;
				if(mergedHeap != pickedHeap) {
					if(mergedHeap <= (heaps.length - adjuster)) {
						try {
							heaps[pickedHeap].merge(heaps[mergedHeap]);
						} catch(IndexOutOfBoundsException e) {
							System.out.println("You have entered the wrong value");
							System.out.println("You have been redirected to the main menu");
							break;
						}
					}
					else {
						System.out.println("The Heap (number) that you picked doesn't exist. ");
					}
				}
				else {
					System.out.println("Merging hasn't been performed...");
					System.out.println("Chosen Heap for merging is the same as the initial Heap");
				}


				break;
			}
			case op12:{//12 ------------------ Switch heap
				boolean validHeapPick = true; 
				final int firstHeap = 0, lastHeap = heaps.length - 1; 
				do {
					System.out.println("You have " + heaps.length + " Heaps.");
					System.out.println("Which Heap would you like to develop (Pick from one of the numbers below)?: ");
					for(int i = 0; i < heaps.length; i++) {
						System.out.println("Heap #" + (i + 1));
					}
					pickedHeap = scan1.nextInt();
					pickedHeap = pickedHeap + inboundAdjuster;
					validHeapPick = true; 
					if(!((pickedHeap >= firstHeap)&&(pickedHeap <= lastHeap))) {
						validHeapPick = false;
						System.out.println("You have entered an invalid heap number...");
						System.out.println("Please try again");
					}
					
				} while(!validHeapPick);
				break;
			}
			case op13:{//13 ------------------ Exit
				System.out.println("Thank You for using Kabeya's Heap Program =)");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + menuOption);
			}
			if(!(menuOption==op1||menuOption==op2||menuOption==op3||menuOption==op4||menuOption==op5||menuOption==op6||
					menuOption==op7||menuOption==op8||menuOption==op9||menuOption==op10||menuOption==op11||
					menuOption==op12||menuOption==op13)) {
				System.out.println("You have entered an invalid menu option...");
				System.out.println("You have been sent back to the main menu...");
			}
		} while(menuOption != op13);


	}

}