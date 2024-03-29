package com.sourcestudy.media.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sourcestudy.media.service.MediaFilesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 媒资信息 前端控制器
 * </p>
 *
 * @author Dantence
 */
@Slf4j
@RestController
@RequestMapping("mediaFiles")
public class MediaFilesController {

    @Autowired
    private MediaFilesService  mediaFilesService;
}
