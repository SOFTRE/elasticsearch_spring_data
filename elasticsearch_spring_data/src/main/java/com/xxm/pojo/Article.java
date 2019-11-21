package com.xxm.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
/**
 * 1.创建索引
 * 2.创建类型
 * 3.创建文档 唯一的标识
 * 字段定义映射，是否索引，是否存储 是否分词，分词器，数据类型是
 * @Document(indexName = "blog004",type = "article")
 * document 指定映射关系的管理 修饰的类就是一个document
 * indexName 指定索引名称
 * type指定类型
 * @Field(index = true,store = false,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
 * field注解修饰pojo属性，映射到文档中的字段
 * index 指定是否索引，默认是true
 * store 指定是否存储，默认是false
 * analyzer 指定分词器，表示添加文档（动词）的时候使用的分词器 默认使用标准的分词器
 * searchAnalyzer 指定分词器，表示查询的时候使用分词器，一般不配置，默认使用同一个分词器
 * type 指定字段的数据类型
 */

/**
 * @Program: IntelliJ IDEA elasticsearch
 * @Description: TODO
 * @Author: Mr Liu
 * @Creed: Talk is cheap,show me the code
 * @CreateDate: 2019-11-19 16:08:46 周二
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version: V1.0
 */
@Document(indexName = "blog004",type = "article")
public class Article implements Serializable {
    @Id//文档的唯一标识
    private Long id;
    @Field(index = true,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String title;
    @Field(index = true,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.Text)
    private String content;

    public Article() {
    }

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
