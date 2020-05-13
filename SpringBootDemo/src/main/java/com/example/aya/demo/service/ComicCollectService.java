package com.example.aya.demo.service;

import com.example.aya.demo.dao.ComicCollect;

/**
 * @author Aya
 */
public interface ComicCollectService {
    ComicCollect saveComicCollect(ComicCollect comicCollect);
    ComicCollect findComicCollectByUserIdAndComicId(Long userId,Long comicId);
    void  deleteComicCollectById(Long id);
}
