package com.app.androidx_project;

import com.google.gson.annotations.SerializedName;

public class Comments
{
    @SerializedName("name")
    private String name;
    @SerializedName("postId")
    private String postId;

    private String id;

    @SerializedName("body")
    private String body;

    @SerializedName("email")
    private String email;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getPostId ()
    {
        return postId;
    }

    public void setPostId (String postId)
    {
        this.postId = postId;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getBody ()
    {
        return body;
    }

    public void setBody (String body)
    {
        this.body = body;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", postId = "+postId+", id = "+id+", body = "+body+", email = "+email+"]";
    }
}