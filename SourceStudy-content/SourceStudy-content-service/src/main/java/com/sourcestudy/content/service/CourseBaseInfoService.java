package com.sourcestudy.content.service;

import com.sourcestudy.base.model.PageParams;
import com.sourcestudy.base.model.PageResult;
import com.sourcestudy.content.model.dto.AddCourseDto;
import com.sourcestudy.content.model.dto.CourseBaseInfoDto;
import com.sourcestudy.content.model.dto.EditCourseDto;
import com.sourcestudy.content.model.dto.QueryCourseParamsDto;
import com.sourcestudy.content.model.po.CourseBase;


/**
 * @description 课程基本信息管理业务接口
 * @author Dantence
 * @date 2023/2/12 23:42
 * @version 1.0
 */
public interface CourseBaseInfoService  {


    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);
    CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);
    CourseBaseInfoDto getCourseBaseInfo(long courseId);
    CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto);
}