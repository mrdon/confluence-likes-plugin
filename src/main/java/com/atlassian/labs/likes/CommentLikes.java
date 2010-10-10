package com.atlassian.labs.likes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 */
@XmlRootElement
public class CommentLikes
{
    @XmlElement
    private List<String> users;

    @XmlElement
    private boolean liked;

    public CommentLikes(List<String> users, boolean liked)
    {
        this.liked = liked;
        this.users = users;
    }

    public CommentLikes()
    {
    }


    public List<String> getUsers()
    {
        return users;
    }

    public void setUsers(List<String> users)
    {
        this.users = users;
    }

    public boolean isLiked()
    {
        return liked;
    }

    public void setLiked(boolean liked)
    {
        this.liked = liked;
    }
}
