package com.colin.Comparators;

import com.colin.Models.Promoter;

import java.util.Comparator;

public class PromoterBudgetComparator implements Comparator<Promoter>
{
    @Override
    public int compare(Promoter o1, Promoter o2) {
        return (int) (o1.getBudget() - o2.getBudget());
    }
}
