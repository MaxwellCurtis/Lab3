package curtis.max.dbt330;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SQL_Database_Connection {

	private String databaseConnectionString;
	private final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public String getDatabaseConnectionString() {
		return databaseConnectionString;
	}

	public void setDatabaseConnectionString(String databaseConnectionString) {
		this.databaseConnectionString = databaseConnectionString;
	}

	public String Select_From_Table(String table, String... columns) {
		String sql, query = "";
		sql = "Select ";
		if (columns.length > 0) {
			for (int i = 0; i < columns.length; i++) {
				sql += columns[i];
				if (i != columns.length - 1) {
					sql += ",";
				}
			}
		}
		sql += "From";
		sql += table;
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("SQL Driver Malfunction");
		}
		try (Connection connection = DriverManager.getConnection(getDatabaseConnectionString())) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					ResultSetMetaData metaData = resultSet.getMetaData();
					int length = metaData.getColumnCount();
					while (resultSet.next()) {
						for (int i = 1; i <= length; i++) {
							query += resultSet.getString(i);
							if (i > 1) {
								query += ",";
							}
						}
					}
					connection.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(sql + "\n" + query);
		}
		return query;
	}

	public String Select_With_Where(String table, String where_Value, String where_Equils, String... columns) {
		String sql, query = "";
		sql = "Select ";
		if (columns.length > 0) {
			for (int i = 0; i < columns.length; i++) {
				sql += columns[i];
				if (i != columns.length - 1) {
					sql += ",";
				}
			}
		} else {
			sql += " * ";
		}
		sql += " From ";
		sql += table;
		sql += " Where ";
		sql += where_Value;
		sql += " = '";
		sql += where_Equils;
		sql += "'";
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(sql + "\n" + "Driver");
		}
		try (Connection connection = DriverManager.getConnection(getDatabaseConnectionString())) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				try (ResultSet resultSet = statement.executeQuery()) {
					ResultSetMetaData rMetaData = resultSet.getMetaData();
					int columnLength = rMetaData.getColumnCount();
					while (resultSet.next()) {
						for (int i = 1; i >= columnLength; i++) {
							query += resultSet.getString(i);
							if (i > 1) {
								query += ",";
							}
						}
					}
					connection.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(sql + "\n" + query);
		}
		return query;
	}

	public boolean Insert_Into_Table(String table, String[] values, String... columns) {
		String sql = "Insert into " + table + " (";
		if (columns.length > 0) {
			for (int i = 0; i < columns.length; i++) {
				sql += columns[i];
				if (i != columns.length - 1) {
					sql += ",";
				}
			}
			sql += ")";
		} else {
			sql = "";
		}
		sql += " Values (";
		if (values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				sql += values[i];
				if (i != values.length - 1) {
					sql += ",";
				}
			}
			sql += ")";
		} else {
			sql = "";
		}
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection(getDatabaseConnectionString())) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(sql);
			return false;
		}
		return true;
	}

	public boolean Insert_Into_Table_With_Where() {
		return false;
	}

	public void Update_Table() {
	}

	public boolean Create_Table() {
		return false;
	}

}
