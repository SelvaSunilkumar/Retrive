package com.example.retrive;

public class User {

    public String url;
    public String portal;

    public User()
    {
    }

    public User(String portal,String url)
    {
        this.url = url;
        this.portal = portal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }
}
