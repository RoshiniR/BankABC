import java.util.*;

public class Main
{
	enum accountType{
		savings,checking;
	}
	enum natureOfTrans{
		deposit,withdraw;
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter number of customers for ABC bank:");
		int numOfCust = scan.nextInt();
		Person customer = new Person(numOfCust);
		
		for(int i=0;i<numOfCust;i++)
		{
		    System.out.println("Enter Customer's Name:");
		    String custName = scan.nextLine();
		    System.out.println("Enter Account type(savings,checking):");
		    String accType = scan.nextLine();
		    while(!checkValidAccType(accType,accountType))
		    	{
			    System.out.println("Invalid input.Re-enter Account Type(savings,checking):";
			    accType=scan.nextLine();
		    	}
		    String.out.println("Enter Current Balance Amount present in the account:");
		    float balance = scan.nextFloat();
		    String.out.println("Enter Date of Account Creation(dd/mm/yyyy):");
		    String dateoOfAccCreation = scan.nextLine();
		    String.out.println("Enter number of transactions:");
		    int numOfTrans = scan.nextInt();
		    String.out.println("Enter transaction in the format:Date(dd/mm/yyyy) Transaction_Amount Nature_of_Transaction(deposit/withdraw):");
		    
		    for(int j=0;j<numOfTrans;j++)
		    {
		        String strDate = scan.next();
		        float amt = scan.nextFloat();
		        String nature = scan.nextLine();
		        while(!checkValidAccType(nature,natureOfTrans))
		    	{
			    System.out.println("Invalid input.Re-enter Nature_of_Transaction(deposit/withdraw):";
			    accType=scan.nextLine();
		    	}
		    }
		}
	scan.close();	
	}
	
	public int checkValidAccType(String s, accountType at)
	{
		for(accountType accTypes:accountType.values())
		{
			if(accTypes.name().equals(s.toLowerCase()))
			{
				return 1;
			}
		}
		return 0;
	}

	public int checkValidTransactionType(String s, natureOfTrans not)
	{
		for(natureOfTrans tranTypes:natureOfTrans.values())
		{
			if(tranTypes.name().equals(s.toLowerCase()))
			{
				return 1;
			}
		}
		return 0;
	}		
}
