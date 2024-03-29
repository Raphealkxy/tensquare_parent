package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: create by  Raphaelkxy
 * @version: v1.0
 * @description: com.tensquare.base.service
 * @date:2019/6/17
 */
//现在不需要写接口
@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired //之前已经加入到bean容器中
    private IdWorker idWorker;
    public List<Label>findAll(){

        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deletebyId(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {

       return labelDao.findAll(new Specification<Label>() {
           /**
            *
            * @param root 根对象，也就是要把条件封装到哪个对象中，where类名label.getid
            * @param criteriaQuery 封装的都是查询关键字，比如group by order by等 基本用不着
            * @param criteriaBuilder 用来封装条件对象 主要用到root和cb，如果直接返回null 表示不需要任何条件
            * @return
            */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               //new一个list集合，来存放所有条件
                List<Predicate>predicates = new ArrayList<>();
                if(label.getLabelname()!=null&&!"".equals(label.getLabelname())){
                    System.out.println("1");
                  Predicate predicate= criteriaBuilder.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");//相当于where labelname like %labelname%
                 // return predicate;
                   predicates.add(predicate);
               }

                if(label.getState()!=null&&!"".equals(label.getState())){
                    Predicate predicate1= criteriaBuilder.equal(root.get("state").as(String.class),label.getState());//相当于where labelname like %labelname%
                    // return predicate;
                    predicates.add(predicate1);
                }
                //new 一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[predicates.size()];
                //把list直接转成数组
                parr=predicates.toArray(parr);
                return criteriaBuilder.and(parr);
               // where labelname like "" and state="
            }
        });
    }


    public Page<Label> pageQuery(Label label, Integer page, Integer size) {

        //封装一个分页对象
        Pageable pageable= PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //new一个list集合，来存放所有条件
                List<Predicate>predicates = new ArrayList<>();
                if(label.getLabelname()!=null&&!"".equals(label.getLabelname())){
                    System.out.println("1");
                    Predicate predicate= criteriaBuilder.like(root.get("labelname").as(String.class),"%"+label.getLabelname()+"%");//相当于where labelname like %labelname%
                    // return predicate;
                    predicates.add(predicate);
                }

                if(label.getState()!=null&&!"".equals(label.getState())){
                    Predicate predicate1= criteriaBuilder.equal(root.get("state").as(String.class),label.getState());//相当于where labelname like %labelname%
                    // return predicate;
                    predicates.add(predicate1);
                }
                //new 一个数组作为最终返回值的条件
                Predicate[] parr = new Predicate[predicates.size()];
                //把list直接转成数组
                parr=predicates.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        }, pageable);
    }
}
