package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

import gui.FirmenSuchDialog;
import model.Auftrag;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 4775907780893361729L;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private PrefsDialog prefsDialog;
	private FirmenSuchDialog firmenSuchDialog;
	private Preferences prefs;
	private String Firma;

	public MainFrame() {
		super("Anzeigenauftraege");

		setLayout(new BorderLayout());

		toolbar = new Toolbar();
		formPanel = new FormPanel();
		tablePanel = new TablePanel();
		prefsDialog = new PrefsDialog(this);
		firmenSuchDialog=new FirmenSuchDialog(this);
		Firma= firmenSuchDialog.getFirma();
		
		prefs = Preferences.userRoot().node("db");

		controller = new Controller();

		tablePanel.setData(controller.getAuftraege());
		
		tablePanel.setAuftragTableListener(new AuftragTableListener() {
			public void rowDeleted(int row) {
				controller.removeAuftrag(row);
			}
		});
		
		prefsDialog.setPrefsListener(new PrefsListener() {
			public void preferencesSet(String user, String password, int port) {
				prefs.put("user", user);
				prefs.put("password", password);
				prefs.putInt("port", port);
			}
		});
		
		String user = prefs.get("user", "root");
		String password = prefs.get("password", "e5pid8u6");
		Integer port = prefs.getInt("port", 3306);
		
		prefsDialog.setDefaults(user, password, port);

		fileChooser = new JFileChooser();

		fileChooser.addChoosableFileFilter(new AuftragFileFilter());

		setJMenuBar(createMenuBar());

		toolbar.setToolbarListener(new ToolbarListener() {
			public void saveEventOccured() {
				connect();
				
				try {
					controller.save();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to save to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
					System.out.println(e.getMessage());
				}
			}

			public void refreshEventOccured() {
				connect();
				
				try {
					controller.load();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to load from database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
					System.out.println(e.getMessage());
					
				}
				
				tablePanel.refresh();
			}

			@Override
			public void refreshNachFirmenSucheEventOccured() {
				connect();
				
				try {
					Auftrag auftrag;
					String Firma=firmenSuchDialog.getFirma();
					auftrag=controller.nachFirmenSuchen(Firma);
					if(auftrag!=null) {
						
						  FormEvent ev = new FormEvent(this,auftrag.getAuftragsid(),auftrag.getFirmenname(),
								  auftrag.getAnsprechpartner(),auftrag.getStrasse(),auftrag.getPLZ(),auftrag.getOrt(),
								  auftrag.getTelefon(),auftrag.getTelefax(),auftrag.getBranche(),auftrag.getEmail(),
								  auftrag.getAngebotsgroesse(),auftrag.getAngebotspreis(),auftrag.getAngebotsanzahl(),
								  auftrag.getBegindatum(),auftrag.getEnddatum());
						  
						  controller.getAuftraege().clear();
						  
						  controller.addAuftrag(ev);
						  
						  tablePanel.refreshNachFirmenSuche();
					}
					
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to load from database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
					System.out.println(e.getMessage());
					
				}
				
				
				//
				
				
			}
			
		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				controller.addAuftrag(e);
				tablePanel.refresh();
			}
		});
		
		formPanel.setFormListener2(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				controller.getAuftraege().clear();
				controller.addAuftrag(e);
				tablePanel.refresh();
			}
		});

		add(formPanel, BorderLayout.WEST);
		add(toolbar, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);

		setMinimumSize(new Dimension(500, 400));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Cannot connect to database.", "Database Connection Problem", JOptionPane.ERROR_MESSAGE);
		}
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		JMenuItem prefsItem = new JMenuItem("Preferences...");
		JMenuItem firmaItem = new JMenuItem("Firma suchen");

		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Auftrag Form");
		showFormItem.setSelected(true);

		showMenu.add(showFormItem);
		windowMenu.add(showMenu);
		windowMenu.add(prefsItem);
		windowMenu.add(firmaItem);

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);
		
		prefsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);
			}
		});

		
		firmaItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firmenSuchDialog.setVisible(true);
			}
		});

		
		
		
		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();

				formPanel.setVisible(menuItem.isSelected());
			}
		});

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				ActionEvent.CTRL_MASK));

		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this,
								"Could not load data from file.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this,
								"Could not save data to file.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you really want to exit the application?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		return menuBar;
	}
}
