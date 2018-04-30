package policies;

public interface Policie {
	//Returns the name of the Policy
	String getPolicy();
	//Number of Postr or servers(Cajeros) in a Policy.
	int postNumbers();
	//Returns the time.
	double getTime();
	//Average waiting time of customers to be atended.
	double showAverageTime();
	//Number of People that Started receiving service before someone who  arrived earlier.
	double getM();
}
