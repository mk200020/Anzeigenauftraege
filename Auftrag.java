package model;
                                                                         
public class Auftrag {
	
	
	private static int count = 1;
	
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
	private java.util.Date Begindatum;
	private java.util.Date Enddatum;
	
	
	public Auftrag() {
		
	}
	
	public Auftrag(int Auftragsid,String Firmenname,String Ansprechpartner,String Strasse,String Plz,
			String Ort,String Telefon,String Telefax,String	Branche,String Email,
			int Angebotsgroesse,double Angebotspreis,int Angebotsanzahl,
			java.util.Date Begindatum,java.util.Date Enddatum) {
		this.Firmenname=Firmenname;
		this.Ansprechpartner=Ansprechpartner;
		this.Strasse=Strasse;
		this.PLZ=Plz;
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
		
		this.Auftragsid = Auftragsid;
		
	}
	
	
	public int getAuftragsid() {
		return Auftragsid;
	}
	public void setAuftragsid(int auftragsid) {
		Auftragsid = auftragsid;
	}
	public String getFirmenname() {
		return Firmenname;
	}
	public void setFirmenname(String firmenname) {
		Firmenname = firmenname;
	}
	public String getAnsprechpartner() {
		return Ansprechpartner;
	}
	public void setAnsprechpartner(String ansprechpartner) {
		Ansprechpartner = ansprechpartner;
	}
	public String getStrasse() {
		return Strasse;
	}
	public void setStrasse(String strasse) {
		Strasse = strasse;
	}
	public String getPLZ() {
		return PLZ;
	}
	public void setPLZ(String pLZ) {
		PLZ = pLZ;
	}
	public String getOrt() {
		return Ort;
	}
	public void setOrt(String ort) {
		Ort = ort;
	}
	public String getTelefon() {
		return Telefon;
	}
	public void setTelefon(String telefon) {
		Telefon = telefon;
	}
	public String getTelefax() {
		return Telefax;
	}
	public void setTelefax(String telefax) {
		Telefax = telefax;
	}
	public String getBranche() {
		return Branche;
	}
	public void setBranche(String branche) {
		Branche = branche;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getAngebotsgroesse() {
		return Angebotsgroesse;
	}
	public void setAngebotsgroesse(int angebotsgroesse) {
		Angebotsgroesse = angebotsgroesse;
	}
	public double getAngebotspreis() {
		return Angebotspreis;
	}
	public void setAngebotspreis(double angebotspreis) {
		Angebotspreis = angebotspreis;
	}
	public int getAngebotsanzahl() {
		return Angebotsanzahl;
	}
	public void setAngebotsanzahl(int angebotsanzahl) {
		Angebotsanzahl = angebotsanzahl;
	}
	public java.util.Date getBegindatum() {
		return Begindatum;
	}
	public void setBegindatum(java.util.Date begindatum) {
		Begindatum = begindatum;
	}
	public java.util.Date getEnddatum() {
		return Enddatum;
	}
	public void setEnddatum(java.util.Date enddatum) {
		Enddatum = enddatum;
	}

}
