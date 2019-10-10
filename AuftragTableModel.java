package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Auftrag;

public class AuftragTableModel extends AbstractTableModel {
	
	private List<Auftrag> db;
	
	private String[] colNames = {"Auftragsid","Firmenname","Ansprechpartner","Strasse",
	"Plz","Ort","Telefon","Telefax","Branche", "Email","Angebotsgroesse","Angebotspreis",
	"Angebotsanzahl","Begindatum" ,"Enddatum"};
	
	public AuftragTableModel() {
	}
	
	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}



	public void setData(List<Auftrag> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return 15;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Auftrag auftrag = db.get(row);
		
		switch(col) {
		case 0:
			return auftrag.getAuftragsid();
		case 1:
			return auftrag.getFirmenname();
		case 2:
			return auftrag.getAnsprechpartner();
		case 3:
			return auftrag.getStrasse();
		case 4:
			return auftrag.getPLZ();
		case 5:
			return auftrag.getOrt();
		case 6:
			return auftrag.getTelefon();
		case 7:
			return auftrag.getTelefax();
		case 8:
			return auftrag.getBranche();
		case 9:
			return auftrag.getEmail();	
		case 10:
			return auftrag.getAngebotsgroesse();
		case 11:
			return auftrag.getAngebotspreis();
		case 12:
			return auftrag.getAngebotsanzahl();
		case 13:
			return auftrag.getBegindatum();	
		case 14:
			return auftrag.getEnddatum();	
		}
		
		return null;
	}

}
