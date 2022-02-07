package com.crada.nftsystem.controller;

import com.crada.nftsystem.model.Nft;
import com.crada.nftsystem.service.NftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/tokens")
public class NftController {

    @Autowired
    private NftService nftService;

    @PostMapping
    public String add(@RequestBody Nft nft){
        nftService.saveNft(nft);
        return "New NFT is added";
    }

    @GetMapping
    public List<Nft> getAllNfts(){
        return nftService.getAllNfts();
    }
}
