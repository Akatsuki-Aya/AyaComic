package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

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

    @Override
    Optional<Comic> findById(Long id);

    @Override
    Page<Comic> findAll(Pageable pageable);
}
