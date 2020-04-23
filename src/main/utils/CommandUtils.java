package main.utils;

import main.entry.Repo;

import java.io.*;

public class CommandUtils {
    //    Down lib
    public boolean downLib(Repo repo)
    {
        try {
            runCommand(repo.getMoudlePath(),"gradlew build");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //    Get Dep
    public boolean getDep(Repo repo)
    {
        //releaseRuntimeClasspath

       /* runCommand(repo.getMoudlePath(),"gradlew app:dependencies --configuration implementation  > implementationDep.txt");
        runCommand(repo.getMoudlePath(),"gradlew app:dependencies --configuration compile  > compile.txt");*/
        try {
            runCommand(repo.getMoudlePath(), "gradlew app:dependencies --configuration releaseRuntimeClasspath  > " + Config.depFileName);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private void runCommand(String repoPath,String Command) throws IOException, InterruptedException {
        String[] command = {"cmd"};
        Process p = Runtime.getRuntime().exec(command);

        new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
        new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
        PrintWriter stdin = new PrintWriter(p.getOutputStream());
        // 以下可以输入自己想输入的cmd命令
        stdin.println(Config.Drive);
        stdin.println("cd "+repoPath);
        stdin.println(Command);
        stdin.close();
        int returnCode = p.waitFor();
        System.out.println("Return code = " + returnCode);
        p.destroy();
    }
    //    Ana dependencies

}

class SyncPipe implements Runnable {

    private final OutputStream ostrm_;
    private final InputStream istrm_;

    public SyncPipe(InputStream istrm, OutputStream ostrm) {
        istrm_ = istrm;
        ostrm_ = ostrm;
    }

    public void run() {
        try {
            final byte[] buffer = new byte[1024];
            for (int length = 0; (length = istrm_.read(buffer)) != -1; ) {
                ostrm_.write(buffer, 0, length);
            }
        } catch (Exception e) {
            throw new RuntimeException("处理命令出现错误：" + e.getMessage());
        }
    }
}
