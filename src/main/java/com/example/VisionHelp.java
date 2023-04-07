package com.example;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class VisionHelp {
	
	public void analysisPicture(String inputDir, String outputDir) throws Exception{
		ImageAnnotatorClient vision = ImageAnnotatorClient.create();
		List<AnnotateImageRequest> requests = new ArrayList<AnnotateImageRequest>();
		
		File input = new File(inputDir);
		String[] inputFileNames = input.list();
		inputFileNames = removeItemFromArray(inputFileNames, ".DS_Store");

		for(String inputName:inputFileNames){
			String fileName = inputDir + "/" + inputName;
			
			Path path = Paths.get(fileName);
			byte[] data = Files.readAllBytes(path);
			ByteString imgBytes = ByteString.copyFrom(data);
			
			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
					.addFeatures(feat)
					.setImage(img)
					.build();
			requests.add(request);
		}
		
		List<List<String[]>> dataList = new ArrayList<List<String[]>>();
		BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
		List<AnnotateImageResponse> responses = response.getResponsesList();
		
		for(AnnotateImageResponse res : responses){
			if(res.hasError()){
				System.out.println(res.getError().getMessage());
				return;
			}
			
			List<String[]> datas = new ArrayList<String[]>();
			
			for(EntityAnnotation annotation : res.getLabelAnnotationsList()){
				String[] data = new String[2];
				data[0] = annotation.getDescription();
				data[1] = String.valueOf(annotation.getScore());
				
				datas.add(data);
			}
			dataList.add(datas);
		}
		writeData(outputDir, inputFileNames, dataList);
	}
	
	public abstract void writeData(String outDir, String[] inputFileNames, List<List<String[]>> dataList);

	public static String[] removeItemFromArray(String[] input, String item) {
		if (input == null) {
			return null;
		} else if (input.length <= 0) {
			return input;
		} else {
			String[] output = new String[input.length - 1];
			int count = 0;
			for (String i : input) {
				if (!i.equals(item)) {
					output[count++] = i;
				}
			}
			return output;
		}
	}
}
