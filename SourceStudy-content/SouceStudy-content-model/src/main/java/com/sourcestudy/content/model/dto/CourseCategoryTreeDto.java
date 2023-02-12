package com.sourcestudy.content.model.dto;/**
 * @author Dantence
 * @description:
 * @date 2023/2/12
 */

import com.sourcestudy.content.model.po.CourseCategory;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.content.model.dto
 * @className: CourseCategoryTreeDto
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/12 16:15
 * @version: 1.0
 */
@Data
public class CourseCategoryTreeDto extends CourseCategory {
    List<CourseCategoryTreeDto> childrenTreeNodes;

    public static CourseCategoryTreeDto convertToCourseCategoryTreeDto(CourseCategory courseCategory) {
        CourseCategoryTreeDto courseCategoryTreeDto = new CourseCategoryTreeDto();
        BeanUtils.copyProperties(courseCategory, courseCategoryTreeDto);
        return courseCategoryTreeDto;
    }
}
