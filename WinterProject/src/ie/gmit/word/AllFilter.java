package ie.gmit.word;

import java.util.List;

//È«²¿¹ıÂË
public class AllFilter extends Filter {

    private final List<Filter> filters;

    public AllFilter(final List<Filter> filters) {
        this.filters = filters;
    }

    public boolean apply(final String word) {
        for(Filter filter : filters) {
            if(!filter.apply(word)) {
            	return false; }
        }
        return true;
    }

}
