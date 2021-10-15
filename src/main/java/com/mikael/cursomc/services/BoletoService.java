package com.mikael.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import com.mikael.cursomc.domain.PaymentWithBankSlip;

import org.springframework.stereotype.Service;

@Service
public class BoletoService {
    
    public void preencherPagamentoComBoleto(PaymentWithBankSlip pagto, Date instanteDoPedido){
        Calendar cal = Calendar.getInstance();
        cal.setTime(instanteDoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(cal.getTime());
    }
}