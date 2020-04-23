package main.utils;

import main.entry.Dependency;
import main.entry.Repo;

import java.io.*;
import java.util.ArrayList;

public class DependenciesUtils {
    public ArrayList<Dependency> getDependenciesList(Repo repo) throws IOException {
        String depPath = repo.getMoudlePath() +"\\"+ Config.depFileName;
        ArrayList<Dependency>  dependencies = new ArrayList<>();
        ArrayList<String>   dependenciesStrings = anaDepFile(depPath);
        dependencies = anaDependencies(dependenciesStrings);
        return dependencies;
    }
    public ArrayList<String> anaDepFile(String depPath) throws IOException {
        ArrayList<String> dep = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(depPath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = null;
        while((str = bufferedReader.readLine()) != null)
        {
            if(str.contains("--- "))
            {
                String[] tempList = str.split(" ");
                String max = tempList[0];
                for(String s:tempList)
                {
                    if(max.length()<s.length()){max = s;}
                }
                dep.add(max);
            }
        }
        //close
        inputStream.close();
        bufferedReader.close();
        return dep;
    }
    //Analysis on dep
    public ArrayList<Dependency> anaDependencies(ArrayList<String>   dependenciesStrings)
    {
        ArrayList<Dependency> dependencies = new ArrayList<>();
        for (String depString:dependenciesStrings)
        {
            Dependency dependency = new Dependency();
            dependency.setFullName(depString);
            //ana
            String[] tempStrings = depString.split(":");
            String sourcePath = "";
            for (String s:tempStrings)
            {
                sourcePath+=s+"\\";
            }
            dependency.setSourcePath(Config.repoCachePath+"\\"+sourcePath);
            dependency.setCachePath(Config.repoCachePath);
            dependencies.add(dependency);
        }
        return dependencies;
    }
}
