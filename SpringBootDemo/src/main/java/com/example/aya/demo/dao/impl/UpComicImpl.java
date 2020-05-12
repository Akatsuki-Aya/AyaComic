package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.UpComic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aya
 */
public interface UpComicImpl extends JpaRepository<UpComic, Long> {
    @Override
    UpComic save(UpComic upComic);
}
