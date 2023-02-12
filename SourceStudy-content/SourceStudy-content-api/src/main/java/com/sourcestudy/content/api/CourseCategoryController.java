package com.sourcestudy.content.api;/**
 * @author Dantence
 * @description:
 * @date 2023/2/12
 */

import com.sourcestudy.content.model.dto.CourseCategoryTreeDto;
import com.sourcestudy.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.content.api
 * @className: CourseCategoryController
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/12 16:11
 * @version: 1.0
 */
@Slf4j
@RestController
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService courseCategoryService;

    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNodes() {
        return courseCategoryService.queryCourseCategoryTreeNode();
    }
}
