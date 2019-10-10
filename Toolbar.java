package gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Toolbar extends JPanel implements ActionListener {
	private JButton saveButton;
	private JButton refreshButton;
	private JButton refreshNachFirmenSucheButton;
	
	private ToolbarListener textListener;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		saveButton = new JButton("Neuen Auftrag in Datenbank Speichern");
		refreshButton = new JButton("Refresh");
		refreshNachFirmenSucheButton=new JButton("refresh nach Firmen Suche");
		
		saveButton.addActionListener(this);
		refreshButton.addActionListener(this);
		refreshNachFirmenSucheButton.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(saveButton);
		add(refreshButton);
		add(refreshNachFirmenSucheButton);
	}
	
	public void setToolbarListener(ToolbarListener listener) {
		this.textListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == saveButton) {
			if(textListener != null) {
				textListener.saveEventOccured();
			}
		}
		else if(clicked == refreshButton) {
			if(textListener != null) {
				textListener.refreshEventOccured();
			}
		}else if(clicked==refreshNachFirmenSucheButton) {
			if(textListener!=null) {
				textListener.refreshNachFirmenSucheEventOccured();
			}
			
		}
		
	}
}
