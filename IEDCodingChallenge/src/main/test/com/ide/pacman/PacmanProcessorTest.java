package com.ide.pacman;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PacmanProcessorTest {
	
	@InjectMocks
	PacmanProcessor mockPacman;
	
	@Test
	public void testPacmanValidMove(){
		List<String> instrList = new ArrayList<String>();
		instrList.add("PLACE 0,0,NORTH");
		instrList.add("MOVE");
		instrList.add("MOVE");
		instrList.add("LEFT");
		instrList.add("MOVE");
		Stream<String> stream = instrList.stream();
		mockPacman.processPacman(stream);
		assertEquals(mockPacman.getPosX(),0);
		assertEquals(mockPacman.getPosY(),2);
		assertEquals(mockPacman.getDirection(),"WEST");
	}
	@Test
	public void testPacmanInvalidMove(){
		List<String> instrList = new ArrayList<String>();
		instrList.add("PLACE 5,5,NORTH");
		instrList.add("MOVE");
		instrList.add("MOVE");
		Stream<String> stream = instrList.stream();
		mockPacman.processPacman(stream);
		assertEquals(mockPacman.getPosX(),5);
		assertEquals(mockPacman.getPosY(),5);
		assertEquals(mockPacman.getDirection(),"NORTH");
	}
	
}
