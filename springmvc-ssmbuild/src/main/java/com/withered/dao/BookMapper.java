package com.withered.dao;

import com.withered.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//做一些接口操作
public interface BookMapper {

    // 增加一本书
    int addBook(Books books);

    // 删除一本书
    int deleteBookById(@Param("bookId") int id);

    // 更新一本书
    int updateBook(Books books);

    // 查询一本书
    Books queryBookById(@Param("bookId") int id);

    // 查询全部的书
    List<Books> queryAllBook();

    // 按名称查询书籍
    List<Books> queryBookByName(@Param("bookName") String bookName);
}
