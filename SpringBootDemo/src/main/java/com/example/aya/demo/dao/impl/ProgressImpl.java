package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressImpl extends JpaRepository<Progress, Long> {
    /**
     * 查询所有进度
     * @return
     */
    @Override
    List<Progress> findAll();
}
