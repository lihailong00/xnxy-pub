package com.lee.xnxy.validate;

import com.lee.xnxy.validate.module.SchoolAuthValidateModule;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OfficeControllerValidator {
    @Resource
    private SchoolAuthValidateModule schoolAuthValidateModule;

    public void getCourseTable() {
        schoolAuthValidateModule.validate();
    }

    public void getScoreTable() {
        schoolAuthValidateModule.validate();
    }
}
