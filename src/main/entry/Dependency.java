package main.entry;

public class Dependency {
    private String fullName;
    private String sourcePath;
    private String cachePath;
    private boolean haveAAR;
    private String aarPath; //包括文件本身
    private String manifestPath; //包括文件本身

    public String getManifestPath() {
        return manifestPath;
    }

    public void setManifestPath(String manifestPath) {
        this.manifestPath = manifestPath;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCachePath() {
        return cachePath;
    }

    public void setCachePath(String cachePath) {
        this.cachePath = cachePath;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public boolean isHaveAAR() {
        return haveAAR;
    }

    public void setHaveAAR(boolean haveAAR) {
        this.haveAAR = haveAAR;
    }

    public String getAarPath() {
        return aarPath;
    }

    public void setAarPath(String aarPath) {
        this.aarPath = aarPath;
    }
}
