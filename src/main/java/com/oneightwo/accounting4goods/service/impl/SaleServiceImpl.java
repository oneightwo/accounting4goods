package com.oneightwo.accounting4goods.service.impl;

import com.oneightwo.accounting4goods.model.Sale;
import com.oneightwo.accounting4goods.model.SalePiece;
import com.oneightwo.accounting4goods.repository.ProductRepository;
import com.oneightwo.accounting4goods.repository.SaleRepository;
import com.oneightwo.accounting4goods.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Sale> getAll() {
        return saleRepository.findAll();
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public void delete(Sale sale) {
        saleRepository.delete(sale);
    }

    @Override
    public Sale update(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public void save(SalePiece salePiece) {
        BigInteger id = saleRepository.save(
                salePiece.getProduct().getId(),
                salePiece.getUser().getId(),
                salePiece.getDate(),
                salePiece.getCount()
        );
        if (id != null) {
            productRepository.updateCountProduct(salePiece.getProduct().getId(), salePiece.getCount());
        }
    }

    @Override
    public List<Sale> getSaleByUserOfLastWeek(BigInteger userId) {
        return saleRepository.getSaleByUserOfLastWeek(userId);
    }
}
