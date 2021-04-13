package com.colin.Comparators;

import com.colin.Models.Promoter;

import java.util.Comparator;

public class PromoterIDComparator implements Comparator<Promoter>
{
    @Override
    public int compare(Promoter o1, Promoter o2) {
        return o1.getId() - o2.getId();
    }
}
