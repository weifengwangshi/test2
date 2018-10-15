package com.basefrm;

import com.basefrm.entity.Studentmodel2;
import com.basefrm.util2.ReflectUtil;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelutilApplicationTests {

	@Test
	public void contextLoads() {

//	   Class<T>  gh = ReflectUtil.getSuperGenericType(Studentmodel2.class);
//		System.out.println( gh);
		try {
			Class clzz = Class.forName("com.basefrm.entity.Studentmodel2");
			System.out.println(clzz.getSimpleName());
			Long ll = 8l;
			 ll.hashCode();
			 //
			System.out.println();
			//Class<clzz.getSimpleName()>  ffdg; ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
