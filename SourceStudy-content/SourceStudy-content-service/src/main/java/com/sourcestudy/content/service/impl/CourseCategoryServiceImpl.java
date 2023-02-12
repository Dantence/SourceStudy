package com.sourcestudy.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sourcestudy.content.mapper.CourseCategoryMapper;
import com.sourcestudy.content.model.dto.CourseCategoryTreeDto;
import com.sourcestudy.content.model.po.CourseBase;
import com.sourcestudy.content.model.po.CourseCategory;
import com.sourcestudy.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author Dantence
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryCourseCategoryTreeNode() {
        List<CourseCategoryTreeDto> levelOneCategory = getNodesByParentId("1");
        for(CourseCategoryTreeDto courseCategory : levelOneCategory) {
            courseCategory.setChildrenTreeNodes(getNodesByParentId(courseCategory.getId()));
        }
        return levelOneCategory;
    }

    private List<CourseCategoryTreeDto> getNodesByParentId(String parentId) {
        LambdaQueryWrapper<CourseCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CourseCategory::getParentid, parentId);
        return courseCategoryMapper.selectList(queryWrapper).stream().map(CourseCategoryTreeDto::convertToCourseCategoryTreeDto).collect(Collectors.toList());
    }
}
