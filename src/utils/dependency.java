package utils;

import java.util.regex.Pattern;

/**
 * 得到依赖文件
 * 解析
 * 得到项目名
 */
public class dependency {
    public void ana(String depPath)
    {
        String s = "";
        //正则匹配：+--- ， \---
        /**
         *+--- com.github.wowhhh:lintLibs:V2.1
         * 目录: com.github.wowhhh/lintLibs/V2.1
         * 文件名：lintLibs
         * \+--- (.*?)\
         * \\--- (.*?)\
         *
         * */
        Pattern p = Pattern.compile("");
        //得到依赖包列表
        //替换" "
        //以：隔开，提取出目录和项目名
    }
}
