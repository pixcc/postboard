<#-- @ftlvariable name="posts" type="ru.postboard.domain.Post[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#list posts as post>
        <@c.post post=post/>
    </#list>
</@c.page>
