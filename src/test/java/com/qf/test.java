package com.qf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;


/**
 * Created by 长风 on 2019/11/23.
 */
@RunWith(SpringRunner.class)
public class test {
    @Test
    public void test(){
        /*Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        System.out.println(simpleDateFormat.format(date));*/
        Date date = new Date(new java.util.Date().getTime());
        System.out.println(date);
    }
}
