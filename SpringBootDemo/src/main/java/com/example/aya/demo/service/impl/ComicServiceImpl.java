package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.Comic;
import com.example.aya.demo.dao.impl.ComicImpl;
import com.example.aya.demo.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

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

    @Override
    public Comic findComicById(Long id){
        Optional<Comic> comic = comicImpl.findById(id);
        return comic.isPresent()?comic.get():null;
    }

    public Page<Comic> findAll(Pageable pageable){
        return comicImpl.findAll(pageable);
    }
}
