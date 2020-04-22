import utils.GradleCommand;
import utils.Zip;
import utils.downLoad;

import java.io.IOException;
import java.util.ArrayList;
/**
 * 思路：
 * 1：需要分析的项目集合
 * 2：通过命令行，将项目依赖的Lib都下载到
 * 3：根据Lib的声明方式去文件夹内找Lib，搜索查看AndroidManifest.xml
 * 4：分析AndroidManifest.xml里面的TV
 **/

/**
 * https://www.cnblogs.com/diyunpeng/p/6769426.html
 * 只看直接依赖不行么？？？
 * implementation - Implementation only dependencies for 'main' sources.
 * */
//https://github.com/aminecmi/ReaderforSelfoss用于测试
public class main {
    public static downLoad downLoadUtils = new downLoad();

    /**
     * 1:download
     * 2:解压
     * 3:下载lib(需要处理一些异常，imple/compile,gradle文件夹) build
     * 4:得到需要分析的lib的名字 分析依赖
     * 5：搜索文件夹内是否有.aar的文件
     * 6:.aar重命名并解压
     * 7：解析AndroidManifest.xml文件
     *
     * @param args
     */

    public static void main(String[] args) throws IOException {
//        downLoadUtils.downGradleRepo("https://github.com/fython/Blackbulb"+config.downSuffix,
//                config.reposPath,config.GET,"Blackbulb.zip");
//        Zip zipUtils = new Zip();
//        zipUtils.unZipFiles(config.reposPath+"Blackbulb.zip",config.reposPath);
//        GradleCommand command = new GradleCommand();
//        command.downLib("E:\\gradle\\repo\\PDFCreator-master");
        Zip zipUtils = new Zip();
        System.out.println(zipUtils.findAAR("E:\\gradle\\cache\\caches\\modules-2\\files-2.1\\com.github.wowhhh\\lintLibs\\V2.1"));
        //调用Linux/Windows命令行，得到Android项目的依赖关系 gradlew :app:dependencies  > dependenciesTree.txt
        ///根据依赖关系，找到对应的Library,MAVEN？，寻找.aar结尾的文件，解压获取androidManifest.xml文件即可
        /**
         * org.brotli:dec:0.1.2
         * https://mvnrepository.com/artifact/org.brotli/dec/0.1.2
         * */
        //解析Library的AndroidManifest.xml文件内容，查看TV属性值

        //如果看被多少应用波及到 看：Used By	4,525 artifacts 应该可以叭（maven提供的）
    }
}
