package com.withered.dao;

import com.withered.pojo.Books;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

// 原来手动配置dao层注入到Spring中，需要编写一个实现类。实现类如下
public class BookMapperImpk extends SqlSessionDaoSupport implements BookMapper {
    SqlSessionTemplate sessionTemplate;

    public int addBook(Books books) {
        return 0;
    }

    public int deleteBookById(int id) {
        return 0;
    }

    public int updateBook(Books books) {
        return 0;
    }

    public Books queryBookById(int id) {
        return null;
    }

    public List<Books> queryAllBook() {
        return null;
    }

    public List<Books> queryBookByName(String bookName) {
        return null;
    }
}
