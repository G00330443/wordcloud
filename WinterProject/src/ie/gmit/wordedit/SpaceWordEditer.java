package ie.gmit.wordedit;


import java.util.Arrays;
import java.util.List;

public class SpaceWordEditer implements WordEditer {

	@Override
	public List<String> edit(String sentence) {
		return Arrays.asList(sentence.split(" "));
	}

}
