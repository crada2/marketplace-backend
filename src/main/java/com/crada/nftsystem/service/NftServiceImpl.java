/*package com.crada.nftsystem.service;

import com.crada.nftsystem.model.Nft;
import com.crada.nftsystem.repository.NftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NftServiceImpl implements NftService {
    @Autowired
    private NftRepository nftRepository;

    @Override
    public Nft saveNft(Nft nft) {
        return nftRepository.save(nft);
    }

    @Override
    public List<Nft> getAllNfts() {
        return nftRepository.findAll();
    }

}
*/