package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.Comic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Aya
 */
public interface ComicImpl extends JpaRepository<Comic, Long> {
    /**
     * 添加comic
     * @param comic
     * @return
     */
    @Override
    Comic save(Comic comic);
}
