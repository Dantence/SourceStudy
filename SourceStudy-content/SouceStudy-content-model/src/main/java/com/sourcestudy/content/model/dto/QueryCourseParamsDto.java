package com.sourcestudy.content.model.dto;/**
 * @author Dantence
 * @description:
 * @date 2023/2/11
 */

import lombok.Data;
import lombok.ToString;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.model.dto
 * @className: QueryCourseParamsDto
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/11 21:52
 * @version: 1.0
 */
@Data
@ToString
public class QueryCourseParamsDto {
    // 审核状态
    private String auditStatus;

    // 课程名称
    private String courseName;

    // 发布状态
    private String publishStatus;


}
