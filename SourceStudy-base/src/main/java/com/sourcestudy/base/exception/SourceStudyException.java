package com.sourcestudy.base.exception;/**
 * @author Dantence
 * @description:
 * @date 2023/2/12
 */

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.base.exception
 * @className: SourceStudyException
 * @author: Dantence
 * @description: 自定义异常
 * @date: 2023/2/12 20:33
 * @version: 1.0
 */
public class SourceStudyException extends RuntimeException{
    private String errMessage;

    public SourceStudyException() {
        super();
    }

    public SourceStudyException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage(){
        return errMessage;
    }

    public static void cast(String errMessage) {
        throw new SourceStudyException(errMessage);
    }

    public static void cast(CommonError commonError) {
        throw new SourceStudyException(commonError.getErrMessage());
    }
}
