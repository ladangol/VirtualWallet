package ca.infiniti.space.bank;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
* The Wallet class stores the bank accounts of each user.
* Their balance and the transactions.
* @author  Ladan Golshanara
* @version 1.0
* @since   2018-09-04 
*/
public class Wallet {
	private int user_id;
	private int counter;
	private BigDecimal balance;
	private List<Transaction> transactions;
	private Lock lock;
	private Lock translock;
	
	public Wallet(int userid, BigDecimal balance){
		user_id = userid;
		this.balance = balance;
		counter = 0;
		transactions = new ArrayList<>();
		lock = new ReentrantLock();
		translock = new ReentrantLock();
	}
	 /**
	   * The withdraw method is used to withdraw money from the wallet. 
	   * @param money the money that is withdrawn from the wallet.
	   * @exception BalanceIsNotEnoughException when the balance is not enough.
	   * @return BigDecimal The balance after withdraw.
	   */
	public BigDecimal withdraw(BigDecimal money) throws BalanceIsNotEnoughException{
		lock.lock();
		BigDecimal temp = balance;
		try{
			if(temp.compareTo(money)<0){
				throw new BalanceIsNotEnoughException("user" + user_id +" does not have enough balance for this transaction");
			}
			temp = temp.subtract(money);
			counter++;
			balance = temp;
			transactions.add(new Transaction(counter, -1, money));
		}
		finally{
			lock.unlock();
		}
		return temp;
	}
	 /**
	   * The deposit method is used to deposit money to the wallet. 
	   * @param money the money that will be added to the wallet.
	   * @return BigDecimal The balance after deposit.
	   */
	public BigDecimal deposit(BigDecimal money){
		lock.lock();
		BigDecimal temp = balance;
		temp = temp.add(money);

		balance = temp;
		counter++;
		transactions.add(new Transaction(counter, 1, money));
		lock.unlock();
		return temp;
	}
	 /**
	   * The withdraw method is used to withdraw money from the wallet. 
	   * @param user2 The wallet of the recipient
	   * @param money the money that is withdrawn from the wallet.
	   * @exception BalanceIsNotEnoughException when the balance is not enough to do the transfer.
	   * @return BigDecimal The balance after transfer.
	   */
	public BigDecimal TransferTo(Wallet user2, BigDecimal money) throws BalanceIsNotEnoughException{
		try{
		translock.lock();
		withdraw(money);
		user2.deposit(money);}
		finally{
			translock.unlock();
		}
		
		return balance;		
	}
	 /**
	   * This method shows the history of the past N transactions on the wallet. 
	   * @param pastN number of transactions to be returned.
	   * @return List<Transaction> List of past N transactions. If number of the transactions are less than N return all. 
	   * If there is no transaction it returns null.
	   */
	public List<Transaction> History(int pastN){
		if(transactions.size() <=0){
			return null;
		}
		if(pastN >= transactions.size())
			return transactions;
		return transactions.subList(transactions.size()-pastN, transactions.size());
	}
	 /**
	   * This method returns the balance. 
	   * @return BigDecimal The balance.
	   */
	
	public BigDecimal getBalance() {
		return balance;
	}


}
