package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import controller.Controller;
import model.Database;
import model.Auftrag;
import gui.TablePanel;

public class FirmenSuchDialog extends JDialog {

	private JButton okButton;
	private JButton cancelButton;
	//private JSpinner portSpinner;
	//private SpinnerNumberModel spinnerModel;
	private JTextField FirmenSuchField;
	
	private TablePanel tablePanel=new TablePanel();
	
	private String Firma;
	
	private Auftrag auftrag;

	private Database db = new Database();

	public String getFirma() {
		return Firma;
	}

	public void setFirma(String firma) {
		Firma = firma;
	}

	private FormListener formListener;

	public FirmenSuchDialog(JFrame parent) {
		super(parent, "Firmen Suchen", false);

		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		


		FirmenSuchField = new JTextField(15);

		layoutControls();
		
		
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Firma=FirmenSuchField.getText();
				
				//System.out.println("Firma="+Firma);
				
				db=new Database();
				
				try {
					db.connect();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				
				
				try {
					
					auftrag=db.nachFirmenSuchen(Firma);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(auftrag!=null) {
				
				  FormEvent ev = new FormEvent(this,auftrag.getAuftragsid(),auftrag.getFirmenname(),
						  auftrag.getAnsprechpartner(),auftrag.getStrasse(),auftrag.getPLZ(),auftrag.getOrt(),
						  auftrag.getTelefon(),auftrag.getTelefax(),auftrag.getBranche(),auftrag.getEmail(),
						  auftrag.getAngebotsgroesse(),auftrag.getAngebotspreis(),auftrag.getAngebotsanzahl(),
						  auftrag.getBegindatum(),auftrag.getEnddatum());
				  
				  if (formListener != null) {
					    
						formListener.formEventOccurred(ev);
						
					}
				  
				  tablePanel.refreshNachFirmenSuche();
				}
				  			
				
			
				setVisible(false);	
				
			}
			
			
		});


		/*
		 * okButton.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) {
		 * 
		 * 
		 * Firma = FirmenSuchField.getText(); System.out.println("Firma="+Firma);
		 * 
		 * try { db.nachFirmenSuchen(); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); setVisible(false); }
		 * 
		 * 
		 * setVisible(false);
		 * 
		 * } });
		 */
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		setSize(300, 230);
		setLocationRelativeTo(parent);
	}

	private void layoutControls() {
		
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		int space = 15;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		Border titleBorder = BorderFactory.createTitledBorder("Firmen suchen");
		
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		
		controlsPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.gridy = 0;
		
		Insets rightPadding = new Insets(0, 0, 0, 15);
		Insets noPadding = new Insets(0, 0, 0, 0);

		// ///// First row /////////////////////////////

		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		//gc.gridx = 0;
		//gc.anchor = GridBagConstraints.EAST;
		//gc.insets = rightPadding;
		//controlsPanel.add(new JLabel("User: "), gc);

		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlsPanel.add(FirmenSuchField, gc);

		// ////// Next row ////////////////////////////

		/*
		 * gc.gridy++;
		 * 
		 * gc.weightx = 1; gc.weighty = 1; gc.fill = GridBagConstraints.NONE;
		 * 
		 * gc.gridx = 0; gc.anchor = GridBagConstraints.EAST; gc.insets = rightPadding;
		 * controlsPanel.add(new JLabel("Password: "), gc);
		 * 
		 * gc.gridx++; gc.anchor = GridBagConstraints.WEST; gc.insets = noPadding;
		 * controlsPanel.add(passField, gc);
		 */
		// ////// Next row ////////////////////////////

		/*
		 * gc.gridy++;
		 * 
		 * gc.weightx = 1; gc.weighty = 1; gc.fill = GridBagConstraints.NONE;
		 * 
		 * gc.gridx = 0; gc.anchor = GridBagConstraints.EAST; gc.insets = rightPadding;
		 * controlsPanel.add(new JLabel("Port: "), gc);
		 * 
		 * gc.gridx++; gc.anchor = GridBagConstraints.WEST; gc.insets = noPadding;
		 * controlsPanel.add(portSpinner, gc);
		 */
		// ////////// Buttons Panel ///////////////

		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);
		
		// Add sub panels to dialog
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

}
