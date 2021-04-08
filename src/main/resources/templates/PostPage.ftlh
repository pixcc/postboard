<#-- @ftlvariable name="post" type="ru.postboard.domain.Post" -->
<#-- @ftlvariable name="comment" type="ru.postboard.domain.Comment" -->

<#import 'commons.ftlh' as c>

<@c.page>
    <#if post??>
        <@c.post post/>
        <#if user??>
            <form class="comment-form" method="post">
                <div class="field">
                    <div class="name">
                        <label for="text">New comment</label>
                    </div>
                    <div class="value">
                        <textarea id="text" rows="10" cols="45" name="text">${comment.text!}</textarea>
                    </div>
                </div>
                <input type="submit" value="Post">
                <div class="error">
                    <@c.error "comment.text"/>
                </div>
            </form>
        </#if>
        <#list post.comment as c>
            <div class="comment">
                ${c.user.login}: ${c.text}
            </div>
        </#list>
    <#else>
        No such post
    </#if>
</@c.page>