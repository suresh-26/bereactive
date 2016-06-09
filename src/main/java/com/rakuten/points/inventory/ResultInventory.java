/*
 * Copyright (c) Rakuten, Inc. All Rights Reserved.
 * 
 * This program is the information assets which are handled
 * as "Strictly Confidential".
 * Permission of Use is only admitted in Rakuten Inc.
 * Development Department.
 * If you don't have permission , MUST not be published,
 * broadcast, rewritten for broadcast or publication
 * or redistributed directly or indirectly in any medium.
 * 
 * $Id$
 * Created on Apr 28, 2016
 * Updated on $Date$
 */
package com.rakuten.points.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

/**
 * @author suresh.gupta
 *
 */
public class ResultInventory {
    private Map<String, List<Long>> transactionMap = new HashMap<>();
    private List<Long> ids;
    private String transactionId;

    public ResultInventory(String transactionStr) {
        transactionMap.put(transactionStr, initializeids());
    }

    public static List<Long> initializeids() {
        return LongStream.iterate(0, n -> n + 1).limit(1000000)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public Map<String, List<Long>> getTransactionMap() {
        return transactionMap;
    }

    public void setTransactionMap(Map<String, List<Long>> transactionMap) {
        this.transactionMap = transactionMap;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getIdsListFromMap(String searchStr) {
        return transactionMap.get(searchStr);
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

}
