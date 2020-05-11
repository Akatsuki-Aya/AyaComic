package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.Progress;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Aya
 */
public interface ProgressImpl extends JpaRepository<Progress, Long> {
    /**
     * 查询所有进度
     * @return
     */
    @Override
    List<Progress> findAll();

    /**
     * 查询所有进度 排序
     * @param sort
     * @return
     */
    @Override
    List<Progress> findAll(Sort sort);
}
