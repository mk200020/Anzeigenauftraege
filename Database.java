package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.Auftrag;

public class Database {

	private List<Auftrag> auftraege;

	private Connection con;
	
	Auftrag auftrag = new Auftrag();

	public Database() {
		auftraege= new LinkedList<Auftrag>();
	}

	public void connect() throws Exception {

		if (con != null)
			return;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

		String url = "jdbc:mysql://localhost:3306/Kundendatenbank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

		con = DriverManager.getConnection(url, "root", "e5pid8u6");
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}
		}
	}
	
	public java.sql.Date convertUtilToSql(java.util.Date uDate) {
		
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

	public void save() throws SQLException {

		String checkSql = "select count(*) as count from Anzeigeauftrag where Auftragsid=?";
		PreparedStatement checkStmt = con.prepareStatement(checkSql);

		String insertSql = "insert into Anzeigeauftrag (Auftragsid,Firmenname,Ansprechpartner,Strasse,PLZ,"+
		"Ort,Telefon,Telefax,Branche,Email,Angebotsgroesse,Angebotspreis,Angebotsanzahl,"+
				"Begindatum,Enddatum) values (?,?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		
		String updateSql = "update Anzeigeauftrag set Firmenname=?, Ansprechpartner=?, Strasse=?,"+
		"PLZ=?, Ort=?, Telefon=?, Telefax=?, Branche=?, Email=?, Angebotsgroesse=?, "+
				"Angebotspreis=?, Angebotsanzahl=?, Begindatum=?, Enddatum=? where Auftragsid=?";
		
		PreparedStatement updateStatement = con.prepareStatement(updateSql);

		for (Auftrag auftrag : auftraege) {
			int Auftragsid = auftrag.getAuftragsid();
			String Firmenname = auftrag.getFirmenname();
			String Ansprechpartner = auftrag.getAnsprechpartner();
			String Strasse = auftrag.getStrasse();
			String PLZ = auftrag.getPLZ();
			String Ort = auftrag.getOrt();
			String Telefon = auftrag.getTelefon();
			String Telefax = auftrag.getTelefax();
			String Branche = auftrag.getBranche();
			String Email = auftrag.getEmail();
			int Angebotsgroesse = auftrag.getAngebotsgroesse();
			double Angebotspreis = auftrag.getAngebotspreis();
			int Angebotsanzahl = auftrag.getAngebotsanzahl();
			java.util.Date Begindatum = auftrag.getBegindatum();
			java.util.Date Enddatum = auftrag.getEnddatum();
			
			
			checkStmt.setInt(1, Auftragsid);

			ResultSet checkResult = checkStmt.executeQuery();
			checkResult.next();

			int count = checkResult.getInt(1);
			
			Auftragsid=checkResult.getInt(1);

			if (count == 0) {
				System.out.println("Inserting Auftrag with Auftrags-ID " + Auftragsid);
				
				int col = 1;
				
			    java.sql.Date sBegindatum=this.convertUtilToSql(Begindatum);
			    java.sql.Date sEnddatum=this.convertUtilToSql(Enddatum);
				
				
				insertStatement.setInt(col++, Auftragsid);
				insertStatement.setString(col++, Firmenname);
				insertStatement.setString(col++, Ansprechpartner);
				insertStatement.setString(col++, Strasse);
				insertStatement.setString(col++, PLZ);
				insertStatement.setString(col++, Ort);
				insertStatement.setString(col++, Telefon);
				insertStatement.setString(col++, Telefax);
				insertStatement.setString(col++, Branche);
				insertStatement.setString(col++, Email);
				insertStatement.setInt(col++, Angebotsgroesse);
				insertStatement.setDouble(col++, Angebotspreis);
				insertStatement.setInt(col++, Angebotsanzahl);
				insertStatement.setDate(col++, sBegindatum);
				insertStatement.setDate(col++, sEnddatum);
				
				insertStatement.executeUpdate();
			} else {
				System.out.println("Updating Auftrag with Auftrags-ID " + Auftragsid);
				
				int col = 1;
				
				java.sql.Date sBegindatum=this.convertUtilToSql(Begindatum);
			    java.sql.Date sEnddatum=this.convertUtilToSql(Enddatum);
				
				
				updateStatement.setInt(col++, Auftragsid);
				updateStatement.setString(col++, Firmenname);
				updateStatement.setString(col++, Ansprechpartner);
				updateStatement.setString(col++, Strasse);
				updateStatement.setString(col++, PLZ);
				updateStatement.setString(col++, Ort);
				updateStatement.setString(col++, Telefon);
				updateStatement.setString(col++, Telefax);
				updateStatement.setString(col++, Branche);
				updateStatement.setString(col++, Email);
				updateStatement.setInt(col++, Angebotsgroesse);
				updateStatement.setDouble(col++, Angebotspreis);
				updateStatement.setInt(col++, Angebotsanzahl);
				updateStatement.setDate(col++, sBegindatum);
				updateStatement.setDate(col++, sEnddatum);
				
				updateStatement.executeUpdate();
			}
		}
		
		updateStatement.close();
		insertStatement.close();
		checkStmt.close();
	}
	
	public int getSize() throws SQLException {
		String checkSql = "select count(*) from Anzeigeauftrag";
		PreparedStatement checkStmt = con.prepareStatement(checkSql);
		
		ResultSet checkResult = checkStmt.executeQuery();
		checkResult.next();
		
		int Size = checkResult.getInt(1);
		
		checkResult.close();
		checkStmt.close();
		
		return Size;
	}
	
	public void load() throws SQLException {
		auftraege.clear();
		
		String sql = "select * from Anzeigeauftrag order by Auftragsid";
		Statement selectStatement = con.createStatement();
		
		ResultSet results = selectStatement.executeQuery(sql);
		
		while(results.next()) {
			int Auftragsid = results.getInt("Auftragsid");
			String Firmenname = results.getString("Firmenname");
			String Ansprechpartner = results.getString("Ansprechpartner");
			String Strasse = results.getString("Strasse");
			String PLZ = results.getString("PLZ");
			String Ort = results.getString("Ort");
			String Telefon = results.getString("Telefon");
			String Telefax = results.getString("Telefax");
			String Branche = results.getString("Branche");
			String Email = results.getString("Email");
			int Angebotsgroesse = results.getInt("Angebotsgroesse");
			double Angebotspreis = results.getDouble("Angebotspreis");
			int Angebotsanzahl = results.getInt("Angebotsanzahl");
			java.sql.Date Begindatum = results.getDate("Begindatum");
			java.sql.Date Enddatum = results.getDate("Enddatum");
			
			
			Auftrag auftrag = new Auftrag(Auftragsid,Firmenname,Ansprechpartner,Strasse,PLZ,
					Ort,Telefon,Telefax,Branche,Email,
					Angebotsgroesse,Angebotspreis,Angebotsanzahl,Begindatum,Enddatum);
			auftraege.add(auftrag);
		}
		
		results.close();
		selectStatement.close();
	}
	
	public Auftrag nachFirmenSuchen(String Firma) throws SQLException {
		auftraege.clear();

		String checkSql = "select * from Anzeigeauftrag where Firmenname='"+Firma+"'";
		
        Statement selectStatement = con.createStatement();
        
        if(selectStatement!=null) { 
		
		   ResultSet results = selectStatement.executeQuery(checkSql);

		while(results.next()) {
			int auftragsid = results.getInt("Auftragsid");
			auftrag.setAuftragsid(auftragsid);
			String firmenname = results.getString("Firmenname");
			auftrag.setFirmenname(firmenname);
			String ansprechpartner = results.getString("Ansprechpartner");
			auftrag.setAnsprechpartner(ansprechpartner);
			String strasse = results.getString("Strasse");
			auftrag.setStrasse(strasse);
			String pLZ = results.getString("PLZ");
			auftrag.setPLZ(pLZ);
			String ort = results.getString("Ort");
			auftrag.setOrt(ort);
			String telefon = results.getString("Telefon");
			auftrag.setTelefon(telefon);
			String telefax = results.getString("Telefax");
			auftrag.setTelefax(telefax);
			String branche = results.getString("Branche");
			auftrag.setBranche(branche);
			String email = results.getString("Email");
			auftrag.setEmail(email);
			int angebotsgroesse = results.getInt("Angebotsgroesse");
			auftrag.setAngebotsgroesse(angebotsgroesse);
			double angebotspreis = results.getDouble("Angebotspreis");
			auftrag.setAngebotspreis(angebotspreis);
			int angebotsanzahl = results.getInt("Angebotsanzahl");
			auftrag.setAngebotsanzahl(angebotsanzahl);
			java.sql.Date begindatum = results.getDate("Begindatum");
			auftrag.setBegindatum(begindatum);			
			java.sql.Date enddatum = results.getDate("Enddatum");
			auftrag.setEnddatum(enddatum);
			

			auftraege.add(auftrag);
			
			
		}
		
        
		
		results.close();
		selectStatement.close();
		return auftrag;
        }
		return auftrag;
	}

	
	
	public void addAuftrag(Auftrag auftrag) {
		auftraege.add(auftrag);
	}

	public void removeAuftrag(int index) {
		auftraege.remove(index);
	}

	public List<Auftrag> getAuftraege() {
		return auftraege;
	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Auftrag[] auftrags = auftraege.toArray(new Auftrag[auftraege.size()]);

		oos.writeObject(auftrags);

		oos.close();
	}

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);

		try {
			Auftrag[] auftrags = (Auftrag[]) ois.readObject();

			auftraege.clear();

			auftraege.addAll(Arrays.asList(auftrags));

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ois.close();
	}
}
