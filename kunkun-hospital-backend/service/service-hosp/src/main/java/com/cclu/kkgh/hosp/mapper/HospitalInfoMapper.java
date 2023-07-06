package com.cclu.kkgh.hosp.mapper;

import com.cclu.kkgh.model.entity.hosp.HospitalInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 21237
* @description 针对表【hospital_info(医院设置表)】的数据库操作Mapper
* @createDate 2023-06-28 16:36:21
* @Entity com.cclu.kkgh.hosp.HospitalInfo
*/
@Mapper
public interface HospitalInfoMapper extends BaseMapper<HospitalInfo> {

}




