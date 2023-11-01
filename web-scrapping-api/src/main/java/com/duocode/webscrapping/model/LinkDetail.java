package com.duocode.webscrapping.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "link_details")
public class LinkDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String url;
    private String info;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "link_id")
    private Link link;

    public LinkDetail(String type, String url, String info) {
        this.type = type;
        this.url = url;
        this.info = info;
    }

    public LinkDetail(String type, String url, String info, Link link) {
        this.type = type;
        this.url = url;
        this.info = info;
        this.link = link;
    }
}
