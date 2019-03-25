package com.ide.pacman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Core {

	public static void main(String[] args){
		String filename = System.getProperty("user.dir")+"/src/Files/inputFile.txt";
		try{
			Stream<String> stream = Files.lines(Paths.get(filename));
			PacmanProcessor pacmanObj = new PacmanProcessor();
			pacmanObj.processPacman(stream);
		} catch(IOException ioEx){
			System.out.println("IOException occured while reading file"+filename);
		}
	}
}
