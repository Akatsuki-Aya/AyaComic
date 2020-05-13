package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.ComicDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aya
 */
public interface ComicDetailImpl extends JpaRepository<ComicDetail, Long> {

    @Override
    ComicDetail save(ComicDetail comicDetail);
    ComicDetail findByIdAndComicId(Long id,Long comicId);
}
