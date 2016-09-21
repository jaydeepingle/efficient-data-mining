import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
//import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import org.apache.commons.lang.ArrayUtils;

/**
 * Hello world!
 *
 */
public class App {
	/*
	 * This function is being used to get the maximum element from the matrix
	 * row.
	 */
	public static double getMax(double[] inputArray) {
		double maxValue = inputArray[0];
		for (int i = 1; i < inputArray.length; i++) {
			if (inputArray[i] > maxValue) {
				maxValue = inputArray[i];
			}
		}
		return maxValue;
	}

	/*
	 * This function is used to mine the ZipCode which replaces last two
	 * characters of the zipCode with the "*"
	 */
	public static String replaceLastTwo(String s) {
		int length = s.length();
		if (length < 2)
			return "Error: The provided string is not greater than two characters long.";
		return s.substring(0, length - 2) + "**";
	}

	/*
	 * This function is being to replace the ".0" with the spaces
	 */
	public static String replaceLastTwoFromAge(String s) {
		int length = s.length();
		if (length < 2)
			return "Error: The provided string is not greater than two characters long.";
		return s.substring(0, length - 2) + "";
	}

	/*
	 * Main Function
	 */
	public static void main(String[] args) throws Exception {
		// Opening Fresh.xls and reading data from it to mine
		String excelFilePath = "Fresh.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		// List to add the records read from the Excel Sheet
		List<Preserve> preserveDataList = new ArrayList<Preserve>();
		int number = 0;
		while (iterator.hasNext()) {
			number++;
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			int count = 0;
			Preserve currentPreserve = new Preserve();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				count++;
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					if (count == 1) {
						// Adding Name Value
						currentPreserve.setName(cell.getStringCellValue());
					}
					if (count == 3) {
						// Adding Gender Value
						currentPreserve.setGender(cell.getStringCellValue());
					}
					if (count == 5) {
						// Adding Disease Value
						currentPreserve.setDisease(cell.getStringCellValue());
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (count == 2) {
						// Adding Age Value
						currentPreserve.setAge(cell.getNumericCellValue());
					}
					if (count == 4) {
						// Adding Zip Code Value
						currentPreserve.setZipCode(cell.getNumericCellValue());
					}
					break;
				}
			}
			// Adding record to the preservedDataList
			preserveDataList.add(currentPreserve);
		}

		HashMap<String, Integer> disease = new HashMap<String, Integer>();
		// Disease Array
		String[] diseases = { "Cholera", "Smallpox", "Yellow Fever", "Tuberculosis", "Influenza", "Lung Cancer",
				"Diarrhea", "Perinatal Complications", "Whooping Cough", "Ebola", "Avian Influenza (Bird Flu)",
				"Tetanus", "Chronic Obstructive Pulmonary Disease", "Ischemic Heart Disease", "Meningitis",
				"Influenza A-H1N1 (Swine Flu)", "Syphilis", "Lower Respiratory Infections", "Cerebrovascular Disease",
				"Bubonic Plague", "SARS", "Leprosy", "Measles", "HIV", "Malaria" };
		int size = diseases.length;
		// Putting disease name along with the priority
		for (int i = 0; i < size; i++) {
			disease.put(diseases[i], new Integer(i + 1));
		}

		// Variable to generate number randomly
		Random r = new Random();

		// Matrices [n][n]
		double[][] ageMatrix = new double[number][number];
		double[][] genderMatrix = new double[number][number];
		double[][] zipCodeMatrix = new double[number][number];

		// Modified Matrices [n][5]
		double[][] modifiedAgeMatrix = new double[number][5];
		double[][] modifiedGenderMatrix = new double[number][5];
		double[][] modifiedZipCodeMatrix = new double[number][5];

		System.out.println("\nGenerated Matrix:\n");
		System.out.println("Age Matrix:\n");
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				ageMatrix[i][j] = Double.valueOf(r.nextInt(100 - 0) + 0) / 100;
				System.out.print(ageMatrix[i][j] + "\t");
			}
			System.out.println("\n");
		}
		System.out.println("Gender Matrix:\n");
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				genderMatrix[i][j] = Double.valueOf(r.nextInt(100 - 0) + 0) / 100;
				System.out.print(genderMatrix[i][j] + "\t");
			}
			System.out.println("\n");
		}
		System.out.println("Zip Code Matrix:\n");
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < number; j++) {
				zipCodeMatrix[i][j] = Double.valueOf(r.nextInt(100 - 0) + 0) / 100;
				System.out.print(zipCodeMatrix[i][j] + "\t");
			}
			System.out.println("\n");
		}

		// Original
		HashMap<Double, Object> preservedData = new HashMap<Double, Object>();
		for (int i = 0; i < number; i++) {
			preservedData.put(getMax(ageMatrix[i]), preserveDataList.get(i));
			preservedData.put(getMax(genderMatrix[i]), preserveDataList.get(i));
			preservedData.put(getMax(zipCodeMatrix[i]), preserveDataList.get(i));
		}

		// Modified Matrix Generation
		System.out.println("\nModified Generated Matrix:\n");
		System.out.println("Age Matrix:\n");
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < 5; j++) {
				modifiedAgeMatrix[i][j] = Double.valueOf(r.nextInt(100 - 0) + 0) / 100;
				System.out.print(ageMatrix[i][j] + "\t");
			}
			System.out.println("\n");
		}
		System.out.println("Gender Matrix:\n");
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < 5; j++) {
				modifiedGenderMatrix[i][j] = Double.valueOf(r.nextInt(100 - 0) + 0) / 100;
				System.out.print(genderMatrix[i][j] + "\t");
			}
			System.out.println("\n");
		}
		System.out.println("Zip Code Matrix:\n");
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < 5; j++) {
				modifiedZipCodeMatrix[i][j] = Double.valueOf(r.nextInt(100 - 0) + 0) / 100;
				System.out.print(zipCodeMatrix[i][j] + "\t");
			}
			System.out.println("\n");
		}

		// Modified
		System.out.println("\nDiagonal Index of the numbers in the modified generated matrix:\n");
		HashMap<Double, Object> modifiedPreservedData = new HashMap<Double, Object>();
		int count = 0;
		// Getting the row and column index
		while (count < (number - 1)) {
			for (int j = 0; j < 5; j++) {
				System.out.println("[" + count + "][" + j + "]");
				modifiedPreservedData.put(modifiedAgeMatrix[count][j], preserveDataList.get(count));
				modifiedPreservedData.put(modifiedGenderMatrix[count][j], preserveDataList.get(count));
				modifiedPreservedData.put(modifiedZipCodeMatrix[count][j], preserveDataList.get(count));
				count++;
			}
			for (int j = 3; j > 0; j--) {
				System.out.println("[" + count + "][" + j + "]");
				modifiedPreservedData.put(modifiedAgeMatrix[count][j], preserveDataList.get(count));
				modifiedPreservedData.put(modifiedGenderMatrix[count][j], preserveDataList.get(count));
				modifiedPreservedData.put(modifiedZipCodeMatrix[count][j], preserveDataList.get(count));
				count++;
			}
		}
		// Data Mining Logic
		ArrayList<MinedData> minedData = new ArrayList<MinedData>();
		for (Preserve preserve : preserveDataList) {
			Integer priority = disease.get(preserve.getDisease());
			if (priority != null && priority.compareTo(new Integer(10)) == -1) {
				MinedData currentMinedData = new MinedData();
				// Mining Gender with 0s and 1s
				if (preserve.getGender().equals("M")) {
					currentMinedData.setGender("0");
				} else {
					currentMinedData.setGender("1");
				}

				// Mining Zip Code with 2 *s at the end
				String tempString = String.valueOf(preserve.getZipCode());
				String a = tempString.replace(".0", "");
				currentMinedData.setZipCode(replaceLastTwo(a));

				// Setting Disease
				currentMinedData.setDisease(preserve.getDisease());

				// Mining Age by forming the age groups
				List<Double> ageList = new ArrayList<Double>();
				for (Preserve preserve1 : preserveDataList) {
					if (preserve.getDisease().equals(preserve1.getDisease())) {
						ageList.add(preserve1.getAge());
					}
				}
				Collections.sort(ageList);
				if (ageList.size() > 1) {
					double lowAge = ageList.get(0);
					double highAge = ageList.get(ageList.size() - 1);
					currentMinedData
							.setAge((int) (lowAge - (lowAge % 10)) + "-" + (int) (highAge + (10 - (highAge % 10))));
				} else {
					String temp = ageList.get(0).toString();
					currentMinedData.setAge(replaceLastTwoFromAge(temp));
				}

				// Adding element to the List
				minedData.add(currentMinedData);
			}
		}
		System.out.println("\nMined Data:\n");
		for (MinedData currentMinedData : minedData) {
			System.out.print(currentMinedData.getAge() + "\t\t");
			System.out.print(currentMinedData.getGender() + "\t\t");
			System.out.print(currentMinedData.getZipCode() + "\t\t");
			System.out.print(currentMinedData.getDisease() + "\n");
		}
		int counter = 0;
//		ArrayList<MinedData> minedData1 = new ArrayList<MinedData>();
//		for (MinedData element : minedData) {
//			for (MinedData element1 : minedData) {
//				if (element.getAge().equals(element1.getAge()) && element.getGender().equals(element1.getGender())
//						&& element.getZipCode().equals(element1.getZipCode())
//						&& element.getDisease().equals(element1.getDisease())) {
//					counter++;
//				} else {
//				}
//			}
//		}
//
//		System.out.println("\nMined Data after removing duplicates:\n");
//		for (MinedData currentMinedData : minedData1) {
//			System.out.print(currentMinedData.getAge() + "\t\t");
//			System.out.print(currentMinedData.getGender() + "\t\t");
//			System.out.print(currentMinedData.getZipCode() + "\t\t");
//			System.out.print(currentMinedData.getDisease() + "\n");
//		}

		XSSFWorkbook workbook1 = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet sheet = workbook1.createSheet("Employee Data");
		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		counter = 0;
		for (MinedData currentMinedData : minedData) {
			counter++;
			data.put("" + counter + "", new Object[] { currentMinedData.getAge(), currentMinedData.getGender(),
					currentMinedData.getZipCode(), currentMinedData.getDisease() });
		}
		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File("Mined_Data.xlsx"));
			workbook1.write(out);
			out.close();
			System.out.println("Mined_Data.xlsx written successfully on disk");
		} catch (Exception e) {
			e.printStackTrace();
		}
		workbook.close();
		workbook1.close();
		inputStream.close();
	}
}
