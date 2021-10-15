package com.mikael.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.mikael.cursomc.domain.Address;
import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.domain.City;
import com.mikael.cursomc.domain.Client;
import com.mikael.cursomc.domain.Order;
import com.mikael.cursomc.domain.OrderItem;
import com.mikael.cursomc.domain.Payment;
import com.mikael.cursomc.domain.PaymentWithBankSlip;
import com.mikael.cursomc.domain.PaymentWithCard;
import com.mikael.cursomc.domain.Product;
import com.mikael.cursomc.domain.State;
import com.mikael.cursomc.domain.enums.ClientType;
import com.mikael.cursomc.domain.enums.PaymentState;
import com.mikael.cursomc.repositories.AddressRepository;
import com.mikael.cursomc.repositories.CategoryRepository;
import com.mikael.cursomc.repositories.CityRepository;
import com.mikael.cursomc.repositories.ClientRepository;
import com.mikael.cursomc.repositories.OrderItemRepository;
import com.mikael.cursomc.repositories.OrderRepository;
import com.mikael.cursomc.repositories.PaymentRepository;
import com.mikael.cursomc.repositories.ProductRepository;
import com.mikael.cursomc.repositories.StateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	} 
}
