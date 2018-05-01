//Bernardo Jr. Sein Acevedo
//84-14-8646
//Section 030
package Main;

import java.util.LinkedList;

public class Clerks {
private LinkedList<Customer> fila=new LinkedList<>();
private int id;
private Customer c;
	public Clerks(int id,LinkedList<Customer> fila) {
		this.fila=fila;
		this.id=id;
	}
	public Clerks(int id,Customer c) {
		this.id=id;
		this.c=c;
		
	}
	public Clerks(int id) {
		this.id=id;
	}
	public int getCustomers() {
		return  fila.size();
	}
	public void addCustomer(Customer c) {
		fila.add(c);
		
	}
	public Customer getFirst() {
		return fila.peekFirst();
	}
	public Customer removeCustomer() {
		return fila.removeFirst();
	}
	public Customer removeLast() {
		return fila.pollLast();
	}
	public int getID() {
		return id;
	}
}
