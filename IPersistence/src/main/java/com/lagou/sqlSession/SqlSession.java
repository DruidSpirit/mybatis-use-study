package com.lagou.sqlSession;

import java.util.List;

public interface SqlSession {

    //查询所有
     <E> List<E> selectList(String statementid,Object... params) throws Exception;

    //根据条件查询单个
     <T> T selectOne(String statementid,Object... params) throws Exception;

    /**
     * 根据条件插入
     * @param statementId  statementId
     * @param params       插入语句条件参数
     * @return             true 插入成功  false 插入失败
     */
    boolean insert(String statementId, Object... params)throws Exception;

    /**
     * 根据条件修改
     * @param statementId  statementId
     * @param params       修改语句条件参数
     * @return             true 修改成功  false 修改失败
     */
    boolean update(String statementId, Object... params)throws Exception;

    /**
     * 根据条件删除
     * @param statementId  statementId
     * @param params       删除语句条件参数
     * @return             true 删除成功  false 删除失败
     */
    boolean delete(String statementId, Object... params)throws Exception;

    //为Dao接口生成代理实现类
     <T> T getMapper(Class<?> mapperClass);


}
