package com.duocode.webscrapping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String description;
    private int linkCount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "link", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LinkDetail> linkDetails;

    public Link(String url, String description, int linkCount) {
        this.url = url;
        this.description = description;
        this.linkCount = linkCount;
    }

    public Link(String url, String description, int linkCount, User user) {
        this.url = url;
        this.description = description;
        this.linkCount = linkCount;
        this.user = user;
    }
}
