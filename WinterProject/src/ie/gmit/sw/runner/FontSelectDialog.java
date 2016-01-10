package ie.gmit.sw.runner;

import java.net.URL;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FontSelectDialog extends JPanel {

	private static ClassLoader loader;  // 类的加载器
	private JList style;                // 选择字体
	private JList font;                // 选择字型
	private JList size;                 // 选择字号
	private JScrollPane jsp1;           // 字体
	private JScrollPane jsp2;           // 字型
	private JScrollPane jsp3;           // 字号
	private JTextField jtf1;            // 字体
	private JTextField jtf2;            // 字型
	private JTextField jtf3;            // 字号
	private JLabel jLabel1;             // 字体
	private JLabel jLabel2;             // 字型
	private JLabel jLabel3;             // 字号
	private JLabel jLabel6;             // 显示文字内容
	private JPanel jPanel2;             // 展示区
	private JButton jButton;            // 输入
	static JFrame jf = new JFrame();

	public void start(){
		FontSelectDialog fsDialog = new FontSelectDialog();
		
		jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);;
		jf.getContentPane().add(fsDialog);
		jf.setResizable(false);
		jf.setSize(450, 375);
		jf.setTitle("Font");
		jf.pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = jf.getSize();
		jf.setLocation(
				(screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2
				);
		jf.setVisible(true);

	}
	public FontSelectDialog() {
		setLayout(null);
		setPreferredSize(new Dimension(450, 375));
		initComponent();
	}

	private void initComponent() {

		// 初始化资源
		loader = this.getClass().getClassLoader();

		// 字体选择
		jLabel1 = new JLabel("Font (F):");
		jLabel1.setBounds(20, 15, 80, 17);
		this.add(jLabel1);

		jtf1 = new JTextField("Fixedsys");
		jtf1.setBounds(20, 32, 150, 18);
		this.add(jtf1);

		style = new JList(getFontStyle());
		style.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				jtf1.setText(style.getSelectedValue().toString());
				jLabel6.setFont(new Font(jtf1.getText(), getCharFont(jtf2.getText()), Integer.parseInt(jtf3.getText())));
			}
		});
		jsp1 = new JScrollPane(style);
		jsp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp1.setBounds(20, 50, 150, 140);
		this.add(jsp1);

		// 字型选择
		jLabel2 = new JLabel("Style (St):");
		jLabel2.setBounds(180, 15, 80, 17);
		this.add(jLabel2);

		jtf2 = new JTextField("Plaint");
		jtf2.setBounds(180, 32, 110, 18);
		this.add(jtf2);

		font = new JList(getCharFont());
		font.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				jtf2.setText(font.getSelectedValue().toString());
				jLabel6.setFont(new Font(jtf1.getText(), getCharFont(jtf2.getText()), Integer.parseInt(jtf3.getText())));
			}
		});
		jsp2 = new JScrollPane(font);
		jsp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp2.setBounds(180, 50, 110, 140);
		this.add(jsp2);

		// 字号选择
		jLabel3 = new JLabel("Weight(W):");
		jLabel3.setBounds(300, 15, 50, 17);
		this.add(jLabel3);

		jtf3 = new JTextField("3");
		jtf3.setBounds(300, 32, 50, 18);
		this.add(jtf3);

		size = new JList(getCharSize());
		size.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				jtf3.setText(size.getSelectedValue().toString());
				jLabel6.setFont(new Font(jtf1.getText(), getCharFont(jtf2.getText()), Integer.parseInt(jtf3.getText())));
			}
		});
		jsp3 = new JScrollPane(size);
		jsp3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp3.setBounds(300, 50, 50, 140);
		this.add(jsp3); 

		// 展示文本
		jPanel2 = new JPanel();
		jPanel2.setLayout(null);
		jPanel2.setBorder(BorderFactory.createTitledBorder("Example"));
		jPanel2.setBounds(180, 210, 170, 120);
		this.add(jPanel2);
		jLabel6 = new JLabel("<html>Testing");
		jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel6.setBounds(5, 15, 160, 90);
		jPanel2.add(jLabel6);

		jButton = new JButton("Setting");
	
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runner.setFontJT1(jtf1.getText());
				Runner.setFontJT2(jtf2.getText());
				Runner.setFontJT3(jtf3.getText());
				jf.dispose();
			}
		});

		jButton.setBounds(270, 330, 80, 20);
		this.add(jButton);
	}


	public String[] getFontStyle() {

		Font[] systemFont = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		String[] systemFontString = new String[systemFont.length];
		for(int i=0; i<systemFontString.length; i++) {
			systemFontString[i] = systemFont[i].getName();
			//systemFontString[i] = systemFont[i].getFontName();
		}

		return systemFontString;
	}


	public String[] getCharFont() {

		String[] font = {"PLAIN", "BOLD" , "ITALIC" , "ITALIC + BOLD"};
		return font;
	}

	public int getCharFont(String str) {

		if(str.equals("PLAIN")) {
			return Font.PLAIN;
		}
		else if(str.equals("BOLD")) {
			return Font.BOLD;
		}
		else if(str.equals("ITALIC")){
			return Font.ITALIC;
		}
		else {
			return Font.ITALIC + Font.BOLD;
		}
	}


	public String[] getCharSize() {

		String[] size = new String[70];
		for(int i=0; i<size.length; i++) {
			size[i] = Integer.toString(i+8);
		}
		return size;
	}

}