package edu.neu.coe.info6205.sort.finalProjectSorts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// credit: https://www.cnblogs.com/0201zcr/p/5009975.html

public class Input {

    public static List<String> readTxtFileIntoStringArrList(String filePath)
    {
        List<String> list = new ArrayList<>();
        try
        {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists())
            {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("Can not find the file!");
            }
        }
        catch (Exception e)
        {
            System.out.println("Read error!");
            e.printStackTrace();
        }

        return list;
    }
}
