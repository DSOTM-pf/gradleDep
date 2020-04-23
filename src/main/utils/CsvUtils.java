package main.utils;

import java.io.BufferedReader;
import java.io.FileReader;

public class CsvUtils {
    public  String getRowCol(int row,int col) {
        try {
            BufferedReader reade = new BufferedReader(new FileReader(Config.gradleCsvPath));
            String line = null;
            int index = 0;
            while ((line = reade.readLine()) != null) {
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                if (index == row - 1) {
//                    System.out.println(item.length);
                    if (item.length >= col - 1) {
                        String last = item[col - 1];//这就是你要的数据了
                        return last;
                    }
                }
//                int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
