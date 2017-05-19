package <#$package#>repository;

import <#$package#>TestSmartApplication;
import <#$package#>entity.<#$entity.entityClassName#>;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
* <#$entity.entityName#>
* Created by <#$auth#> on <#$date#>.
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestSmartApplication.class)
@ComponentScan("<#$package#>")
@EnableAutoConfiguration
public class <#$entity.entityClassName#>DaoTest {

    @Autowired
    private <#$entity.entityClassName#>Dao dao;

    @Test
    public void findAll() {
        List<<#$entity.entityClassName#>> lists = dao.findAllBy();

        Assert.assertNotNull(lists);

        Assert.assertEquals(lists.size(), 0);
    }
}
