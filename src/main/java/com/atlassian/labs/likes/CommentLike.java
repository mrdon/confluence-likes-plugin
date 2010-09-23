package com.atlassian.labs.likes;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement
public class CommentLike
{
    private String user;
    private long commentId;

    public CommentLike()
    {
    }

    public CommentLike(String user, long commentId)
    {
        this.user = user;
        this.commentId = commentId;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public long getCommentId()
    {
        return commentId;
    }

    public void setCommentId(long commentId)
    {
        this.commentId = commentId;
    }
}
