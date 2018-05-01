package policies;
import java.util.LinkedList;

import Main.Clerks;
import Main.Customer;
//Multiple Lines Multiple Servers and Balanced Line Lengths
public class MLMSBLL  implements Policie {
/*This case is similar to the previous policy but a customer is not stuck to the line
 *  he/she initially chooses as in that one. This scheme includes a monitor
 *   (a person or a specialized device) that decides when, and to what line, 
 *   a person already in one of the lines can transfer to. The initial selection
 *    is automatically done as in the previous policy. However, the monitor is
 *     there to control that persons move from one line to another in a controlled 
 *     manner by implementing the following policy. */

		int posts,nCustomers;
		double time=0;
		private double averageTime=0;
		private LinkedList<Customer> customers;
		private LinkedList<Customer> waitingList;
		private Clerks[]clerks;
		private LinkedList<Customer>toCompare=new LinkedList<>();


		public MLMSBLL(LinkedList<Customer> customers,int posts) {
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
				Monitor();
				
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
		private void Monitor() {
			int[] check=minAndMax();
			if(!(check[0]==check[1])&& (clerks[check[0]].getCustomers()>1 &&clerks[check[1]].getCustomers()>1)){
				clerks[check[0]].addCustomer(clerks[check[1]].removeLast());
				
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
			}
		}
		public String getPolicy() {
			return "MLMSBLL";
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
	

	private int[] minAndMax() {
		//Save the clerks customers lenght.
		int[] rt=new int[2];
		int[]larga= new int[clerks.length];
		for(int i=0;i<larga.length;i++) {
			larga[i]=clerks[i].getCustomers();
		}
		 int minValue = larga[0];
		 int index=0;
		    for (int i = 1; i < larga.length; i++) {
		        if (larga[i] < minValue) {
		            index = i;
		        }
		    }
		     rt[0]=index;
		     int maxValue = larga[0];
		     int index2=0;
		     for (int i = 1; i < larga.length; i++) {
		         if (larga[i] > maxValue) {
		             index2 = i;
		         }
		     }
		     rt[1]= index2;
		     
		     return rt;
		
		
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