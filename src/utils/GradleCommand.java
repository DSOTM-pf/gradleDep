package utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class GradleCommand {
    //    Down lib
    public boolean getDep(String repoPath)
    {
        //releaseRuntimeClasspath

        runCommand(repoPath,"gradlew :app:dependencies -q  --configuration implementation  > implementationDep.txt");
        return true;
    }
    public boolean runCommand(String repoPath,String Command) {
        String[] command = {"cmd"};
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
            new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
            /** 以下可以输入自己想输入的cmd命令 */
            stdin.println("E:");
            stdin.println("cd "+repoPath);
            stdin.println("gradlew "+command);
//            stdin.println("gradle copyJars");
//            stdin.println("gradlew -q app:dependencies --configuration compile > compileDep.txt");
//            stdin.println("gradlew :app:dependencies -q  --configuration implementation  > implementationDep.txt");
//            gradlew build 可以下载依赖包
            stdin.close();
        } catch (Exception e) {
            return false;
        }
        return true;
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
