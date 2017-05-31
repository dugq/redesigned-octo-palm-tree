import org.junit.Test;

import java.io.*;

/**
 * Created by duq on 2017/5/24.
 */
public class TestIO {
    @Test
    public void testIOStream() throws Exception {
        File file = new File("");
        FileInputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] arr = new byte[1024];
        int len = 0;
        do{
            len = bufferedInputStream.read(arr);
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(arr);
        }while (len !=-1);

    }


}
