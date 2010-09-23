package com.atlassian.labs.likes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;

/**
 *
 */
@XmlRootElement
public class PageLikes
{
    @XmlElement
    private Map<Long, CommentLikes> commentLikes;

    public PageLikes()
    {
    }

    public PageLikes(Map<Long, CommentLikes> commentLikes)
    {
        this.commentLikes = commentLikes;
    }

    public Map<Long, CommentLikes> getCommentLikes()
    {
        return commentLikes;
    }

    public void setCommentLikes(Map<Long, CommentLikes> commentLikes)
    {
        this.commentLikes = commentLikes;
    }
}
