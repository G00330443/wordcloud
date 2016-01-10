package ie.gmit.word;

import ch.lambdaj.function.matcher.Predicate;

public abstract class Filter extends Predicate<String> {
    public abstract boolean apply(String word);
}

