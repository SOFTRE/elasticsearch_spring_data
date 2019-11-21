package com.xxm;

import com.xxm.dao.ArticleDao;
import com.xxm.pojo.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Program: IntelliJ IDEA elasticsearch_spring_data
 * @Description: TODO
 * @Author: Mr Liu
 * @Creed: Talk is cheap,show me the code
 * @CreateDate: 2019-11-19 21:35:44 周二
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version: V1.0
 */
@ContextConfiguration("classpath:spring-elasticsearch.xml")
@RunWith(SpringRunner.class)
public class Elasticsearch {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Test
    public void createMapping(){
        //1.创建索引
        elasticsearchTemplate.createIndex(Article.class);
        //2.创建映射
        elasticsearchTemplate.putMapping(Article.class);
    }
    //创建文档
    @Test
    public void createDocument(){
        for (long i = 0; i < 100; i++) {
            Article article=new Article();
            article.setId(i);
            article.setTitle("测试标题"+i);
            article.setContent("我是测试的内容"+i);
            articleDao.save(article);
        }
    }
    //删除
    @Test
    public void deleteDocument(){
        articleDao.deleteById(1L);
    }
    //查询
    @Test
    public void selectAllDocument(){
        Iterable<Article> all = articleDao.findAll();
        for (Article article : all) {
            System.out.println(article.getContent());
        }
    }
    //分页
    @Test
    public void pageQuery(){
        /**
         * 参数一:指定分页开始的位置
         * 参数二:指定每页显示的行
         */
        PageRequest pageRequest=PageRequest.of(0,20);
        Page<Article> all = articleDao.findAll(pageRequest);
        System.out.println("总记录数："+all.getTotalElements());
        List<Article> content = all.getContent();//获取当前页的记录
        for (Article article : content) {
            System.out.println(article.getTitle());
        }
    }
    //  排序
    @Test
    public void pageQuerySort(){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=PageRequest.of(0,20,sort);
        Page<Article> all = articleDao.findAll(pageable);
        System.out.println("总记录数："+all.getTotalElements());
        List<Article> content = all.getContent();//获取当前页的记录
        for (Article article : content) {
            System.out.println(article.getTitle()+",id:"+article.getId());
        }
        List<Article> titles = articleDao.findByTitle("标题");
        for (Article title : titles) {
            System.out.println("根据条件查询："+title.getId());
        }
    }
}
