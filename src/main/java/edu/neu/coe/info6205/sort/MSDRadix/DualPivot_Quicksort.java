package edu.neu.coe.info6205.sort.MSDRadix;

import edu.neu.coe.info6205.graphs.BFS_and_prims.StdRandom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

// credit: https://algs4.cs.princeton.edu/23quicksort/QuickDualPivot.java.html

public class DualPivot_Quicksort {

    // quicksort the array a[] using dual-pivot quicksort
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // quicksort the subarray a[lo .. hi] using dual-pivot quicksort
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        // make sure a[lo] <= a[hi]
        if (less(a[hi], a[lo])) exch(a, lo, hi);

        int lt = lo + 1, gt = hi - 1;
        int i = lo + 1;
        while (i <= gt) {
            if       (less(a[i], a[lo])) exch(a, lt++, i++);
            else if  (less(a[hi], a[i])) exch(a, i, gt--);
            else                         i++;
        }
        exch(a, lo, --lt);
        exch(a, hi, ++gt);

        // recursively sort three subarrays
        sort(a, lo, lt-1);
        if (less(a[lt], a[gt])) sort(a, lt+1, gt-1);
        sort(a, gt+1, hi);

        assert isSorted(a, lo, hi);
    }



    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    public List<String> dpSort(List<String> a){
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
        List<String> output = dpSort(input);
        File f = new File("DualPivot_QuicksortOutput.txt");
        FileOutputStream fos = new FileOutputStream(f);
        OutputStreamWriter dos = new OutputStreamWriter(fos);
        for(String name: output){
            dos.write(name + '\n');
        }
        dos.close();
    }


    public static void main(String[] args) throws IOException {
        DualPivot_Quicksort quicksort = new DualPivot_Quicksort();
        quicksort.inputAndOutput();
    }

}
