import com.withered.pojo.Books;
import com.withered.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBean {
    @Test
    public void testSql1() {
        // 获取servlet配置文件的路径
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取BookServiceImp类的bean
        BookService bookServiceImp = context.getBean("BookServiceImp", BookService.class);
        // 调用sql语句，sql是否执行成功
        for (Books books : bookServiceImp.queryAllBook()) {
            System.out.println(books);
        }
    }

    @Test
    public void testSql2() {
        // 获取servlet配置文件的路径
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取BookServiceImp类的bean
        BookService bookServiceImp = context.getBean("BookServiceImp", BookService.class);
        // 调用sql语句，sql是否执行成功
        for (Books books : bookServiceImp.queryBookByName("MySql")) {
            System.out.println(books);
        }
    }
}
