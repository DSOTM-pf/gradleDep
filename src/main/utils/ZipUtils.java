package main.utils;

import main.entry.Dependency;
import main.entry.Repo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtils {

    public void upZipAAR(Dependency dependency) throws IOException {
        String zipPath = dependency.getAarPath();
        String descDir = "";
        String[] paths = dependency.getAarPath().split("\\\\");
        for(int i = 0 ;i < paths.length-1 ; i++)
        {
            descDir += paths[i]+"\\";
        }
        unZipFiles(new File(zipPath),descDir);
//        dependency.setManifestPath(descDir+"\\null\\"+"AndroidManifest.xml");
        System.out.println("******************解压完毕********************");
    }
    /**
     * 解压到指定目录
     */
    public  void unZipRepo(Repo repo) throws IOException {
        String zipPath = repo.getRepoPath() + repo.getRepoName() + ".zip";
        String descDir = repo.getRepoPath();

        unZipFiles(new File(zipPath), descDir);
        System.out.println("******************解压完毕********************");
    }

    /**
     * 解压文件到指定目录
     * 解压后的文件名，和之前一致
     * @param zipFile	待解压的zip文件
     * @param descDir 	指定目录
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFiles(File zipFile, String descDir) throws IOException {
        ZipFile zip = null;
        String name = null;
        try {
           zip = new ZipFile(zipFile, Charset.forName("GBK"));//解决中文文件夹乱
           name = zip.getName().substring(zip.getName().lastIndexOf('\\')+1, zip.getName().lastIndexOf('.'));
        }
        catch (Exception e)
        {
            return;
        }

        File pathFile = new File(descDir+name);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + name +"/"+ zipEntryName).replaceAll("\\*", "/");

            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            // 输出文件路径信息
//			System.out.println(outPath);

            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }

        return;
    }
}
