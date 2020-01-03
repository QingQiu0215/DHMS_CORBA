/*
 * This is final version of DHMS_CORBA. 
 */

package Client;
import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import OperationsApp.Operations;
import OperationsApp.OperationsHelper;

public class Client extends Thread implements Runnable{
	static String clientID;
	static String patientID;
	static String oldAppointmentID;
	static String oldAppointmentType;
	static String newAppointmentID;
	static String newAppointmentType;
	
	static ORB orbMTL=null;
	static ORB orbQUE=null;
	static ORB orbSHE=null;
	Operations MTLobj =null;
	Operations QUEobj =null;
	Operations SHEobj =null;
	static boolean repeat=true;
	public Client() {
		
		try {
			//-ORBInitialPort 1050 -ORBInitialHost localhost
			org.omg.CORBA.Object objRefMTL = orbMTL.resolve_initial_references("NameService");
			NamingContextExt ncRefMTL = NamingContextExtHelper.narrow(objRefMTL);
			MTLobj = (Operations) OperationsHelper.narrow(ncRefMTL.resolve_str("MTLFunctions"));
			
			org.omg.CORBA.Object objRefQUE = orbQUE.resolve_initial_references("NameService");
			NamingContextExt ncRefQUE = NamingContextExtHelper.narrow(objRefQUE);
			QUEobj = (Operations) OperationsHelper.narrow(ncRefQUE.resolve_str("QUEFunctions"));
			
			org.omg.CORBA.Object objRefSHE = orbSHE.resolve_initial_references("NameService");
			NamingContextExt ncRefSHE = NamingContextExtHelper.narrow(objRefSHE);
			SHEobj = (Operations) OperationsHelper.narrow(ncRefSHE.resolve_str("SHEFunctions"));
		}catch (Exception e) {
			System.out.println("Client exception: " + e);
			e.printStackTrace();
		}
		
	}
	public Client(String clientID, String patientID,String oldAppointmentID, String oldAppointmentType,String newAppointmentID, String newAppointmentType) {

		try {
			//-ORBInitialPort 1050 -ORBInitialHost localhost
			org.omg.CORBA.Object objRefMTL = orbMTL.resolve_initial_references("NameService");
			NamingContextExt ncRefMTL = NamingContextExtHelper.narrow(objRefMTL);
			MTLobj = (Operations) OperationsHelper.narrow(ncRefMTL.resolve_str("MTLFunctions"));
			
			org.omg.CORBA.Object objRefQUE = orbQUE.resolve_initial_references("NameService");
			NamingContextExt ncRefQUE = NamingContextExtHelper.narrow(objRefQUE);
			QUEobj = (Operations) OperationsHelper.narrow(ncRefQUE.resolve_str("QUEFunctions"));
			
			org.omg.CORBA.Object objRefSHE = orbSHE.resolve_initial_references("NameService");
			NamingContextExt ncRefSHE = NamingContextExtHelper.narrow(objRefSHE);
			SHEobj = (Operations) OperationsHelper.narrow(ncRefSHE.resolve_str("SHEFunctions"));
		}catch (Exception e) {
			System.out.println("Client exception: " + e);
			e.printStackTrace();
		}
		this.clientID=clientID;
		this.patientID=patientID;
		this.oldAppointmentID=oldAppointmentID;
		this.oldAppointmentType=oldAppointmentType;
		this.newAppointmentID=newAppointmentID;
		this.newAppointmentType=newAppointmentType;
	}
	public static void main(String args[]) throws Exception
	{
		orbMTL = ORB.init(args, null);
		orbQUE = ORB.init(args, null);
		orbSHE = ORB.init(args, null);
		Client client=new Client();
		AdminClient admin=new AdminClient();
		PatientClient patient=new PatientClient();
		Scanner keyboard=new Scanner(System.in);		
		System.out.println("*** Welcome to use DHMS ***");
		System.out.println("");
		System.out.println("The following are all Admin:");
		admin.outputClientInfo();
		System.out.println("");
		System.out.println("The following are all Patient:");
		patient.outputClientInfo();
		System.out.println("");
		System.out.println("\nAre you a new client? \n1.yes\n2.no");
		
		int choose=0;
		boolean boundOk=true;
		String clientID="";
		choose=keyboard.nextInt();

		while(boundOk) {
			if(choose==1){
				while(repeat) {
					Scanner keyboardForThreadTest=new Scanner(System.in);
					System.out.println("Do you want to test multiple threads? yes/no");
					String chooseForThreadTest=keyboardForThreadTest.nextLine();
					if(chooseForThreadTest.equalsIgnoreCase("yes")) {
						runThread();
					}
					
					
					System.out.println("Please sign up your ClientID:");
					Scanner keyboard2=new Scanner(System.in);
					String newClient=keyboard2.nextLine();
					if(newClient.charAt(3)=='A'){
						if(!admin.getAdminMap().containsValue(newClient))
							admin.addAdminMap(newClient);	
						
						admin.adminStart(newClient);
						System.out.println("Do you want to continue? yes/no");
						Scanner keyboard3=new Scanner(System.in);
						String continue3=keyboard3.nextLine();
						if(continue3.equalsIgnoreCase("yes"))
							repeat=true;
						else {
							System.out.println("Thanks for using DHMS.");
							repeat=false;
						}					
					}					
					else {
						if(!patient.getPatientMap().containsValue(newClient))
							patient.addPatientMap(newClient);
						
						patient.patientStart(newClient);
						System.out.println("Do you want to continue? yes/no");
						Scanner keyboard3=new Scanner(System.in);
						String continue3=keyboard3.nextLine();
						if(continue3.equalsIgnoreCase("yes"))
							repeat=true;
						else {
							System.out.println("Thanks for using DHMS.");
							repeat=false;
						}	
					}				
					boundOk=false;
				}
				
			}else if(choose==2){
				while(repeat) {
					Scanner keyboardForThreadTest=new Scanner(System.in);
					System.out.println("Do you want to test multiple threads? yes/no");
					String chooseForThreadTest=keyboardForThreadTest.nextLine();
					if(chooseForThreadTest.equalsIgnoreCase("yes")) {
						runThread();
					}
					
					System.out.println("Please enter your clientID:");
					Scanner keyboard4=new Scanner(System.in);
					clientID=keyboard4.nextLine();
					char type=clientID.charAt(3);		
					if(type=='A') {
						admin.adminStart(clientID);
						System.out.println("Do you want to continue? yes/no");
						Scanner keyboard5=new Scanner(System.in);
						String continue5=keyboard5.nextLine();
						if(continue5.equalsIgnoreCase("yes"))
							repeat=true;
						else {
							System.out.println("Thanks for using DHMS.");
							repeat=false;
						}
					}					
					else {
						patient.patientStart(clientID);
						System.out.println("Do you want to continue? yes/no");
						Scanner keyboard5=new Scanner(System.in);
						String continue5=keyboard5.nextLine();
						if(continue5.equalsIgnoreCase("yes"))
							repeat=true;
						else {
							System.out.println("Thanks for using DHMS.");
							repeat=false;
						}
					}					
					boundOk=false;
				}
				
			}else {
				System.out.println("Please select 1 or 2:");
				choose=keyboard.nextInt();
			}
		}


	}
	public static String setAppointmentID()
	{		
		System.out.println("Please enter the appointmentID:");
		String appointmentID="";
		Scanner keyboard=new Scanner(System.in);
		appointmentID=keyboard.nextLine();
		return appointmentID;
	}
	public static String setAppointmentType()
	{
		System.out.println("Please enter the appointmentType:\n1. Physician;\n2. Surgeon;\n3. Dental");
		String appointmentType="";
		int input=0;
		Scanner keyboard=new Scanner(System.in);
		input=keyboard.nextInt();
		switch(input) {
			case 1: appointmentType="Physician";break;
			case 2: appointmentType="Surgeon";break;
			case 3: appointmentType="Dental";break;
		}
		return appointmentType;
	}
	public int setAppointmentWeek()
	{
		System.out.println("Please enter the appointment week");
		int input=0;
		Scanner keyboard=new Scanner(System.in);
		input=keyboard.nextInt();
		return input;
	}
	public int setCapacity()
	{
		System.out.println("Please enter the capacity you want(should be less than the maximum capacity:3)");
		int input=0;
		boolean doLoop=false;
		Scanner keyboard=new Scanner(System.in);
		do {			
			input=keyboard.nextInt();
			if(input>3) {
				System.out.println("Should be less than the maximum capacity:3. Please enter again.");
				doLoop=true;
			}else {
				doLoop=false;
			}
		}while(doLoop);
		
		return input;
	}
	public static String setPatientID()
	{
		System.out.println("Please enter the patientID:");
		String patientID="";
		Scanner keyboard=new Scanner(System.in);
		patientID=keyboard.nextLine();
		return patientID;
	}
	public static String setClientID()
	{
		System.out.println("Please enter the ClientID:");
		String clientID="";
		Scanner keyboard=new Scanner(System.in);
		clientID=keyboard.nextLine();
		return clientID;
	}
	
	
	public static void runThread() {
		Scanner keyboardForThreadTest=new Scanner(System.in);
		System.out.println("How many threads you want to test?");
		int threadQty=keyboardForThreadTest.nextInt();
		Client [] client=new Client[threadQty];
		System.out.println("Please input information for all clients:");
		for(int i=0;i<client.length;i++) {
			System.out.println("Please input information for client: "+(i+1));
			String clientIDTemp=setClientID();
			String patientIDTemp=setPatientID();
			System.out.println("Please type the old appointment ID and type:");
			String oldAppointmentIDTemp=setAppointmentID();
			String oldAppointmentTypeTemp=setAppointmentType();
			System.out.println("Please type the new appointment ID and type:");
			String newAppointmentIDTemp=setAppointmentID();
			String newAppointmentTypeTemp=setAppointmentType();
			client[i]=new Client(clientIDTemp,patientIDTemp,oldAppointmentIDTemp,oldAppointmentTypeTemp,newAppointmentIDTemp,newAppointmentTypeTemp);
		}
		System.out.println("Starting the test...");
		for(int i=0;i<client.length;i++) {
			//System.out.println(client[i].clientID);
			client[i].start();
		}
	}
	@Override
	public void run() {

		MTLobj.swapAppointment(clientID, patientID, oldAppointmentID, oldAppointmentType, newAppointmentID, newAppointmentType);
		
	}
}
