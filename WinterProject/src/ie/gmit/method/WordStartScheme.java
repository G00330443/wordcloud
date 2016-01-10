package ie.gmit.method;

import java.awt.Dimension;
import java.awt.Point;

import ie.gmit.wordcloud.Word;


//提供一个开始位置
public interface WordStartScheme {

    public Point getStartingPoint(Dimension imagedimensions, Word word);

}
