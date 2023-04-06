package Application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import Entities.People;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Entre com o caminho do arquivo: ");
		String path = sc.nextLine();
		System.out.print("Entre com o salário: ");
		double salary = sc.nextDouble();
		System.out.println("email que o salário for maior que " + String.format("%.2f", salary) + ": ");
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<People> list = new ArrayList<>();
			
// 		Leitura das linhas do arquivo
			
			String line = br.readLine();
			while(line!= null) {
				String[] fields = line.split(",");
				list.add(new People(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
// 		Emails maiores que 2000.00
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> emails = list.stream()
			.filter(x -> x.getSalary() > 2000.00)
			.map(x -> x.getEmail())
			.sorted(comp)
			.collect(Collectors.toList());
			emails.forEach(System.out::println);
			
			
//		Soma dos salários
			
			double m = list.stream()
			.filter(x -> x.getName().charAt(0) == 'M' || x.getName().charAt(0) == 'm')
			.map(x -> x.getSalary())
			.reduce(0.0, (x, y) -> x+y);
			
			System.out.println("Soma dos salários que começam com 'M' : "+ String.format("%.2f", m));
		
		}catch (IOException e) {
			System.out.println("Error: "+ e.getMessage());
		}
		sc.close();
	}
}