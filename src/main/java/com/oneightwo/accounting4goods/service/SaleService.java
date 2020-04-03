package com.oneightwo.accounting4goods.service;

import com.oneightwo.accounting4goods.model.Sale;
import com.oneightwo.accounting4goods.model.SalePiece;
import com.oneightwo.accounting4goods.service.basic.BasicService;

import java.math.BigInteger;
import java.util.List;

public interface SaleService extends BasicService<Sale> {

    void save(SalePiece salePiece);

    List<Sale> getSaleByUserOfLastWeek(BigInteger userId);

}
