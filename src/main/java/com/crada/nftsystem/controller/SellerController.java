package com.crada.nftsystem.controller;

import com.crada.nftsystem.model.Seller;
import com.crada.nftsystem.repository.NftRepository;
import com.crada.nftsystem.repository.SellerRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/sellers")
public class SellerController {
    private final SellerRepository sellerRepository;
    private final NftRepository nftRepository;

    @Autowired
    public SellerController(SellerRepository sellerRepository, NftRepository nftRepository) {
        this.sellerRepository = sellerRepository;
        this.nftRepository = nftRepository;
    }

    @PostMapping
    public ResponseEntity<Seller> create(@RequestBody Seller seller) {
        Seller savedSeller = sellerRepository.save(seller);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedSeller.getId()).toUri();

        return ResponseEntity.created(location).body(savedSeller);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seller> update(@PathVariable Integer id, @RequestBody Seller seller) {
        Optional<Seller> optionalSeller = sellerRepository.findById(id);
        if (!optionalSeller.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        seller.setId(optionalSeller.get().getId());
        sellerRepository.save(seller);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Seller> delete(@PathVariable Integer id) {
        Optional<Seller> optionalSeller = sellerRepository.findById(id);

        if (!optionalSeller.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        sellerRepository.delete(optionalSeller.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getById(@PathVariable Integer id) {
        Optional<Seller> optionalSeller = sellerRepository.findById(id);

        if (!optionalSeller.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalSeller.get());
    }

    @GetMapping
    public ResponseEntity<Page<Seller>> getAll(Pageable pageable) {
        return ResponseEntity.ok(sellerRepository.findAll(pageable));
    }
}
