package com.sourcestudy.base.exception;/**
 * @author Dantence
 * @description:
 * @date 2023/2/12
 */

import java.io.Serializable;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.base.exception
 * @className: RestErrorResponse
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/12 20:42
 * @version: 1.0
 */
public class RestErrorResponse implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}