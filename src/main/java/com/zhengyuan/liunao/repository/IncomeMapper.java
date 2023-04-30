package com.zhengyuan.liunao.repository;

import java.util.List;

import com.zhengyuan.liunao.entity.Income;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IncomeMapper {
	int updateIncome(int yearMonth, double income, String companyID);
	int insertIncome(Income income);
	List<Income> findByYearMonth(String companyID, int yearMonth);
	List<Income> selectMonthIncome(String companyID, int year);

}
