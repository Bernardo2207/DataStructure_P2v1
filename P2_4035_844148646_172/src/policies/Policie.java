//Bernardo Jr. Sein Acevedo
//84-14-8646
//Section 030

package policies;
//Basic Mehods that every Policie should have.
public interface Policie {
	//Returns the name of the Policy
	String getPolicy();
	//Number of Postr or servers(Cajeros) in a Policy.
	int postNumbers();
	//Returns the time.
	double getTime();
	//Average waiting time of customers to be atended.
	double showAverageTime();
	//Number of People that Started rece
	double getM();
	int numberOfCustomer();
}
