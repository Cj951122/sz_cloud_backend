package com.lunz.fin.config.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author haha
 * @desc 节点提交操作项配置
 */
@Mapper
public interface WorkflowConfigMapper extends BaseMapper<String> {

    List<String> getUploadOprConfigList(String param);

}