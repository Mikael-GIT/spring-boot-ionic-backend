package com.mikael.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mikael.cursomc.domain.Client;
import com.mikael.cursomc.domain.dtos.ClientNewDTO;
import com.mikael.cursomc.domain.enums.ClientType;
import com.mikael.cursomc.repositories.ClientRepository;
import com.mikael.cursomc.resources.exception.FieldMessage;
import com.mikael.cursomc.services.validation.utils.BR;

import org.springframework.beans.factory.annotation.Autowired;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
    
    @Autowired
    private ClientRepository repo;
    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(ClientType.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido."));
        }
        if (objDto.getTipo().equals(ClientType.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido."));
        }
        // inclua os testes aqui, inserindo erros na lista
        Client aux = repo.findByEmail(objDto.getEmail());
        if(aux != null){
            list.add(new FieldMessage("email", "Email já existente."));
        }
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}