package com.sourcestudy.media.service;

import com.sourcestudy.base.model.PageParams;
import com.sourcestudy.base.model.PageResult;
import com.sourcestudy.base.model.RestResponse;
import com.sourcestudy.media.model.dto.QueryMediaParamsDto;
import com.sourcestudy.media.model.dto.UploadFileParamsDto;
import com.sourcestudy.media.model.dto.UploadFileResultDto;
import com.sourcestudy.media.model.po.MediaFiles;

/**
 * @author Mr.M
 * @version 1.0
 * @description 媒资文件管理业务类
 * @date 2022/9/10 8:55
 */
public interface MediaFileService {

    /**
     * @param pageParams          分页参数
     * @param queryMediaParamsDto 查询条件
     * @return com.xuecheng.base.model.PageResult<com.xuecheng.media.model.po.MediaFiles>
     * @description 媒资文件查询方法
     * @author Mr.M
     * @date 2022/9/10 8:57
     */
    PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto);

    UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, byte[] bytes, String folder, String objectName);

    MediaFiles addMediaFilesToDb(Long companyId, String fileId, UploadFileParamsDto uploadFileParamsDto, String bucket_files, String objectName);
    RestResponse<Boolean> checkFile(String fileMd5);
    RestResponse<Boolean> checkChunk(String fileMd5, int chunkIndex);
    RestResponse uploadChunk(String fileMd5,int chunk,byte[] bytes);
    RestResponse mergechunks(Long companyId,String fileMd5,int chunkTotal,UploadFileParamsDto uploadFileParamsDto);
    MediaFiles getFileById(String id);

}
