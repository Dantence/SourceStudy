package com.sourcestudy.media.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sourcestudy.media.mapper.MediaFilesMapper;
import com.sourcestudy.media.mapper.MediaProcessHistoryMapper;
import com.sourcestudy.media.mapper.MediaProcessMapper;
import com.sourcestudy.media.model.po.MediaFiles;
import com.sourcestudy.media.model.po.MediaProcess;
import com.sourcestudy.media.model.po.MediaProcessHistory;
import com.sourcestudy.media.service.MediaProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dantence
 */
@Slf4j
@Service
public class MediaProcessServiceImpl implements MediaProcessService {

    @Autowired
    MediaFilesMapper mediaFilesMapper;

    @Autowired
    MediaProcessMapper mediaProcessMapper;

    @Autowired
    MediaProcessHistoryMapper mediaProcessHistoryMapper;


    @Transactional
    public void saveProcessFinishStatus(int status, String fileId, String url, String errorMsg) {
        MediaProcess mediaProcess = mediaProcessMapper.selectOne(new LambdaQueryWrapper<MediaProcess>().eq(MediaProcess::getFileId, fileId));
        if (mediaProcess == null) {
            return;
        }
        //处理失败
        if (status == 3) {
            mediaProcess.setStatus("3");
            mediaProcess.setErrormsg(errorMsg);
            mediaProcessMapper.updateById(mediaProcess);
            return;
        }
        MediaFiles mediaFiles = mediaFilesMapper.selectById(fileId);
        if (mediaFiles != null) {
            mediaFiles.setUrl(url);
            mediaFilesMapper.updateById(mediaFiles);
        }

        mediaProcess.setUrl(url);
        mediaProcess.setStatus("2");
        mediaProcess.setFinishDate(LocalDateTime.now());
        mediaProcessMapper.updateById(mediaProcess);
        //添加到历史记录
        MediaProcessHistory mediaProcessHistory = new MediaProcessHistory();
        BeanUtils.copyProperties(mediaProcess, mediaProcessHistory);
        mediaProcessHistoryMapper.insert(mediaProcessHistory);
        //删除mediaProcess
        mediaProcessMapper.deleteById(mediaProcess.getId());

    }

    @Override
    public List<MediaProcess> getMediaProcessList(int shardIndex, int shardTotal, int count) {
        List<MediaProcess> mediaProcesses = mediaProcessMapper.selectListByShardIndex(shardTotal, shardIndex, count);
        return mediaProcesses;
    }
}