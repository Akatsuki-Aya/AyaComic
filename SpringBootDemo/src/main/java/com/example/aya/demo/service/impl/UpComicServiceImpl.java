package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.UpComic;
import com.example.aya.demo.dao.impl.UpComicImpl;
import com.example.aya.demo.service.UpComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aya
 */
@Service
public class UpComicServiceImpl implements UpComicService {
    @Autowired
    private UpComicImpl upComicImpl;
    @Override
    public UpComic saveUpComicService(UpComic upComic) {
        return upComicImpl.save(upComic);
    }
}
