package com.sourcestudy.media.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sourcestudy.media.model.po.MediaProcess;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Dantence
 * @since 2023-02-14
 */
public interface MediaProcessService {
    @Transactional
    void saveProcessFinishStatus(int status, String fileId, String url, String errorMsg);

    /**
     * @description 获取待处理任务
     * @param shardIndex 分片序号
     * @param shardTotal 分片总数
     * @param count 获取记录数
     * @return java.util.List<com.xuecheng.media.model.po.MediaProcess>
     * @author Mr.M
     * @date 2022/9/14 14:49
     */
    List<MediaProcess> getMediaProcessList(int shardIndex, int shardTotal, int count);

}
