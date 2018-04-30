package Main;

public class Customer {
int arrivalTime,serviceTime,departureTime=0;

	public Customer(int arrival,int timeNeeded) {
		this.arrivalTime= arrival;
		this.serviceTime=timeNeeded;
		
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(int departureTime) {
		this.departureTime = departureTime;
	}
	
	
}
