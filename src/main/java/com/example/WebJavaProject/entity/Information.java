package com.example.WebJavaProject.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "information")
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @NotNull(message = "is required")
    @Size(min = 3, max = 20, message = "has to 3-20 characters/digit long")
    private String title;

    @Column(name = "content")
    @NotNull(message = "is required")
    @Size(min = 5, max = 500, message = "has to 5-500 characters/digit long")
    private String content;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @Column(name = "link")
    private String link;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    @NotNull(message = "is required")
    private Category category;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;


    public Information() {
    }

    public Information(int id, String title, String content, Date creationDate, String link, User user, List<User> users) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.link = link;
        this.user = user;

    }

    public Information(int id, String title, String content, Date creationDate, String link, Category category, User user, List<User> users) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.link = link;
        this.category = category;
        this.user = user;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", link='" + link + '\'' +
                ", category=" + category +
                ", user=" + user +
                '}';
    }


}
