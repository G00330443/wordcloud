package ie.gmit.wordcloud;

import ch.lambdaj.Lambda;
import ie.gmit.background.Background;
import ie.gmit.collision.CollisionChecker;
import ie.gmit.collision.ImageChecker;
import ie.gmit.collision.ImageCollidable;
import ie.gmit.draw.ColorPalette;
import ie.gmit.draw.Paint;
import ie.gmit.draw.Painting;
import ie.gmit.font.CloudFont;
import ie.gmit.font.FontScalar;
import ie.gmit.font.FontWeight;
import ie.gmit.font.FontScalarSetting;
import ie.gmit.image.AngleGenerator;
import ie.gmit.image.CollisionRaster;
import ie.gmit.image.ImageRotation;
import ie.gmit.method.RandomWordStart;
import ie.gmit.method.WordStartScheme;

import org.apache.log4j.Logger;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ch.lambdaj.Lambda.on;

public class WordCloud {

	private static final Logger LOGGER = Logger.getLogger(WordCloud.class);

	private final int width;

	private final int height;

	private final CollisionMode collisionMode;

	private final CollisionChecker collisionChecker;

	private final Paint drawing;

	//设置  模式
	private int pp = 0;

	public void setPp(int pp) {
		this.pp = pp;
	}


	private Background background;

	private final ImageCollidable backgroundCollidable;

	private Color backgroundColor = Color.YELLOW;

	private FontScalar fontScalar = new FontScalarSetting(10, 40);

	private CloudFont cloudFont = new CloudFont("Calibri", FontWeight.BOLD);

	private AngleGenerator angleGenerator = new AngleGenerator();
	

	public void setAngleGenerator(AngleGenerator angleGenerator) {
		this.angleGenerator = angleGenerator;
	}


	private final CollisionRaster collisionRaster;
	
	
//图片
	
	private final BufferedImage bufferedImage;

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public Set<Word> getSkipped() {
		return skipped;
	}


	//代替文字的模式
	private final Set<Word> placedWords = new HashSet<>();

	private final Set<Word> skipped = new HashSet<>();

	//开始模式
	private WordStartScheme startscheme = new RandomWordStart();

//颜色
	private ColorPalette colorPalette = new ColorPalette(Color.ORANGE, Color.WHITE, Color.YELLOW, Color.GRAY, Color.GREEN);


	public void setColorPalette(ColorPalette colorPalette) {
		this.colorPalette = colorPalette;
	}
	
	public void build(List<WordFrequency> wordFrequencies) {
		Collections.sort(wordFrequencies);
		int curword = 1;
		final Dimension dimensions = new Dimension(width, height);
		for(final Word word : buildwords(wordFrequencies, this.colorPalette)) {
			final Point p = startscheme.getStartingPoint(dimensions, word);
			final boolean placed = place(word, p.x, p.y);

			if (placed) {
				LOGGER.info("placed: " + word.getWord() + " (" + curword + "/" + wordFrequencies.size() + ")");
			} else {
				LOGGER.info("skipped: " + word.getWord() + " (" + curword + "/" + wordFrequencies.size() + ")");
			}

			curword++;
		}
		drawForgroundToBackground();
	}
	//
	//输出文本  PNG

	public void writeToFile(final String outputFileName) {
		String extension = "";
		int i = outputFileName.lastIndexOf('.');
		if (i > 0) {
			extension = outputFileName.substring(i + 1);
		}
		try {
			LOGGER.info("Saving WordCloud to " + outputFileName);
			ImageIO.write(bufferedImage, extension, new File(outputFileName));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	
	//建立背景
	private void drawForgroundToBackground() {
		if(backgroundColor == null) { return; }

		final BufferedImage backgroundBufferedImage = new BufferedImage(width, height, this.bufferedImage.getType());
		final Graphics graphics = backgroundBufferedImage.getGraphics();

		graphics.setColor(backgroundColor);
		graphics.fillRect(0, 0, width, height);
		graphics.drawImage(bufferedImage, 0, 0, null);

		final Graphics graphics2 = bufferedImage.getGraphics();
		graphics2.drawImage(backgroundBufferedImage, 0, 0, null);
	}

	//放置文字

	private boolean place(final Word word, final int startX, final int startY) {
		final Graphics graphics = this.bufferedImage.getGraphics();

		final int maxRadius = width;

		for(int r = 0; r < maxRadius; r += 2) {
			for(int x = -r; x <= r; x++) {
				if(startX + x < 0) { continue; }
				if(startX + x >= width) { continue; }

				boolean placed = false;
				word.setX(startX + x);

		
				int y1 = (int) Math.sqrt(r * r - x * x);
				if(startY + y1 >= 0 && startY + y1 < height) {
					word.setY(startY + y1);
					placed = toPlace(word);
				}
				
				int y2 = -y1;
				if(!placed && startY + y2 >= 0 && startY + y2 < height) {
					word.setY(startY + y2);
					placed = toPlace(word);
				}
				if(placed) {
					collisionRaster.mask(word.getCollisionRaster(), word.getX(), word.getY());
					graphics.drawImage(word.getBufferedImage(), word.getX(), word.getY(), null);
					return true;
				}

			}
		}
		skipped.add(word);
		return false;
	}


	//选择碰撞模式 与 判定是否碰撞
	public WordCloud(int width, int height, CollisionMode collisionMode) {
		this.width = width;
		this.height = height;
		this.collisionMode = collisionMode;
		this.drawing = new Painting();
		this.collisionChecker = new ImageChecker();
		this.collisionRaster = new CollisionRaster(width, height);
		this.bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.backgroundCollidable = new ImageCollidable(collisionRaster, 0, 0);
		//  this.background = new RecBackground(width, height);
	}


	private boolean toPlace(final Word word) {
		if(!background.checkPosition(word)) { return false; }

			if(backgroundCollidable.check(word)) { 
				return false; 
				}else{
			placedWords.add(word);
			return true;
				}
	}

	private List<Word> buildwords(final List<WordFrequency> wordTimes, final ColorPalette colorPalette) {
		final int maxFrequency = maxFrequency(wordTimes);

		final List<Word> words = new ArrayList<>();
		for(final WordFrequency wordFrequency : wordTimes) {
			if(!wordFrequency.getWord().isEmpty()) {
				words.add(buildWord(wordFrequency, maxFrequency, colorPalette));
			}
		}
		return words;
	}

	private Word buildWord(final WordFrequency wordFrequency, int maxTimes, final ColorPalette colorPalette) {
		final Graphics graphics = this.bufferedImage.getGraphics();

		final int frequency = wordFrequency.getFrequency();
		final float fontHeight = this.fontScalar.scale(frequency, 0, maxTimes);
		final Font font = cloudFont.getFont().deriveFont(fontHeight);getClass();

		final FontMetrics fontMetrics = graphics.getFontMetrics(font);
		final Word word = new Word(wordFrequency.getWord(), colorPalette.next(), fontMetrics, this.collisionChecker);

		final double theta = angleGenerator.randomNext();
		
		//使得有竖着的文字
		if(theta != 0) {
			word.setBufferedImage(ImageRotation.rotate(word.getBufferedImage(), theta));
		}
		if(pp > 0) {
			drawing.paint(word, pp);
		}
		return word;
	}

	private int maxFrequency(final Collection<WordFrequency> wordFrequencies) {
		if(wordFrequencies.isEmpty()) { 
			return 1; 
		}
		return Lambda.max(wordFrequencies, on(WordFrequency.class).getFrequency());
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public void setBackground(Background background) {
		this.background = background;
	}

	public void setFontScalar(FontScalar fontScalar) {
		this.fontScalar = fontScalar;
	}

	public void setCloudFont(CloudFont cloudFont) {
		this.cloudFont = cloudFont;
	}

	public void setWordStartScheme(WordStartScheme startscheme) {
		this.startscheme = startscheme;
	}
}
