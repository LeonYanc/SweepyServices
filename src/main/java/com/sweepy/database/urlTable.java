package com.sweepy.database;

import javax.persistence.*;

@Entity
@Table(name = "urls")
public class urlTable {

    public urlTable() {

    }

    public urlTable(String longUrl, String shortUrl, String method) {
        this.method = method;
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    @Id
    @Column(name="urlId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "longUrl")
    private String longUrl;
    @Column(name = "shortUrl")
    private String shortUrl;

    @Column(name = "method")
    private String method;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "urlTable{" +
                "id=" + id +
                ", longUrl='" + longUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
