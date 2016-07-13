import com.finefocus.tryspread.common.MCPTool;
import org.junit.Test;

import java.io.File;

/**
 * @author WenhuChang
 * @version Version 1.0
 * @package PACKAGE_NAME
 * @date 2016/7/13
 * @Description: ${todo}
 */
public class WriteChannelAndGetPathTest {
    @Test
    public void testWriteChannelAndGetPath() {
        String channelNumber = "00000002";
        try {
            String path = MCPTool.writeChannelAndGetPath(channelNumber);
            System.out.println(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo2() {
        String fileName = "111.apk";
        int dot = fileName.lastIndexOf(".");
        String prefix = fileName.substring(0, dot);
        String suffix = fileName.substring(dot);
//        String userId=;
        String of = String.valueOf(00000001);
        String newFile = prefix + "_" + of + suffix;
        System.out.println(newFile);
//        File target =null;
//            target = new File(outdir, prefix + "_" + content + suffix);

    }

}
