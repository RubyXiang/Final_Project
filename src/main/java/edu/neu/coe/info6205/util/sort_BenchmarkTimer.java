package edu.neu.coe.info6205.util;

import edu.neu.coe.info6205.sort.MSDRadix.*;
import edu.neu.coe.info6205.sort.MSDRadix.huskysort.huskySort.PureHuskySort;
import edu.neu.coe.info6205.sort.MSDRadix.huskysort.huskySortUtils.HuskyCoderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.lang.Math;

public class sort_BenchmarkTimer {

    public static void main(String[] args) {
        Supplier<List<String>> supplier_quarter = () -> {
            List<String> input = Input.readTxtFileIntoStringArrList("shuffledChinese.txt");
            int n = input.size();
            int quarter = Math.round(n/4);
            List<String> output = input.subList(0, quarter);
            return output;
        };
        Supplier<List<String>> supplier_half = () -> {
            List<String> input = Input.readTxtFileIntoStringArrList("shuffledChinese.txt");
            int n = input.size();
            int half = Math.round(n/2);
            List<String> output = input.subList(0, half);
            return output;
        };
        Supplier<List<String>> supplier_origin = () -> {
            List<String> input = Input.readTxtFileIntoStringArrList("shuffledChinese.txt");
            return input;
        };
        Supplier<List<String>> supplier_twice = () -> {
            List<String> input = Input.readTxtFileIntoStringArrList("shuffledChinese.txt");
            List<String> output = new ArrayList<>();
            output.addAll(input);
            output.addAll(input);
            return output;
        };
        Supplier<List<String>> supplier_four = () -> {
            List<String> input = Input.readTxtFileIntoStringArrList("shuffledChinese.txt");
            List<String> output = new ArrayList<>();
            output.addAll(input);
            output.addAll(input);
            output.addAll(input);
            output.addAll(input);
            return output;
        };

        int m = 10;


        // MSD_Sort
        System.out.println("----------time MSD Radix Sort-------------");
        MSD msd = new MSD();

        Consumer<List<String>> consumer_msd= array->msd.msdSort(array);
        Benchmark_Timer<List<String>> benchmarkTimer_msd = new Benchmark_Timer<List<String>>("MSD radix sort", consumer_msd);
        System.out.println("MSD sort time taken: " + benchmarkTimer_msd.runFromSupplier(supplier_quarter, m) + " with 250k names");
        System.out.println("MSD sort time taken: " + benchmarkTimer_msd.runFromSupplier(supplier_half, m) + " with 500k names");
        System.out.println("MSD sort time taken: " + benchmarkTimer_msd.runFromSupplier(supplier_origin, m) + " with 1M names");
        System.out.println("MSD sort time taken: " + benchmarkTimer_msd.runFromSupplier(supplier_twice, m) + " with 2M names");
        System.out.println("MSD sort time taken: " + benchmarkTimer_msd.runFromSupplier(supplier_four, m) + " with 4M names");

        // LSD_Sort
        System.out.println("----------time LSD Radix Sort-------------");
        LSD lsd = new LSD();

        Consumer<List<String>> consumer_lsd= array->lsd.lsdSort(array);
        Benchmark_Timer<List<String>> benchmarkTimer_lsd = new Benchmark_Timer<List<String>>("LSD radix sort", consumer_lsd);

        System.out.println("LSD sort time taken: " + benchmarkTimer_lsd.runFromSupplier(supplier_quarter, m) + " with 250k names");
        System.out.println("LSD sort time taken: " + benchmarkTimer_lsd.runFromSupplier(supplier_half, m) + " with 500k names");
        System.out.println("LSD sort time taken: " + benchmarkTimer_lsd.runFromSupplier(supplier_origin, m) + " with 1M names");
        System.out.println("LSD sort time taken: " + benchmarkTimer_lsd.runFromSupplier(supplier_twice, m) + " with 2M names");
        System.out.println("LSD sort time taken: " + benchmarkTimer_lsd.runFromSupplier(supplier_four, m) + " with 4M names");

        // Dual-pivot quick sort
        System.out.println("----------time Dual-pivot Quicksort-------------");
        DualPivot_Quicksort dp = new DualPivot_Quicksort();

        Consumer<List<String>> consumer_dp= array->dp.dpSort(array);
        Benchmark_Timer<List<String>> benchmarkTimer_dp = new Benchmark_Timer<List<String>>("Dual-pivot Quicksort", consumer_dp);

        System.out.println("Dual-pivot Quicksort time taken: " + benchmarkTimer_dp.runFromSupplier(supplier_quarter, m) + " with 250k names");
        System.out.println("Dual-pivot Quicksort time taken: " + benchmarkTimer_dp.runFromSupplier(supplier_half, m) + " with 500k names");
        System.out.println("Dual-pivot Quicksort time taken: " + benchmarkTimer_dp.runFromSupplier(supplier_origin, m) + " with 1M names");
        System.out.println("Dual-pivot Quicksort time taken: " + benchmarkTimer_dp.runFromSupplier(supplier_twice, m) + " with 2M names");
        System.out.println("Dual-pivot Quicksort time taken: " + benchmarkTimer_dp.runFromSupplier(supplier_four, m) + " with 4M names");


        // Huskysort
        System.out.println("----------time Huskysort-------------");
        PureHuskySort<String> husky = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);

        Consumer<List<String>> consumer_husky= array->husky.huskySort(array);
        Benchmark_Timer<List<String>> benchmarkTimer_husky = new Benchmark_Timer<List<String>>("Huskysort", consumer_husky);

        System.out.println("Huskysort time taken: " + benchmarkTimer_husky.runFromSupplier(supplier_quarter, m) + " with 250k names");
        System.out.println("Huskysort time taken: " + benchmarkTimer_husky.runFromSupplier(supplier_half, m) + " with 500k names");
        System.out.println("Huskysort time taken: " + benchmarkTimer_husky.runFromSupplier(supplier_origin, m) + " with 1M names");
        System.out.println("Huskysort time taken: " + benchmarkTimer_husky.runFromSupplier(supplier_twice, m) + " with 2M names");
        System.out.println("Huskysort time taken: " + benchmarkTimer_husky.runFromSupplier(supplier_four, m) + " with 4M names");
        }

}


