package com.buildcrm.application.port.in;

import com.buildcrm.application.service.FinanceOverview;

import java.util.List;

public interface FinanceUseCase {
    FinanceOverview getFinanceOverview();
    List<FinanceOverview> getProfitAndLossByProject();
}
