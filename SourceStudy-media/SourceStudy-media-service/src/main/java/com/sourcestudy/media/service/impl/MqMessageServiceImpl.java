package com.sourcestudy.media.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sourcestudy.media.mapper.MqMessageMapper;
import com.sourcestudy.media.model.po.MqMessage;
import com.sourcestudy.media.service.MqMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dantence
 */
@Slf4j
@Service
public class MqMessageServiceImpl extends ServiceImpl<MqMessageMapper, MqMessage> implements MqMessageService {

}
