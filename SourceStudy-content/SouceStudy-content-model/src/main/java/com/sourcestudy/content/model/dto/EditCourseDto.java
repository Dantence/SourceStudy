package com.sourcestudy.content.model.dto;/**
 * @author Dantence
 * @description:
 * @date 2023/2/12
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.content.model.dto
 * @className: EditCourseDto
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/12 21:21
 * @version: 1.0
 */
@Data
@ApiModel(value="EditCourseDto", description="修改课程基本信息")
public class EditCourseDto extends AddCourseDto {

    @ApiModelProperty(value = "课程名称", required = true)
    private Long id;

}