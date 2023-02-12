package com.sourcestudy.content.api;/**
 * @author Dantence
 * @description:
 * @date 2023/2/13
 */

import com.sourcestudy.content.model.dto.CourseBaseInfoDto;
import com.sourcestudy.content.model.po.CourseTeacher;
import com.sourcestudy.content.service.CourseTeacherService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.content.api
 * @className: CourseTeacherController
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/13 0:35
 * @version: 1.0
 */
@Slf4j
@RestController
public class CourseTeacherController {
    @Autowired
    CourseTeacherService courseTeacherService;

    @ApiOperation("读取某课程的教师列表")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher> getTeacherByCourseId(@PathVariable Long courseId) {
        return courseTeacherService.getTeacherByCourseId(courseId);
    }

    @ApiOperation("保存教师")
    @PostMapping("/courseTeacher")
    public CourseTeacher getTeacherByCourseId(@RequestBody CourseTeacher courseTeacher) {
        courseTeacherService.saveOrUpdate(courseTeacher);
        return courseTeacherService.getById(courseTeacher.getId());
    }
}
