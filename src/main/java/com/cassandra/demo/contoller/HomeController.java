package com.cassandra.demo.contoller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cassandra.demo.entity.Product;
import com.cassandra.demo.service.ProductService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	ProductService productService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	@RequestMapping(value = "/products", method=RequestMethod.GET)
	public ModelAndView products(){
		List<Product> productList = productService.getAllProducts();
		ModelAndView model = new ModelAndView("products");
		model.addObject("productList", productList);
	    model.addObject("product", new Product());
		return model;
	}
	
	
	@RequestMapping(value ="/addProduct", method=RequestMethod.POST)
	public ModelAndView createProduct(@ModelAttribute("product") Product product){
		//TODO Validation
		productService.createProduct(product); 
		List<Product> productList = productService.getAllProducts();
		ModelAndView model = new ModelAndView("products");
		model.addObject("productList", productList);
	    model.addObject("product", new Product());
		return model;
	}
	
}
