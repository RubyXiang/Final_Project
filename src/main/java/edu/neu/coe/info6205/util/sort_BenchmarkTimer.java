package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.MSDRadix.Hanyu;
import edu.neu.coe.info6205.sort.MSDRadix.Input;
import edu.neu.coe.info6205.sort.MSDRadix.MSD;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class sort_BenchmarkTimer {

    public static void main(String[] args) {
        // covert Chinese to pinyin
        List<String> a = Input.readTxtFileIntoStringArrList("/Users/Evelyn/info_6205_finalProject/shuffledChinese.txt");

        String[] pinyin = new String[a.size()];
        int i = 0;
        Hanyu hanyu = new Hanyu();
        for(String name : a){
            pinyin[i++] = hanyu.getStringPinYin(name) + "," + i;
        }


            // MSD_Sort
            System.out.println("time MSD_sort");
            MSD msd = new MSD();

            Consumer<String[]> consumer= array->msd.sort(array);
            Benchmark_Timer<String[]> benchmarkTimer = new Benchmark_Timer<String[]>("MSD Sort", consumer);
            int m = 10;

            Supplier<String[]> supplierR = () -> {
                String[] msd_pinyin = pinyin.clone();

                return msd_pinyin;
            };
            System.out.println("MSD sort time taken: " + benchmarkTimer.runFromSupplier(supplierR, m));
        }


}


