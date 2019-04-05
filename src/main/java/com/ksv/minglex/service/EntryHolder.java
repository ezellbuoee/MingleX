package com.ksv.minglex.service;

import java.util.Map;

public class EntryHolder {
    Map.Entry<String,? extends Object> entry;

    EntryHolder(Map.Entry<String,? extends Object> entry) {
        this.entry = entry;
    }
}
