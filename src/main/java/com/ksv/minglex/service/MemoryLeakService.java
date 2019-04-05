package com.ksv.minglex.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

@Service
public class MemoryLeakService {

    private final List<EntryHolder> holdersCache = new ArrayList<>();
    private static final int MAP_SIZE = 100_000;

    public void createMemoryLeak(List<? extends Object> o) {

        IntStream.range(0, o.size()).forEach(value -> {
            // create map
            final Map<String, ? extends Object> map = createLeakingData(o);

            final int index = new Random().nextInt(MAP_SIZE);

            // get random entry from map
            for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
                if (entry.getValue().equals(index)) {
                    holdersCache.add(new EntryHolder(entry));
                    break;
                }
            }
            // to observe behavior in visualvm
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    public static Map<String,? extends Object> createLeakingData(List<? extends Object> o) {
        final Map<String, Object> map = new TreeMap<>();
        IntStream.range(0, o.size()-1).forEach(i -> map.put(String.valueOf(i), o.get(i)));
        return map;
    }

}
