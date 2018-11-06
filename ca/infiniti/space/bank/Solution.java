package ca.infiniti.space.bank;

import java.math.BigDecimal;
import java.util.List;

public class Solution implements Runnable {

	static Wallet user1wallet;
	static Wallet user2wallet;
	static Wallet user3wallet;
	static Wallet user4wallet;
	
	public static void main(String[] args) {
		user1wallet = new Wallet(1, new BigDecimal(100.00));
		user2wallet = new Wallet(2, new BigDecimal(200.00));
		user3wallet = new Wallet(3, new BigDecimal(0.00));
		user4wallet = new Wallet(4, new BigDecimal(1.00));
		
	    Solution solutionTest = new Solution();
	    Thread thread1 = new Thread(solutionTest, "test1");
	    Thread thread2 = new Thread(solutionTest, "test2");
	    Thread thread3 = new Thread(solutionTest, "test3");
	    Thread thread4 = new Thread(solutionTest, "test4");
	    thread1.start();
	    thread2.start();
	    thread3.start();
	    thread4.start();
	    
	    try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	   // System.out.println("Here");
		List<Transaction> user1list = user1wallet.History(3);
		if(user1list != null){
			System.out.println("user 1 transactions:");
			for(Transaction trans : user1list){
				System.out.println(trans.toString());
			}
			
		}
		System.out.println("user 1 balance:"+user1wallet.getBalance());
		List<Transaction> user2list = user2wallet.History(3);
		if(user2list != null){
			System.out.println("user 2 transactions:");
			for(Transaction trans : user2list){
				System.out.println(trans.toString());
			}
			
		}
		System.out.println("user 2 balance:"+user2wallet.getBalance());
		List<Transaction> user3list = user3wallet.History(3);
		if(user3list != null){
			System.out.println("user 3 transactions:");
			for(Transaction trans : user3list){
				System.out.println(trans.toString());
			}

		}
		System.out.println("user 3 balance:"+user3wallet.getBalance());
		//System.out.println("Aftre printing all");
	}

	@Override
	public void run() {
		switch (Thread.currentThread().getName()) {
		case "test1": {
			try{
			user2wallet.TransferTo(user1wallet, new BigDecimal(300.00));
			System.out.println("Wallet 1 after transfer "+user1wallet.getBalance());
			System.out.println("Wallet 2 after transfer: "+user2wallet.getBalance());
			System.out.println("test1 is finished");
			
			
			}
			catch(BalanceIsNotEnoughException e){
				System.out.println(e.getMessage());
			}
			break;
		}
		case "test2": {
			try{
			user1wallet.TransferTo(user2wallet, new BigDecimal(100));
			user1wallet.TransferTo(user3wallet, new BigDecimal(50));
			System.out.println("Wallet 1 after transfer to user 3: "+user1wallet.getBalance());
			System.out.println("test2 is finished");
			
			}
			catch(BalanceIsNotEnoughException e){
				System.out.println(e.getMessage());
			}
			break;
			
		}
		case "test3":{
			System.out.println("Wallet 3 before withdraw: "+user3wallet.getBalance());
			try{
			user3wallet.withdraw(new BigDecimal(30.00));
			System.out.println("Wallet 3 after withdraw: "+user3wallet.getBalance());
			user1wallet.TransferTo(user3wallet, user1wallet.getBalance());
			
			}
			
			catch(BalanceIsNotEnoughException e){
				System.out.println(e.getMessage());
			}
			break;
		}

		default: break;
		
		}
	}

}
