package com.sourcestudy.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sourcestudy.content.model.po.CourseTeacher;

import java.util.List;

/**
 * <p>
 * 课程-教师关系表 服务类
 * </p>
 *
 * @author Dantence
 * @since 2023-02-12
 */
public interface CourseTeacherService extends IService<CourseTeacher> {
    List<CourseTeacher> getTeacherByCourseId(Long courseId);
}
