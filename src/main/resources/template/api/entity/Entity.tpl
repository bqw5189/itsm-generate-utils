package <#$package#>entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Date;

/**
 * <#$entity.entityName#>
 * <p>
* Created by <#$auth#> on <#$date#>.
 */
@Entity
@Table(name = "<#$entity.tableName#>")
@ApiModel("<#$entity.entityName#>")
public class <#$entity.entityClassName#> extends CMSEntity {
    <#foreach from=$entity.fields item=field#>
    /**
     * <#$field.desc#>
     */
    @ApiModelProperty(value = "<#$field.desc#>", required = false)
    <#if $field.isNull == "not null" #>@NotNull<#/if#>
    @Column(columnDefinition = "<#$field.desc#>")
    private <#$field.type#> <#$field.name#>;
    public <#$field.type#> get<#$field.monthName#>() {
        return <#$field.name#>;
    }
    public void set<#$field.monthName#>(<#$field.type#> <#$field.name#>) {
        this.<#$field.name#> = <#$field.name#>;
    }
    <#/foreach#>
}

