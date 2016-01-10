package ie.gmit.settleword;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

//去掉 两端的控制符 例如“/n /t ”

public class DelBothSideSymbol implements Setting {
    @Override
    public String setting(final String text) {
        return trimToEmpty(text);
    }
}

