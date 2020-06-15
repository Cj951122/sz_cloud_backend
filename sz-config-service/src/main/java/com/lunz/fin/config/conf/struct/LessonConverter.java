package com.lunz.fin.config.conf.struct;


import com.lunz.fin.config.entity.domain.Lesson;
import com.lunz.fin.config.entity.vo.LessonVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author al
 * @date 2020/1/21 9:43
 * @description lesson-map-struct
 */
@Mapper(componentModel = "spring")
public interface LessonConverter {

    /**
     * person DO->DTO
     * @param lesson domain
     * @return DTO
     */
    @Mappings({
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "credit",target = "credit"),
            @Mapping(source = "teacher",target = "teacher"),
            @Mapping(source = "lessonDate",target = "lessonDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    })
    LessonVo lessonDoToDto(Lesson lesson);

    /**
     * 列表转换
     */
    List<LessonVo> personDoToDtos(List<Lesson> lessons);

}
