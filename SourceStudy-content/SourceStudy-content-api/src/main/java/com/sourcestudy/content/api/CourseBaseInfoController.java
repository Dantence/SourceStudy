package com.sourcestudy.content.api;/**
 * @author Dantence
 * @description:
 * @date 2023/2/11
 */

import com.sourcestudy.base.model.PageParams;
import com.sourcestudy.base.model.PageResult;
import com.sourcestudy.content.model.dto.QueryCourseParamsDto;
import com.sourcestudy.content.model.po.CourseBase;
import com.sourcestudy.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.content.api
 * @className: CourseBaseInfoController
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/11 22:09
 * @version: 1.0
 */
@Api(value = "课程信息编辑接口",tags = "课程信息编辑接口")
@RestController
public class CourseBaseInfoController {

    @Autowired
    private CourseBaseInfoService courseBaseInfoService;

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody QueryCourseParamsDto queryCourseParams){

        return courseBaseInfoService.queryCourseBaseList(pageParams,queryCourseParams);

    }

}
