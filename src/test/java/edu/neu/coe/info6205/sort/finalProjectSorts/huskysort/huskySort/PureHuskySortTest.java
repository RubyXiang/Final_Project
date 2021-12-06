package edu.neu.coe.info6205.sort.finalProjectSorts.huskysort.huskySort;


import edu.neu.coe.info6205.sort.finalProjectSorts.Input;
import edu.neu.coe.info6205.sort.finalProjectSorts.huskysort.huskySortUtils.HuskyCoderFactory;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PureHuskySortTest {
    String[] inputArray = {"Liu Chiping", "Hong Wensheng","Fan Huihui","Su Huimin", "Gao Minzheng", "Cao Yude", "Yuan Jipeng", "Shu Dongmei", "Yang Laxiang", "Xu Fengshan", "Wang Guangfeng"};
    String[] expected = {"Cao Yude", "Fan Huihui","Gao Minzheng","Hong Wensheng", "Liu Chiping", "Shu Dongmei", "Su Huimin", "Wang Guangfeng", "Xu Fengshan", "Yang Laxiang", "Yuan Jipeng"};

    @Test
    public void sort() {
        PureHuskySort husky = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        husky.sort(inputArray);
        System.out.println(Arrays.toString(inputArray));
        assertArrayEquals(expected, inputArray);
    }

    @Test
    public void sort1() {
        List<String> test = new ArrayList<>();
        test.add("马冬梅");
        test.add("王小丫");
        test.add("锅包肉");
        test.add("溜肉段");
        test.add("白切鸡");
        test.add("佛跳墙");
        List<String> expected = new ArrayList<>();
        expected.add("白切鸡");
        expected.add("佛跳墙");
        expected.add("锅包肉");
        expected.add("溜肉段");
        expected.add("马冬梅");
        expected.add("王小丫");
        String[] e = expected.toArray(new String[0]);
        PureHuskySort husky = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        List<String> out = husky.huskySort(test);
        String[] o = out.toArray(new String[0]);
        assertArrayEquals(e, o);
    }

    @Test
    public void sort2() {
        List<String> input = Input.readTxtFileIntoStringArrList("unsortedChinese.txt");
        List<String> expected = Input.readTxtFileIntoStringArrList("sortedChinese.txt");
        String[] expectedArray = expected.toArray(new String[0]);
        PureHuskySort husky = new PureHuskySort<>(HuskyCoderFactory.asciiCoder, false, false);
        List<String> output = husky.huskySort(input);
        String[] outputArray = output.toArray(new String[0]);
        assertEquals(expectedArray[0], outputArray[0]);
        assertEquals(expectedArray[10], outputArray[10]);
        assertEquals(expectedArray[20], outputArray[20]);

    }

}