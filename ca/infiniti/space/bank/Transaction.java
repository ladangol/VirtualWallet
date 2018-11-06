package ca.infiniti.space.bank;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
* The Transaction class stores the type of the transaction (credit or debit), transaction date, and an id. 
* Their balance and the transactions.
* @author  Ladan Golshanara
* @version 1.0
* @since   2018-09-04 
*/
public class Transaction {
	private UUID global_id;
	private long transaction_id;
	private int type;
	private LocalDateTime transaction_date;
	private BigDecimal amount;
	public Transaction(long trans_id, int type, BigDecimal amount){
		global_id = UUID.randomUUID();
		transaction_id = trans_id;
		this.type = type;
		transaction_date = LocalDateTime.now();
		this.amount = amount;
	}
	 /**
	   * The deposit method is used to deposit money to the wallet. 
	   * @return String .
	   */
	public String toString(){
		StringBuilder result = new StringBuilder(Long.toString(transaction_id) + "-");
		if(type == -1){
			result.append("debit $");
		}
		else
		{
			result.append("credit $");
		}
		result.append(amount.toString());
		result.append(" ");
		result.append(transaction_date);
		return result.toString();
			
	}
	public UUID getGlobalId(){
		return global_id;
	}
}
