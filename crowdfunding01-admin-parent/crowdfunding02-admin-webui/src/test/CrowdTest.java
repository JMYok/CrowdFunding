import org.bobjiang.entity.Admin;
import org.bobjiang.mapper.AdminMapper;
import org.bobjiang.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author BobJiang
 * @version 1.0
 * @date 2021-01-20 13:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    /**
     * 生成后台用户数据
     */
    @Test
    public void generateData(){
        for (int i=0;i<238;i++){
            Admin admin = new Admin(null,"testAcct"+i,"测试数据"+i,"11","test@test.com",null);
            adminService.saveAdmin(admin);
        }
    }

    /**
     * 测试Admin插入
     */
    @Test
    public void testAdminInsert(){
        Admin admin = new Admin(null,"tom","汤姆","123456","tom@126.com",null);
        int count = adminMapper.insert(admin);

        //在实际开发中，如果在所有想查看的地方都使用System.out来打印，会给项目上线带来很多问题
        //System.out本质是一个IO操作，通常IO操作比较消耗性能。如果项目中过多的System.out，可能对性能造成影响
        //如果使用日志系统，就可以通过控制日志级别，来批量控制打印信息。
        System.out.println("受影响行数:"+count);
    }

    /**
     * 测试数据库连接
     * @throws SQLException
     */
    @Test
    public void testConnection() throws SQLException{
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    /**
     * 打印自定义的日志
     */
    @Test
    public void testLogger(){
        //获取Logger对象，这里传入的Class就是当前打印日志的类
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        //等级 DEBUG < INFO < WARN < ERROR
        logger.debug("I am DEBUG!!!");
        logger.debug("I am DEBUG!!!");
        logger.debug("I am DEBUG!!!");

        logger.info("I am INFO!!!");
        logger.info("I am INFO!!!");
        logger.info("I am INFO!!!");

        logger.warn("I am WARN!!!");
        logger.warn("I am WARN!!!");
        logger.warn("I am WARN!!!");

        logger.error("I am ERROR!!!");
        logger.error("I am ERROR!!!");
        logger.error("I am ERROR!!!");
    }

    /**
     * 测试事务
     */
    @Test
    public void testTx(){
        Admin admin = new Admin(null,"bob","鲍勃","123456","22@126.com",null);
        adminService.saveAdmin(admin);
    }

}
