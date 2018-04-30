package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

import policies.MLMS;
import policies.MLMSBLL;
import policies.MLMSBWT;
import policies.Policie;
import policies.SLMS;

public class MainReader {

	public static void main(String[] args) throws FileNotFoundException {
		
		Run();

	}
		
// customer, clerk, event, arrival event, service-starts event, service-completed event, 
//transfer event, monitor, line of service, collection of events, collection
		//of customers, collection of lines
	
		public static LinkedList<LinkedList<Customer>> theData() throws FileNotFoundException{
			
		LinkedList<LinkedList<Customer>>alpha= new LinkedList<>();
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
				throw new FileNotFoundException("Input file not found.");
			} 
			
			
			for(String s:todo) {
				LinkedList<Customer>cl= new LinkedList<>();
				System.out.println(s);
				Scanner parameters2;
				
					try {
						File target=new File(parentDirectory, s);
					parameters2 = new Scanner(target);
					//Informacion dentro de los documentos.
					while(parameters2.hasNext()) {
						
						String data= parameters2.nextLine();
						System.out.println(data.length());
						
						//System.out.println(data);
						FileReader y= new FileReader(target);
						
						StringTokenizer st= new StringTokenizer(data," ");
						if(st.countTokens()!=2) {
							throw new IllegalArgumentException("\r\n" + "Input file does not meet the expected format or it is empty.\r\n");
						}
						try {
						cl.add(new Customer(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));}
						catch(NumberFormatException e) {
							throw new NumberFormatException("\r\n" + "Input file does not meet the expected format or it is empty.\r\n");
						}
					}
						}catch (FileNotFoundException e) {
							throw new FileNotFoundException("Input file not found.");
						} 
				
				
				alpha.add(cl);
				
			}
			return alpha;
		}
		public static void Run() throws FileNotFoundException { 
			int file=0;
			NumberFormat f=new DecimalFormat("0.00");
			LinkedList<LinkedList<Customer>>alpha= theData();
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
				s=s.replace(".txt", "_OUT.txt");
				System.out.println(s);
			PrintStream out = new PrintStream(new File("inputFiles", s));
			
			LinkedList<Policie> d =new LinkedList<>();
			d.add(new SLMS(alpha.get(file),1));
			d.add(new SLMS(alpha.get(file),3));
			d.add(new SLMS(alpha.get(file),5));
			
			d.add(new MLMS(alpha.get(file),1));
			d.add(new MLMS(alpha.get(file),3));
			d.add(new MLMS(alpha.get(file),5));
			
			d.add(new MLMSBLL(alpha.get(file),1));
			d.add(new MLMSBLL(alpha.get(file),3));
			d.add(new MLMSBLL(alpha.get(file),5));
			
			d.add(new MLMSBWT(alpha.get(file),1));
			d.add(new MLMSBWT(alpha.get(file),3));
			d.add(new MLMSBWT(alpha.get(file),5));
			
	
	
			for(int i=0;i<d.size();i++) {
			out.printf(d.get(i).getPolicy()+" "+d.get(i).postNumbers()+":"+d.get(i).getTime()+"  "+f.format(d.get(i).showAverageTime())+" "+f.format(d.get(i).getM())); 
			out.println();
			if(i!=0 && (i+1)%3==0)
				out.println();
			}
			
			file++;
			out.close();
			}
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

