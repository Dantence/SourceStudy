package com.sourcestudy.content.service.impl;/**
 * @author Dantence
 * @description:
 * @date 2023/2/12
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sourcestudy.base.exception.CommonError;
import com.sourcestudy.base.exception.SourceStudyException;
import com.sourcestudy.base.model.PageParams;
import com.sourcestudy.base.model.PageResult;
import com.sourcestudy.content.mapper.CourseBaseMapper;
import com.sourcestudy.content.mapper.CourseCategoryMapper;
import com.sourcestudy.content.mapper.CourseMarketMapper;
import com.sourcestudy.content.model.dto.AddCourseDto;
import com.sourcestudy.content.model.dto.CourseBaseInfoDto;
import com.sourcestudy.content.model.dto.EditCourseDto;
import com.sourcestudy.content.model.dto.QueryCourseParamsDto;
import com.sourcestudy.content.model.po.CourseBase;
import com.sourcestudy.content.model.po.CourseCategory;
import com.sourcestudy.content.model.po.CourseMarket;
import com.sourcestudy.content.service.CourseBaseInfoService;
import com.sourcestudy.content.service.CourseMarketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @projectName: SourceStudy
 * @package: com.sourcestudy.content.service.impl
 * @className: CourseBaseInfoServiceImpl
 * @author: Dantence
 * @description: TODO
 * @date: 2023/2/12 0:08
 * @version: 1.0
 */
@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {


    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Autowired
    CourseMarketMapper courseMarketMapper;

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Autowired
    CourseMarketService courseMarketService;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {


        //构建查询条件对象
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //构建查询条件，根据课程名称查询
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()), CourseBase::getName, queryCourseParamsDto.getCourseName());
        //构建查询条件，根据课程审核状态查询
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, queryCourseParamsDto.getAuditStatus());
        //构建查询条件，根据课程发布状态查询
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()), CourseBase::getAuditStatus, queryCourseParamsDto.getPublishStatus());

        //分页对象
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        // 查询数据内容获得结果
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);
        // 获取数据列表
        List<CourseBase> list = pageResult.getRecords();
        // 获取数据总数
        long total = pageResult.getTotal();
        // 构建结果集
        return new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());


    }

    @Transactional
    @Override
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto dto) {
        //新增对象
        CourseBase courseBaseNew = new CourseBase();
        //将填写的课程信息赋值给新增对象
        BeanUtils.copyProperties(dto, courseBaseNew);
        //设置审核状态
        courseBaseNew.setAuditStatus("202002");
        //设置发布状态
        courseBaseNew.setStatus("203001");
        //机构id
        courseBaseNew.setCompanyId(companyId);
        //添加时间
        courseBaseNew.setCreateDate(LocalDateTime.now());
        //插入课程基本信息表
        int insert = courseBaseMapper.insert(courseBaseNew);
        Long courseId = courseBaseNew.getId();
        //课程营销信息
        CourseMarket courseMarketNew = new CourseMarket();
        BeanUtils.copyProperties(dto, courseMarketNew);
        courseMarketNew.setId(courseId);

        boolean insert1 = this.saveCourseMarket(courseMarketNew);
        if (insert <= 0 || insert1 == false) {
            SourceStudyException.cast(CommonError.UNKOWN_ERROR);
        }
        //添加成功
        //返回添加的课程信息
        return getCourseBaseInfo(courseId);

    }

    //根据课程id查询课程基本信息，包括基本信息和营销信息
    @Override
    public CourseBaseInfoDto getCourseBaseInfo(long courseId) {

        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);

        if (courseBase == null) {
            return null;
        }
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase, courseBaseInfoDto);
        if (courseMarket != null) {
            BeanUtils.copyProperties(courseMarket, courseBaseInfoDto);
        }

        //查询分类名称
        CourseCategory courseCategoryBySt = courseCategoryMapper.selectById(courseBase.getSt());
        courseBaseInfoDto.setStName(courseCategoryBySt.getName());
        CourseCategory courseCategoryByMt = courseCategoryMapper.selectById(courseBase.getMt());
        courseBaseInfoDto.setMtName(courseCategoryByMt.getName());

        return courseBaseInfoDto;

    }

    @Transactional
    @Override
    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto) {
        //业务规则校验，本机构只允许修改本机构的课程
        //课程id
        Long courseId = dto.getId();
        CourseBase courseBase_u = courseBaseMapper.selectById(courseId);
        if (courseBase_u == null) {
            SourceStudyException.cast("课程信息不存在");
        }
        if (!companyId.equals(courseBase_u.getCompanyId())) {
            SourceStudyException.cast("本机构只允许修改本机构的课程");
        }

        //封装数据
        //将请求参数拷贝到待修改对象中
        BeanUtils.copyProperties(dto, courseBase_u);
        courseBase_u.setChangeDate(LocalDateTime.now());
        //更新到数据库
        int i = courseBaseMapper.updateById(courseBase_u);

        //查询课程营销信息
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        if (courseMarket == null) {
            courseMarket = new CourseMarket();
        }
        BeanUtils.copyProperties(dto, courseMarket);
        this.saveCourseMarket(courseMarket);
        return getCourseBaseInfo(courseId);
    }


    // 抽取对营销的保存
    private boolean saveCourseMarket(CourseMarket courseMarket) {
        String charge =  courseMarket.getCharge();
        if (StringUtils.isBlank(charge)) {
            SourceStudyException.cast("收费规则没有选择");
        }
        if (charge.equals("201001")) {
            Float price = courseMarket.getPrice();
            if (price == null || price.floatValue() <= 0) {
                SourceStudyException.cast("课程设置了收费价格不能为空且必须大于0");
            }
        }
        return courseMarketService.saveOrUpdate(courseMarket);
    }
}
