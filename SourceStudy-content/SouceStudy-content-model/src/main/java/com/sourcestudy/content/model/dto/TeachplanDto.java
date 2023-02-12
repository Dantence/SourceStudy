package com.sourcestudy.content.model.dto;/**
 * @author Dantence
 * @description:
 * @date 2023/2/12
 */

import com.sourcestudy.content.model.po.Teachplan;
import com.sourcestudy.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.content.model.dto
 * @className: TeachplanDto
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/12 22:17
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class TeachplanDto extends Teachplan {

    //课程计划关联的媒资信息
    TeachplanMedia teachplanMedia;

    //子结点
    List<TeachplanDto> teachPlanTreeNodes;

}