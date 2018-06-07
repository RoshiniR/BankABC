import java.util.*;

public class Main
{

	public static HashMap<String, Person> personMap = new HashMap<String, Person>();
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter number of customers for ABC bank:");
		int numOfAccs = scan.nextInt();
		
		for(int i=0;i<numOfAccs;i++)
		{
		    System.out.println("Enter Customer's Name:");
		    String custName = scan.next();
		    Person person;
		    if (!personMap.containsKey(custName)){
		    	person = new Person(custName);
		    }
		    else {
		    	person = personMap.get(custName);
		    }
		    
		    System.out.println("Enter Account type(S for Savings and C for Checking Account):");
		    String accType = scan.next();
		    System.out.println("Enter Current Balance Amount present in the account:");
		    float init_balance = scan.nextFloat();
		    System.out.println("Enter Date in the format(yyyy-mm-dd) from which the transactions need to begin:");
		    String createDtTime = scan.next();
		    createDtTime += " 00:00:00";
		    //System.out.println("Ok");
		    System.out.println("Enter Account number - an integer");
		    int accno = scan.nextInt();
		    if (accType.equals("S")) {
		    	person.addSavingsAcc(init_balance, createDtTime, accno);
		    	System.out.println("Enter number of transactions:");
			    int numOfTrans = scan.nextInt();
			    System.out.println("Enter transaction in the format:DateTime(yyyy-mm-dd_hh:mm:ss) Transaction_Amount Nature_of_Transaction(deposit/withdraw)");
			    
			    for(int j=0;j<numOfTrans;j++)
			    {
			        String strDate = scan.next();
			        float amt = scan.nextFloat();
			        String nature = scan.nextLine().replaceAll(" ", "");
			        strDate = strDate.replace("_", " "); 
			        person.addSavingsAccTrans(accno, nature, amt, strDate);      
			    }
			    person.printAccountDetails(accno);

		    }
		    else if (accType.equals("C")) {
		    	person.addChckAcc(init_balance, createDtTime, accno);
		    	System.out.println("Enter number of transactions:");
			    int numOfTrans = scan.nextInt();
			    System.out.println("Enter transaction in the format:DateTime(yyyy-mm-dd_hh:mm:ss) Transaction_Amount Nature_of_Transaction(deposit/withdraw)");
			    
			    for(int j=0;j<numOfTrans;j++)
			    {
			        String strDate = scan.next();
			        float amt = scan.nextFloat();
			        String nature = scan.nextLine().replaceAll(" ", "");
			        strDate = strDate.replace("_", " "); 
			        person.addCheckingAccTrans(accno, nature, amt, strDate);      
			    }
			    person.printAccountDetails(accno);
			    
		    }
		    
		}
	scan.close();	
	}
}
