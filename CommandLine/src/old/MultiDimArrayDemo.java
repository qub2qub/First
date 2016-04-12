package old;

import java.util.Arrays;
import java.util.List;

class MultiDimArrayDemo {

    public static void main(String[] args) {
        String[][] names = {
                {"Mr. ", "Mrs. ", "Ms. "},
                {"Smith", "Jones"}
        };
        // Mr. Smith
        System.out.println(names[0][0] + names[1][0]);
        // Ms. Jones
        System.out.println(names[0][2] + names[1][1]);

        char[] copyFrom = {'d', 'e', 'c', 'a', 'f', 'f', 'e',
                'i', 'n', 'a', 't', 'e', 'd'};
        char[] copyTo = new char[7];

        System.arraycopy(copyFrom, 2, copyTo, 0, 7);
        System.out.println(new String(copyTo));
        char[] copyTo2 = Arrays.copyOfRange(copyFrom, 2, 9);

        System.out.println(new String(copyTo2));

        Character[] cha = {'d', 'e', 'c', 'a', 'f'};
        List<Character> list = Arrays.asList(cha);
        System.out.println("list1 = " + list);
        // java.lang.UnsupportedOperationException
        // (AbstractList.java:148)
//        list.add('D');
        System.out.println("list2 = " + list);



    }
}