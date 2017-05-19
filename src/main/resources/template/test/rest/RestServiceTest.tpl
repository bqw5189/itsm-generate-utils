package <#$package#>facade;

import <#$package#>dto.ListFilter;
import <#$package#>dto.PageSearch;
import <#$package#>dto.SearchFilter;
import <#$package#>dto.Sort;
import <#$package#>entity.<#$entity.entityClassName#>;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

/**
 * <#$entity.entityName#>
 * Created by <#$auth#> on <#$date#>.
 */
public class <#$entity.entityClassName#>RestServiceTest {

  private SmartHttpClient client = new SmartHttpClient();

  @Test
  public void create() throws Exception {
      String token = client.buildToken("admin", "admin");
      <#$entity.entityClassName#> entity = new <#$entity.entityClassName#>();

      client.post("/<#$entity.path#>", token, JSON.toJSONString(entity));
  }

  @Test
  public void detail() throws Exception {
      String token = client.buildToken("admin", "admin");
      client.get("/<#$entity.path#>/27f962a0bbc7c0bc129fdcf6559cfae6", token);
  }

  @Test
  public void list() throws Exception {
      String token = client.buildToken("admin", "admin");
      client.get("/<#$entity.path#>/list", token);
  }

  @Test
  public void page() throws Exception {
      String token = client.buildToken("admin", "admin");
      PageSearch pageSearch = new PageSearch();
      pageSearch.setPageSize(10);
      pageSearch.setPageNumber(1);
      Sort sort = new Sort();
      sort.setFieldName("displayName");
      sort.setDirection("desc");
      pageSearch.setSort(sort);
      client.post("/<#$entity.path#>/page", token, JSON.toJSONString(pageSearch));
  }


  @Test
  public void delete() throws Exception {
      String token = client.buildToken("admin", "admin");
      client.delete("/<#$entity.path#>/2c913f795b9f1039015b9f15cf2e0000", token);
  }
}
