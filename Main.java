import java.util.*;

public class Main
{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter number of customers for ABC bank:");
		int numOfCust = scan.nextInt();
		Person customer = new Person(numOfCust);
		
		for(int i=0;i<numOfCust;i++)
		{
		    System.out.println("Enter Customer's Name:");
		    String custName = scan.nextLine();
		    System.out.println("Enter Account type:");
		    String accType = scan.nextLine();
		    String.out.println("Enter Current Balance Amount present in the account:");
		    float balance = scan.nextFloat();
		    
		    String.out.println("Enter number of transactions:");
		    int numOfTrans = scan.nextInt();
		    String.out.println("Enter transaction in the format:Date(dd/mm/yyyy) Transaction_Amount Nature_of_Transaction(deposit/withdraw)");
		    
		    for(int j=0;j<numOfTrans;j++)
		    {
		        String strDate = scan.next();
		        float amt = scan.nextFloat();
		        String nature = scan.nextLine();
		        
		    }
		}
	scan.close();	
	}
}
