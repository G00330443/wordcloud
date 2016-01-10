package ie.gmit.sw.runner;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import javax.swing.*;

import ie.gmit.image.AngleGenerator;
import ie.gmit.word.showtimes.WordTimes;



public class Runner extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contain=new JPanel(new GridLayout(4,1));
	private JPanel Naming=new JPanel(new BorderLayout(10, 10));
	private JPanel jpNam=new JPanel(new BorderLayout(10,10));
	private JPanel jpTT=new JPanel(new GridLayout(1, 2));
	private JTextField FileNameJTF=new JTextField();

	private JLabel imageJL=new JLabel();
	private JTextArea txtJTF=new JTextArea();
	private static String NewImageName;
	private static boolean flag=false;
	
	
	
	//字体栏
	private static JPanel FontContainer=new JPanel(new GridLayout(1,7));
	
	private JLabel FontJL1=new JLabel("Font :");
	private JLabel FontJL2=new JLabel("Style :");
	private JLabel FontJL3=new JLabel("Set Min Weight :");
	private static JTextField FontJT1=new JTextField("TimesRoman");

	private static JTextField FontJT2=new JTextField("Bold");
	
	private static JTextField FontJT3=new JTextField("3");
	
	//新的按钮
	
	private JButton FontChoose=new JButton("Choose Font");
	
	static FontSelectDialog fsd=new FontSelectDialog();
		
	
	public static String getFontJT1() {
		return FontJT1.getText();
	}
	public static int getFontJT2() {
		return fsd.getCharFont(FontJT2.getText());
	}
	public static int getFontJT3() {
		return Integer.parseInt(FontJT3.getText());
	}


	public static void setFontJT1(String fontJT1) {
		FontJT1.setText( fontJT1);
	}
	public static void setFontJT2(String fontJT2) {
		FontJT2.setText( fontJT2);
		}
	public static void setFontJT3(String fontJT3) {
		FontJT3.setText( fontJT3);
		}

	public void FontChooseButton(){
		FontChoose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fsd.start();
			}
		});
	}
	public void addCom(){
	/*	
		FontJL1.setPreferredSize(new Dimension(50,10));
		FontJL2.setPreferredSize(new Dimension(50,10));
		FontJL3.setPreferredSize(new Dimension(50,10));
		*/
		FontContainer.add(FontJL1);
		FontContainer.add(FontJT1);
		FontContainer.add(FontJL2);
		FontContainer.add(FontJT2);
		FontContainer.add(FontJL3);
		FontContainer.add(FontJT3);

		FontContainer.add(FontChoose);
		
	}
	
	//角度设置
	private static AngleGenerator ag=new AngleGenerator();
	private JLabel AngleJL1=new JLabel("angle1");
	private JLabel AngleJL2=new JLabel("angle2");
	private static JTextField AngleJT1=new JTextField("-90");
	private static JTextField AngleJT2=new JTextField("90");
	 
	public static void setAngleJT1(int a) {
		int angle=a;
		AngleJT1.setText(""+a);
	}
	
	public static int getAngleJT1(){
		return Integer.parseInt( AngleJT1.getText());
	}
	
	public static void setAngleJT2(int a) {
		int angle=a;
		AngleJT2.setText(""+a);
	}
	
	public static int getAngleJT2(){
		return Integer.parseInt( AngleJT2.getText());
	}
	
	//设置颜色选项
	private static HashMap list_color=new HashMap();


	public static HashMap getList_color() {
		return list_color;
	}

	public static void setList_color(HashMap list_color) {
		Runner.list_color = list_color;
	}
	private JPanel ColorJP=new JPanel(new GridLayout(1,9));
	private JButton colorChoose1=new JButton("Color1");
	private JButton colorChoose2 =new JButton("Color2");
	private JButton colorChoose3 =new JButton("Color3");
	private JButton colorChoose4 =new JButton("Color4");
	private JButton colorChoose5 =new JButton("Color5");
	private JButton colorChoose6 =new JButton("Color6");

	public void ColorSet(){
		ColorJP.add(colorChoose1);
		ColorJP.add(colorChoose2);
		ColorJP.add(colorChoose3);
		ColorJP.add(colorChoose4);
		ColorJP.add(colorChoose5);
		ColorJP.add(colorChoose6);
		ColorJP.add(AngleJL1);
		ColorJP.add(AngleJT1);
		ColorJP.add(AngleJL2);
		ColorJP.add(AngleJT2);
	}
	private Color c ;
	public void ColorCheck(){

		colorChoose1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = JColorChooser.showDialog(null, "Color1", colorChoose1.getBackground());
				if (c != null)
					colorChoose1.setBackground(c);

				list_color.put(1, c);
			}
		});

		colorChoose2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = JColorChooser.showDialog(null, "Color1", colorChoose2.getBackground());
				if (c != null)
					colorChoose2.setBackground(c);

				list_color.put(2, c);
			}

		});

		colorChoose3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = JColorChooser.showDialog(null, "Color1", colorChoose3.getBackground());
				if (c != null)
					colorChoose3.setBackground(c);

				list_color.put(3, c);
			}

		});

		colorChoose4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = JColorChooser.showDialog(null, "Color1", colorChoose4.getBackground());
				if (c != null)
					colorChoose4.setBackground(c);

				list_color.put(4, c);
			}

		});

		colorChoose5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = JColorChooser.showDialog(null, "Color1", colorChoose5.getBackground());
				if (c != null)
					colorChoose5.setBackground(c);

				list_color.put(5, c);
			}

		});


		colorChoose6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = JColorChooser.showDialog(null, "Color1", colorChoose6.getBackground());
				if (c != null){
					colorChoose6.setBackground(c);
				}
				list_color.put(6, c);
			}

		});

	}

	//查询文件按钮
	private JLabel fileChoose=new JLabel("Please Enter File Address Or URL : ") ;
	private JButton Browser=new JButton("Browser");  

	//设置开始按钮
	private JPanel JPContainerStart=new JPanel(new BorderLayout(10, 10));
	private JButton Start=new JButton("Start");

	//判断是否生成图片
	public static boolean isFlag() {
		return flag;
	}

	public static void setFlag(boolean flag) {
		Runner.flag = flag;
	}

	//naming new image
	private JLabel namingJL=new JLabel("Please  Enter  New Image Name :   ");
	private JTextField namingJTF=new JTextField();

	public static String getNewImageName() {
		return NewImageName;
	}

	public void setNewImageName(String newImageName) {
		NewImageName = newImageName;
	}

	private ImageIcon flagImage;
	private String txt;
	private static String FileName ;

	//搜索框
	private Search search=new Search();


	//背景选择
	private JRadioButton jchx1=new JRadioButton("Cricle",false);
	private JRadioButton jchx2=new JRadioButton("Rectangle",false);
	private JRadioButton jchx3=new JRadioButton("Hexagon",false);
	private JRadioButton jchx4=new JRadioButton("Donut ",false);
	private JRadioButton jchx5=new JRadioButton("Triangle ",false);
	private JRadioButton jchx6=new JRadioButton("Choose Other Image : ",false);
	private ButtonGroup group = new ButtonGroup();

	private JPanel BackgroungChooseContainer =new JPanel(new BorderLayout());
	private JLabel BackgroundChooseTitle=new JLabel("Please Choose BackGround From The Following Options");

	private JPanel BackgroundChoose=new JPanel(new GridLayout(2,4));
	public void setBackground(){

		group.add(jchx1);

		group.add(jchx2);

		group.add(jchx3);

		group.add(jchx4);

		group.add(jchx5);

		group.add(jchx6);

		BackgroundChoose.add(jchx1);

		BackgroundChoose.add(jchx2);

		BackgroundChoose.add(jchx3);

		BackgroundChoose.add(jchx4);

		BackgroundChoose.add(jchx5);

		BackgroundChoose.add(jchx6);

		BackgroundChooseTitle.setFont(new   java.awt.Font("Dialog",   1,   15));  

		BackgroungChooseContainer.add(BackgroundChoose,BorderLayout.CENTER);
		BackgroungChooseContainer.add(BackgroundChooseTitle,BorderLayout.NORTH);
		//	BackgroundChoose.add(jchx4);
	}
	private static String ChooseImageName;

	public static String getChooseImageName() {
		return ChooseImageName;
	}

	public static void setChooseImageName(String chooseImageName) {
		ChooseImageName = chooseImageName;
	}
	//检查是否为自选图形
	private static boolean checkFlag=false;
	public static boolean isCheckFlag() {
		return checkFlag;
	}

	public static void setCheckFlag(boolean checkFlag) {
		checkFlag = checkFlag;
	}

	private int numberBackgroung; 

	public int chooseBackground(){
		jchx1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setChooseImageName("cricle.png");
				//numberBackgroung=1;
			}
		});

		jchx2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setChooseImageName("Rectangle.png");
				//numberBackgroung=2;
			}
		});

		jchx3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setChooseImageName("hec1.png");
				//	numberBackgroung=3;
			}
		});

		jchx4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setChooseImageName("ring.png");
				//numberBackgroung=4;
			}
		});

		jchx5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setChooseImageName("triangle.png");
				//numberBackgroung=5;
			}
		});

		jchx6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				search.Start();
				search.getFileAddress();
				checkFlag=true;
				//numberBackgroung=6;
				setChooseImageName(search.getFileAddress());
			}
		});
		return numberBackgroung;
	}

	//获取文件名
	public static String getFileName() {
		return FileName;
	}

	public static void setFileName(String fileName) {

		FileName = fileName;
	}
	//网址验证
	public static boolean checkUrl(String url){ 
		return url.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" );
	}


	//	JComboBox jcbSize;
	public Runner() throws IOException{

		setLayout();
		setBackground();
		chooseBackground();
		ColorCheck();
		FontChooseButton();
		
		Browser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				search.Start();

				FileNameJTF.setText(search.getFileAddress());

				System.out.println(FileNameJTF.getText());

			}
		});
		Start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				NewImageName=namingJTF.getText();
				setList_color(list_color);
				//	System.out.println("------------"+namingJTF.getText());
				setNewImageName(NewImageName);

				setAngleJT1(Integer.parseInt( AngleJT1.getText()));
				setAngleJT2(Integer.parseInt( AngleJT2.getText()));
				
				if(AngleJT1.getText()==null ){
					setAngleJT1(-90);
				}
				if(AngleJT2.getText()==null){
					setAngleJT1(90);
				}
				
				setFileName(FileNameJTF.getText());
				System.out.println(FileNameJTF.getText());

				WordCloudTest wct=new WordCloudTest();
				
					try {
						wct.Test();	

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					File file=new File(FileNameJTF.getText());
					
					
					if(!checkUrl(FileNameJTF.getText())){
					if(!file.exists()){
						JOptionPane.showMessageDialog( jpNam, this,"File Not Exist", getDefaultCloseOperation());
					}else{
						//	File file1=new File(NewImageName);
						if(flag==true){
							flagImage=new ImageIcon(""+NewImageName);
							imageJL.setIcon(flagImage);

							txt=readTxt(file.toString());
							txtJTF.setText(txt);
						}
					}
				}else{
					if(flag==true){
						flagImage=new ImageIcon(""+NewImageName);
						imageJL.setIcon(flagImage);
						WordTimes w=new WordTimes();
						try {
							txt=w.load(new URL(FileNameJTF.getText())).toString();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						txtJTF.setText(txt);
					}
				}
			}
		});
	}

	public String readTxt(String fileAdd){
		String txtContent = null;
		try {

			File file=new File(fileAdd);
			if(file.isFile() && file.exists()){ 
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file));//考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null){
					txtContent=txtContent+"\n"+lineTxt;
					System.out.println(lineTxt);
				}
				read.close();
			}else{
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return txtContent;
	}

	//设置框架
	public void setLayout(){
		//设置字体
		namingJL.setFont(new   java.awt.Font("Dialog",   1,   15));  
		fileChoose.setFont(new   java.awt.Font("Dialog",   1,   15)); 
		FontJL1.setFont(new   java.awt.Font("Dialog",   1,   15)); 
		FontJL2.setFont(new   java.awt.Font("Dialog",   1,   15)); 
		FontJL3.setFont(new   java.awt.Font("Dialog",   1,   15)); 
		AngleJL1.setFont(new   java.awt.Font("Dialog",   1,   15)); 
		AngleJL2.setFont(new   java.awt.Font("Dialog",   1,   15)); 
		
		
		Start.setPreferredSize(new Dimension(150,50));
		
		
		jpNam.add(fileChoose,BorderLayout.WEST);
		jpNam.add(FileNameJTF,BorderLayout.CENTER);
		jpNam.add(Browser, BorderLayout.EAST);
		//	jpNam.add(colorChoose);

		jpNam.setBorder(BorderFactory.createEtchedBorder());

		Naming.add(namingJL,BorderLayout.WEST);
		Naming.add(namingJTF);

		contain.add(jpNam);
		contain.add(Naming);

		//颜色
		ColorSet();
		contain.add(ColorJP);
		
		//下拉列表 字体
		addCom();
		contain.add(FontContainer);

		//设置开始
		JPContainerStart.add(Start,BorderLayout.EAST);
		JPContainerStart.add(contain, BorderLayout.CENTER);


		add(JPContainerStart,BorderLayout.NORTH);

	
		txtJTF.setBorder(BorderFactory.createEtchedBorder());
		txtJTF.setLineWrap(true);//激活自动换行功能 
		txtJTF.setWrapStyleWord(true);//激活断行不断字功能 
        jpTT.setBackground(Color.white);
		jpTT.add(new   JScrollPane(txtJTF),BorderLayout.WEST);
		
		jpTT.add(imageJL,BorderLayout.EAST);
		add(jpTT, BorderLayout.CENTER);
		add(BackgroungChooseContainer,BorderLayout.SOUTH);

	}

	public static void setFrame() throws IOException{
		Runner frame=new Runner();
		frame.setTitle("WordCloud");
		frame.setSize(1400, 700);
		frame.setLocationRelativeTo(null);
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	//	frame.setBackground(new ImageIcon("whale.png").getImage());
		frame.addWindowListener(new WindowAdapter() {
	          public void windowClosing(WindowEvent e) {
	           System.exit(0);
	          }
	      });
	}
	public static void main(String[] args) throws IOException{
		setFrame();
		System.out.println(getFileName());
	}
	
	class BackgroundPanel extends JPanel  
	{  
	    Image im;  
	    public BackgroundPanel(Image im)  
	    {  
	        this.im=im;  
	        this.setOpaque(true);  
	    }  
	    //Draw the back ground.  
	    public void paintComponent(Graphics g)  
	    {  
	        super.paintComponents(g);  
	        g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);  
	          
	    }  
	}
	
	//Button
	
}
