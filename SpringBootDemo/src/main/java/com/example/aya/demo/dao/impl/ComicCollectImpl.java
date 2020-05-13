package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.ComicCollect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Aya
 */
public interface ComicCollectImpl extends JpaRepository<ComicCollect, Long> {
    @Override
    ComicCollect save(ComicCollect comicCollect);
    @Override
    Optional<ComicCollect> findById(Long id);

    ComicCollect findByComicIdAndUserId(Long comicId, Long userId);

    @Override
    void deleteById(Long id);
}
