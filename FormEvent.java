package gui;
import java.util.EventObject;

public class FormEvent extends EventObject {

	private int Auftragsid;
	
	private String Firmenname;
	private String Ansprechpartner;
	private String Strasse;
	private String PLZ;
	private String Ort;
	private String Telefon;
	private String Telefax;
	private String Branche;
	private String Email;
	private int Angebotsgroesse;
	private double Angebotspreis;
	private int Angebotsanzahl;
	java.util.Date Begindatum;
	java.util.Date Enddatum;

	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(Object source, int Auftragsid,String Firmenname,String Ansprechpartner,
	 String Strasse,String PLZ,String Ort,String Telefon,String Telefax, String Branche,
	 String Email, int Angebotsgroesse, double Angebotspreis,int Angebotsanzahl,
	java.util.Date Begindatum,java.util.Date Enddatum) {
		super(source);

		this.Auftragsid=Auftragsid;
		this.Firmenname=Firmenname;
		this.Ansprechpartner=Ansprechpartner;
		this.Strasse=Strasse;
		this.PLZ=PLZ;
		this.Ort=Ort;
		this.Telefon=Telefon;
		this.Telefax=Telefax;
		this.Branche=Branche;
		this.Email=Email;
		this.Angebotsgroesse=Angebotsgroesse;
		this.Angebotspreis=Angebotspreis;
		this.Angebotsanzahl=Angebotsanzahl;
		this.Begindatum=Begindatum;
		this.Enddatum=Enddatum;
	}
	
	public int getAuftragsid() {
		return Auftragsid;
	}

	public String getFirmenname() {
		return Firmenname;
	}

	public String getAnsprechpartner() {
		return Ansprechpartner;
	}

	public String getStrasse() {
		return Strasse;
	}

	public String getPLZ() {
		return PLZ;
	}

	public String getOrt() {
		return Ort;
	}

	public String getTelefon() {
		return Telefon;
	}

	public String getTelefax() {
		return Telefax;
	}

	public String getBranche() {
		return Branche;
	}

	public String getEmail() {
		return Email;
	}

	public int getAngebotsgroesse() {
		return Angebotsgroesse;
	}

	public double getAngebotspreis() {
		return Angebotspreis;
	}

	public int getAngebotsanzahl() {
		return Angebotsanzahl;
	}

	public java.util.Date getBegindatum() {
		return Begindatum;
	}

	public java.util.Date getEnddatum() {
		return Enddatum;
	}

}
