package interpreter;

import java.util.HashMap;
import java.util.Map;

public class Environment {

    private Map<String, Integer> vars = new HashMap<>();

    public void set(String name, int value) {
        vars.put(name, value);
    }

    public int get(String name) {
        if (!vars.containsKey(name)) {
            throw new RuntimeException("Runtime Error: Undefined variable " + name);
        }
        return vars.get(name);
    }

    public boolean exists(String name) {
        return vars.containsKey(name);
    }
}