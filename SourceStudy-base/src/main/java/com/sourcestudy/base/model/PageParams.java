package com.sourcestudy.base.model;/**
 * @author Dantence
 * @description:
 * @date 2023/2/11
 */

import lombok.Data;
import lombok.ToString;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.base.model
 * @className: PageParams
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/11 21:48
 * @version: 1.0
 */
@Data
@ToString
public class PageParams {
    // 当前页码默认值
    public static final long DEAFULT_PAGE_CURRENT = 1L;

    // 每页记录数默认值
    public static final long DEFAULT_PAGE_SIZE = 10L;

    // 当前页码
    private Long pageNo = DEAFULT_PAGE_CURRENT;

    // 每页记录数
    private Long pageSize = DEFAULT_PAGE_SIZE;

    public PageParams() {

    }

    public PageParams(long pageNo, long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

}
