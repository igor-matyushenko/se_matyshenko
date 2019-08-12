package sef.module8.activity;


/**
 * Thsi class represents a simple representation of an account encapsulating
 * a name 
 * 
 * @author John Doe
 *
 */
public class Account {
	/**
	 * Creates an Account object with the specified name.  If the accout name
	 * given violates the minimum requirements, then an AccountException is thrown
	 * 
	 * @param accountName
	 * @throws AccountException
	 */
	private String accountName;
	public  Account(String accountName) throws AccountException{
		this.accountName = accountName;
		if (accountName.length() < 5) {
			throw new AccountException("Name must be longer than 4 characters", accountName);
		} else if (accountName.length() < 5 && !accountName.matches("\\w\\d")) {
			throw new AccountException("Name must contain a combination of letters and numbers", accountName);
		}
	}
	
	
	/**
	 * Returns the account name
	 * 
	 * @return the account name
	 */
	public String getName(){
		return accountName;
	}
}
