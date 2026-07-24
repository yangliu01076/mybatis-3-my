package org.apache.ibatis;

import org.apache.ibatis.domain.blog.Author;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author duoyian
 * @date 2026/7/24
 */
public class DemoTest extends BaseDataTest{

    @Test
    public void test() throws SQLException, IOException {
        createBlogDataSource();
        final String resource = "org/apache/ibatis/builder/MapperConfig.xml";
        final Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        try (SqlSession session = sqlSessionFactory.openSession(TransactionIsolationLevel.SERIALIZABLE)) {
            List<Author> authors = session.selectList("org.apache.ibatis.domain.blog.mappers.AuthorMapper.selectAllAuthors");
            assertEquals(2, authors.size());
        }
    }
}
