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

    public CommentLikes(List<String> users)
    {
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
}
