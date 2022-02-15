package com.crada.nftsystem.repository;

import com.crada.nftsystem.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
