package <#$package#>service;

import <#$package#>entity.<#$entity.entityClassName#>;
import <#$package#>repository.<#$entity.entityClassName#>Dao;

import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 *  <#$entity.entityName#>
 * Created by <#$auth#> on <#$date#>.
 */
@Getter
public class <#$entity.entityClassName#>ServiceImpl extends CURDServiceBase<<#$entity.entityClassName#>> implements <#$entity.entityClassName#>Service {
    @Autowired
    private <#$entity.entityClassName#>Dao dao;
}