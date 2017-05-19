package <#$package#>facade;

import <#$package#>converter.Converter;
import <#$package#>dto.<#$entity.entityClassName#>DTO;
import <#$package#>entity.<#$entity.entityClassName#>;

/**
 * <#$entity.entityName#> 接口
 *
* Created by <#$auth#> on <#$date#>.
 */
public interface <#$entity.entityClassName#>RestService extends CURDRestService<<#$entity.entityClassName#>DTO>,Converter<<#$entity.entityClassName#>DTO, <#$entity.entityClassName#>>{

}
