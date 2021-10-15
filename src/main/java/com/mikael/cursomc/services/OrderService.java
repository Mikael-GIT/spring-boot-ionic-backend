package com.mikael.cursomc.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import com.mikael.cursomc.domain.Category;
import com.mikael.cursomc.domain.Order;
import com.mikael.cursomc.domain.OrderItem;
import com.mikael.cursomc.domain.PaymentWithBankSlip;
import com.mikael.cursomc.domain.enums.PaymentState;
import com.mikael.cursomc.repositories.OrderItemRepository;
import com.mikael.cursomc.repositories.OrderRepository;
import com.mikael.cursomc.repositories.PaymentRepository;
import com.mikael.cursomc.repositories.ProductRepository;
import com.mikael.cursomc.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository repository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
	private ClientService clientService;

    public Order find(Integer id){
        Optional<Order> order = repository.findById(id);
        return order.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
    }

    public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clientService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(PaymentState.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PaymentWithBankSlip) {
			PaymentWithBankSlip pagto = (PaymentWithBankSlip) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		for (OrderItem item : obj.getItens()) {
			item.setDesconto(0.0);
			item.setPreco(productService.find(item.getProduct().getId()).getPreco());
		}
		obj = repository.save(obj);
		paymentRepository.save(obj.getPagamento());
		return obj;
	}
}