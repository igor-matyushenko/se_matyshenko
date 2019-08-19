package sef.module13.activity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

//@SuppressWarnings("unused")
	private Statement  statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private Connection conn;
	private Account account;
	private  String sql;
	private List<Account> arrayList =  new ArrayList<Account>();;

	public AccountDAOImpl(Connection conn) {

		this.conn = conn;
	}

	public List<Account> findAccount(String firstName, String lastName)
			throws AccountDAOException {
		sql = "SELECT * FROM ACCOUNT WHERE FIRST_NAME LIKE ? AND LAST_NAME LIKE ?";
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, firstName+'%');
			preparedStatement.setString(2, lastName + '%');
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				account = new AccountImpl(resultSet.getInt(1),resultSet.getString(2)
						, resultSet.getString(3), resultSet.getString(4));
				arrayList.add(account);
			}
			return arrayList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Account findAccount(int id) throws AccountDAOException {
		String resFirstName;
		String resLastName;
		String resEmail;
		sql = "SELECT * FROM ACCOUNT";
		try {
			statement = conn.createStatement();
			resultSet = statement.executeQuery(sql);
			while (true) {
				resultSet.next();
				if (resultSet.getInt(1) == id) {
					resFirstName = resultSet.getString(2);
					resLastName = resultSet.getString(3);
					resEmail = resultSet.getString(4);
					break;
				}
			}
			account = new AccountImpl(id,resFirstName, resLastName, resEmail);
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


	public boolean insertAccount(String firstName, String lastName, String email)
			throws AccountDAOException {
		sql = "INSERT INTO ACCOUNT (FIRST_NAME, LAST_NAME, E_MAIL) VALUES ('firstName', 'lastName', 'email')";
		try {
			preparedStatement = conn.prepareStatement(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
