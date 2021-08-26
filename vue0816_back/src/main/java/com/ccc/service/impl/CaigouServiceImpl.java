package com.ccc.service.impl;

import com.ccc.entity.Caigou;
import com.ccc.dao.CaigouDao;
import com.ccc.service.CaigouService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Caigou)表服务实现类
 *
 * @author makejava
 * @since 2021-08-19 21:51:39
 */
@Service("caigouService")
public class CaigouServiceImpl implements CaigouService {
    @Resource
    private CaigouDao caigouDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Caigou queryById(Integer id) {
        return this.caigouDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Caigou> queryAllByLimit(int offset, int limit) {
        return this.caigouDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param caigou 实例对象
     * @return 实例对象
     */
    @Override
    public Caigou insert(Caigou caigou) {
        this.caigouDao.insert(caigou);
        return caigou;
    }

    /**
     * 修改数据
     *
     * @param caigou 实例对象
     * @return 实例对象
     */
    @Override
    public Caigou update(Caigou caigou) {
        this.caigouDao.update(caigou);
        return this.queryById(caigou.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.caigouDao.deleteById(id) > 0;
    }
}