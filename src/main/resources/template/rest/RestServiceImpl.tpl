package <#$package#>facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import <#$package#>dto.<#$entity.entityClassName#>DTO;
import <#$package#>entity.<#$entity.entityClassName#>;
import <#$package#>service.<#$entity.entityClassName#>Service;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import io.swagger.annotations.Api;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <#$entity.entityName#>
 * Created by <#$auth#> on <#$date#>.
 */
@Api(value="<#$entity.path#>", description = "<#$entity.entityName#>接口")
@Path("<#$entity.path#>")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
@Getter
public class <#$entity.entityClassName#>RestServiceImpl extends ConverterRestServiceBase<<#$entity.entityClassName#>DTO, <#$entity.entityClassName#>> implements <#$entity.entityClassName#>RestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(<#$entity.entityClassName#>RestServiceImpl.class);

    @Autowired
    private <#$entity.entityClassName#>Service service;
}
