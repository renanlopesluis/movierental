package br.com.forall.movierental.serviceTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.forall.movierental.dataprovider.MovieCopyDataProvider;
import br.com.forall.movierental.entity.MovieCopy;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MovieCopyServiceTest {
		
	@Autowired
	private MovieCopyDataProvider dataProvider;
			
	@Test
	public void shouldInsertAMovieCopy() throws Exception{
		MovieCopy movieCopy = dataProvider.build();
		
		Assert.assertNotNull(movieCopy.getId());
	}

}
