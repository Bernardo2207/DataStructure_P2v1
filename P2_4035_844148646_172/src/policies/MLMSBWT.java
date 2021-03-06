//Bernardo Jr. Sein Acevedo
//84-14-8646
//Section 030
package policies;

import java.util.LinkedList;

import Main.Clerks;
import Main.Customer;

//Multiple Lines Multiple Servers and Balanced Waiting Times 
public class MLMSBWT  implements Policie {
	/*This scheme is similar to the previous two. Here, there is a monitor too, 
	 * many servers and one waiting line per server. No line crossing is allowed.
	 * But in this case, the monitor decides which line the new arriving customer 
	 * has  to go to. The decision is based on the total expected time on each line. 
	 * The new customer will be assigned to the first line having minimum total waiting 
	 * time at that moment. In case of ties, the line with minimum index wins.
	 * To determine the expected time, the monitor always keeps, for each line,
	 * the sum of the service times of all those persons in the line, as well as 
	 * the remaining time for service of the person who is being served at the moment, if any. */
	
	private int posts,nCustomers;
	private double time=0;
	private double averageTime=0;
	private LinkedList<Customer> customers;
	private LinkedList<Customer> waitingList;
	private Clerks[]clerks;
	private int[] lineTime;
	private LinkedList<Customer>toCompare=new LinkedList<>();


	public MLMSBWT(LinkedList<Customer> customers,int posts) {
		//To maintain the original customers List the list is copied.
		this.customers=copy(customers);
		this.nCustomers=this.customers.size();
		this.posts=posts;
		this.clerks=new Clerks[posts];
		this.lineTime=new int[posts];
		this.waitingList=new LinkedList<>();
		createClerks();
		Simulate();
	}
//This method runs until every customer has been attended. 
	private void Simulate() {
		while(!Finished()) {		
		for(Customer c: customers) {
			if(c.getArrivalTime()==time) {
				waitingList.add(c);
			}
		}
			addToPostDisponible();
			Serve();
			//time begins at 0.
			time++;
			
		}	
	}

//Based on the Policy each customer is assgined to a server or clerk.
	public void addToPostDisponible() {
		//Index of the clerks with the less people.
		while(!waitingList.isEmpty()) {
		int lowIndex=lineTime[0];
		int index=0;
	
		for(int i=1;i<lineTime.length;i++) {
			if(lineTime[i]<lowIndex)
				index=i;}
				Customer toAdd=waitingList.removeFirst();
				clerks[index].addCustomer(toAdd);
				lineTime[index]=lineTime[index]+toAdd.getServiceTime();
				
			}
		}
		
	public void Serve() {
		for(Clerks c: clerks) {
		if(c.getCustomers()!=0) {
			if(c.getCustomers()!=0) {
				if(!toCompare.contains(c.getFirst())) {toCompare.addLast(new Customer(c.getFirst().getArrivalTime(),c.getFirst().getServiceTime()));}
				c.getFirst().setTimeServed(c.getFirst().getTimeServed()+1);
				c.getFirst().setDepartureTime(c.getFirst().getDepartureTime()+1);
				lineTime[c.getID()]=lineTime[c.getID()]-1;
			}
			if(c.getFirst().getTimeServed()==c.getFirst().getServiceTime()) {
				
				Customer tr=c.removeCustomer();
				tr.setDepartureTime((int)(time+1)-tr.getArrivalTime()-tr.getDepartureTime());
				averageTime=averageTime+tr.getDepartureTime();
				customers.remove(tr);}
			}
		}
	}
	private LinkedList<Customer> copy(LinkedList<Customer>c) {
		
		LinkedList<Customer> copy= new LinkedList<>();
		for(Customer x: c) {
			copy.add(new Customer(x.getArrivalTime(),x.getServiceTime()));
		}
		return copy;
	}

	public double showAverageTime() {
		return averageTime/nCustomers;
	}
	public int numberOfCustomer() {
		return nCustomers;
	}
	public void createClerks() {
		for(int i=0;i<posts;i++) {
			clerks[i]=new Clerks(i);
			lineTime[i]=0;
		}
	}
	public String getPolicy() {
		return "MLMSBWT";
	}
	public int postNumbers() {
		return posts;
	}
	public double getTime() {
		return time;
	}
	public boolean Finished() {
		return customers.isEmpty();
	}
	public double getM() {
		int j=0;
		int count=0;
		for(Customer c:toCompare) {
			for(int i=j;i<toCompare.size();i++) {
				if(c.getArrivalTime()>toCompare.get(i).getArrivalTime()) {
					count++;
				}
			}
			j++;
		}
		return (count/numberOfCustomer());
		}
}
