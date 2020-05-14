package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.UpComic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Aya
 */
public interface UpComicImpl extends JpaRepository<UpComic, Long> {
    @Override
    UpComic save(UpComic upComic);

    List<UpComic> findByUserId(Long userId);
}
