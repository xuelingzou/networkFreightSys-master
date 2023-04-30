package com.zhengyuan.liunao.repository;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IncomeMapperTest extends TestCase {

    @Autowired
    IncomeMapper incomeMapper;

    @Test
    public void testUpdateIncome() {
    }

    @Test
    public void testInsertIncome() {
    }

    @Test
    public void testFindByYearMonth() {
    }

    @Test
    public void testSelectMonthIncome() {
    }
}