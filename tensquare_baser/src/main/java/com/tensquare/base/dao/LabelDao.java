package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: create by  Raphaelkxy
 * @version: v1.0
 * @description: com.tensquare.base.dao
 * @date:2019/6/17
 */
public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {
    //第一个参数是类型名，第二个是Id的类型
    //复杂查询
}
