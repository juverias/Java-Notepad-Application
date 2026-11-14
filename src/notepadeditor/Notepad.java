package notepadeditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Notepad implements ActionListener{
	JFrame jf , jfr , font_Frame;
	JMenuBar jmb;
	JMenu file , edit , format , help ;
	JMenuItem neww, open , save , saveas ,page_setup , print, exit;
	JMenuItem cut,copy,paste,replace,date_time;
	JMenuItem  font,font_color,textarea_color;
	JCheckBoxMenuItem word_wrap;
	
	JTextArea jta;
	JScrollPane jsp;
	
	String title="Untitled - Notepad";
	
	JFileChooser jfc ;
	File filee;
	
	JTextField jtf1,jtf2;
	JLabel jl1 , jl2;
	JButton jb1 , ok;
	
	JComboBox font_family , font_style , font_size;
	
	public Notepad() {
		
		//UIManager.getCrossPlatformLookAndFeelClassName();
		try {
			//UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel( "javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException| UnsupportedLookAndFeelException e) {
		e.printStackTrace();
	}
		
		jf = new JFrame(title);
		jf.setSize(500,500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jmb=new JMenuBar(); // added on frame 
		jf.setJMenuBar(jmb);
		
		file=new JMenu("File"); // added on menubar 
		jmb.add(file);
		
		neww=new JMenuItem("New");
		file.add(neww); // added on menu 
		neww.addActionListener(this);
		neww.setAccelerator(KeyStroke.getKeyStroke('N',InputEvent.CTRL_DOWN_MASK));
		
		open= new JMenuItem("Open");
		file.add(open);
		open.addActionListener(this);
		open.setAccelerator(KeyStroke.getKeyStroke('O',InputEvent.CTRL_DOWN_MASK));
		
		save=new JMenuItem("Save");
		file.add(save);
		save.addActionListener(this);
		save.setAccelerator(KeyStroke.getKeyStroke('S',InputEvent.CTRL_DOWN_MASK));
		
		saveas=new JMenuItem("Save As");
		file.add(saveas);
		saveas.addActionListener(this);
		saveas.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
		file.addSeparator();
		
		page_setup= new JMenuItem("Page Setup");
		file.add(page_setup);
		page_setup.addActionListener(this);
		page_setup.setAccelerator(KeyStroke.getKeyStroke('P',InputEvent.CTRL_DOWN_MASK));
		
		print=new JMenuItem("Print");
		file.add(print);
		print.addActionListener(this);
		print.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
		
		file.addSeparator();
		
		exit=new JMenuItem("Exit");
		file.add(exit);
		exit.addActionListener(this);
		exit.setAccelerator(KeyStroke.getKeyStroke('E',InputEvent.CTRL_DOWN_MASK));

		
		jta=new JTextArea();        
		jsp=new JScrollPane(jta); // Adding Text area on JScrollPane 
		jf.add(jsp);
		jsp.setVerticalScrollBarPolicy(jsp.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(jsp.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		edit=new JMenu("Edit");
		jmb.add(edit);
		
		cut=new JMenuItem("Cut");
		edit.add(cut);
		cut.addActionListener(this);
		
		copy=new JMenuItem("Copy");
		edit.add(copy);
		copy.addActionListener(this);
		
		paste=new JMenuItem("Paste");
		edit.add(paste);
		paste.addActionListener(this);
		
		edit.addSeparator();
		
		replace=new JMenuItem("Replace");
		edit.add(replace);
		replace.addActionListener(this);
		
		edit.addSeparator();
		
		date_time=new JMenuItem("Date / Time ");
		edit.add(date_time);
		date_time.addActionListener(this);
		
		format= new JMenu("Format");
		jmb.add(format);
		
		word_wrap= new JCheckBoxMenuItem("Word Wrap");
		format.add(word_wrap);
		word_wrap.addActionListener(this);
		
		format.addSeparator();
		
		font=new JMenuItem("Font");
		format.add(font);
		font.addActionListener(this);
		
		format.addSeparator();
		
		font_color=new JMenuItem("Font Color");
		format.add(font_color);
		font_color.addActionListener(this);
		
		textarea_color = new JMenuItem("TextArea Color");
		format.add(textarea_color);
		textarea_color.addActionListener(this);
		
		help = new JMenu("Help");
		jmb.add(help);
		help.addActionListener(this);
		
		Image iconImage = Toolkit.getDefaultToolkit().getImage(Notepad.class.getResource("/notepadeditor/notepad-icon-7.png"));
		jf.setIconImage(iconImage);
		
		jf.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
           if(e.getSource()==neww) {
        	   newNotepad();
           }
           if(e.getSource()==exit) {
        	   System.exit(0);
           }
           if(e.getSource()==save) {
        	   save();
           }if(e.getSource()==open) {
        	   openFile();
           }if(e.getSource()==saveas) {
        	   saveAs();
           }if(e.getSource()==page_setup) {
        	   page_setup();
           }if(e.getSource()==print) {
        	   print_page();
           }if(e.getSource()==cut) {
        	   jta.cut();
           }if(e.getSource()==copy) {
        	   jta.copy();
           }if(e.getSource()==paste) {
        	   jta.paste();
           }if(e.getSource()==replace) {
        	   replaceFrame();
           }if(e.getSource()==jb1) {
        	   replace();
           }if(e.getSource()==date_time) {
        	   setDateTime();
           }if(e.getSource()==font_color) {
        	   setFontColor();
           }if(e.getSource()==textarea_color) {
        	   setTextAreaColor();
           }if(e.getSource()==font) {
        	   openFontFrame();
           }if(e.getSource()==ok) {
        	   setFontToNotepad();
           }if(e.getSource()==word_wrap) {
        	   boolean b=word_wrap.getState();
        	   jta.setLineWrap(b);
           }
	}
	


	public void newNotepad() {
		String text = jta.getText();
		if(!text.equals("")) { // If not empty 
			int i = JOptionPane.showConfirmDialog(jf, "Do u want to save this file ? ");
			if(i==0) {
				saveAs();
				if(!jf.getTitle().equals(title)) {
					setTitle(title);
					jta.setText("");
				}

			}else if(i==1) {
				jta.setText("");
			}
		}
	}
	
	public void saveAs() {
	     jfc = new JFileChooser();
		int i = jfc.showSaveDialog(jf);
		if(i==0) {
			String text=jta.getText();
		     filee = jfc.getSelectedFile();
			try {
				FileOutputStream fos = new FileOutputStream(filee);
				byte[] b=text.getBytes();
				try {
					fos.write(b);
					fos.close();
					
					setTitle(file.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}else {
			JOptionPane.showMessageDialog(jfc, "You didnt saved the File ....","File not Saved !",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void setTitle(String title) {
		jf.setTitle(title);
	}
	
	public void openFile() {

		    jfc = new JFileChooser();
			int ii = jfc.showOpenDialog(jf); // choose a file dialogue  
			if(ii==0) { // If file is selected 
			    jta.setText("");
			    
				try {
					FileInputStream fis = new FileInputStream(jfc.getSelectedFile());// get the selected file name 
					int i ;
					while((i=fis.read())!=-1) {
						jta.append(String.valueOf((char)i));
					}	
					
					fis.close();
					setTitle(jfc.getSelectedFile().getName());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	public void save() {
		if(jf.getTitle().equals(title)) {
			saveAs();
		}else {
					String text=jta.getText();
					byte[] b=text.getBytes();
					FileOutputStream fos;
					try {
						fos = new FileOutputStream(filee);
						fos.write(b);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					 catch (IOException e) {
						e.printStackTrace();
					}
		}
	}
	
	public void page_setup() {
		PrinterJob pj =  PrinterJob.getPrinterJob();
		PageFormat pf= pj.pageDialog(pj.defaultPage());
	}
	
	public void print_page() {
		PrinterJob pj =  PrinterJob.getPrinterJob();
        if(pj.printDialog()) {
   
        		try {
					pj.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}

        }
	}
	
	public void replaceFrame() {
	    jfr = new JFrame("Replace");
		jfr.setSize(500,300);
		jfr.setLayout(null);
		
		jl1 = new JLabel("Find what : ");
		jl1.setBounds(40, 20, 100, 50);
		jfr.add(jl1);
		
		jtf1 = new JTextField();
		jtf1.setBounds(140, 20, 230, 50);
		jfr.add(jtf1);
		
		jl2 = new JLabel("Replace with : ");
		jl2.setBounds(40, 100, 100, 50);
		jfr.add(jl2);
		
		jtf2 = new JTextField();
		jtf2.setBounds(140, 100, 230, 50);
		jfr.add(jtf2);
		
	    jb1 = new JButton("Replace");
		jb1.setBounds(190,180,100,50);
		jfr.add(jb1);
		jb1.addActionListener(this);
		
		jfr.setVisible(true);
		
	}
	
	public void replace() {
		String find_What=jtf1.getText();
		String replace_with=jtf2.getText();
		
		String text = jta.getText();
		
		String new_text=text.replace(find_What, replace_with);
		jta.setText(new_text);
		
		jfr.setVisible(false);
	}
	
	public void setDateTime() {
		LocalDateTime ldt = LocalDateTime.now();
		String date_time= ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		jta.append(date_time);
	}
	
	public void setFontColor() {
		Color c = JColorChooser.showDialog(jf, "Select Font Color ", Color.black);
		jta.setForeground(c);
	}
	
	public void setTextAreaColor() {
		Color c = JColorChooser.showDialog(jf, "Select TextArea Color ", Color.white);
		jta.setBackground(c);
	}
	
	public void openFontFrame() {
		font_Frame = new JFrame("Fonts");
		font_Frame.setSize(500,300);
		font_Frame.setLayout(null);
		
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontFamilies = ge.getAvailableFontFamilyNames();
		 font_family = new JComboBox(fontFamilies);
		font_family.setBounds(20, 40, 200, 40);
		font_Frame.add(font_family);
		
		String[] styles= {"Plain","Bold","Italic","Undderline"};
		 font_style = new JComboBox(styles);
		font_style.setBounds(240, 40, 100, 40);
		font_Frame.add(font_style);
		
		Integer[] sizes = {10,20,30,40,50,60,70,80,90};
		font_size = new JComboBox(sizes);
		font_size.setBounds(355, 40, 100, 40);
		font_Frame.add(font_size);
		
	    ok = new JButton("Ok");
		ok.setBounds(200, 200, 100, 40);
		font_Frame.add(ok);
		ok.addActionListener(this);
		
		font_Frame.setVisible(true);
		
	}
	
	public void setFontToNotepad() {
	     String ffs=(String)font_family.getSelectedItem();	
	     String fst=(String)font_style.getSelectedItem();
	     Integer fs=(Integer)font_size.getSelectedItem();
	     
	     int font_stylee=0;
	     if(fst.equals("Plain")) {
	    	 font_stylee=Font.PLAIN;
	     }else if(fst.equals("Bold")) {
	    	 font_stylee=Font.BOLD;
	     }else if(fst.equals("Italic")) {
	    	 font_stylee=Font.ITALIC;
	     }
	     Font f =  new Font(ffs, font_stylee, fs);
	     jta.setFont(f);
	     
	     font_Frame.setVisible(false);
	}
}
