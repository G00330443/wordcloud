package ie.gmit.settleword;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

//ȥ�� ���˵Ŀ��Ʒ� ���硰/n /t ��

public class DelBothSideSymbol implements Setting {
    @Override
    public String setting(final String text) {
        return trimToEmpty(text);
    }
}

