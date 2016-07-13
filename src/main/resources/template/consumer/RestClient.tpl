
package <#$package#>restclient;


import com.alibaba.fastjson.JSON;
import <#$package#>entity.<#$entity.entityClassName#>;
import junit.framework.Assert;
import org.dubbo.x.consumer.Assertable;
import org.dubbo.x.consumer.RestClientTestCase;
import org.dubbo.x.entity.RestResultEnum;
import org.dubbo.x.facade.RestResult;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ProcessingException;
import java.util.List;

/**
* Created by <#$auth#> on <#$date#>.
 */
public class <#$entity.entityClassName#>RestClient extends RestClientTestCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(<#$entity.entityClassName#>RestClient.class);
    private static final String TOKEN = "fc5db3b3-4063-4a12-a511-880ba19e4b58";
    private static final String URL = "http://localhost:8818/api/v2/<#$entity.path#>";

    @Test(expected = ProcessingException.class)
    public void testList(){
         assertRequest(new Assertable() {
            @Override
            public void assertBlack(RestResult restResult) throws Exception {
                RestResult<List<<#$entity.entityClassName#>>> result = (RestResult<List<<#$entity.entityClassName#>>>)restResult;
                Assert.assertEquals(restResult.getCode(), RestResultEnum.OK.getCode());

                Assert.assertNotNull(result.getData());

                Assert.assertEquals(result.getData().size(), 0);

                LOGGER.info("<#$entity.entityClassName#> list:{}", JSON.toJSON(restResult));
            }
        }, URL, <#$entity.entityClassName#>RestClient.TOKEN);
    }


//    @Test(expected = ProcessingException.class)
//    public void testCreate(){
//        final <#$entity.entityClassName#> <#$entity.fieldClassName#> = new <#$entity.entityClassName#>();
////        <#$entity.fieldClassName#>.set*("*");
//
//        Response response = getBuilder(URL, <#$entity.fieldClassName#>RestClient.TOKEN).post(Entity.entity(<#$entity.fieldClassName#>, MediaType.APPLICATION_JSON));
//
//        assertRequest(new Assertable() {
//            @Override
//            public void assertBlack(RestResult restResult) throws Exception {
//                LOGGER.info("restResult:{}", restResult);
//                RestResult<HashMap> result = (RestResult<HashMap>)restResult;
//                Assert.assertEquals(restResult.getCode(), RestResultEnum.OK.getCode());
//
//                Assert.assertNotNull(result.getData());
//
//                LOGGER.info("result.getData():{}", result.getData().getClass());
//
//                Assert.assertNotNull(result.getData().get("id"));
//
//                LOGGER.info("list:{}", JSON.toJSON(restResult));
//            }
//        }, response);
//    }



}
