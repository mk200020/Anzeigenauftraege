import java.sql.SQLException;

import model.Database;
import model.Auftrag;


public class TestDatabase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Running database test");

		Database db = new Database();
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			db.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disconnect();
	}

}
