package Answers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Denis on 24-02-2017
 */
public class CompareTexts {

    public static void main(String[] args) {
    		String s1 = "qwe rty aaa w uio";
    		String s2 = "qwe rty aaa uio";
        System.out.println(findWord2(s2, s1));
    }

    public static String findWord2(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("null strings");
        }
        String[] split1 = s1.split(" ");
        String[] split2 = s2.split(" ");
        int abs = Math.abs(split1.length - split2.length);
        if (abs > 1 || abs == 0) {
            throw new IllegalArgumentException("wrong words count");
        }
        Arrays.sort(split1);
        Arrays.sort(split2);
        int minLength;
        String[] small;
        String[] big;
        if (split1.length > split2.length) {
            minLength = split2.length;
            small = split2;
            big = split1;
        } else {
            minLength = split1.length;
            small = split1;
            big = split2;
        }
        for (int i = 0; i < minLength; i++) {
            if (!big[i].equals(small[i])) {
                return big[i];
            }
        }
        return big[big.length-1];
    }

    public static String findWord(String s1, String s2) {
        String res = "";
        String[] split1 = s1.split(" ");
        String[] split2 = s2.split(" ");
        Set<String> set;
        String[] check;
        if (split1.length > split2.length) {
            check = split1;
            set = new HashSet<>(Arrays.asList(split2));
        } else {
            check = split2;
            set = new HashSet<>(Arrays.asList(split1));
        }
        for (String s : check) {
            if (!set.contains(s)) {
                return s;
            }
        }
        // throw
        return null;
    }
}
