package ie.gmit.sw.runner;

import java.awt.Color;  
import java.awt.Container;  
import java.awt.Dimension;  
import java.awt.FlowLayout;  
import java.awt.Font;  
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.RadialGradientPaint;  
import java.awt.RenderingHints;  
import java.awt.Shape;  
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;  
import java.awt.font.LineMetrics;  
import java.awt.geom.Ellipse2D;  
import java.awt.geom.Point2D;  
import java.awt.geom.Rectangle2D;  
import java.awt.geom.RoundRectangle2D;  
import java.awt.image.BufferedImage;  
import java.io.IOException;  
  
import javax.imageio.ImageIO;  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.UIManager;  
import javax.swing.UIManager.LookAndFeelInfo;  
import javax.swing.UnsupportedLookAndFeelException;  
  
@SuppressWarnings("serial")  
public class Button_module extends JFrame {  
  
    public Button_module() {  
        for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {  
            if (laf.getName().equals("Nimbus")) {  
                try {  
                    UIManager.setLookAndFeel(laf.getClassName());  
                } catch (ClassNotFoundException | InstantiationException  
                        | IllegalAccessException  
                        | UnsupportedLookAndFeelException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            }  
        }  
        // TODO Auto-generated constructor stub  
        Container c = getContentPane();  
        c.setLayout(new FlowLayout());  
  
        final JButton button = new MyButton("button 2");  
  
        c.add(button);  
        JButton button2 = new JButton("button 2");  
        c.add(button2);  
        button2.setBackground(Color.blue);  
  
        JButton button3 = new MyButton2("Cancel");  
        c.add(button3);  
  
        // 完全重绘的Button，其Text的HTML设置特性消失  
        // JButton button4 = new  
        // MyButton3("<html><font size=12>Sub</font></html>");  
        JButton button4 = new MyButton3("Sub");  
        // button4.setFont(new Font("Serif", Font.PLAIN, 14));  
        c.add(button4);  
    }  
  
    private class MyButton extends JButton {  
        private String text;  
        private String state = "normal";  
        // private String state = "focused";  
        // private String state = "pressed";  
        // private String state = "released";  
  
        Shape shape;  
  
        // 无参构造继承时自动调用，而有参构造继承时则需手动重写  
        MyButton(String text) {  
            // super("<html><font size=5>" + text + "</font></html>");  
            super(text);  
            this.text = text;  
  
            // 下 面的代码块若是放到下面的paintComponent()方法里则Swing界面初始化时，  
            // 布局管理器还是采用的是系统默认的PreferredSize。因为构造函数要优先于  
            // paintComponent()方法执行。  
            Dimension preferredSize = getPreferredSize();  
            Dimension preferredSizeNew = new Dimension(preferredSize.width,  
                    preferredSize.width);  
            setPreferredSize(preferredSizeNew);  
        }  
  
        @Override  
        protected void paintComponent(Graphics g) {  
            Graphics2D g2 = (Graphics2D) g;  
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
                    RenderingHints.VALUE_ANTIALIAS_ON);  
  
            int width = this.getPreferredSize().width;  
            int height = this.getPreferredSize().height;  
  
            if (state.equals("normal")) {  
                // draw background pattern  
                Point2D center = new Point2D.Float(width / 2, height / 2);  
                float radius = height / 2;  
                float[] dist = { 0.0f, 1.0f };  
                Color[] colors = { new Color(0, 0, 0, 255),  
                        new Color(255, 255, 255, 0) };  
                RadialGradientPaint paint = new RadialGradientPaint(center,  
                        radius, dist, colors);  
                g2.setPaint(paint);  
                shape = new Ellipse2D.Double(width / 2 - height / 2, 0, height,  
                        height);  
                g2.fill(shape);  
                // draw string text  
                g2.setColor(Color.RED);  
                Font defaultFont = getFont();  
                g2.setFont(defaultFont);  
                Rectangle2D rect = defaultFont.getStringBounds(text,  
                        g2.getFontRenderContext());  
                LineMetrics lineMetrics = defaultFont.getLineMetrics(text,  
                        g2.getFontRenderContext());  
                g2.drawString(  
                        text,  
                        (float) (width / 2 - rect.getWidth() / 2),  
                        (float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics  
                                .getDescent()) / 2 - lineMetrics.getDescent())));  
  
            } else if (state.equals("focused")) {  
                // draw background pattern  
                Point2D center = new Point2D.Float(width / 2, height / 2);  
                float radius = height / 2;  
                float[] dist = { 0.2f, 1.0f };  
                Color[] colors = { new Color(0, 0, 0, 255),  
                        new Color(255, 255, 255, 0) };  
                RadialGradientPaint paint = new RadialGradientPaint(center,  
                        radius, dist, colors);  
                g2.setPaint(paint);  
                g2.fill(new Ellipse2D.Double(width / 2 - height / 2, 0, height,  
                        height));  
                // draw string text  
                g2.setColor(Color.RED);  
                Font defaultFont = getFont();  
                g2.setFont(defaultFont);  
                Rectangle2D rect = defaultFont.getStringBounds(text,  
                        g2.getFontRenderContext());  
                LineMetrics lineMetrics = defaultFont.getLineMetrics(text,  
                        g2.getFontRenderContext());  
                g2.drawString(  
                        text,  
                        (float) (width / 2 - rect.getWidth() / 2),  
                        (float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics  
                                .getDescent()) / 2 - lineMetrics.getDescent())));  
            } else if (state.equals("pressed")) {  
                // draw background pattern  
                int offsetCenter = 1;  
                Point2D center = new Point2D.Float(width / 2 + offsetCenter,  
                        height / 2 + offsetCenter);  
                float radius = height / 2;  
                float[] dist = { 0.2f, 1.0f };  
                Color[] colors = { new Color(0, 0, 0, 255),  
                        new Color(255, 255, 255, 0) };  
                RadialGradientPaint paint = new RadialGradientPaint(center,  
                        radius, dist, colors);  
                g2.setPaint(paint);  
                g2.fill(new Ellipse2D.Double(width / 2 - height / 2  
                        + offsetCenter, offsetCenter, height, height));  
                // draw string text  
                g2.setColor(Color.RED);  
                Font defaultFont = getFont();  
                g2.setFont(defaultFont);  
                Rectangle2D rect = defaultFont.getStringBounds(text,  
                        g2.getFontRenderContext());  
                LineMetrics lineMetrics = defaultFont.getLineMetrics(text,  
                        g2.getFontRenderContext());  
                g2.drawString(  
                        text,  
                        (float) (width / 2 - rect.getWidth() / 2)  
                                + offsetCenter,  
                        (float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics  
                                .getDescent()) / 2 - lineMetrics.getDescent()))  
                                + offsetCenter);  
            } else if (state.equals("released")) {  
                // draw background pattern  
                Point2D center = new Point2D.Float(width / 2, height / 2);  
                float radius = height / 2;  
                float[] dist = { 0.2f, 1.0f };  
                Color[] colors = { new Color(0, 0, 0, 255),  
                        new Color(255, 255, 255, 0) };  
                RadialGradientPaint paint = new RadialGradientPaint(center,  
                        radius, dist, colors);  
                g2.setPaint(paint);  
                g2.fill(new Ellipse2D.Double(width / 2 - height / 2, 0, height,  
                        height));  
                // draw string text  
                g2.setColor(Color.RED);  
                Font defaultFont = getFont();  
                g2.setFont(defaultFont);  
                Rectangle2D rect = defaultFont.getStringBounds(text,  
                        g2.getFontRenderContext());  
                LineMetrics lineMetrics = defaultFont.getLineMetrics(text,  
                        g2.getFontRenderContext());  
                g2.drawString(  
                        text,  
                        (float) (width / 2 - rect.getWidth() / 2),  
                        (float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics  
                                .getDescent()) / 2 - lineMetrics.getDescent())));  
            }  
  
            addMouseListener(new MouseAdapter() {  
  
                @Override  
                public void mouseEntered(MouseEvent e) {  
                    // TODO Auto-generated method stub  
                    System.out.println("光标移入组件");  
                    state = "focused";  
                    repaint();  
                }  
  
                @Override  
                public void mouseExited(MouseEvent e) {  
                    // TODO Auto-generated method stub  
                    System.out.println("光标移出组件");  
                    state = "normal";  
                    repaint();  
                }  
  
                @Override  
                public void mousePressed(MouseEvent e) {  
                    // TODO Auto-generated method stub  
                    System.out.print("鼠标按键被按下，");  
                    state = "pressed";  
                    repaint();  
                }  
  
                @Override  
                public void mouseReleased(MouseEvent e) {  
                    // TODO Auto-generated method stub  
                    System.out.print("鼠标按键被释放，");  
                    state = "released";  
                    repaint();  
                }  
  
            });  
  
        }  
  
        // Gives the UI delegate an opportunity to define the precise shape of  
        // this component for the sake of mouse processing.  
        @Override  
        public boolean contains(int x, int y) {  
            if (shape.contains(x, y)) {  
                return true;  
            } else {  
                return false;  
            }  
        }  
    }  
  
    private class MyButton2 extends JButton {  
        private String text;  
        private String state = "normal";  
        // private String state = "focused";  
        // private String state = "pressed";  
        // private String state = "released";  
          
        Shape shape;  
  
        // Initialize the size of the button according to the length and width  
        // of the text string  
        MyButton2(String text) {  
            super(text);  
            this.text = text;  
        }  
  
        @Override  
        protected void paintComponent(Graphics g) {  
            Graphics2D g2 = (Graphics2D) g;  
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
                    RenderingHints.VALUE_ANTIALIAS_ON);  
            int width = this.getWidth();  
            int height = this.getHeight();  
  
            if (state.equals("normal")) {  
                // draw background pattern  
                Point2D center = new Point2D.Float(width / 2, height / 2);  
                float radius = width / 2;  
                float[] dist = { 0.0f, 0.8f };  
                Color[] colors = { new Color(255, 255, 255, 0),  
                        new Color(0, 0, 0, 255) };  
                RadialGradientPaint paint = new RadialGradientPaint(center,  
                        radius, dist, colors);  
                g2.setPaint(paint);  
                shape = new RoundRectangle2D.Double(0, 0, width, height,  
                        height, height);  
                g2.fill(shape);  
                // draw string text  
                Font defaultFont = getFont();  
                g2.setFont(defaultFont);  
                g2.setColor(Color.BLACK);  
                Rectangle2D rect = defaultFont.getStringBounds(text,  
                        g2.getFontRenderContext());  
                LineMetrics lineMetrics = defaultFont.getLineMetrics(text,  
                        g2.getFontRenderContext());  
                g2.drawString(  
                        text,  
                        (float) (width / 2 - rect.getWidth() / 2),  
                        (float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics  
                                .getDescent()) / 2 - lineMetrics.getDescent())));  
            } else if (state.equals("focused")) {  
                // draw background pattern  
                Point2D center = new Point2D.Float(width / 2, height / 2);  
                float radius = width / 2;  
                float[] dist = { 0.0f, 0.8f };  
                Color[] colors = { new Color(255, 255, 255, 0),  
                        new Color(20, 20, 20, 255) };  
                RadialGradientPaint paint = new RadialGradientPaint(center,  
                        radius, dist, colors);  
                g2.setPaint(paint);  
                g2.fill(new RoundRectangle2D.Double(0, 0, width, height,  
                        height, height));  
                // draw string text  
                Font defaultFont = getFont();  
                g2.setFont(defaultFont);  
                g2.setColor(Color.BLACK);  
                Rectangle2D rect = defaultFont.getStringBounds(text,  
                        g2.getFontRenderContext());  
                LineMetrics lineMetrics = defaultFont.getLineMetrics(text,  
                        g2.getFontRenderContext());  
                g2.drawString(  
                        text,  
                        (float) (width / 2 - rect.getWidth() / 2),  
                        (float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics  
                                .getDescent()) / 2 - lineMetrics.getDescent())));  
            } else if (state.equals("pressed")) {  
                Point2D center = new Point2D.Float(width / 2, height / 2);  
                float radius = width / 2;  
                float[] dist = { 0.0f, 1.0f };  
                Color[] colors = { new Color(255, 255, 255, 0),  
                        new Color(20, 20, 20, 255) };  
                RadialGradientPaint paint = new RadialGradientPaint(center,  
                        radius, dist, colors);  
                g2.setPaint(paint);  
                g2.fill(new RoundRectangle2D.Double(0, 0, width, height,  
                        height, height));  
                // draw string text  
                Font defaultFont = getFont();  
                g2.setFont(defaultFont);  
                g2.setColor(Color.BLACK);  
                Rectangle2D rect = defaultFont.getStringBounds(text,  
                        g2.getFontRenderContext());  
                LineMetrics lineMetrics = defaultFont.getLineMetrics(text,  
                        g2.getFontRenderContext());  
                g2.drawString(  
                        text,  
                        (float) (width / 2 - rect.getWidth() / 2),  
                        (float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics  
                                .getDescent()) / 2 - lineMetrics.getDescent())));  
            } else if (state.equals("released")) {  
                Point2D center = new Point2D.Float(width / 2, height / 2);  
                float radius = width / 2;  
                float[] dist = { 0.0f, 0.8f };  
                Color[] colors = { new Color(255, 255, 255, 0),  
                        new Color(20, 20, 20, 255) };  
                RadialGradientPaint paint = new RadialGradientPaint(center,  
                        radius, dist, colors);  
                g2.setPaint(paint);  
                g2.fill(new RoundRectangle2D.Double(0, 0, width, height,  
                        height, height));  
                // draw string text  
                Font defaultFont = getFont();  
                g2.setFont(defaultFont);  
                g2.setColor(Color.BLACK);  
                Rectangle2D rect = defaultFont.getStringBounds(text,  
                        g2.getFontRenderContext());  
                LineMetrics lineMetrics = defaultFont.getLineMetrics(text,  
                        g2.getFontRenderContext());  
                g2.drawString(  
                        text,  
                        (float) (width / 2 - rect.getWidth() / 2),  
                        (float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics  
                                .getDescent()) / 2 - lineMetrics.getDescent())));  
            }  
  
            addMouseListener(new MouseAdapter() {  
  
                @Override  
                public void mouseEntered(MouseEvent e) {  
                    // TODO Auto-generated method stub  
                    System.out.println("光标移入组件");  
                    state = "focused";  
                    repaint();  
                }  
  
                @Override  
                public void mouseExited(MouseEvent e) {  
                    // TODO Auto-generated method stub  
                    System.out.println("光标移出组件");  
                    state = "normal";  
                    repaint();  
                }  
  
                @Override  
                public void mousePressed(MouseEvent e) {  
                    // TODO Auto-generated method stub  
                    System.out.print("鼠标按键被按下，");  
                    state = "pressed";  
                    repaint();  
                }  
  
                @Override  
                public void mouseReleased(MouseEvent e) {  
                    // TODO Auto-generated method stub  
                    System.out.print("鼠标按键被释放，");  
                    state = "released";  
                    repaint();  
                }  
  
            });  
  
        }  
          
        @Override  
        public boolean contains(int x, int y) {  
            if (shape.contains(x, y)) {  
                return true;  
            } else {  
                return false;  
            }  
        }  
    }  
  
    private class MyButton3 extends JButton {  
        private String text;  
        private String state = "normal";  
  
        // private String state = "focused";  
        // private String state = "pressed";  
        // private String state = "released";  
  
        Shape shape;  
          
        // Initialize the size of the button according to the length and width  
        // of the text string  
        MyButton3(String text) {  
            super(text);  
            this.text = text;  
        }  
  
        @Override  
        protected void paintComponent(Graphics g) {  
            Graphics2D g2 = (Graphics2D) g;  
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  
                    RenderingHints.VALUE_ANTIALIAS_ON);  
            int width = this.getWidth();  
            int height = this.getHeight();  
  
            if (state.equals("normal")) {  
                Point2D center = new Point2D.Float(width / 2, height / 2);  
                float radius = width / 2;  
                float[] dist = { 0.0f, 0.8f };  
                Color[] colors = { new Color(255, 255, 255, 0),  
                        new Color(0, 0, 0, 255) };  
                RadialGradientPaint paint = new RadialGradientPaint(center,  
                        radius, dist, colors);  
                g2.setPaint(paint);  
                shape = new RoundRectangle2D.Double(0, 0, width, height,  
                        height, height);  
                g2.fill(shape);  
                // draw string text  
                Font defaultFont = getFont();  
                g2.setFont(defaultFont);  
                g2.setColor(Color.BLACK);  
                Rectangle2D rect = defaultFont.getStringBounds(text,  
                        g2.getFontRenderContext());  
                LineMetrics lineMetrics = defaultFont.getLineMetrics(text,  
                        g2.getFontRenderContext());  
                g2.drawString(  
                        text,  
                        (float) (width / 2 - rect.getWidth() / 2),  
                        (float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics  
                                .getDescent()) / 2 - lineMetrics.getDescent())));  
            } else if (state.equals("focused")) {  
                Point2D center = new Point2D.Float(width / 2, height / 2);  
                float radius = width / 2;  
                float[] dist = { 0.0f, 0.8f };  
                Color[] colors = { new Color(255, 255, 255, 0),  
                        new Color(20, 20, 20, 255) };  
                RadialGradientPaint paint = new RadialGradientPaint(center,  
                        radius, dist, colors);  
                g2.setPaint(paint);  
                g2.fill(new RoundRectangle2D.Double(0, 0, width, height,  
                        height, height));  
                // draw string text  
                Font defaultFont = getFont();  
                g2.setFont(defaultFont);  
                g2.setColor(Color.BLACK);  
                Rectangle2D rect = defaultFont.getStringBounds(text,  
                        g2.getFontRenderContext());  
                LineMetrics lineMetrics = defaultFont.getLineMetrics(text,  
                        g2.getFontRenderContext());  
                g2.drawString(  
                        text,  
                        (float) (width / 2 - rect.getWidth() / 2),  
                        (float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics  
                                .getDescent()) / 2 - lineMetrics.getDescent())));  
            } else if (state.equals("pressed")) {  
                g2.setColor(new Color(0, 147, 255));  
                g2.fill(new RoundRectangle2D.Double(0, 0, width, height,  
                        height, height));  
                Point2D center = new Point2D.Float(width / 2, height / 2);  
                float radius = width / 2;  
                float[] dist = { 0.0f, 1.0f };  
                Color[] colors = { new Color(255, 255, 255, 0),  
                        new Color(20, 20, 20, 255) };  
                RadialGradientPaint paint = new RadialGradientPaint(center,  
                        radius, dist, colors);  
                g2.setPaint(paint);  
                double borderWidth = 2.0f; // 2 pixels  
                g2.fill(new RoundRectangle2D.Double(borderWidth, borderWidth,  
                        width - borderWidth * 2.0f,  
                        height - borderWidth * 2.0f, height - borderWidth  
                                * 2.0f, height - borderWidth * 2.0f));  
                // draw string text  
                Font defaultFont = getFont();  
                g2.setFont(defaultFont);  
                g2.setColor(Color.BLACK);  
                Rectangle2D rect = defaultFont.getStringBounds(text,  
                        g2.getFontRenderContext());  
                LineMetrics lineMetrics = defaultFont.getLineMetrics(text,  
                        g2.getFontRenderContext());  
                g2.drawString(  
                        text,  
                        (float) (width / 2 - rect.getWidth() / 2),  
                        (float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics  
                                .getDescent()) / 2 - lineMetrics.getDescent())));  
            } else if (state.equals("released")) {  
                Point2D center = new Point2D.Float(width / 2, height / 2);  
                float radius = width / 2;  
                float[] dist = { 0.0f, 0.8f };  
                Color[] colors = { new Color(255, 255, 255, 0),  
                        new Color(20, 20, 20, 255) };  
                RadialGradientPaint paint = new RadialGradientPaint(center,  
                        radius, dist, colors);  
                g2.setPaint(paint);  
                g2.fill(new RoundRectangle2D.Double(0, 0, width, height,  
                        height, height));  
                // draw string text  
                Font defaultFont = getFont();  
                g2.setFont(defaultFont);  
                g2.setColor(Color.BLACK);  
                Rectangle2D rect = defaultFont.getStringBounds(text,  
                        g2.getFontRenderContext());  
                LineMetrics lineMetrics = defaultFont.getLineMetrics(text,  
                        g2.getFontRenderContext());  
                g2.drawString(  
                        text,  
                        (float) (width / 2 - rect.getWidth() / 2),  
                        (float) ((height / 2) + ((lineMetrics.getAscent() + lineMetrics  
                                .getDescent()) / 2 - lineMetrics.getDescent())));  
            }  
  
            addMouseListener(new MouseAdapter() {  
  
                @Override  
                public void mouseEntered(MouseEvent e) {  
                    // TODO Auto-generated method stub  
                    System.out.println("光标移入组件");  
                    state = "focused";  
                    repaint();  
                }  
  
                @Override  
                public void mouseExited(MouseEvent e) {  
                    // TODO Auto-generated method stub  
                    System.out.println("光标移出组件");  
                    state = "normal";  
                    repaint();  
                }  
  
                @Override  
                public void mousePressed(MouseEvent e) {  
                    // TODO Auto-generated method stub  
                    System.out.print("鼠标按键被按下，");  
                    state = "pressed";  
                    repaint();  
                }  
  
                @Override  
                public void mouseReleased(MouseEvent e) {  
                    // TODO Auto-generated method stub  
                    System.out.print("鼠标按键被释放，");  
                    state = "released";  
                    repaint();  
                }  
  
            });  
  
        }  
          
        @Override  
        public boolean contains(int x, int y) {  
            if (shape.contains(x, y)) {  
                return true;  
            } else {  
                return false;  
            }  
        }  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        Button_module frame = new Button_module();  
        frame.pack();  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);  
    }  
  
}  