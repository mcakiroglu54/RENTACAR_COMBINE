package utils;
import all.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadTxtFiles {

	public static List<Customer> readCustomer(String fileName) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		List<Customer> list = new ArrayList<>();
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				Customer customer2 = new Customer();
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
				if (line == null)
					break;
				String[] customer = line.split(",");
				customer2.setFirstName(customer[0]);
				customer2.setLastName(customer[1]);
				customer2.setSSN(customer[2]);
				list.add(customer2);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			br.close();
			return list;
		}
	}
}
