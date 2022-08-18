package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.io.Resources;
import com.lagou.pojo.User;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    SqlSession sqlSession;

    public IPersistenceTest() throws PropertyVetoException, DocumentException {

        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        sqlSession = sqlSessionFactory.openSession();
    }


    @Test
    public void test() throws Exception {


        //调用
        User user = new User();
        user.setId(1);
        user.setUsername("lucy");
      /*  User user2 = sqlSession.selectOne("user.selectOne", user);

        System.out.println(user2);*/

       /* List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }*/

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
        User byCondition = userDao.findByCondition(user);
        System.out.println(byCondition);

    }

    @Test
    public void testInsert() throws Exception {

        User user = new User();
        user.setUsername("小汤姆");
        user.setPassword("12345");
        user.setBirthday("2000-1-01");

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        boolean result = userDao.saveByParam(user);
        System.out.println("插入执行结果："+result);

    }

    @Test
    public void testUpdate()throws Exception{

        User user = new User();
        user.setUsername("小汤姆");
        user.setPassword("54321");
        user.setBirthday("1999-09-09");

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        boolean result = userDao.updateCondition(user);
        System.out.println("修改执行结果："+result);
    }

    @Test
    public void testDelete()throws Exception{

        User user = new User();
        user.setUsername("小汤姆");

        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        boolean result = userDao.removeByCondition(user);
        System.out.println("删除执行结果："+result);
    }



}
