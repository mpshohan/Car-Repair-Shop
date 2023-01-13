// Md Patwary
// Car Repair Shop Porject
// Main class


import java.util.Scanner;

public class CarRepairShopDriver {

	private static Scanner input = new Scanner(System.in);		
	private static CarRepairShop shop = new CarRepairShop(); 
	
	public static void main(String[] args) {
		int choice;
		String[] choices = {
				"Add a new car to the database", 
				"Record a repair ticket for a car",
				"Get the cost for a repair ticket by ticket number",
				"Get the total repair costs for a car using VIN",
				"Get the make of car that has the greatest total number of repairs",
				"Change the cost for a repair by ticket number",
				"Delete a repair ticket by ticket number",
				"Delete all repair tickets using VIN",
				"Delete a car and its repairs from the database using VIN",
				"Quit Program"
				};
		
		System.out.println("Welcome to Wolfie's Car Repair Shop!");
		do {
			System.out.println("\nWolfie's Car Repair Shop Main Menu");
			System.out.println("----------------------------------");			
			for (int i = 0; i < choices.length; i++) {
				System.out.println((i < choices.length-1 ? i+1 : 0) + ". " + choices[i]);
			}			
			
			System.out.print("Make your choice: ");
			choice = input.nextInt();
			
			switch (choice) {
			case 1: // Add a new car to the database
				main_addNewCar();		
				break;
			case 2: // Record a repair ticket for a car
				main_addRepairTicket();
				break;
			case 3: // Get the cost for a repair ticket by ticket number
				main_getRepairCost();
				break;
			case 4: // Get the total repair costs for a car using VIN
				main_getTotalRepairCosts();
				break;
			case 5: // Get the total repair costs for a car using VIN
				main_getWorstCarMake();
				break;
			case 6: // Change the cost for a repair by ticket number
				main_updateRepairCost();
				break;
			case 7: // Delete a repair ticket by ticket number
				main_deleteRepair();
				break;				
			case 8: // Delete all repair tickets using VIN
				main_deleteAllRepairsForCar();
				break;
			case 9: // Delete a car and its repairs from the database using VIN
				main_deleteCarAndRepairs();
				break;
			case 0: // Quit
				System.exit(0);
			default:
				System.out.println("Invalid choice. Try again.");
			}
			
		} while (choice != 0);
		
		input.close();
	}
	
	public static void main_addNewCar() {
		String vin, make;
		int year, carNum;
		input.nextLine();
		System.out.print("Enter car's VIN: ");
		vin = input.nextLine();
		System.out.print("Enter car's year: ");				
		year = input.nextInt();
		input.nextLine();
		System.out.print("Enter car's make: ");
		make = input.nextLine();
		
		carNum = shop.addNewCar(vin, make, year);
		if (carNum != -1)
			System.out.println("Car #" + carNum + " successfully added to database.");
		else
			System.out.println("Error: car already exists in database.");		
	}
	
	public static void main_addRepairTicket() {
		String vin, description;
		int ticketNum;
		double cost;
		input.nextLine();
		System.out.print("Enter car's VIN: ");
		vin = input.nextLine();
		System.out.print("Enter repair cost: ");
		cost = input.nextDouble();
		input.nextLine();
		System.out.print("Enter repair description: ");
		description = input.nextLine();
		ticketNum = shop.addRepairTicket(vin, cost, description);
		if (ticketNum != -1)
			System.out.println("Repair ticket #" + ticketNum + " successfully added to database.");
		else
			System.out.println("Error: could not record repair in database.");		
	}
	
	public static void main_getRepairCost() {
		int ticketNum;
		double cost;
		System.out.print("Enter repair ticket number: ");
		ticketNum = input.nextInt();
		cost = shop.getRepairCost(ticketNum);
		if (cost != -1)
			System.out.printf("Repair ticket #" + ticketNum + " had a cost of $%.2f\n", cost);
		else
			System.out.println("Error: ticket number not found in database.");	
	}

	public static void main_getTotalRepairCosts() {
		String vin;
		double total;
		input.nextLine();
		System.out.print("Enter VIN: ");
		vin = input.nextLine();
		total = shop.getTotalRepairCosts(vin);
		if (total != -1)
			System.out.printf("Total costs to repair car with VIN " + vin + " was $%.2f\n", total);
		else
			System.out.println("Error: VIN not found in database.");	
	}

	public static void main_getWorstCarMake() {
		String worstCarMake = shop.getWorstCarMake();
		if (worstCarMake == null)
			System.out.println("No tickets in database.");
		else
			System.out.println("The car make with most repairs is: " + worstCarMake);
	}
	
	public static void main_updateRepairCost() {
		int ticketNum;
		boolean success;
		double newCost;
		System.out.print("Enter repair ticket number: ");
		ticketNum = input.nextInt();
		System.out.print("Enter new repair cost: ");
		newCost = input.nextDouble();		
		success = shop.updateRepairCost(ticketNum, newCost);
		if (success)			
			System.out.printf("Repair ticket #" + ticketNum + " now has a cost of $%.2f\n", newCost);
		else
			System.out.println("Error: ticket number not found in database.");	
	}
	
	public static void main_deleteRepair() {
		int ticketNum;
		boolean success;
		System.out.print("Enter repair ticket number: ");
		ticketNum = input.nextInt();
		success = shop.deleteRepair(ticketNum);
		if (success)			
			System.out.println("Repair ticket #" + ticketNum + " deleted from database.");
		else
			System.out.println("Error: ticket number not found in database.");	
	}		
	
	public static void main_deleteAllRepairsForCar() {
		String vin;
		boolean success;
		input.nextLine();
		System.out.print("Enter VIN: ");
		vin = input.nextLine();
		success = shop.deleteAllRepairsForCar(vin);
		if (success)
			System.out.println("Deleted repairs for car with VIN " + vin + " from database.");
		else
			System.out.println("Error: VIN not found in database.");	
	}	
	
	public static void main_deleteCarAndRepairs() {
		String vin;
		boolean success;
		input.nextLine();
		System.out.print("Enter VIN: ");
		vin = input.nextLine();
		success = shop.deleteCarAndRepairs(vin);
		if (success)
			System.out.println("Deleted car with VIN " + vin + " from database.");
		else
			System.out.println("Error: VIN not found in database.");	
	}	
}

