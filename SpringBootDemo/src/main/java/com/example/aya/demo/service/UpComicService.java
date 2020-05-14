package com.example.aya.demo.service;

import com.example.aya.demo.dao.UpComic;

import java.util.List;

/**
 * @author Aya
 */
public interface UpComicService {
    UpComic saveUpComicService(UpComic upComic);
    List<UpComic> findUpComicByUserId(Long userId);
}
