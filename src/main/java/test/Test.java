package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import vo.User;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test {
    public void testFindAll() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> list = sqlSession.selectList("vo.User.findall");

        for (User user: list){
            System.out.println("User's name is " + user.getName());
        }

        sqlSession.close();
    }

    public void deleteTest() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setName("李华");

        int result = sqlSession.delete("vo.User.deleteall",user);
        if(result == 1){
            System.out.println("User delete succeed!");
        }else{
            System.out.println("User delete failed!");
        }

        sqlSession.close();
    }

    public void addTest() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setName("袁术");
        user.setId(7);

        int result = sqlSession.insert("vo.User.insertall", user);
        if(result == 1){
            System.out.println("User insert succeed!");
        }else{
            System.out.println("User insert failed!");
        }

        sqlSession.close();
    }

    public void updateTest() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setName("白幸");
        user.setId(4);

        int result = sqlSession.update("vo.User.updateall", user);
        if (result == 1){
            System.out.println("User update succeed!");
        }else{
            System.out.println("User update failed!");
        }

        sqlSession.close();
    }

    public static void main(String[] args) throws IOException {
        Test test = new Test();
//        查找所有user的名字
        test.testFindAll();
//        删除名为“李华”的用户
        test.deleteTest();
//        添加uid为7，名为“袁术”的用户
        test.addTest();
//        更改uid为4的用户的姓名为“白幸”
        test.updateTest();
    }
}
