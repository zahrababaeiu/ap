package ap.projects.scraper.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap;

public class ObjectCounter {
    private List<String> items;
    private List<Integer> counts;

    public ObjectCounter() {
        items = new ArrayList<>();
        counts = new ArrayList<>();
    }

    public void add(String item) {
        int index = items.indexOf(item);
        if (index == -1) {
            items.add(item);
            counts.add(1);
        } else {
            counts.set(index, counts.get(index) + 1);
        }
    }

    public int getCount(String item) {
        int index = items.indexOf(item);
        if (index == -1) {
            return 0;
        } else {
            return counts.get(index);
        }
    }
    public List<Map.Entry<String, Integer>> getTop(int k) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            entries.add(new AbstractMap.SimpleEntry<>(items.get(i), counts.get(i)));
        }
        entries.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));
        if (k > entries.size()) {
            k = entries.size();
        }
        return entries.subList(0, k);
    }
}
