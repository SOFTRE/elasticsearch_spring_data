package com.xxm.dao;

import com.xxm.pojo.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


/**
 * @Program: IntelliJ IDEA elasticsearch_spring_data
 * @Description: TODO
 * @Author: Mr Liu
 * @Creed: Talk is cheap,show me the code
 * @CreateDate: 2019-11-19 20:20:20 周二
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version: V1.0
 */

public interface ArticleDao extends ElasticsearchRepository<Article,Long> {
    List<Article> findByTitle(String title);
}
