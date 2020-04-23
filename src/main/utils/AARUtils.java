package main.utils;

import main.entry.Dependency;

import java.io.File;
import java.util.ArrayList;

public class AARUtils {
    private static ArrayList<String> aarPaths = new ArrayList<>();

    public  void findAARInDep(Dependency dependency)
    {
        String aarPath = findAAR(dependency.getSourcePath());
        if(aarPath == "") {
            dependency.setHaveAAR(false);
            dependency.setAarPath(null);
            dependency.setManifestPath(null);
            return;
        }
        dependency.setHaveAAR(true);
        dependency.setAarPath(aarPath);
        dependency.setManifestPath(aarPath.replace(".aar","\\")+"AndroidManifest.xml");
//        dependency.setManifestPath(aarPath.replace(".aar","\\")+"AndroidManifest.xml");
    }
    private  String findAAR(String filePath)
    {
        aarPaths.clear();
        File dir = new File(filePath);
        File fileList[] = dir.listFiles();
        allFileInPath(dir);
        if(aarPaths.size()!=0) {
            return aarPaths.get(0);
        }
        return "";
    }
    private  String allFileInPath(File file)
    {
        File[] fs = file.listFiles();
        if(fs!=null) {
            for (File f : fs) {
                if (f.isDirectory())    //若是目录，则递归打印该目录下的文件
                    allFileInPath(f);
                else if (f.getName().endsWith(".aar")) {
                    aarPaths.add(f.getAbsolutePath());
                } else {
                    continue;
                }
                //System.out.println(f);
            }
        }
        return  null;
    }

    public  void reNameAARInDep(Dependency dependency)
    {
//        reNameAAR();
        reNameAAR(dependency.getAarPath(),dependency.getAarPath().replace("aar","zip"));
    }
    public static void reNameAAR(String oldFilePath,String newFilePath)
    {
        File oldFile = new File(oldFilePath);
        File newFile = new File(newFilePath);
        if (oldFile.exists() && oldFile.isFile()) {
            oldFile.renameTo(newFile);
        }
    }
}
