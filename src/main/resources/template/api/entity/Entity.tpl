package <#$package#>entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * <#$entity.entityName#>
 * <p>
* Created by <#$auth#> on <#$date#>.
 */
@ApiModel("<#$entity.entityName#>")
@Entity
@Table(name = "<#$entity.tableName#>")
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@ToString
public class <#$entity.entityClassName#> extends IdEntity {
    <#foreach from=$entity.fields item=field#>
    /**
     * <#$field.desc#>
     */
    @ApiModelProperty(value = "<#$field.desc#>", required = false)<#if $field.isNull == "not null" #>@NotNull<#/if#>
    private <#$field.type#> <#$field.name#>;
    <#/foreach#>
}