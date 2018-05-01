//Bernardo Jr. Sein Acevedo
//84-14-8646
//Section 030
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
	//Once the program runs it will execute this method and Run every Simulation.	
		Run();

	}
	
	
	
	
	public static void Run() throws FileNotFoundException { 
		int file=0;
		//To format the output double values to tow decimal points.
		NumberFormat f=new DecimalFormat("0.00");
		LinkedList<LinkedList<Customer>>inputDataList= theData();
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
			e.printStackTrace();
		} 
		
		
		for(String s:todo) {
			s=s.replace(".txt", "_OUT.txt");
			
		PrintStream out = new PrintStream(new File("inputFiles", s));
		
		LinkedList<Policie> d =new LinkedList<>();
		d.add(new SLMS(inputDataList.get(file),1));
		d.add(new SLMS(inputDataList.get(file),3));
		d.add(new SLMS(inputDataList.get(file),5));
		
		d.add(new MLMS(inputDataList.get(file),1));
		d.add(new MLMS(inputDataList.get(file),3));
		d.add(new MLMS(inputDataList.get(file),5));
		
		d.add(new MLMSBLL(inputDataList.get(file),1));
		d.add(new MLMSBLL(inputDataList.get(file),3));
		d.add(new MLMSBLL(inputDataList.get(file),5));
		
		d.add(new MLMSBWT(inputDataList.get(file),1));
		d.add(new MLMSBWT(inputDataList.get(file),3));
		d.add(new MLMSBWT(inputDataList.get(file),5));
		

		out.println("Number of customers is: "+d.get(0).numberOfCustomer());
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
		public static LinkedList<LinkedList<Customer>> theData() throws FileNotFoundException{
			//List containing a list of all customers. each for every inputData File.
			LinkedList<LinkedList<Customer>>cData= new LinkedList<>();
			String parentDirectory; 		
			parentDirectory = "inputFiles"; 
			Scanner parameters;
			ArrayList<String>inputDataFiles=new ArrayList<>();
			
			//If the dataFiles.txt is not found it will throw an exception.
			try {
				parameters = new Scanner(new File(parentDirectory, "dataFiles.txt"));
				while(parameters.hasNext()) {
					String s= parameters.nextLine(); 
					inputDataFiles.add(s);
					}
			} catch (FileNotFoundException e) {
				throw new FileNotFoundException("Input file not found.");
			} 
			
			
			for(String s:inputDataFiles) {
				LinkedList<Customer>cl= new LinkedList<>();
				Scanner parameters2;
				
					try {
						File target=new File(parentDirectory, s);
					parameters2 = new Scanner(target);
					//Informacion dentro de los documentos.
					while(parameters2.hasNext()) {
						
						String data= parameters2.nextLine();												
						StringTokenizer st= new StringTokenizer(data," ");
						//if one line does not contain two integers separeted by a space an Exception will occur.
						
						if(st.countTokens()!=2) {
							throw new IllegalArgumentException("\r\n" + "Input file does not meet the expected format or it is empty.\r\n");
						}
						
						try {
							int v1=Integer.parseInt(st.nextToken());
							int v2=Integer.parseInt(st.nextToken());
							
						//if the input data for a customer does not meet the required parameters as stated in the ReadMe an exception will occur.
						if(v1>0 && v2>0)	
						cl.add(new Customer(v1,v2));
						else
							throw new NumberFormatException("\r\n" + "Input file does not meet the expected format or it is empty.\r\n");
 }
						catch(NumberFormatException e) {
							throw new NumberFormatException("\r\n" + "Input file does not meet the expected format or it is empty.\r\n");
						}
					}
						}catch (FileNotFoundException e) {
							throw new FileNotFoundException("Input file not found.");
						} 
				
				
				cData.add(cl);
				
			}
			return cData;
		}
	
		

		}

