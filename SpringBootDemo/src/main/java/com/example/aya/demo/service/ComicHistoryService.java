package com.example.aya.demo.service;

import com.example.aya.demo.dao.ComicHistory;

/**
 * @author Aya
 */
public interface ComicHistoryService {
    ComicHistory findComicHistoryByUserIdAndComicId(Long userId, Long comicId);
    ComicHistory saveComicHistory(ComicHistory comicHistory);
    void  deleteComicHistoryById(Long id);

}
