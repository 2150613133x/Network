package com.cx.bean;

/**
 * Created by ly-chenxiao on 15/10/2021
 * Email: chenxiao@szlanyou.com
 * Description:
 *
 * @author: chenxiao
 */
public class CommentItem {

    private String articleId;
    private String commentContent;

    public CommentItem(String articleId, String commentContent) {
        this.articleId = articleId;
        this.commentContent = commentContent;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
