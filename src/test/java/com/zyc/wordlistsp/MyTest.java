package com.zyc.wordlistsp;

import com.zyc.wordlistsp.service.SearchWordServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.UUID;

@Slf4j
public class MyTest {
    @Test
    public void test1() {
        String uid = UUID.randomUUID().toString();
        log.info(uid.replace("-", ""));
        String s = new String("vc");
        WeakReference<String> sRef = new WeakReference<>(s);
        String s1 = sRef.get();
    }

    @Test
    public void test2() {

    }

}
