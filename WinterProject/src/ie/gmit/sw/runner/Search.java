package ie.gmit.sw.runner;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JFileChooser; 
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter; 

public class Search extends JFrame{ 
	/** * */ 
	private static final long serialVersionUID = 1L; 
	private JFileChooser fileChooser = new JFileChooser(); 
	private final String[][] fileENames = { { ".java", "JAVAԴ���� �ļ�(*.java)" },
			{ ".jpg", "JPG �ļ�(*.jpg)" },
			{ ".png", "PNG �ļ�(*.png)" },
			{ ".txt", "TXT �ļ�(*.txt)" },
			{ ".gif", "GIF �ļ�(*.gif)" }
	};
	private static String FileAddress;
	
	public static String getFileAddress() {
		return FileAddress;
	}

	public static void setFileAddress(String fileAddress) {
		FileAddress = fileAddress;
	}

	public Search() { 
		
	} 
	public void Start(){
		this.setTitle("�ļ���ѡ��"); 
		this.setPreferredSize(new Dimension(200, 100)); 
		//this.getContentPane().add(button, BorderLayout.NORTH); 
		//button.addActionListener(this); 
		this.pack(); 
		this.setLocationRelativeTo(null); 
		this.setIgnoreRepaint(true); 
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setAcceptAllFileFilterUsed(false);
		showFiles();
		fileChooser.setDialogTitle("���ļ���"); 
		int ret = fileChooser.showOpenDialog(null); 
		if (ret == JFileChooser.APPROVE_OPTION) { 
			//�ļ���·�� 
			setFileAddress(fileChooser.getSelectedFile().getAbsolutePath()); 
		} 
	}
	public void showFiles(){
		// ��ʾ�����ļ�
		fileChooser.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File file) {
				return true;
			}
			public String getDescription() {
				return "�����ļ�(*.*)";
			}
		});

		// ѭ�������Ҫ��ʾ���ļ�
		for (final String[] fileEName : fileENames) {

			fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

				public boolean accept(File file) { 

					if (file.getName().endsWith(fileEName[0]) || file.isDirectory()) {

						return true;
					}

					return false;
				}

				public String getDescription() {

					return fileEName[1];
				}

			});
		}
	}

}
