package practice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import practice.utils.dao.impl.ExcelReaderImpl;

@RestController
@EnableAutoConfiguration
@ComponentScan
public class App {

	@Autowired
	ExcelReaderImpl excelReaderImpl;

	@RequestMapping("/hello")
	public String sample() {
		excelReaderImpl.removeNewLines("sample.txt", "\t");
		return "Hello from Utils using Spring boot!";
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
