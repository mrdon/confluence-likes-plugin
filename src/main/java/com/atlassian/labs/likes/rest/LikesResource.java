package com.atlassian.labs.likes.rest;

import com.atlassian.labs.likes.LikesManager;
import com.atlassian.sal.api.user.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 *
 */
@Path("/")
public class LikesResource
{
    private final LikesManager likesManager;
    private final UserManager userManager;

    public LikesResource(LikesManager likesManager, UserManager userManager)
    {
        this.likesManager = likesManager;
        this.userManager = userManager;
    }

    @GET
    @Path("/page/{id}")
    public Response getForPage(@PathParam("id") long pageId)
    {
        return Response.ok(likesManager.getLikesForPage(pageId)).build();
    }

    @GET
    @Path("/comment/{id}")
    public Response getForComment(@PathParam("id") long commentId)
    {
        return Response.ok(likesManager.getLikesForComment(commentId)).build();
    }

    @POST
    @Path("/comment/{id}")
    public Response likeComment(@PathParam("id") long commentId, @Context HttpServletRequest req)
    {
        String user = userManager.getRemoteUsername(req);
        likesManager.likeComment(user, commentId);
        return Response.created(UriBuilder.fromPath("").build()).build();
    }

    @DELETE
    @Path("/comment/{id}")
    public Response unlikeComment(@PathParam("id") long commentId, @Context HttpServletRequest req)
    {
        String user = userManager.getRemoteUsername(req);
        likesManager.unlikeComment(user, commentId);
        return Response.ok().build();
    }
}
