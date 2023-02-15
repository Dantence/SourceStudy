package com.sourcestudy.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sourcestudy.content.model.dto.BindTeachplanMediaDto;
import com.sourcestudy.content.model.dto.SaveTeachplanDto;
import com.sourcestudy.content.model.dto.TeachplanDto;
import com.sourcestudy.content.model.po.Teachplan;
import com.sourcestudy.content.model.po.TeachplanMedia;

import java.util.List;

/**
 * <p>
 * 课程计划 服务类
 * </p>
 *
 * @author Dantence
 * @since 2023-02-12
 */
public interface TeachplanService extends IService<Teachplan> {
    List<TeachplanDto> findTeachplayTree(long courseId);
    void saveTeachplan(SaveTeachplanDto teachplanDto);

    void deleteTeachPlan(Long teachPlanId);

    TeachplanMedia associationMedia(BindTeachplanMediaDto bindTeachplanMediaDto);

    void delAassociationMedia(Long teachPlanId, String mediaId);

}
