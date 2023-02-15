package com.sourcestudy.content.model.dto;/**
 * @author Dantence
 * @description:
 * @date 2023/2/15
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.content.model.dto
 * @className: BindTeachplanMediaDto
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/15 16:49
 * @version: 1.0
 */
@Data
@ApiModel(value="BindTeachplanMediaDto", description="教学计划-媒资绑定提交数据")
public class BindTeachplanMediaDto {

    @ApiModelProperty(value = "媒资文件id", required = true)
    private String mediaId;

    @ApiModelProperty(value = "媒资文件名称", required = true)
    private String fileName;

    @ApiModelProperty(value = "课程计划标识", required = true)
    private Long teachplanId;


}
