package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

import policies.MLMS;
import policies.MLMSBLL;
import policies.Policie;
import policies.SLMS;

public class MainReader {

	public static void main(String[] args) {
		//Instace of Deque
		LinkedList<LinkedList<Customer>>alpha= theData();
		LinkedList<Customer>x= new LinkedList<>();
		
		SLMS q= new SLMS(alpha.get(2),1);
		SLMS q2= new SLMS(alpha.get(2),3);
		SLMS q3= new SLMS(alpha.get(2),5);
		
		q.Simulate();
		q2.Simulate();
		q3.Simulate();
		
		showResult(q);
		showResult(q2);
		showResult(q3);
	}
		
// customer, clerk, event, arrival event, service-starts event, service-completed event, 
//transfer event, monitor, line of service, collection of events, collection
		//of customers, collection of lines
	public static void showResult(Policie q) {
		System.out.println(q.getPolicy()+" "+q.postNumbers()+" :"+q.getTime()+"  "+q.showAverageTime());
	}
	
		public static LinkedList<LinkedList<Customer>> theData(){
		LinkedList<LinkedList<Customer>>alpha= new LinkedList<>();
		//TEST
		
			String parentDirectory; 		
			parentDirectory = "inputFiles"; 
			Scanner parameters;
			ArrayList<String>todo=new ArrayList<>();
			try {
				parameters = new Scanner(new File(parentDirectory, "dataFiles.txt"));
				while(parameters.hasNext()) {
					String s= parameters.nextLine(); 
					todo.add(s);
					}
			} catch (FileNotFoundException e) {
				System.out.println("No file");
				e.printStackTrace();
			} 
			
			
			for(String s:todo) {
				LinkedList<Customer>cl= new LinkedList<>();
				
				Scanner parameters2;
				try {
					
					parameters2 = new Scanner(new File(parentDirectory, s));
					//Informacion dentro de los documentos.
					while(parameters2.hasNext()) {
						
						String data= parameters2.nextLine(); 
						System.out.println(data);
						
						StringTokenizer st= new StringTokenizer(data," ");
						cl.add(new Customer(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
						
						}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("data is Missing");
					e.printStackTrace();
				} 
				
				alpha.add(cl);
				
			}
			return alpha;
		}
				

//				}
//				double ave=0;
//				for(int i=0;i<terminatedJobs.size();i++) {
//					ave=ave+(terminatedJobs.get(i).getDepartureTime()-terminatedJobs.get(i).getArrivalTime());
//				}
//				System.out.printf("Average Time: %.2f",(ave/(terminatedJobs.size())));
//			
//				

		}

