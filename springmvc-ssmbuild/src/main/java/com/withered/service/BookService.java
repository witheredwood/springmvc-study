package com.withered.service;

import com.withered.pojo.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 业务层接口
public interface BookService {
    // 增加一本书
    int addBook(Books books);

    // 删除一本书
    int deleteBook(int id);

    // 更新一本书
    int updateBook(Books books);

    // 查询一本书
    Books queryBookById(int id);

    // 查询全部的书
    List<Books> queryAllBook();

    // 按名称查询书籍
    List<Books> queryBookByName(String bookName);

}
