package myJavaProject;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class NotePad extends JFrame implements ActionListener,WindowListener{

	JTextArea jta = new JTextArea();
	File fnameContainer;
	
	public NotePad() {
		Font fnt = new Font("Arial",Font.PLAIN,15);
		Container con = getContentPane();
		
		JMenuBar jmb = new JMenuBar();
		
		JMenu jmfile = new JMenu("File");
		JMenu jmedit = new JMenu("edit");
		JMenu jmhelp = new JMenu("help");
		
		con.setLayout(new BorderLayout());
		
		JScrollPane sbrText = new JScrollPane(jta);
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sbrText.setVisible(true);
	
		jta.setFont(fnt);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		
		con.add(sbrText);
		
		createMenuItem(jmfile,"New");
		createMenuItem(jmfile,"Open");
		createMenuItem(jmfile,"Save");
		jmfile.addSeparator();
		createMenuItem(jmfile,"Exit");
		
		createMenuItem(jmedit,"cut");
		createMenuItem(jmedit,"copy");
		createMenuItem(jmedit,"paste");
		
		createMenuItem(jmhelp,"about NotePad");
		
		jmb.add(jmfile);
		jmb.add(jmedit);
		jmb.add(jmhelp);
		
		setJMenuBar(jmb);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("notepad.gif"));
		addWindowListener(this);
		setSize(500,500);
		setTitle("Untitled.txt - Notepad");
		setVisible(true);
	
	
	}
	
	
	
	public void createMenuItem(JMenu jm, String txt) {
		// TODO Auto-generated method stub
		JMenuItem jmi = new JMenuItem(txt);
		jmi.addActionListener(this);
		jm.add(jmi);
	}

	public void actionPerformed(ActionEvent e) {
		
		JFileChooser jfc = new JFileChooser();
		
		if (e.getActionCommand().equals("New")) {
			this.setTitle("untitled.txt - Notepad");
			jta.setText("");
			fnameContainer = null;
		}
		else if (e.getActionCommand().equals("Open")) {
			
			int ret = jfc.showDialog(null, "Open");
			
			if (ret == JFileChooser.APPROVE_OPTION) {
				try {
					File fyl = jfc.getSelectedFile();
					OpenFile(fyl.getAbsolutePath());
					this.setTitle(fyl.getName() + " - Notepad");
					fnameContainer = fyl;
				}
				catch(IOException ers) {}
			}
		}
		else if (e.getActionCommand().equals("Save")) {
			
			if (fnameContainer != null) {
				jfc.setCurrentDirectory(fnameContainer);
				jfc.setSelectedFile(fnameContainer);
			}
			else {
				jfc.setSelectedFile(new File("untitled.txt"));
			}
			
			int ret = jfc.showSaveDialog(null);
			if (ret == JFileChooser.APPROVE_OPTION) {
				try {
					File fyl = jfc.getSelectedFile();
					saveFile(fyl.getAbsolutePath());
					this.setTitle(fyl.getName()+ "- Notepad");
					fnameContainer = fyl;
				}
				catch(Exception ers2) {}
			}
		}
		else if (e.getActionCommand().equals("Exit")) {
			Exiting();
		}
		else if (e.getActionCommand().equals("copy")) {
			jta.copy();
		}
		else if (e.getActionCommand().equals("paste")) {
			jta.paste();
		}
		else if (e.getActionCommand().equals("about Notepad")) {
			JOptionPane.showMessageDialog(this,"Created by : Subhasish","Notepad",JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getActionCommand().equals("cut")) {
			jta.cut();
		}
	}

	public void Exiting() {
		// TODO Auto-generated method stub
		System.exit(0);
	}



	public void saveFile(String fname) throws IOException{
		// TODO Auto-generated method stub
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		DataOutputStream o = new DataOutputStream(new FileOutputStream(fname));
		o.writeBytes(jta.getText());
		o.close();
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}



	public void OpenFile(String fname) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
		String l;
		
		jta.setText("");
		
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		while((l=d.readLine()) != null) {
			jta.setText(jta.getText() + l + "\r\n");
		}
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		d.close();
	}
	
	public void windowDeactivated(WindowEvent e) {}
	
	public void windowActivated(WindowEvent e) {}
	
	public void windowDeiconified(WindowEvent e) {}
	
	public void windowIconified(WindowEvent e) {}
	
	public void windowClosed(WindowEvent e) {}
	
	public void windowClosing(WindowEvent e) {
		Exiting();
	}
	
	public void windowOpened(WindowEvent e) {}



//	@Override
//	public void windowDeactivated(WindowEvent e) {
//		// TODO Auto-generated method stub
//		
//	}


//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//	}

}
