package <#$package#>dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * <#$entity.entityName#>
 *
 * <p>
 * Created by <#$auth#> on <#$date#>.
 */
@ApiModel("<#$entity.entityName#>")
@ToString
@Setter
@Getter
public class <#$entity.entityClassName#>DTO{
    @ApiModelProperty(value = "id")
    private String id;

    <#foreach from=$entity.fields item=field#>
    /**
     * <#$field.desc#>
     */
    @ApiModelProperty(value = "<#$field.desc#>", required = false)<#if $field.isNull == "not null" #>@NotNull<#/if#>
     private <#$field.type#> <#$field.name#>;
    <#/foreach#>
}