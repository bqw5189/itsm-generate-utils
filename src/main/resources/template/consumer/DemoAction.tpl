package <#$package#>consumer;


import com.alibaba.fastjson.JSON;
import <#$package#>entity.<#$entity.entityClassName#>;
import <#$package#>service.<#$entity.entityClassName#>Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
* Created by <#$auth#> on <#$date#>.
 */
public class <#$entity.entityClassName#>DemoAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(<#$entity.entityClassName#>DemoAction.class);
    private <#$entity.entityClassName#>Service <#$entity.fieldClassName#>Service;

    public <#$entity.entityClassName#>Service get<#$entity.entityClassName#>Service() {
        return <#$entity.fieldClassName#>Service;
    }

    public void set<#$entity.entityClassName#>Service(<#$entity.entityClassName#>Service <#$entity.fieldClassName#>Service) {
        this.<#$entity.fieldClassName#>Service = <#$entity.fieldClassName#>Service;
    }

    public void start() throws Exception {
        List<<#$entity.entityClassName#>> <#$entity.fieldClassName#>s = <#$entity.fieldClassName#>Service.listAll();
        assert(<#$entity.fieldClassName#>s != null);
        assert(<#$entity.fieldClassName#>s.size() == 1);

        LOGGER.info("list all:{}", JSON.toJSON(<#$entity.fieldClassName#>s));
    }
}
