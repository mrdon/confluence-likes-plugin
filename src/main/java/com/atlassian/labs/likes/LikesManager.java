package com.atlassian.labs.likes;

import com.atlassian.confluence.pages.Comment;
import com.atlassian.confluence.pages.CommentManager;
import com.atlassian.confluence.pages.Page;
import com.atlassian.confluence.pages.PageManager;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LikesManager
{
    private final PluginSettings pluginSettings;
    private final PageManager pageManager;
    private final CommentManager commentManager;

    public LikesManager(PluginSettingsFactory factory, PageManager pageManager, CommentManager commentManager)
    {
        this.pageManager = pageManager;
        this.commentManager = commentManager;
        pluginSettings = factory.createGlobalSettings();
    }
    public void likeComment(String user, long commentId)
    {
        List<String> likes = getLikesInternal(commentId);
        if (!likes.contains(user))
        {
            likes.add(user);
        }
        pluginSettings.put(createKey(commentId), likes);
    }

    public void unlikeComment(String user, long commentId)
    {
        List<String> likes = getLikesInternal(commentId);
        likes.remove(user);
        pluginSettings.put(createKey(commentId), likes);
    }

    private List<String> getLikesInternal(long commentId)
    {
        List<String> result = (List<String>) pluginSettings.get(createKey(commentId));
        if (result == null)
        {
            result = new ArrayList<String>();
        }
        return result;
    }

    private String createKey(long commentId)
    {
        return "likes-" + commentId;
    }

    public PageLikes getLikesForPage(long pageId, String user)
    {
        Map<Long,CommentLikes> likes = new HashMap<Long, CommentLikes>();
        Page page = pageManager.getPage(pageId);
        for (Comment comment : page.getComments())
        {
            likes.put(comment.getId(), getLikesForComment(comment.getId(), user));
        }
        return new PageLikes(likes);
    }

    public CommentLikes getLikesForComment(long commentId, String user)
    {
        List<String> likes = getLikesInternal(commentId);
        return new CommentLikes(likes, likes.contains(user));
    }
}
