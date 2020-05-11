package com.example.aya.demo.service;

import com.example.aya.demo.dao.Progress;

import java.util.List;

/**
 * @author Aya
 */
public interface ProgressService {
    /**
     * 查询所有进度
     * @return
     */
    List<Progress> findAll();
}
