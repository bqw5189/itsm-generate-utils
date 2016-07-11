package <#$package#>service;

import <#$package#>entity.<#$entity.entityClassName#>;
import org.dubbo.x.repository.DaoBase;
import org.dubbo.x.service.CURDServiceBase;
import <#$package#>repository.<#$entity.entityClassName#>Dao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  <#$entity.entityName#>
 * @author baiqw
 */
public class <#$entity.entityClassName#>ServiceImpl extends CURDServiceBase<<#$entity.entityClassName#>> implements <#$entity.entityClassName#>Service {
    @Autowired
    private <#$entity.entityClassName#>Dao <#$entity.fieldClassName#>Dao;

    @Override
    public DaoBase<<#$entity.entityClassName#>> getDao() {
        return <#$entity.fieldClassName#>Dao;
    }
}
