import java.util.*;


import java.text.*;


public class SavingsAccount extends BankAccount 
{
    public final float interestRate = (float) 10.0;
    public float balance = (float) 0.0;

    public ArrayList<Transaction> transactionList; 
    public ArrayList<AccountStatementLine> accStmtList;
    
    SavingsAccount(float initbal, String createDatestr){

    	transactionList = new ArrayList<Transaction>();
    	Transaction tr = new Transaction(createDatestr, initbal, TransactionType.deposit);
    	transactionList.add(tr);
    	accStmtList = new ArrayList<AccountStatementLine>();

     }
    
    
    public int getNumDays(String month, int year) {
    	boolean leap = false;
    	int ret_val = 0;
    	if (year % 4 == 0)
    		leap = true;
    	if ((month.equals("02")||month.equals("2")) && leap) {
    		ret_val = 29;
    	}
    	if ((month.equals("02")||month.equals("2")) && !leap) {
    		ret_val = 28;
    	}
    	switch(month){
    	case "01": 
    	case "1":
    	case "03":
    	case "3":
    	case "05":
    	case "5":
    	case "07":
    	case "7":
    	case "09":
    	case "9":
    	case "11":
    		ret_val = 31; break;
    	case "04": 
    	case "4":
    	case "06":
    	case "6":
    	case "08":
    	case "8":
    	case "10":
    	case "12":
    		ret_val = 30; break;
    	}
  
    	return ret_val;
    }
    
    @SuppressWarnings("deprecation")
	public void evalInterestCompBalance() {
    	
    	Transaction[] sortedTransList = this.transactionList.toArray(new Transaction[transactionList.size()]);
		Arrays.sort(sortedTransList, Transaction.datecomparator);
    	
		for (Transaction tr : sortedTransList) {
			tr.printTransaction();
		}
		
		String fulldatestr;
		String currdaymonthstr;
		String todaymonthstr;
		float local_balance = 0;

		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String startMonthDate = dateFormat.format(sortedTransList[0].getDate());
        String endMonthDate = dateFormat.format(sortedTransList[sortedTransList.length-1].getDate());
        

		System.out.println("StartMonthDate:"+startMonthDate.substring(8, 10));

		int initial_day = Integer.parseInt(startMonthDate.substring(8, 10));
		String str_initial_month = startMonthDate.substring(5, 7);
		int initial_month = Integer.parseInt(str_initial_month);
		String str_initial_year = startMonthDate.substring(0, 4);
		int initial_year = Integer.parseInt(str_initial_year);
		
		String str_final_year = endMonthDate.substring(0, 4);
		String str_final_month = endMonthDate.substring(5, 7);
		
		int final_month = Integer.parseInt(str_final_month);

		int final_year = initial_year;
		if (final_month == 1 && (initial_month != final_month))
			final_year = initial_year + 1;
		int daysinInitialMonth = this.getNumDays(Integer.toString(initial_month), initial_year);
		int daysinFinalMonth = this.getNumDays(Integer.toString(final_month), final_year);
		
		ArrayList<String> dateList = new ArrayList<String>();
		
	    int daysLeftinInitialMonth = daysinInitialMonth - initial_day;
	    
	    for(int i = 0; i < daysLeftinInitialMonth+1; i++) {
	    	int ddate = initial_day + i;
	    	String str_ddate;
	    	if (Integer.toString(ddate).length() == 1)
	    		str_ddate = "0"+Integer.toString(ddate);
	    	else
	    		str_ddate = Integer.toString(ddate);
	    	String ddmmyyyy = str_initial_year+"-"+str_initial_month+"-"+str_ddate;
	    	dateList.add(ddmmyyyy);
	    }
	    if (initial_month != final_month) {
		    for(int i = 1; i <= daysinFinalMonth; i++) {
		    	String str_ddate;
		    	if (Integer.toString(i).length() == 1)
		    		str_ddate = "0"+Integer.toString(i);
		    	else
		    		str_ddate = Integer.toString(i);
		    	String ddmmyyyy = str_final_year+"-"+str_final_month+"-"+str_ddate;
		    	dateList.add(ddmmyyyy);
		    }
	    }
	    
	    
	    
	    float min_daily_balance = 0;
	    int transIndex = 0;
	    float monthlyInterestAccrued = 0;
	    for (String ddmmyyyy : dateList) {
	    	if(transIndex!=0)
	        {
	            min_daily_balance = this.balance;
	        }
	    	String trans_ddmmyyyy = null;
	    	if (transIndex < sortedTransList.length)
	    	{	
	    		startMonthDate = dateFormat.format(sortedTransList[transIndex].getDate());
	    		trans_ddmmyyyy = startMonthDate.substring(0, 10);
	    	}
	    	
	    	if (ddmmyyyy.equals(trans_ddmmyyyy))
	    	{

	    		while(true) {

	        		
	        		AccStatTransType actranstype = null;
	        		if (sortedTransList[transIndex].transType.equals(TransactionType.deposit)) {
	        			actranstype = AccStatTransType.deposit;
	        			balance += sortedTransList[transIndex].amount;
	        		}
	        		else if (sortedTransList[transIndex].transType.equals(TransactionType.withdraw)) {
	        			actranstype = AccStatTransType.withdraw;
	        			balance -= sortedTransList[transIndex].amount;
	        			min_daily_balance = balance;
	        		}
	        		AccountStatementLine accstatline = new AccountStatementLine(sortedTransList[transIndex].getDate(), 
	        				actranstype, sortedTransList[transIndex].amount, balance);
	        		
	        		accStmtList.add(accstatline);
	        		
	        		transIndex += 1;
	        		int indic = 0;
	        		if (transIndex >= sortedTransList.length) {
	        			actranstype = AccStatTransType.interestcredited;
	    	    		float credited_amount = (float) ((min_daily_balance * this.interestRate / 100.0)/365.0);
	    	    		SimpleDateFormat dtformat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	    	    		String datestr = trans_ddmmyyyy+" 23:59:59";
	    	        	Date transdate = null;
	    	    		try {
	    	    			transdate = dtformat.parse(datestr);
	    	    		} catch (ParseException e) {
	    	    			// TODO Auto-generated catch block
	    	    			e.printStackTrace();
	    	    		}  
	    	    		accstatline = new AccountStatementLine(transdate, 
	    	    				actranstype, credited_amount, balance);
	    	    		accStmtList.add(accstatline);
	    	    		
	    	    		if (ddmmyyyy.substring(8, 10).equals(Integer.toString(this.getNumDays(ddmmyyyy.substring(5, 7), 
	    	    				Integer.parseInt(ddmmyyyy.substring(0, 4)))))) {
	    	    		
	    	    			actranstype = AccStatTransType.monthlyInterest;
	    	    			this.balance += monthlyInterestAccrued;
	    	    			accstatline = new AccountStatementLine(transdate, 
	    		    				actranstype, monthlyInterestAccrued, balance);
	    	    			accStmtList.add(accstatline);
	    	    			monthlyInterestAccrued = 0;
	    	    		}
	    	    		
	    	    		break;
	        		}
	        		startMonthDate = dateFormat.format(sortedTransList[transIndex].getDate());
	        		String prev_trans_ddmmyyyy = trans_ddmmyyyy;
	        		trans_ddmmyyyy = startMonthDate.substring(0, 10);
	        		//System.out.println(trans_ddmmyyyy+" "+ddmmyyyy);
	        		if (!trans_ddmmyyyy.equals(ddmmyyyy)) {
	        			actranstype = AccStatTransType.interestcredited;
	    	    		float credited_amount = (float) ((min_daily_balance * this.interestRate / 100.0)/365.0);
	    	    		SimpleDateFormat dtformat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	    	    		String datestr = prev_trans_ddmmyyyy+" 23:59:59";
	    	        	Date transdate = null;
	    	    		try {
	    	    			transdate = dtformat.parse(datestr);
	    	    		} catch (ParseException e) {
	    	    			// TODO Auto-generated catch block
	    	    			e.printStackTrace();
	    	    		}  
	    	    		accstatline = new AccountStatementLine(transdate, 
	    	    				actranstype, credited_amount, balance);
	    	    		accStmtList.add(accstatline);
	    	    		
	    	    		
	    	    		String prev_month = prev_trans_ddmmyyyy.substring(5, 7);
	    	    		String curr_month = trans_ddmmyyyy.substring(5, 7);
	    	    		if (!prev_month.equals(curr_month)) {
	    	    			
	    	    			actranstype = AccStatTransType.monthlyInterest;
	    	    			this.balance += monthlyInterestAccrued;
	    	    			accstatline = new AccountStatementLine(transdate, 
	    		    				actranstype, monthlyInterestAccrued, balance);
	    	    			accStmtList.add(accstatline);
	    	    			monthlyInterestAccrued = 0;
	    	    		}
	    	    		
	    	    		
	    	    		break;
	        		}
	        		
	        		//After each transaction and/or each day add the transaction to accstatline and 
	        		// at the eod add the interest only to accstatline without adding to balance
	        	}
	    		
	    		
	    	}
	    	else 
	    	{
	    		AccStatTransType actranstype = AccStatTransType.interestcredited;
	    		float credited_amount = (float) ((balance * this.interestRate / 100.0)/365.0);
	    		SimpleDateFormat dtformat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	    		String datestr = ddmmyyyy+" 23:59:59";
	        	Date transdate = null;
	    		try {
	    			transdate = dtformat.parse(datestr);
	    		} catch (ParseException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}  
	    		AccountStatementLine accstatline = new AccountStatementLine(transdate, 
	    				actranstype, credited_amount, balance);
	    		accStmtList.add(accstatline);
	    		
	    		monthlyInterestAccrued += credited_amount;

	    		if (ddmmyyyy.substring(8, 10).equals(Integer.toString(this.getNumDays(ddmmyyyy.substring(5, 7), 
	    				Integer.parseInt(ddmmyyyy.substring(0, 4)))))) {
	    			
	    			actranstype = AccStatTransType.monthlyInterest;
	    			this.balance += monthlyInterestAccrued;
	    			accstatline = new AccountStatementLine(transdate, 
		    				actranstype, monthlyInterestAccrued, balance);
	    			accStmtList.add(accstatline);
	    			monthlyInterestAccrued = 0;
	    		}
	    	}
	    	
	    }  	
    }
    
    public void deposit(float amount, String transdatestr)
    {
    	
    	Transaction tr = new Transaction(transdatestr, amount, TransactionType.deposit);
    	this.transactionList.add(tr);
    	return;
    }
    
    public void withdraw(float amount, String transdatestr)
    {
    	Transaction tr = new Transaction(transdatestr, amount, TransactionType.withdraw);
    	this.transactionList.add(tr);
        return;
    }

    public void printAccountStatement() {
    	this.evalInterestCompBalance();
    	System.out.println("\nMonthly Statement:");
    	for(AccountStatementLine acstLine : accStmtList) {
    		acstLine.printAccStmtLine();
    	}
    }

}
 
