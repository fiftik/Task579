package ru.acmp.task579;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class Main {

	public static void main(String[] args) throws IOException {

		Map<Integer, String> sqncMap = new HashMap<>();

		String myJarPath = Main.class.getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		String dirPath = new File(myJarPath).getParent();
		dirPath = dirPath + "\\files";

		FileInputStream fstream = new FileInputStream(dirPath + "\\INPUT.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		br.readLine();
		String sqnc = br.readLine();
		br.close();

		Pattern pattern = Pattern.compile("-\\d+|\\d");
		String word = sqnc;
		Matcher matcher = pattern.matcher(word);
		int start = 0, key = 1;
		while (matcher.find(start)) {
			String value = word.substring(matcher.start(), matcher.end());
			start = matcher.end();
			sqncMap.put(key, value);
			key++;
		}
		String sqncNgtv = "", sqncPstv = "";
		int sumNgtv = 0, sumPstv = 0, countNgtv = 0, countPstv = 0;
		for (int i = 1; i <= sqncMap.size(); i++) {
			int y = Integer.parseInt((String) sqncMap.get(i));
			if (y < 0) {
				sqncNgtv = sqncNgtv + i + " ";
				sumNgtv = sumNgtv + Math.abs(y);
				countNgtv++;
			}
			if (y > 0) {
				sqncPstv = sqncPstv + i + " ";
				sumPstv = sumPstv + Math.abs(y);
				countPstv++;
			}
			if (sumNgtv > sumPstv) {
				String str1 = Integer.toString(countNgtv);
				String str2 = sqncNgtv;
				try (FileWriter writer = new FileWriter(dirPath
						+ "\\OUTPUT.txt", false)) {
					writer.write(str1 + "\n");
					writer.write(str2);
					writer.flush();
				} catch (IOException ex) {

					System.out.println(ex.getMessage());
				}
			} else {
				String str1 = Integer.toString(countPstv);
				String str2 = sqncPstv;
				try (FileWriter writer = new FileWriter(dirPath
						+ "\\OUTPUT.txt", false)) {
					writer.write(str1 + "\n");
					writer.write(str2);
					writer.flush();
				} catch (IOException ex) {

					System.out.println(ex.getMessage());
				}
			}

		}
	}

}
