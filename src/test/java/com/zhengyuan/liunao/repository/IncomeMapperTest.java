package com.zhengyuan.liunao.repository;

import cn.hutool.crypto.SecureUtil;
import com.zhengyuan.liunao.entity.Income;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IncomeMapperTest extends TestCase {

    @Autowired
    IncomeMapper incomeMapper;

    @Test
    public void testUpdateIncome() {
        // 成功更新income对应公司月收入测试用例
        int num = incomeMapper.updateIncome(202304,100,"123456");
        assertEquals(1,num);

        // 失败更新income对应公司月收入测试用例（因为原公司id不存在999）
        int num1 = incomeMapper.updateIncome(202304,100,"999");
        assertEquals(0,num1);
    }

    @Test
    public void testInsertIncome() {
        // 添加income月收入记录测试用例
        Income income = new Income("123456",202304,250);
        int num = incomeMapper.insertIncome(income);
        assertEquals(1,num);

        Income income1 = new Income("123456",202303,300);
        int num1 = incomeMapper.insertIncome(income1);
        assertEquals(1,num1);
    }

    @Test
    public void testFindByYearMonth() {
    }

    @Test
    public void testSelectMonthIncome() {
    }
}