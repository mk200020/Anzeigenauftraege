package gui;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Database;
import gui.*;

public class FormPanel extends JPanel {
    //java.util.Locale locale=new java.util.Locale("de-DE");
	private java.text.DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	
	private JLabel AuftragsidLabel;
	private JLabel FirmennameLabel;
	private JLabel AnsprechpartnerLabel;
	private JLabel StrasseLabel;
	private JLabel PlzLabel;
	private JLabel OrtLabel;
	private JLabel TelefonLabel;
	private JLabel TelefaxLabel;
	private JLabel BrancheLabel;
	private JLabel EmailLabel;
	private JLabel AngebotsgroesseLabel;
	private JLabel AngebotspreisLabel;
	private JLabel AngebotsanzahlLabel;
	private JLabel BeginndatumLabel;
	private JLabel EnddatumLabel;

	private JTextField AuftragsidField;
	private JTextField FirmennameField;
	private JTextField AnsprechpartnerField;
	private JTextField StrasseField;
	private JTextField PlzField;
	private JTextField OrtField;
	private JTextField TelefonField;
	private JTextField TelefaxField;
	private JTextField BrancheField;
	private JTextField EmailField;
	private JTextField AngebotsgroesseField;
	private JTextField AngebotspreisField;
	private JTextField AngebotsanzahlField;
	private JTextField BegindatumField;
	private JTextField EnddatumField;
	
	
	
	private JButton okBtn;
	private FormListener formListener;
	
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		
		AuftragsidLabel=new JLabel("Auftragsid: "); 
		FirmennameLabel=new JLabel("Firmenname: ");
		AnsprechpartnerLabel=new JLabel("Ansprechpartner: ");
		StrasseLabel=new JLabel("Strasse: ");
		PlzLabel=new JLabel("PLZ: ");
		OrtLabel=new JLabel("Ort ");
		TelefonLabel=new JLabel("Telefon: ");
		TelefaxLabel=new JLabel("Telefax: ");
		BrancheLabel=new JLabel("Branche: ");
		EmailLabel=new JLabel("Email: ");
		AngebotsgroesseLabel=new JLabel("Angebotsgroesse: ");
		AngebotspreisLabel=new JLabel("Angebotspreis: ");
		AngebotsanzahlLabel=new JLabel("Angebotsanzahl: ");
		BeginndatumLabel=new JLabel("Beginndatum: ");
		EnddatumLabel=new JLabel("Enddatum: ");
		
		AuftragsidField= new JTextField(10);
		FirmennameField= new JTextField(10);
		AnsprechpartnerField= new JTextField(10);
		StrasseField= new JTextField(10);
		PlzField= new JTextField(10);
		OrtField= new JTextField(10);
		TelefonField= new JTextField(10);
		TelefaxField= new JTextField(10);
		BrancheField= new JTextField(10);
		EmailField= new JTextField(10);
		AngebotsgroesseField= new JTextField(10);
		AngebotspreisField= new JTextField(10);
		AngebotsanzahlField= new JTextField(10);
		BegindatumField= new JTextField(10);
		EnddatumField= new JTextField(10);
		
		
		
		
		
		okBtn = new JButton("OK");
		
		// Set up mnemomics
		okBtn.setMnemonic(KeyEvent.VK_O);
		

		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int Auftragsid= Integer.parseInt(AuftragsidField.getText());
				String Firmenname = FirmennameField.getText();
				String Ansprechpartner = AnsprechpartnerField.getText();
				String Strasse = StrasseField.getText();
				String Plz = PlzField.getText();
				String Ort = OrtField.getText();
				String Telefon = TelefonField.getText();
				String Telefax = TelefaxField.getText();
				String Branche = BrancheField.getText();
				String Email = EmailField.getText();
				int Angebotsgroesse = Integer.parseInt(AngebotsgroesseField.getText());
				double Angebotspreis=Double.parseDouble(AngebotspreisField.getText());
				int Angebotsanzahl = Integer.parseInt(AngebotsanzahlField.getText());
				String BegindatumStr = BegindatumField.getText();
				String EnddatumStr = EnddatumField.getText();
				
				
				
				String year1=BegindatumStr.substring(0,4);
				//System.out.println("year1="+year1);
				String month1=BegindatumStr.substring(5, 7);
				//System.out.println("month1="+month1);
				String day1=BegindatumStr.substring(BegindatumStr.length()-2, BegindatumStr.length());
				//System.out.println("day1="+day1);
				
				String year2=EnddatumStr.substring(0,4);
				//System.out.println("year2="+year2);
				String month2=EnddatumStr.substring(5, 7);
				//System.out.println("month2="+month2);
				String day2=EnddatumStr.substring(EnddatumStr.length()-2, EnddatumStr.length());
				//System.out.println("day2="+day2);
				
				java.util.Date Begindatum = new java.util.Date();
				java.util.Date Enddatum =new java.util.Date();
				
				//System.out.println("Begindatum="+Begindatum);
				//System.out.println("Enddatum="+Enddatum);
				
				
				
				  try { 
				  Begindatum= dateFormat.parse(year1+"-"+month1+"-"+day1);
				  Begindatum.setTime(Begindatum.getTime()+86400000);
				  Enddatum=dateFormat.parse(year2+"-"+month2+"-"+day2);
				  Enddatum.setTime(Enddatum.getTime()+86400000);
				  }
				  catch (ParseException pe) {  
					  pe.printStackTrace();
				  }
				 
				
				
				FormEvent ev = new FormEvent(this,Auftragsid,Firmenname,Ansprechpartner,
						        Strasse,Plz,Ort,Telefon,Telefax,Branche,Email, Angebotsgroesse,
						 Angebotspreis, Angebotsanzahl,Begindatum,Enddatum);
				
				if (formListener != null) {
					formListener.formEventOccurred(ev);
				}
			}
		});

		Border innerBorder = BorderFactory.createTitledBorder("Neue Auftrag");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();
		
	
	}	
	

	public void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// ////////// First row ///////////////////////////////////

		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(AuftragsidLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(AuftragsidField, gc);

		// //////////Second row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(FirmennameLabel, gc);

		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(FirmennameField, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(AnsprechpartnerLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(AnsprechpartnerField, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(StrasseLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(StrasseField, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(PlzLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(PlzField, gc);

		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(OrtLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(OrtField, gc);
		
		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(TelefonLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(TelefonField, gc);
		
		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(TelefaxLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(TelefaxField, gc);

		////////////////////////////////////////////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(EmailLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(EmailField, gc);
		
		////////////////////////////////////////////////////////////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(BrancheLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(BrancheField, gc);
		
		//////////////////////////////////////////////////////
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(AngebotsgroesseLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(AngebotsgroesseField, gc);
		
		////////////////////////////////////////////////////
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(AngebotspreisLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(AngebotspreisField, gc);
		
		/////////////////////////////////////////////////////////
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(AngebotsanzahlLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(AngebotsanzahlField, gc);
		
		/////////////////////////////////////////////////////////////////
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(BeginndatumLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(BegindatumField, gc);
		
		//////////////////////////////////////////////////////////////
		
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		add(EnddatumLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(EnddatumField, gc);
		// //////////Next row ///////////////////////////////////

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);
	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
	
	public void setFormListener2(FormListener listener) {
		this.formListener = listener;
	}
}