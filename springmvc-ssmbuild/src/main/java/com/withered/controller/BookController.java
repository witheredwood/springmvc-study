package com.withered.controller;

import com.withered.pojo.Books;
import com.withered.service.BookService;
import com.withered.service.BookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    // controller 调用 service 层
    @Autowired
    @Qualifier("BookServiceImp")  // 加载指定的类
    private BookService bookService = new BookServiceImp();

    // 查询全部的书籍，并返回到书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook";
    }

    // 跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPaper() {
        return "addBook";
    }

    // 添加书籍的请求
    @RequestMapping("/addBook")
    public String addPaper(Books books) {
        System.out.println("addBook=>" + books);
        bookService.addBook(books);
        return "redirect:/book/allBook";  // 重定向，实现请求复用
    }

    // 跳转到修改书籍页面
    @RequestMapping("/toUpdateBook")
    public String toUpdatePaper(int id, Model model) {
        // 跳转到修改页面时需要显示该书籍的信息
        Books books = bookService.queryBookById(id);  // 查询该书籍信息，返回前端时显示在页面上
        model.addAttribute("aBook", books);
        return "updateBook";
    }

    // 修改书籍的请求
    @RequestMapping("/updateBook")
    public String updateBook(Books books) {
        System.out.println("updateBook=>" + books);
        // 在业务层修改
        bookService.updateBook(books);
        return "redirect:/book/allBook";  // 重定向，实现请求复用
    }


    // 删除书籍的请求
    @RequestMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id) {
        // 在业务层修改
        bookService.deleteBook(id);
        return "redirect:/book/allBook";  // 重定向，实现请求复用
    }

    // 查询书籍的请求
    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName, Model model) {
        // 在业务层修改
        List<Books> list = bookService.queryBookByName(queryBookName);
        if (list.size() == 0) {
            list = bookService.queryAllBook();
            model.addAttribute("error", "未查到");
        }
        model.addAttribute("list", list);
        System.out.println("queryBook=>" + list);

        return "allBook";
    }

}
