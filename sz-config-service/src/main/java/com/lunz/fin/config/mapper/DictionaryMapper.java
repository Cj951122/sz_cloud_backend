package com.lunz.fin.config.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lunz.fin.config.entity.domain.Dictionary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictionaryMapper extends BaseMapper<Dictionary> {

}