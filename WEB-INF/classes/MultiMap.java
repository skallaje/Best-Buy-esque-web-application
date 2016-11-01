import java.util.*;

public class MultiMap extends HashMap<String, List<Integer>> {
	private static final long serialVersionUID = 1L;

	public void put(String key, Integer number) {
        List<Integer> current = get(key);
        if (current == null) {
            current = new ArrayList<Integer>();
            super.put(key, current);
        }
        current.add(number);
    }
    
    public float calculateAverage(List<Integer> l){
    	float sum=0;
    	for(Integer i:l)
    		sum+=i;
    	return new Float((sum/l.size()));
    }
    
    public Map<String, Float> getSortedMap(MultiMap m){
        Map<String, Float> map = new HashMap<String, Float>();
        for(Map.Entry e : m.entrySet()) {
            map.put((String)e.getKey(), m.calculateAverage((List<Integer>) e.getValue()));
        }
        ValueComparator bvc = new ValueComparator(map);
        TreeMap<String, Float> sm = new TreeMap<String, Float>(bvc);
        
        sm.putAll(map);
        return sm;
    }
}

class ValueComparator implements Comparator<String> {
    Map<String, Float> base;

    public ValueComparator(Map<String, Float> base) {
        this.base = base;
    }
    
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}