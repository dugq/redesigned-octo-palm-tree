import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * Created by duq on 2017/5/24.
 */
public class testFile {
    @Test
    public void test(){
        File file = new File("./");
        System.out.println(file.getAbsoluteFile().toString());
        file.isFile();
        file.exists();
        file.canRead();
        file.canWrite();
        file.getName();
        file.getPath();
//        file.renameTo()
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return false;
            }
        });
        file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return false;
            }
        });
    }

}
