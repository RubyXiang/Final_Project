package edu.neu.coe.info6205.sort.MSDRadix;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class LSD {

    private final int R = 256;

    private int findMaxLength(String[] strArr) {
        int maxLength = strArr[0].length();
        for (String str : strArr)
            maxLength = Math.max(maxLength, str.length());
        return maxLength;
    }

    private int charAsciiVal(String str, int charPosition) {
        if (charPosition >= str.length()) {
            return 0;
        }
        return str.charAt(charPosition);
    }

    /**
     * charSort method is implementation of LSD sort algorithm at particular character.
     *
     * @param strArr       It contains an array of String on which LSD char sort needs to be performed
     * @param charPosition This is the character position on which sort would be performed
     * @param from         This is the starting index from which sorting operation will begin
     * @param to           This is the ending index up until which sorting operation will be continued
     */
    private void charSort(String[] strArr, int charPosition, int from, int to) {
        int[] count = new int[R + 2];
        String[] result = new String[strArr.length];

        for (int i = from; i <= to; i++) {
            int c = charAsciiVal(strArr[i], charPosition);
            count[c + 2]++;
        }

        // transform counts to indices
        for (int r = 1; r < R + 2; r++)
            count[r] += count[r - 1];

        // distribute
        for (int i = from; i <= to; i++) {
            int c = charAsciiVal(strArr[i], charPosition);
            result[count[c + 1]++] = strArr[i];
        }

        // copy back
        if (to + 1 - from >= 0) System.arraycopy(result, 0, strArr, from, to + 1 - from);
    }

    /**
     * sort method is implementation of LSD String sort algorithm.
     *
     * @param strArr It contains an array of String on which LSD sort needs to be performed
     * @param from   This is the starting index from which sorting operation will begin
     * @param to     This is the ending index up until which sorting operation will be continued
     */
    public void sort(String[] strArr, int from, int to) {
        int maxLength = findMaxLength(strArr);
        for (int i = maxLength - 1; i >= 0; i--)
            charSort(strArr, i, from, to);
    }

    /**
     * sort method is implementation of LSD String sort algorithm.
     *
     * @param strArr It contains an array of String on which LSD sort needs to be performed
     */
    public void sort(String[] strArr) {
        sort(strArr, 0, strArr.length - 1);
    }

    public List<String> lsdSort(List<String> a){
        String[] pinyin = new String[a.size()];
        int i = 0;
        List<String> out = new ArrayList<>();
        Hanyu hanyu = new Hanyu();
        for(String name : a){
            pinyin[i++] = hanyu.getStringPinYin(name) + "," + i;
        }

        sort(pinyin);

        for(String name : pinyin){
            int index = Integer.valueOf(name.split(",")[1]);
            out.add(a.get(index-1));
        }

        return out;
    }

    public void inputAndOutput() throws IOException {
        List<String> input = Input.readTxtFileIntoStringArrList("shuffledChinese.txt");
        List<String> output = lsdSort(input);
        File f = new File("LSDOutput.txt");
        FileOutputStream fos = new FileOutputStream(f);
        OutputStreamWriter dos = new OutputStreamWriter(fos);
        for(String name: output){
            dos.write(name + '\n');
        }
        dos.close();
    }


    public static void main(String[] args) throws IOException {
        LSD lsd = new LSD();
        lsd.inputAndOutput();
    }

}

