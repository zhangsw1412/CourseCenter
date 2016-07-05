package buaa.course.util;

import buaa.course.utils.PagingUtil;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 熊纪元 on 2016/7/4.
 */
public class PagingUtilTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-config.xml");
    PagingUtil util = context.getBean(PagingUtil.class);

    @Test
    public void test(){
        util.setTotalRow(12);
        util.setPageRow(5);
        util.toNewPage("3");
        Assert.assertEquals(10, util.getFirstIndex());
        Assert.assertEquals(11, util.getLastIndex());
    }
}
