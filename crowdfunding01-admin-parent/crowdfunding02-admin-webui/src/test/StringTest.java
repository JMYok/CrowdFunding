import com.bobjiang.crowd.util.CrowdUtil;
import org.junit.Test;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-23 2:24
 */
public class StringTest {
    
    @Test
    public void TestMD5(){
        String source = "123456";
        String res = CrowdUtil.md5(source);
        System.out.println(res);
    }
}
