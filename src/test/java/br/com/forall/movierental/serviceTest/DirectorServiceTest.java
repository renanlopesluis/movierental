package br.com.forall.movierental.serviceTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.forall.movierental.dataprovider.DirectorDataProvider;
import br.com.forall.movierental.entity.Director;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DirectorServiceTest {
	
	@Autowired
	private DirectorDataProvider dataProvider;
				
	@Test
	public void shouldInsertADirector() throws Exception{
		Director director = dataProvider.build();
			
		Assert.assertNotNull(director.getId());
		
	}
}
