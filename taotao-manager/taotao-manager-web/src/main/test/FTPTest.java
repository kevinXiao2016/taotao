import com.xiaoyue.utils.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FTPTest {

    @Test
    public void testFtpClient() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.86.129",21);
        ftpClient.login("ftpuser", "password");

        //上传文件
        FileInputStream imputStream = new FileInputStream(new File("D:\\github\\Blog\\imageStorage\\activiti\\binxing.png"));
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        //设置上传文件的类型为二进制类型
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();
        ftpClient.storeFile("binxing1.png",imputStream);

        imputStream.close();
        ftpClient.logout();

    }

    @Test
    public void testFtpUtils() throws IOException {

        FileInputStream imputStream = new FileInputStream(new File("D:\\github\\Blog\\imageStorage\\keytool.png"));

        String host = "192.168.86.129";

        int port = 21;
        String username = "ftpuser";
        String password = "password";
        String bathPath = "/home/ftpuser/www/images";
        String filePath = "/2018/06/13";
        String filename = "keytool.png";
        boolean b = FtpUtil.uploadFile(host, port, username, password, bathPath, filePath, filename, imputStream);
        System.out.println(b);

    }
}
