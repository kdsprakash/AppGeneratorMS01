package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class PostgresExample
{
	public static void main(String[] args)
	{
		try (Connection connection = DriverManager
				.getConnection("jdbc:postgresql://localhost:5432/prakashdb", "prakash", "prakash"))
		{
			System.out.println("Java JDBC PostgreSQL Example");
			// When this class first attempts to establish a connection, it automatically loads any
			// JDBC 4.0 drivers found within
			// the class path. Note that your application must manually load any JDBC drivers prior
			// to version 4.0.
			// Class.forName("org.postgresql.Driver");

			System.out.println("Connected to PostgreSQL database!");
			Statement statement = connection.createStatement();
			System.out.println("Students");
			ResultSet rs = statement.executeQuery("SELECT * FROM students");
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next())
			{
				for (int i = 1; i <= rsmd.getColumnCount(); i++)
				{
					System.out.print(" | " + rs.getString(i));
				}

				System.out.println(" |");
			}
		}
		catch (Exception e)
		{
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
}