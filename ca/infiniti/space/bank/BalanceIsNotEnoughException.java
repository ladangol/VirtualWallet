package ca.infiniti.space.bank;

public class BalanceIsNotEnoughException extends Exception {
	/**
	 * BalanceIsNotEnoughException occurs when the balance is not enough to do withdrawal.
	 * @author  Ladan Golshanara
	 * @version 1.0
	 * @since   2018-09-04 
	 */
	private static final long serialVersionUID = 1L;
	public BalanceIsNotEnoughException(){
		super();
	}
	public BalanceIsNotEnoughException(String message){
		super(message);
	}
}
