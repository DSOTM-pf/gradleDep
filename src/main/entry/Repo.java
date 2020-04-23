package main.entry;

import java.util.ArrayList;

public class Repo {
    private int TV;
    private String repoUrl;
    private String downLoadUrl;
    private String repoName;
    private String moudlePath;
    private String repoPath;
    private ArrayList<Dependency> dependencies = new ArrayList<>();
    // dependenciesPath = moudlePath + Config.depName
    //ZipPath = repoPath + repoName + ".zip"


    public int getTV() {
        return TV;
    }

    public void setTV(int TV) {
        this.TV = TV;
    }

    public String getRepoPath() {
        return repoPath;
    }

    public void setRepoPath(String repoPath) {
        this.repoPath = repoPath;
    }


    public String getDownLoadUrl() {
        return downLoadUrl;
    }

    public void setDownLoadUrl(String downLoadUrl) {
        this.downLoadUrl = downLoadUrl;
    }

    public String getMoudlePath() {
        return moudlePath;
    }

    public void setMoudlePath(String moudlePath) {
        this.moudlePath = moudlePath;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public ArrayList<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(ArrayList<Dependency> dependencies) {
        this.dependencies = dependencies;
    }
}
