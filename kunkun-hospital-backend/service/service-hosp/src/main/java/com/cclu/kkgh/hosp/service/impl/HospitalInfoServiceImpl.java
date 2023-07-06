package com.cclu.kkgh.hosp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cclu.kkgh.model.entity.hosp.HospitalInfo;
import com.cclu.kkgh.hosp.service.HospitalInfoService;
import com.cclu.kkgh.hosp.mapper.HospitalInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 21237
* @description 针对表【hospital_info(医院设置表)】的数据库操作Service实现
* @createDate 2023-06-28 16:36:21
*/
@Service
public class HospitalInfoServiceImpl extends ServiceImpl<HospitalInfoMapper, HospitalInfo>
    implements HospitalInfoService{

}




