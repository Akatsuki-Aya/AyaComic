package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.Comic;
import com.example.aya.demo.dao.impl.ComicImpl;
import com.example.aya.demo.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aya
 */
@Service
public class ComicServiceImpl implements ComicService {
    @Autowired
    private ComicImpl comicImpl;

    @Override
    public Comic saveComic(Comic comic) {
        return comicImpl.save(comic);
    }
}
