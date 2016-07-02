import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by 熊纪元 on 2016/7/2.
 */
public class OverTest {
    public static void main(String[] args){
        String str = "abc";
        System.out.println(DigestUtils.sha1Hex(str));
    }
}
