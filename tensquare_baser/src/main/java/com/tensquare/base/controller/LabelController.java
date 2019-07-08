package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusConde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: create by  Raphaelkxy
 * @version: v1.0
 * @description: com.tensquare.base.controller
 * @date:2019/6/17
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
          return new Result(true, StatusConde.OK,"查询成功",labelService.findAll());
    }

    //形参要和value保持一致,如果不一致，那么可以通过PathVariable指定
    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId){
        return new Result(true, StatusConde.OK,"查询成功",labelService.findById(labelId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){ //json转对象

        labelService.save(label);
        return new Result(true, StatusConde.OK,"添加成功");
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result update(@PathVariable("labelId") String labelId,@RequestBody Label label){ //json转对象
        label.setId(labelId);
     labelService.update(label);
        return new Result(true, StatusConde.OK,"更新成功");
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable("labelId") String labelId){ //json转对象

      labelService.deletebyId(labelId);
        return new Result(true, StatusConde.OK,"删除成功");
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){
        List<Label>list=labelService.findSearch(label);
        return new Result(true,StatusConde.OK,"查询成功",list);
    }

    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findSearch(@PathVariable("page")Integer page,@PathVariable("size")Integer size,@RequestBody Label label){
        Page<Label> pageData =labelService.pageQuery(label,page,size);
        return new Result(true,StatusConde.OK,"查询成功",new PageResult<Label>(pageData.getTotalElements(),pageData.getContent()));
    }


}
