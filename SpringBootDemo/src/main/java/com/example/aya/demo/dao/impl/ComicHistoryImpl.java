package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.ComicHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aya
 */
public interface ComicHistoryImpl extends JpaRepository<ComicHistory,Long> {
    @Override
    ComicHistory save(ComicHistory comicHistory);
    ComicHistory findByUserIdAndComicId(Long userId,Long comicId);
    @Override
    void deleteById(Long id);
}
