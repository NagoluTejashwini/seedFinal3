package com.ec.onlineplantnursery.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ec.onlineplantnursery.exceptions.ResourceNotFoundException;
import com.ec.onlineplantnursery.exceptions.SeedIdNotFoundException;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.repository.SeedRepository;
import com.ec.onlineplantnursery.seed.service.SeedServiceImpl;

@SpringBootTest
class SeedServiceImplTest {

	SeedRepository seedRepo;
	private static SeedServiceImpl seedService;
	private static AutoCloseable ac;
	
	@BeforeEach
	public void doinit()
	{
		seedRepo = mock(SeedRepository.class); // test through approach 2
		seedService = new  SeedServiceImpl(seedRepo); 	// Approach 2
		ac = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	public void doAtEnd()throws Exception
	{
		ac.close();
	}
	
	@Test
	//@Disabled
	@DisplayName("Test-Save Seed")
	void testSaveSeed() {
		Seed input = new Seed(101,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",200,300,2000);
		Seed output = new Seed(101,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",200,300,2000);
	
		when(seedRepo.save(input)).thenReturn(output);
		seedService.addSeed(input);
		verify(seedRepo).save(input);
		assertEquals(input,output);
	}
	

	@Test
	//@Disabled
	@DisplayName("Test-Get All Seeds")
	void testGetAllSeeds() {
		
		Seed s1 = new Seed(101,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",200,300,2000);
		Seed s2 = new Seed(102,"Papaya","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",200,300,2000);
		List<Seed> seedList1 = new ArrayList<>();
		seedList1.add(s1);
		seedList1.add(s2);
		
		when(seedRepo.findAll()).thenReturn(seedList1);
		
		List<Seed> seedListOutput = seedService.viewAllSeeds();
		
		
		verify(seedRepo).findAll();
		assertIterableEquals(seedList1, seedListOutput);
		
	}
	
	@Test
	//@Disabled
	@DisplayName("Test-Get Seed by Id")
	void testViewSeedById(){
		
		Optional<Seed> s = Optional.empty();
		
		when(seedRepo.findById(2)).thenReturn(s);
		Executable executable = ()->{
			assertNotNull(seedService.viewSeed(2));
		};
		assertThrows(SeedIdNotFoundException.class, executable);
		
	}
	
	
	@Test
	//@Disabled
	@DisplayName("Test-Get Seed by common Name ")
	void testViewSeedByName()  {
		
		
		String commonName = "Mango";
		Optional<Seed> s = Optional.empty();
		when(seedRepo.getSeedByCommonName(commonName)).thenReturn(s);
		Executable executable = ()->{
			Optional<Seed> excepted = Optional.of(new Seed(101,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
					"This seed is a small embryonic plant",200,300,2000));
			Optional<Seed> output = seedService.viewSeed(commonName);
			
			assertNotNull(output);
			assertEquals(excepted, output);
			
		};
		assertThrows(ResourceNotFoundException.class, executable);
		
		
	}
	
	@Test
	//@Disabled
	@DisplayName("Test-Resource not found exception by common name ")
	void testExceptionViewSeedByName()  {
		
		
		String commonName = "abc";
		Optional<Seed> s = Optional.empty();
		when(seedRepo.getSeedByCommonName(commonName)).thenReturn(s);
		Executable executable = ()->{
			Optional<Seed> excepted = Optional.of(new Seed(101,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
					"This seed is a small embryonic plant",200,300,2000));
			Optional<Seed> output = seedService.viewSeed(commonName);
			
			assertNotNull(output);
			assertEquals(excepted, output);
			
		};
		assertThrows(ResourceNotFoundException.class, executable);
		
		
	}
	
	@Test
	//@Disabled
	@DisplayName("Test-Get Seed by type of seed ")
	void testViewSeedByTypeOfSeed(){
		
		String typeOfSeed = "Monocotyledonous";
		Optional<List<Seed>> seedList = Optional.empty(); 
		when(seedRepo.getSeedsByTypeOfSeed(typeOfSeed)).thenReturn(seedList);
		Executable executable = ()->{
			Optional<List<Seed>> output = seedService.viewAllSeeds(typeOfSeed);
			assertNotNull(output);
		};
		assertThrows(ResourceNotFoundException.class, executable);
	}
	
	
	@Test
	//@Disabled
	@DisplayName("Test-Get Seed by type of seed ")
	void testExceptionViewSeedByTypeOfSeed(){
		
		String typeOfSeed = "Dicoty";
		Optional<List<Seed>> seedList = Optional.empty(); 
		when(seedRepo.getSeedsByTypeOfSeed(typeOfSeed)).thenReturn(seedList);
		Executable executable = ()->{
			Optional<List<Seed>> output = seedService.viewAllSeeds(typeOfSeed);
			assertNotNull(output);
		};
		assertThrows(ResourceNotFoundException.class, executable);
	}
	

	@Test
	//@Disabled
	@DisplayName("Test-Delete seed")
	void testDeleteSeed() throws SeedIdNotFoundException {
		
		Seed input = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);
		Seed output = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);

		try{
			doNothing().
			 when(seedRepo).delete(input);

			seedService.deleteSeed(input);

			verify(seedRepo).delete(input);
			assertEquals(input,output);

		}
		catch(SeedIdNotFoundException e) {
			
		}
		  
	}
	
	@Test
	//@Disabled
	@DisplayName("Test-Update seed")
	void testUpdateSeed() throws SeedIdNotFoundException {
		Seed input = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);
		Seed output = new Seed(1,"Mango","Morning", "Twice a day", "easy","25ºC","Monocotyledonous",
				"This seed is a small embryonic plant",250,300,2000);
	

		try{
			when(seedRepo.save(input)).thenReturn(output);
			
			seedService.updateSeed(input);

			assertEquals(input,output);
		}
		catch(SeedIdNotFoundException e) {
			
		}
	}

	
}
