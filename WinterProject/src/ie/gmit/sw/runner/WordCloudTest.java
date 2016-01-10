package ie.gmit.sw.runner;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import ie.gmit.background.RandomBackground;

import ie.gmit.draw.ColorPalette;
import ie.gmit.font.CloudFont;
import ie.gmit.font.FontWeight;
import ie.gmit.font.FontScalarSetting;
import ie.gmit.image.AngleGenerator;
import ie.gmit.word.showtimes.WordTimes;
import ie.gmit.wordcloud.CollisionMode;
import ie.gmit.wordcloud.WordCloud;
import ie.gmit.wordcloud.WordFrequency;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class WordCloudTest {

	private static final Logger LOGGER = Logger.getLogger(WordCloudTest.class);

	private static Runner  runner;

	public void Test1() throws IOException {
		final WordTimes wordTimes = new WordTimes();
		wordTimes.setWordFrequencesToReturn(300);
		wordTimes.setMinWordLength(5);
		wordTimes.setStopWords(loadStopWords());

        final List<WordFrequency> wordFrequencies = wordTimes.load(getInputStream("test2.txt"));
        final WordCloud wordCloud = new WordCloud(1100, 1100, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPp(1);
        wordCloud.setBackground(new RandomBackground(getInputStream("coke.gif")));
        wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
        wordCloud.setFontScalar(new FontScalarSetting(10, 40));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("454409090.png");
        System.out.println("************88");
    }
	
	public void Test() throws IOException {
		final WordTimes wordTimes = new WordTimes();
		wordTimes.setWordFrequencesToReturn(600);
		wordTimes.setMinWordLength(3);
		wordTimes.setStopWords(loadStopWords());
		
		final List<WordFrequency> wordFrequencies;
		final WordCloud wordCloud = new WordCloud(1100,1100, CollisionMode.PIXEL_PERFECT);
		wordCloud.setPp(1);
		//wordCloud.setAngleGenerator(new AngleGenerator(-90, 90, 10));
	//	wordCloud.setAngleGenerator(new AngleGenerator(runner.getAngleJT1(),runner.getAngleJT2(), 10));
		wordCloud.setBackgroundColor(Color.WHITE);
		
		if(!runner.checkUrl(runner.getFileName())){
			wordFrequencies = wordTimes.load(""+runner.getFileName());
			System.out.println(wordFrequencies);
		}else{
			wordFrequencies = wordTimes.load(new URL(runner.getFileName()));
		}
		
		if(!runner.getChooseImageName().contains(":")){
			wordCloud.setBackground(new RandomBackground(getInputStream(""+runner.getChooseImageName())));
			System.out.print(runner.getChooseImageName()+"***");
		}else{
			wordCloud.setBackground(new RandomBackground(runner.getChooseImageName()));
			System.out.print(runner.getChooseImageName()+"***");
		}

		 wordCloud.setCloudFont(new CloudFont("Simple Slumg__G", FontWeight.BOLD));

	   HashMap colorSet=runner.getList_color();
		
		wordCloud.setColorPalette(new ColorPalette((Color)colorSet.get(1),(Color)colorSet.get(2),(Color)colorSet.get(3),(Color)colorSet.get(4),(Color)colorSet.get(5), (Color)colorSet.get(6)));

		 wordCloud.setFontScalar(new FontScalarSetting(runner.getFontJT3(), 30));


	//	 wordCloud.setAngleGenerator(new AngleGenerator(-90,90, 10));
		wordCloud.build(wordFrequencies);

		wordCloud.writeToFile(""+runner.getNewImageName());

		// System.out.println(runner.getNewImageName());
		runner.setFlag(true);
		System.out.println("finish");

	}

	private static Set<String> loadStopWords() {
		try {
			final List<String> lines = IOUtils.readLines(getInputStream("stop_words.txt"));
			return new HashSet<>(lines);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return Collections.emptySet();
	}

	private static InputStream getInputStream(String path) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	}

}
