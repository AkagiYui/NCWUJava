package com.dzf.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AkagiYui
 */

public class StringUtil {

    public static String[] split(String str, String separatorChars) {
        if (str == null) {
            return null;
        }
        if (separatorChars == null) {
            return new String[]{str};
        }
        List<String> list = new ArrayList<>();
        int len = str.length();
        int start = 0;
        int end = 0;
        while (end < len) {
            int index = str.indexOf(separatorChars, start);
            if (index == end) {
                list.add("");
                end += separatorChars.length();
                start = end;
            } else if (index == -1) {
                list.add(str.substring(start));
                end = len;
            } else {
                list.add(str.substring(start, index));
                end = index;
                start = end + separatorChars.length();
            }
        }
        return list.toArray(new String[list.size()]);
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str);
    }
}
