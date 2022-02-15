package com.crada.nftsystem.repository;

import com.crada.nftsystem.model.Nft;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface NftRepository extends JpaRepository <Nft, Integer> {

}
