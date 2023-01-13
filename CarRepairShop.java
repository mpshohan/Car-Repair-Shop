// Md Patwary
// Car Repair Shop class


public class CarRepairShop {
	
	private int carsCount = 0;
	private int ticketCount = 0;
	private Car [] vehicle = new Car [carsCount +1];
	private RepairTicket [] tickets = new RepairTicket[ticketCount+1];
	

	public int addNewCar(String vin, String make, int year) {
		Car [] c = new Car [vehicle.length+1];
		for (int i = 0; i<vehicle.length; i++) {
			Car car = vehicle[i];
			if (car != null) {
				c[i] = new Car(car);
			}
		}
		
		String VIN;
		for (int i = 0; i<vehicle.length; i++) {
			if (vehicle[i] != null) {
				VIN = vehicle[i].getVIN();
				if ( VIN.equals(vin)) {
					return -1;
				}
			}
		}
		
		c[carsCount] = new Car (vin, make, year);
		carsCount++;
		vehicle = new Car [carsCount+1];
		for (int i =0; i<vehicle.length; i++) {
			Car c2 = c[i];
			if (c2 != null) {
				vehicle[i] = new Car (c2);
			}
		}
		return carsCount;
	}

	public int addRepairTicket(String vin, double cost, String description) {
		int count = 0;
		RepairTicket [] t = new RepairTicket[tickets.length+1];
		for (int i = 0; i<tickets.length; i++) {
			RepairTicket tick = tickets[i];
			if (tick != null) {
				t[i] = new RepairTicket(tick);
			}
		}
		String VIN;
		for (int i = 0; i<tickets.length; i++) {
			if (vehicle[i] != null) {
				VIN = vehicle[i].getVIN();
				if (VIN.equals(vin)) {
					count++;
				}
			}
		}
		if (count == 0) {
			return -1;
		}
		t[count] = new RepairTicket(vin, cost, description);
		count++;
		tickets = new RepairTicket[count+1];
		for (int i = 0; i<tickets.length; i++) {
			RepairTicket tckt = t[i];
			if (tckt != null) {
				tickets[i] = new RepairTicket(tckt);
			}
		}
		return count;
	}

	public double getRepairCost(int ticketNum) {
		if (ticketNum > tickets.length) {
			return -1;
		}
		if (ticketNum <= 0) {
			return -1;
		}
		if (tickets[ticketNum-1] != null) {
			double cost = tickets[ticketNum - 1].getCost();
			return cost;
		}
		else return -1;
	}

	public double getTotalRepairCosts(String vin) {
		int noCar = 0;
		for (int i = 0; i<vehicle.length; i++) {
			if (vehicle[0] == null) {
				if(vehicle[i].getVIN().equals(vin)) {
					noCar++;				}
			}
		}
	
	if ( noCar == 0 ) {
		return -1;
	}
	double cost = 0;
	for (int i = 0; i<tickets.length-1; i++) {
		if (tickets[i] != null) {
			if (tickets[i].getVin().equals(vin)) {
				cost += tickets[i].getCost();
			}
		}
	}
	return cost;
}

	public String getWorstCarMake() {
		int checkCar = 0, count = 0, tempCount;
		String vin, element = "", tempElement, make ="";
		String [] arraytick = new String [tickets.length-1];
		for (int i = 0; i<vehicle.length-1; i++) {
			if (vehicle[i] != null) {
				checkCar++;
			}
		}
			if (checkCar == 0) {
				return null;
			}
		for (int i = 0; i<tickets.length-1; i++) {
			if (tickets[i] != null) {
				vin = tickets[i].getVin();
				arraytick[i] = vin;
			}
		}
		for (int i = 0; i<arraytick.length;i++) {
			tempElement = arraytick[i];
			tempCount = 0;
			for (int j = 0; j<arraytick.length; i++) {
				if (arraytick[j].equals(tempElement)) {
					tempCount++;
				}
				if ( tempCount > count) {
					element = tempElement;
					count = tempCount;
				}
			}
		}
		for (int i =0; i<vehicle.length -1; i++) {
			if (vehicle[i] != null) {
				String VIN = vehicle[i].getVIN();
				if (VIN.equals(element)) {
					make = vehicle[i].getMake();
					break;
				}
			}
		}
		return make;
	}

	public boolean updateRepairCost(int ticketNum, double newCost) {
		if (ticketNum > tickets.length) {
			return false;
		}
		if (ticketNum <= 0) {
			return false;
		}
		if (tickets[ticketNum-1] != null) {
			tickets[ticketNum-1].setCost(newCost);
			return true;
		}
		else return false;
	}

	public boolean deleteRepair(int ticketNum) {
		if (ticketNum > tickets.length) {
			return false;
		}
		if (ticketNum <=0) {
			return false;
		}
		if (tickets[ticketNum -1] != null) {
			tickets[ticketNum -1] = null;
			return true;
		}
		else return false;
	}

	public boolean deleteAllRepairsForCar(String VIN) {
		int noCar = 0;
		for (int i = 0; i<vehicle.length-1;i++) {
			if (vehicle[0] == null) {
				if (vehicle[i].getVIN().equals(VIN)) {
					noCar++;
				}
			}
		}
		if (noCar ==0) {
			return false;
		}
		for (int i =0; i<tickets.length-1;i++) {
			if (tickets[i] != null) {
				if (tickets[i].getVin().equals(VIN)) {
					tickets[i] = null;
				}
			}
		}
		return true;
	}

	public boolean deleteCarAndRepairs(String VIN) {
		int noCar = 0;
		for (int i = 0; i<vehicle.length; i++) {
			if (vehicle[i] != null) {
				if (vehicle[i].getVIN().equals(VIN)) {
					noCar++;
				}
			}
		}
		if (noCar == 0) {
			return false;
		}
		for (int i = 0; i<vehicle.length-1; i++) {
			if (vehicle[i] != null) {
				if (vehicle[i].getVIN().equals(VIN)) {
					vehicle[i]=null;
				}
			}
		}
		for (int i = 0; i<tickets.length-1; i++) {
			if (tickets[i] != null) {
				if (tickets[i].getVin().equals(VIN)) {
					tickets[i] = null;
				}
			}
		}
		return true;
	}
}

class Car {
	String VIN;
	String make;
	int model;
	
	public Car (String vin, String make1, int model1) {
		VIN = vin;
		make = make1;
		model = model1;
	}
	
	public String getVIN () {
		return VIN;
	}
	public String getMake() {
		return make;
	}
	public int getModel() {
		return model;
	}
	
	public Car (Car car) {
		this(car.getVIN(), car.getMake(), car.getModel());
	}

}

class RepairTicket {
	private String VIN;
	private double cost;
	private String type;
	
	public RepairTicket (String vin, double price, String type1) {
		VIN = vin;
		cost = price;
		type = type1;
	}
	
	public String getVin() {
		return VIN;
	}
	public double getCost () {
		return cost;
	}
	public void setCost(double newcost) {
		cost = newcost;
	}
	public String getType() {
		return type;
	}
	
	public RepairTicket(RepairTicket ticket) {
		this(ticket.getVin(), ticket.getCost(), ticket.getType());
	}

}

