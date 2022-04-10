package com.crada.nftsystem.repository;

import com.crada.nftsystem.model.Nft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftRepository extends JpaRepository <Nft, Integer> {

}
