////Bernardo Jr. Sein Acevedo
//84-14-8646
//Section 030
package policies;

import java.util.LinkedList;

import Main.Clerks;
import Main.Customer;

//Multiple Lines Multiple Servers
public class MLMS  implements Policie{

	/*Under this policy, each service post has its own waiting line (one line per server).
	 *  Once a person enters a waiting line, that person cannot transfer to another line, 
	 *  even if one becomes empty. When a new person arrives looking for service, the person
	 *   will choose the first line that has minimum number of persons waiting, as per the indexes
	 *  identifying the corresponding service posts. If a line becomes empty, then the server 
	 *  there remains idle until a new customer arrives and selects that particular line. 
	 *  (This happens often in lines for cars to pay at the toll gate on a highway; once you are
	 *  in a line, it is hard to change to another, even if one becomes empty.)
*/
	
	int posts,nCustomers;
	double time=0;
	private double averageTime=0;
	private LinkedList<Customer> customers;
	private LinkedList<Customer> waitingList;
	private Clerks[]clerks;
	private LinkedList<Customer>toCompare=new LinkedList<>();


	public MLMS(LinkedList<Customer> customers,int posts) {
		//pre:Customers list must be ordered.
		
		this.customers=copy(customers);
		this.nCustomers=this.customers.size();
		this.posts=posts;
		this.clerks=new Clerks[posts];
		this.waitingList=new LinkedList<>();
		createClerks();
		Simulate();
	}

	private void Simulate() {
		while(!Finished()) {
			
		for(Customer c: customers) {
			if(c.getArrivalTime()==time) {
				waitingList.add(c);
			}
		}
			addToPostDisponible();
			Serve();
			time++;
			
		}	
	}


	private void addToPostDisponible() {
		//Index of the clerks with the less people.
		while(!waitingList.isEmpty()) {
		int lowIndex=clerks[0].getCustomers();
		int index=0;
	
		for(int i=1;i<clerks.length;i++) {
			if(clerks[i].getCustomers()<lowIndex)
				index=i;
		}
				clerks[index].addCustomer(waitingList.removeFirst());
			}
		}
		
	
	private void Serve() {
		for(Clerks c: clerks) {
		if(c.getCustomers()!=0) {
			if(c.getCustomers()!=0) {
				if(!toCompare.contains(c.getFirst())) {toCompare.addLast(new Customer(c.getFirst().getArrivalTime(),c.getFirst().getServiceTime()));}
				c.getFirst().setTimeServed(c.getFirst().getTimeServed()+1);
				c.getFirst().setDepartureTime(c.getFirst().getDepartureTime()+1);
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
	private void createClerks() {
		for(int i=0;i<posts;i++) {
			clerks[i]=new Clerks(i);
		}
	}
	public String getPolicy() {
		return "MLMS";
	}
	public int postNumbers() {
		return posts;
	}
	public double getTime() {
		return time;
	}
	private boolean Finished() {
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
