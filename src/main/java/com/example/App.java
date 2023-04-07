package com.example;

public class App {
	public static void main(String[] args) throws Exception{
		String inputDir = "/Users/lulu/Documents/GitHub/VisionAI_demo/input";
		String outputDir = "/Users/lulu/Documents/GitHub/VisionAI_demo/output";

		VisionHelp help = new ExcelHelp();
		help.analysisPicture(inputDir, outputDir);
	}
}