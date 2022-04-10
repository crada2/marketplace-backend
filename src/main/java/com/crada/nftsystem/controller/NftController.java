package com.crada.nftsystem.controller;

import com.crada.nftsystem.model.Nft;
import com.crada.nftsystem.model.Seller;
import com.crada.nftsystem.repository.NftRepository;
import com.crada.nftsystem.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/tokens")

public class NftController {
    private final NftRepository nftRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public NftController(NftRepository nftRepository, SellerRepository sellerRepository){
        this.nftRepository = nftRepository;
        this.sellerRepository = sellerRepository;
    }

    @PostMapping
    public ResponseEntity<Nft> create(@RequestBody Nft nft) {
        Optional<Seller> optionalSeller = sellerRepository.findById(nft.getSeller().getId());
        if (!optionalSeller.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        nft.setSeller(optionalSeller.get());

        Nft savedNft = nftRepository.save(nft);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedNft.getId()).toUri();

        return ResponseEntity.created(location).body(savedNft);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nft> update(@RequestBody Nft nft, @PathVariable Integer id) {
        Optional<Seller> optionalSeller = sellerRepository.findById(nft.getSeller().getId());
        if (!optionalSeller.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Nft> optionalNft = nftRepository.findById(id);
        if (!optionalNft.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        nft.setSeller(optionalSeller.get());
        nft.setId(optionalNft.get().getId());
        nftRepository.save(nft);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Nft> delete (@PathVariable Integer id) {
        Optional<Nft> optionalNft = nftRepository.findById(id);
        if (!optionalNft.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        nftRepository.delete(optionalNft.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Nft>> getAll(Pageable pageable) {
        return ResponseEntity.ok(nftRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nft> getById(@PathVariable Integer id) {
        Optional<Nft> optionalNft = nftRepository.findById(id);
        if (!optionalNft.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalNft.get());
    }
}
