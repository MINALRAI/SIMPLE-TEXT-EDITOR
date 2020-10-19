import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.Formatter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class SimpleTextEditor {
	
	private final String TITLE="Simple Text Editor";

	private JFrame frame;
	private JTextArea textArea;
	
	private File openfile;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
					SimpleTextEditor window = new SimpleTextEditor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SimpleTextEditor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure you want to close this window?", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		        else 
		        	frame.getDefaultCloseOperation();
		        
		    }
		});
		
		frame.setBackground(Color.DARK_GRAY);
		frame.setTitle(TITLE);
		frame.setBounds(100, 100, 583, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().add(textArea, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open ");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				open();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save As");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				create();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Close");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		
	}
	
	private void open() {
		try {
			
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Select a Text File to Open");
			chooser.showOpenDialog(null);
			
			openfile= chooser.getSelectedFile();
			
			
			
			
			if(openfile!=null&&!openfile.exists()) {
				JOptionPane.showMessageDialog(null, "Failed to open file,This file dosen't exixt!","Error",JOptionPane.ERROR_MESSAGE);
				openfile=null;
				return;
			}
			Scanner reader = new Scanner(openfile);
			String contents="";
			while(reader.hasNextLine()) {
				contents=contents + reader.nextLine()+"\n";
			}
			reader.close();
			textArea.setText(contents);
			
			frame.setTitle(TITLE+" - "+openfile.getName());
			
			
		}
		catch(Exception e) {
		e.printStackTrace();
		}
	}
	private void create() {
        try {
        	
        	JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Save new file");
			int c =chooser.showSaveDialog(null);
			if (c == JFileChooser.CANCEL_OPTION) {
			    System.out.println("cancel");
			    return;}
			
			openfile = chooser.getSelectedFile();
			if(openfile==null)
        	{
        		JOptionPane.showMessageDialog(null, "Failed to save file, No file is Selected!","Error",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
			
			save();
			
			
		}
		catch(Exception e) {
		e.printStackTrace();
		}
	}
        private void save() {
            try {
            	if(openfile==null)
            	{
            		create();
            		return;
            	}
            	
            	String contents=textArea.getText();
            	
            	Formatter form=new Formatter(openfile);
    			form.format("%s", contents);
    			
    			form.close();
    			
    			frame.setTitle(TITLE+" - "+openfile.getName());
    			
    			}
   		catch(Exception e) {
   		e.printStackTrace();
   		}
		
	}
        private void close() {
        	if(openfile==null)
        	{
        		create();
        		return;
        	}
            try {
            	
            	int input=JOptionPane.showConfirmDialog(null, "Do you want to save this file before closing?","Wait",JOptionPane.YES_NO_OPTION);
   			    if(input==JOptionPane.YES_OPTION)
   			    {
   			    	save();
   			    }
   			    textArea.setText("");
   			    openfile=null;
   			    
   			 frame.setTitle(TITLE);
   	
            	
   		}
   		catch(Exception e) {
   		e.printStackTrace();
   		}
   }
        
}


