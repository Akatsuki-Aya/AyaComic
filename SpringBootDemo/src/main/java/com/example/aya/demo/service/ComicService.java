package com.example.aya.demo.service;

import com.example.aya.demo.dao.Comic;

/**
 * @author Aya
 */
public interface ComicService {
    /**
     * 保存comic
     * @param comic
     * @return
     */
    Comic saveComic(Comic comic);
    Comic findComicById(Long id);
}
