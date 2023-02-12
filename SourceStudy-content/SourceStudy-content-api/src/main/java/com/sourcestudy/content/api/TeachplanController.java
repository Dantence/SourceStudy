package com.sourcestudy.content.api;/**
 * @author Dantence
 * @description:
 * @date 2023/2/12
 */

import com.sourcestudy.content.model.dto.SaveTeachplanDto;
import com.sourcestudy.content.model.dto.TeachplanDto;
import com.sourcestudy.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.content.api
 * @className: TeachplanController
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/12 22:19
 * @version: 1.0
 */
@Api(value = "课程计划编辑接口",tags = "课程计划编辑接口")
@RestController
public class TeachplanController {

    @Autowired
    TeachplanService teachplanService;

    @ApiOperation("查询课程计划树形结构")
    @ApiImplicitParam(value = "courseId",name = "课程基础Id值",required = true,dataType = "Long",paramType = "path")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
        return teachplanService.findTeachplayTree(courseId);
    }

    @ApiOperation("课程计划创建或修改")
    @PostMapping("/teachplan")
    public void saveTeachplan( @RequestBody SaveTeachplanDto teachplan){
        teachplanService.saveTeachplan(teachplan);
    }

    @ApiOperation("课程计划删除")
    @DeleteMapping("/teachplan/{teachPlanId}")
    public void saveTeachplan(@PathVariable Long teachPlanId){
        teachplanService.deleteTeachPlan(teachPlanId);
    }
}
