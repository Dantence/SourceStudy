package com.sourcestudy.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sourcestudy.base.exception.SourceStudyException;
import com.sourcestudy.content.mapper.TeachplanMapper;
import com.sourcestudy.content.mapper.TeachplanMediaMapper;
import com.sourcestudy.content.model.dto.BindTeachplanMediaDto;
import com.sourcestudy.content.model.dto.SaveTeachplanDto;
import com.sourcestudy.content.model.dto.TeachplanDto;
import com.sourcestudy.content.model.po.Teachplan;
import com.sourcestudy.content.model.po.TeachplanMedia;
import com.sourcestudy.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程计划 服务实现类
 * </p>
 *
 * @author Dantence
 */
@Slf4j
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements TeachplanService {

    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;
    @Autowired
    TeachplanMapper teachplanMapper;

    @Override
    public List<TeachplanDto> findTeachplayTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    @Transactional
    @Override
    public void saveTeachplan(SaveTeachplanDto teachplanDto) {

        //课程计划id
        Long id = teachplanDto.getId();
        //修改课程计划
        if (id != null) {
            Teachplan teachplan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(teachplanDto, teachplan);
            teachplanMapper.updateById(teachplan);
        } else {
            //取出同父同级别的课程计划数量
            int count = getTeachplanCount(teachplanDto.getCourseId(), teachplanDto.getParentid());
            Teachplan teachplanNew = new Teachplan();
            //设置排序号
            teachplanNew.setOrderby(count + 1);
            BeanUtils.copyProperties(teachplanDto, teachplanNew);

            teachplanMapper.insert(teachplanNew);

        }

    }

    @Override
    public void deleteTeachPlan(Long teachPlanId) {
        Teachplan teachplan = teachplanMapper.selectById(teachPlanId);
        if (teachplan == null) {
            SourceStudyException.cast("该教学计划不存在!");
        }
        LambdaUpdateWrapper<Teachplan> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Teachplan::getId, teachPlanId);
        updateWrapper.set(Teachplan::getStatus, 0);
        teachplanMapper.update(null, updateWrapper);
        if (teachplan.getGrade() == 1) {
            updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Teachplan::getParentid, teachPlanId);
            updateWrapper.set(Teachplan::getStatus, 0);
        }
    }

    /**
     * @param courseId 课程id
     * @param parentId 父课程计划id
     * @return int 最新排序号
     * @description 获取最新的排序号
     * @author Mr.M
     * @date 2022/9/9 13:43
     */
    private int getTeachplanCount(long courseId, long parentId) {
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Teachplan::getCourseId, courseId);
        queryWrapper.eq(Teachplan::getParentid, parentId);
        return teachplanMapper.selectCount(queryWrapper);
    }

    @Override
    public TeachplanMedia associationMedia(BindTeachplanMediaDto bindTeachplanMediaDto) {
        //教学计划id
        Long teachplanId = bindTeachplanMediaDto.getTeachplanId();
        Teachplan teachplan = teachplanMapper.selectById(teachplanId);
        if(teachplan==null){
            SourceStudyException.cast("教学计划不存在");
        }
        Integer grade = teachplan.getGrade();
        if(grade!=2){
            SourceStudyException.cast("只允许第二级教学计划绑定媒资文件");
        }
        //课程id
        Long courseId = teachplan.getCourseId();

        //先删除原因该教学计划绑定的媒资
        teachplanMediaMapper.delete(new LambdaQueryWrapper<TeachplanMedia>().eq(TeachplanMedia::getTeachplanId,teachplanId));

        //再添加教学计划与媒资的绑定关系
        TeachplanMedia teachplanMedia = new TeachplanMedia();
        teachplanMedia.setCourseId(courseId);
        teachplanMedia.setTeachplanId(teachplanId);
        teachplanMedia.setMediaFilename(bindTeachplanMediaDto.getFileName());
        teachplanMedia.setMediaId(bindTeachplanMediaDto.getMediaId());
        teachplanMedia.setCreateDate(LocalDateTime.now());
        teachplanMediaMapper.insert(teachplanMedia);
        return teachplanMedia;
    }

    @Override
    public void delAassociationMedia(Long teachPlanId, String mediaId) {
        teachplanMediaMapper.delete(new LambdaQueryWrapper<TeachplanMedia>().eq(TeachplanMedia::getTeachplanId,teachPlanId).eq(TeachplanMedia::getMediaId,mediaId));
    }

    //交换位置
    private void swapTeachplan(Teachplan left,Teachplan right){
        if(left==null || right==null){
            return ;
        }
        Integer orderby_left = left.getOrderby();
        Integer orderby_right = right.getOrderby();
        left.setOrderby(orderby_right);
        right.setOrderby(orderby_left);
        teachplanMapper.updateById(left);
        teachplanMapper.updateById(right);
        log.debug("课程计划交换位置，left:{},right:{}",left.getId(),right.getId());
    }

}
