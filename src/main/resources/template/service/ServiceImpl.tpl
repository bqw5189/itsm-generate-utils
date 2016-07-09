package com.itsm.platform.account.service;

import com.itsm.platform.account.entity.<#$entity.entityClassName#>;
import com.itsm.platform.account.repository.DaoBase;
import com.itsm.platform.account.repository.<#$entity.entityClassName#>Dao;
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
