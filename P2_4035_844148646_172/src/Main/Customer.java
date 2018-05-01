package Main;

public class Customer {
int arrivalTime,serviceTime,timeServed=0,departureTime=0;

	public Customer(int arrival,int timeNeeded) {
		this.arrivalTime= arrival;
		this.serviceTime=timeNeeded;
		
	}
	public int getTimeServed() {
		return timeServed;
	}
public void setTimeServed(int t) {
	timeServed=t;
	
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
