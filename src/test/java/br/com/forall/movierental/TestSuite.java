package br.com.forall.movierental;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.forall.movierental.serviceTest.DirectorServiceTest;
import br.com.forall.movierental.serviceTest.LoginServiceTest;
import br.com.forall.movierental.serviceTest.MovieCopyServiceTest;
import br.com.forall.movierental.serviceTest.MovieServiceTest;
import br.com.forall.movierental.serviceTest.RentalServiceTest;
import br.com.forall.movierental.serviceTest.UserServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	UserServiceTest.class, 
	LoginServiceTest.class, 
	DirectorServiceTest.class,
	MovieServiceTest.class,
	MovieCopyServiceTest.class,
	RentalServiceTest.class})
public class TestSuite {


}
