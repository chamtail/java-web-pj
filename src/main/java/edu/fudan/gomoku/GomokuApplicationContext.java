package edu.fudan.gomoku;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
@MapperScan(value = "edu.fudan.gomoku.repository.mapper")
public class GomokuApplicationContext {

    public static void main(String[] args) {
        SpringApplication.run(GomokuApplicationContext.class, args);
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        HikariDataSource masterDataSource = new HikariDataSource();
        masterDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        // todo config these
        masterDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/gomoku?zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false&roundRobinLoadBalance=true&characterEncoding=utf-8&characterSetResults=utf-8&useLocalSessionState=true&jdbcCompliantTruncation=false&useUnbufferedInput=false&cacheServerConfiguration=true&cachePrepStmts=true&cacheCallableStmts=true&prepStmtCacheSize=250&prepStmtCacheSqlLimit=2048&connectTimeout=20000&socketTimeout=50000&allowMultiQueries=true");
        masterDataSource.setUsername("root");
        masterDataSource.setPassword("123456");
        masterDataSource.setIdleTimeout(25000);
        masterDataSource.setMinimumIdle(5);
        masterDataSource.setMaximumPoolSize(30);
        masterDataSource.setPoolName("db_pool_gomoku");
        return masterDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/*.xml"));
        sqlSessionFactoryBean.setConfigurationProperties(properties);
        return sqlSessionFactoryBean;
    }
}
