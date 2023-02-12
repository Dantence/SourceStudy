package com.sourcestudy.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sourcestudy.content.model.dto.CourseCategoryTreeDto;
import com.sourcestudy.content.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 服务类
 * </p>
 *
 * @author Dantence
 * @since 2023-02-12
 */
public interface CourseCategoryService extends IService<CourseCategory> {
    List<CourseCategoryTreeDto> queryCourseCategoryTreeNode();

}
