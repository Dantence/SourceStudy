package com.sourcestudy.content.api;/**
 * @author Dantence
 * @description:
 * @date 2023/2/11
 */

import com.sourcestudy.base.model.PageParams;
import com.sourcestudy.base.model.PageResult;
import com.sourcestudy.content.model.dto.AddCourseDto;
import com.sourcestudy.content.model.dto.CourseBaseInfoDto;
import com.sourcestudy.content.model.dto.EditCourseDto;
import com.sourcestudy.content.model.dto.QueryCourseParamsDto;
import com.sourcestudy.content.model.po.CourseBase;
import com.sourcestudy.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.content.api
 * @className: CourseBaseInfoController
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/11 22:09
 * @version: 1.0
 */
@Api(value = "课程信息编辑接口", tags = "课程信息编辑接口")
@RestController
public class CourseBaseInfoController {

    @Autowired
    private CourseBaseInfoService courseBaseInfoService;

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody QueryCourseParamsDto queryCourseParams) {
        return courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParams);

    }

    @ApiOperation("新增课程基础信息")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated AddCourseDto addCourseDto) {
        return courseBaseInfoService.createCourseBase(22L, addCourseDto);
    }

    @ApiOperation("根据课程id查询课程基础信息")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseBaseById(@PathVariable Long courseId) {
        return courseBaseInfoService.getCourseBaseInfo(courseId);
    }

    @ApiOperation("修改课程基础信息")
    @PutMapping("/course")
    public CourseBaseInfoDto modifyCourseBase(@RequestBody @Validated EditCourseDto editCourseDto) {
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 1232141425L;
        return courseBaseInfoService.updateCourseBase(companyId, editCourseDto);
    }

}
