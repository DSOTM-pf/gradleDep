package main;

import main.entry.Dependency;
import main.entry.Repo;
import org.xml.sax.SAXException;
import main.utils.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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
 * implementation - Implementation only dependencies for 'main.main' sources.
 * */
//https://github.com/aminecmi/ReaderforSelfoss用于测试
public class main {

    //Utils
    private static DownloadUtils downloadUtils = new DownloadUtils();
    private static ZipUtils zipUtils = new ZipUtils();
    private static CommandUtils commandUtils = new CommandUtils();
    private static DependenciesUtils dependenciesUtils = new DependenciesUtils();
    private static AARUtils aarUtils = new AARUtils();
    private static XmlUtils xmlUtils = new XmlUtils();
    private static fileUtils fileUtils = new fileUtils();
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

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
   /* Dependency dependency = new Dependency();
    dependency.setAarPath("E:\\gradle\\cache\\caches\\modules-2\\files-2.1\\com.github.wowhhh\\lintLibs\\V2.1\\9a773cdcba02ef923d86004d5b53def76948d932\\a.aar");
    zipUtils.upZipAAR(dependency);*/
    CsvUtils csvUtils = new CsvUtils();
    for(int i=2 ;i<1029;i++) {
        String url = csvUtils.getRowCol(i, 2);
        int tv = new Integer(csvUtils.getRowCol(i, 3));
        runSingleRepo(url, tv);
    }
    }


    //对每一个连接得到Repo的目录，文件名 https://github.com/fython/Blackbulb
    public  static Repo repoInit(String url,int TV)
    {
        Repo tempRepo = new Repo();
        String[] temp = url.split("/");
        String repoName = temp[temp.length-1];
        String downLoadUrl = url + Config.downSuffix;
        //TV
        tempRepo.setTV(TV);
        tempRepo.setRepoUrl(url);
        tempRepo.setDownLoadUrl(url+Config.downSuffix);
        tempRepo.setRepoName(repoName);
        tempRepo.setRepoPath(Config.reposPath);
        tempRepo.setMoudlePath(Config.reposPath+repoName+"\\"+repoName+"-master");
        return tempRepo;
    }
    public  static void runSingleRepo(String url,int TV) throws IOException, ParserConfigurationException, SAXException {

        //Repo信息初始化
        Repo tempRepo = repoInit(url,TV);

        try {
            //下载并保存Jar
            downloadUtils.downLoadGradleRepo(tempRepo);
            //解析Jar
            zipUtils.unZipRepo(tempRepo);
            //Cmd,build,得到Cache(线程)
//        Vector<Thread> threadVector = new Vector<>();

            commandUtils.downLib(tempRepo); //exception
            //Cmd,解析得到依赖
            commandUtils.getDep(tempRepo); //exception
            //分析依赖      getDependenciesList
            tempRepo.setDependencies(dependenciesUtils.getDependenciesList(tempRepo));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //失败
            return;
        }
        fileUtils.saveContent("allTvDifference.txt",tempRepo.getRepoUrl());
        fileUtils.saveContent("all.txt",tempRepo.getRepoUrl());
        fileUtils.saveContent("maxsdkVersion.txt",tempRepo.getRepoUrl());
        for(Dependency dependency : tempRepo.getDependencies())
        {
            // 寻找aar
            aarUtils.findAARInDep(dependency);
            //是否有aar
            if(dependency.isHaveAAR() == true)
            {
                //解压aar
                zipUtils.upZipAAR(dependency);
                //解析manifest.xml
                int repoTV = xmlUtils.getRepoTV(dependency);
                if(xmlUtils.findMaxsdkVersion(dependency))
                {
                    fileUtils.saveContent("maxsdkVersion.txt","     "+dependency.getFullName());
                }
                fileUtils.saveContent("all.txt","   "+dependency.getFullName());
                if((repoTV!=0) && (TV != repoTV))
                {
                    fileUtils.saveContent("allTvDifference.txt","   "+dependency.getFullName());
                }
            }
        }
    }
}
