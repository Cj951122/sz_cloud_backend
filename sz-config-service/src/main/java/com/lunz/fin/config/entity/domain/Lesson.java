package com.lunz.fin.config.entity.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
  * @author al
  * @date 2020/2/14 15:48
  * @description  这是一个测试DEMO
  */
@ApiModel(value="测试类-domain")
@Data
@TableName(value = "lesson")
public class Lesson implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="主键ID")
    private String id;

    /**
     * 课程名称
     */
    @TableField(value = "name")
    @ApiModelProperty(value="课程名称")
    private String name;

    /**
     * 分数
     */
    @TableField(value = "credit")
    @ApiModelProperty(value="分数")
    private BigDecimal credit;

    /**
     * 授课老师
     */
    @TableField(value = "teacher")
    @ApiModelProperty(value="授课老师")
    private String teacher;

    @TableField(exist = false)
    private Date lessonDate;

    private static final long serialVersionUID = 1L;
}