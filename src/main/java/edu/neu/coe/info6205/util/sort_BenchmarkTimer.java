package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.MSDRadix.Hanyu;
import edu.neu.coe.info6205.sort.MSDRadix.Input;
import edu.neu.coe.info6205.sort.MSDRadix.MSD;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class sort_BenchmarkTimer {

    public static void main(String[] args) {
        // covert Chinese to pinyin



            // MSD_Sort
            System.out.println("time MSD_sort");
            MSD msd = new MSD();

            Consumer<List<String>> consumer= array->msd.msdSort(array);
            Benchmark_Timer<List<String>> benchmarkTimer = new Benchmark_Timer<List<String>>("MSD Sort", consumer);
            int m = 1;

            Supplier<List<String>> supplierR = () -> {
                List<String> a = new ArrayList<>();
                a.add("王二");
                a.add("张三");
                a.add("李四");

                return a;
            };
            System.out.println("MSD sort time taken: " + benchmarkTimer.runFromSupplier(supplierR, m));
        }


}


