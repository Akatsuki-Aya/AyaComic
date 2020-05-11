package com.example.aya.demo.service.impl;

import com.example.aya.demo.dao.Progress;
import com.example.aya.demo.dao.impl.ProgressImpl;
import com.example.aya.demo.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aya
 */
@Service
public class ProgressServiceImpl implements ProgressService {
    @Autowired
    private ProgressImpl progressImpl;

    @Override
    public List<Progress> findAll() {
        return progressImpl.findAll();
    }
}
