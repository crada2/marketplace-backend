package com.crada.nftsystem.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private Set<Nft> nfts = new HashSet<>();



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Nft> getNfts() {
        return nfts;
    }

    public void setNfts(Set<Nft> nfts) {
        this.nfts = nfts;

        for(Nft n : nfts) {
            n.setSeller(this);
        }
    }
}