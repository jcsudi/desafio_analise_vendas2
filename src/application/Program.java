package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import entities.Sale;

public class Program {
	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre com o caminho do arquivo:");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) { ///home/jose/projetos-ds/in.csv

			List<Sale> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");

				Integer moth = Integer.parseInt(fields[0]);
				Integer year = Integer.parseInt(fields[1]);
				Integer items = Integer.parseInt(fields[3]);
				Double total = Double.parseDouble(fields[4]);
				
				list.add(new Sale(moth, year, fields[2], items, total));

				line = br.readLine();
			}
			System.out.println();
			System.out.println("Total de vendas por vendedor: ");
			
			Map<String, Double> map = list.stream()
	                .collect(Collectors.groupingBy(c -> c.getSeller(), Collectors.summingDouble(c -> c.getTotal())));
			
			for(Map.Entry<String, Double> entry : map.entrySet()) {
	            String seller = entry.getKey();
	            Double total = entry.getValue();

	            System.out.println(new Sale(seller, total));
	              
			}
					
		}
		catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());

		}

	}
}
