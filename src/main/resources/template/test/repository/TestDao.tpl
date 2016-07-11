package <#$package#>repository;

import <#$package#>entity.<#$entity.entityClassName#>;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import java.util.List;

/**
 * <#$entity.entityName#>
 * Created by tom on 16/6/21.
 */
@ContextConfiguration(locations = {"/META-INF/spring/applicationContext.xml"})
public class Test<#$entity.entityClassName#>Dao extends SpringTransactionalTestCase {

    @Autowired
    private <#$entity.entityClassName#>Dao <#$entity.fieldClassName#>Dao;

    @Test
    public void findAll(){
        List<<#$entity.entityClassName#>> <#$entity.fieldClassName#>s = <#$entity.fieldClassName#>Dao.findAllBy();

        Assert.assertNotNull(<#$entity.fieldClassName#>s);

        Assert.assertEquals(<#$entity.fieldClassName#>s.size(), 3);
    }
}
