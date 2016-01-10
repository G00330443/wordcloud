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
	private final String[][] fileENames = { { ".java", "JAVA源程序 文件(*.java)" },
			{ ".jpg", "JPG 文件(*.jpg)" },
			{ ".png", "PNG 文件(*.png)" },
			{ ".txt", "TXT 文件(*.txt)" },
			{ ".gif", "GIF 文件(*.gif)" }
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
		this.setTitle("文件夹选择"); 
		this.setPreferredSize(new Dimension(200, 100)); 
		//this.getContentPane().add(button, BorderLayout.NORTH); 
		//button.addActionListener(this); 
		this.pack(); 
		this.setLocationRelativeTo(null); 
		this.setIgnoreRepaint(true); 
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setAcceptAllFileFilterUsed(false);
		showFiles();
		fileChooser.setDialogTitle("打开文件夹"); 
		int ret = fileChooser.showOpenDialog(null); 
		if (ret == JFileChooser.APPROVE_OPTION) { 
			//文件夹路径 
			setFileAddress(fileChooser.getSelectedFile().getAbsolutePath()); 
		} 
	}
	public void showFiles(){
		// 显示所有文件
		fileChooser.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File file) {
				return true;
			}
			public String getDescription() {
				return "所有文件(*.*)";
			}
		});

		// 循环添加需要显示的文件
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
