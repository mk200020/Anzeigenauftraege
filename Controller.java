package controller;

import gui.FormEvent;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.Database;
import model.Auftrag;

public class Controller {
	Database db = new Database();
	
	Auftrag auftrag;
	
	
	public List<Auftrag> getAuftraege() {
		return db.getAuftraege();
	}
	
	public void removeAuftrag(int index) {
		db.removeAuftrag(index);
	}
	
	public void save() throws SQLException {
		db.save();
	}
	
	public void load() throws SQLException {
		db.load();
	}
	
	public Auftrag nachFirmenSuchen(String Firma) throws SQLException {
		auftrag=db.nachFirmenSuchen(Firma);
		return auftrag;
	}
	
	public void connect() throws Exception {
		db.connect();
	}
	
	public void disconnect() {
		db.disconnect();
	}
	
	public void addAuftrag(FormEvent ev) {
		int Auftragsid = ev.getAuftragsid();
		String Firmenname = ev.getFirmenname();
		String Ansprechpartner = ev.getAnsprechpartner();
		String Strasse = ev.getStrasse();
		String PLZ = ev.getPLZ();
		String Ort = ev.getOrt();
		String Telefon = ev.getTelefon();
		String Telefax = ev.getTelefax();
		String Branche = ev.getBranche();
		String Email = ev.getEmail();
		int Angebotsgroesse = ev.getAngebotsgroesse();
		double Angebotspreis = ev.getAngebotspreis();
		int Angebotsanzahl = ev.getAngebotsanzahl();
		java.util.Date Begindatum = ev.getBegindatum();
		java.util.Date Enddatum = ev.getEnddatum();
		
		Auftrag auftrag = new Auftrag(Auftragsid,Firmenname,Ansprechpartner,Strasse,PLZ,Ort,Telefon,Telefax,Branche,Email,
				Angebotsgroesse,Angebotspreis,Angebotsanzahl,Begindatum,Enddatum);
		db.addAuftrag(auftrag);
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
}