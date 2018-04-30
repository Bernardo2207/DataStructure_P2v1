package policies;

//Multiple Lines Multiple Servers and Balanced Waiting Times 
public class MLMSBWT  implements Policie {
	int posts;
	/*This scheme is similar to the previous two. Here, there is a monitor too, 
	 * many servers and one waiting line per server. No line crossing is allowed.
	 * But in this case, the monitor decides which line the new arriving customer 
	 * has  to go to. The decision is based on the total expected time on each line. 
	 * The new customer will be assigned to the first line having minimum total waiting 
	 * time at that moment. In case of ties, the line with minimum index wins.
	 * To determine the expected time, the monitor always keeps, for each line,
	 * the sum of the service times of all those persons in the line, as well as 
	 * the remaining time for service of the person who is being served at the moment, if any. */

	@Override
	public String getPolicy() {
		// TODO Auto-generated method stub
		return "MLMSBET";
	}

	@Override
	public int postNumbers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double showAverageTime() {
		// TODO Auto-generated method stub
		return 0;
	}

}
