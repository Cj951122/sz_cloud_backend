package com.lunz.fin.config.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
  * @author al
  * @date 2020/2/14 15:48
  * @description 这是一个测试DEMO
  */
@ApiModel(value="测试类-domain")
@Data
public class LessonVo implements Serializable {
    /**
     * 课程名称
     */
    @ApiModelProperty(value="课程名称")
    private String name;

    /**
     * 分数
     */
    @ApiModelProperty(value="分数")
    private BigDecimal credit;

    /**
     * 授课老师
     */
    @ApiModelProperty(value="授课老师")
    private String teacher;

    @ApiModelProperty(value="课程时间")
    private String lessonDate;

}