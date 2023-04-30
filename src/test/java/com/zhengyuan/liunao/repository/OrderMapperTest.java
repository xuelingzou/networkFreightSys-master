package com.zhengyuan.liunao.repository;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderMapperTest extends TestCase {

    @Autowired
    OrderMapper orderMapper;

    @Test
    public void testAddOrder() {
    }

    @Test
    public void testUpdateCoidNState() {
    }

    @Test
    public void testUpdateSendTNState() {
    }

    @Test
    public void testUpdateReceiveTNState() {
    }

    @Test
    public void testFindOrderByCeid() {
    }

    @Test
    public void testFindOrderByCoid() {
    }

    @Test
    public void testFindOrderWaitReceive() {
    }

    @Test
    public void testShowAllOrder() {
    }
}