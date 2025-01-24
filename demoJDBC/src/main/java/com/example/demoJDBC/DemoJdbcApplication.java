package com.example.demoJDBC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@SpringBootApplication
public class DemoJdbcApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoJdbcApplication.class, args);
		// Database URL, username, and password
		String jdbcURL = "jdbc:mysql://localhost:3306/demojdbc"; // Replace with your database name
		String username = "root"; // Replace with your username
		String password = "Nov28@2024"; // Replace with your password


		// SQL query to insert data
		String insertQuery = "INSERT INTO employees (empcode, empname, empage, esalary) VALUES (?, ?, ?, ?)";

		try {
			// Establish database connection
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);

			// Prepare the statement
			PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

			// Data to be inserted
			int[][] employeeData = {
					{101, 25, 10000},
					{102, 30, 20000},
					{103, 20, 40000},
					{104, 40, 80000},
					{105, 25, 90000}
			};
			String[] names = {"Jenny", "Jacky", "Joe", "John", "Shameer"};

			// Insert each employee's data
			for (int i = 0; i < employeeData.length; i++) {
				preparedStatement.setInt(1, employeeData[i][0]); // empcode
				preparedStatement.setString(2, names[i]);       // empname
				preparedStatement.setInt(3, employeeData[i][1]); // empage
				preparedStatement.setInt(4, employeeData[i][2]); // esalary
				preparedStatement.executeUpdate();
			}

			System.out.println("Data inserted successfully!");

			// Close the connection
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
