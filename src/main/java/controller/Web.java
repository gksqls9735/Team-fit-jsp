package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.MealVO;

public class Web {

	public static Scanner sc = new Scanner(System.in);

	public static ArrayList<MealVO> webConnect() {
		
		ArrayList<MealVO> list = new ArrayList<MealVO>();
	
		// 요청 url 생성
		String filePath = "D:/myProject/myjsp/teamfit/src/main/java/db.properties";
		Properties properties = null;
		String key = null;
		StringBuilder urlBuilder = new StringBuilder(
				"https://apis.data.go.kr/1471000/KidMnuInfoService/getKidMnuInfoService");

		try {
			properties = new Properties();
			properties.load(new FileReader(filePath));
			key = properties.getProperty("key");
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
					+ key);
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("6", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("Type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 커넥션 생성
		URL url = null;
		HttpURLConnection conn = null;
		try {
			url = new URL(urlBuilder.toString());
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 요청전송 및 응답 처리

		BufferedReader br = null;

		try {
			int StatusCode = conn.getResponseCode();
			System.out.println(StatusCode);
			if (StatusCode >= 200 && StatusCode <= 300) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			Document doc = parseXML(conn.getInputStream());
/*
			String header = String.format(
				    "%-5s %-15s %-9s %-12s %-10s %-10s %-10s %-12s",
				    "번호", "영어이름", "생성일", "소멸일", "최저 기압", "최대 풍속", "태풍이름", "한반도 영향"
				);
			System.out.println(header);
			*/
			// item 태그객체 목록으로 가져오기
			NodeList descNodes = doc.getElementsByTagName("item");
			// 각 item 태그의 자식 태그에서 정보 가져오기
			for (int i = 0; i < descNodes.getLength(); i++) {
				// item
				Node item = descNodes.item(i);
				MealVO data = new MealVO();
				// item 태그에 순차적으로 접근
				for (Node node = item.getFirstChild(); node != null; node = node.getNextSibling()) {
//					System.out.println(node.getNodeName() + " : " + node.getTextContent());
					switch (node.getNodeName()) {
					case "MEAL_CLSF_NM":
						data.setMeal_clsf_nm(node.getTextContent());
						break;
					case "MEAL_NM":
						data.setMeal_nm(node.getTextContent());
						break;
					case "COOK_MTH_CONT":
						data.setCook_mth_cont(node.getTextContent());
						break;
					case "MATRL_NM":
						data.setMatrl_nm(node.getTextContent());
						break;
					case "CALORIE_QY":
						data.setCalorie_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "CARBOH_QY":
						data.setCarboh_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "PROTEIN_QY":
						data.setProtein_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "FAT_QY":
						data.setFat_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "CELLU_QY":
						data.setCellu_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "CALCIUM_QY":
						data.setCalcium_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "PHOSPH_QY":
						data.setPhosph_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "FE_QY":
						data.setFe_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "NATRIUM_QY":
						data.setNatrium_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "POTASSIUM_QY":
						data.setPotassium_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "VITAMINA_QY":
						data.setVitamina_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "THIAMIN_QY":
						data.setThiamin_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "RIBOFLAMIN_QY":
						data.setRiboflamin_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "NIACIN_QY":
						data.setNiacin_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "VITAMINC_QY":
						data.setVitaminc_qy(Double.parseDouble(node.getTextContent()));
						break;
					case "MEAL_PICTR_FILE_NM":
						data.setMeal_pictr_file_nm(node.getTextContent());
						break;
					}
				}
				list.add(data);
			}
			System.out.println("=====================================================================================================================================================");
			for (MealVO d : list) {
				System.out.println(d);
			}
			System.out.println("=====================================================================================================================================================");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		conn.disconnect();
		return list;

	}

	public static Document parseXML(InputStream inputStream) {
		DocumentBuilderFactory objDocumentBuliderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder objDocumentBulider = null;
		Document doc = null;

		try {
			objDocumentBulider = objDocumentBuliderFactory.newDocumentBuilder();
			doc = objDocumentBulider.parse(inputStream);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public static void main(String[] args) {
		ArrayList<MealVO> list = webConnect();
		MealDAO dao = MealDAO.getInstance();
//		dao.insertArticle(list);
	}
}
