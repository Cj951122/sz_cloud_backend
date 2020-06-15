package com.lunz.fin.config.conf.struct;

import com.lunz.fin.config.entity.DictionaryVo;
import com.lunz.fin.config.entity.domain.Dictionary;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author al
 * @date 2020/2/25 14:22
 * @description
 */
@Mapper(componentModel = "spring")
public interface DictionaryConverter {

    DictionaryVo doTodto(Dictionary dictionary);

    List<DictionaryVo> doTodtos(List<Dictionary> dictionarys);

}
