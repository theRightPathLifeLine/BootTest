package com.line.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.line.dto.CardDateStatisticsDto;
import com.line.dto.CardPointStatisticsDto;
import com.line.entity.CardResult;

/**
 * Created by admin on 2019/1/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CardResultRepositoryTests {

    @Autowired
    private CardResultRepository cardResultRepository;

    @Test
    public void test() throws Exception {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//       for (int i = 0; i < 100; i++) {
//    	   CardResult cardResult = new CardResult();
//    	   Date date = new Date();
//    	   date.setDate(19);
//	   	   String name = sdf.format(date) + ".jpg";
//	   	   cardResult.setImageName(name);
//	   	   cardResult.setDate(date);
//	   	   cardResultRepository.save(cardResult);
//       }
//       for (int i = 0; i < 100; i++) {
//    	   CardResult cardResult = new CardResult();
//    	   Date date = new Date();
//    	   date.setDate(20);
//	   	   String name = sdf.format(date) + ".jpg";
//	   	   cardResult.setImageName(name);
//	   	   cardResult.setDate(date);
//	   	   cardResultRepository.save(cardResult);
//       }
//       for (int i = 0; i < 100; i++) {
//    	   CardResult cardResult = new CardResult();
//    	   Date date = new Date();
//    	   date.setDate(21);
//	   	   String name = sdf.format(date) + ".jpg";
//	   	   cardResult.setImageName(name);
//	   	   cardResult.setDate(date);
//	   	   cardResultRepository.save(cardResult);
//       }
    	List<Object[]> list = cardResultRepository.findbyDay();
    	for (Object[] objects : list) {
			CardDateStatisticsDto dto = new CardDateStatisticsDto((String)objects[0], ((BigInteger)objects[1]).intValue(), ((BigDecimal)objects[2]).intValue());
			System.out.println(dto);
		}
    	List<Object[]> list1 = cardResultRepository.findByPoint();
    	for (Object[] objects : list1) {
			CardPointStatisticsDto dto = new CardPointStatisticsDto((int)objects[0], 
					 ((BigInteger)objects[1]).intValue(), ((BigDecimal)objects[2]).intValue(), 
					 ((BigDecimal)objects[3]).intValue(), ((BigDecimal)objects[4]).intValue(),
					 ((BigDecimal)objects[5]).intValue(), ((BigDecimal)objects[6]).intValue());
			System.out.println(dto);
		}
    	System.out.println(list1);
    	
    }
    
    public void test1(){
    	
    }

}