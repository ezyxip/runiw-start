package com.ezyxip.runiwstart.system;

public class Pair <T1, T2>{
    private T1 value1;
    private T2 value2;

    public Pair(){}

    public Pair(T1 value1, T2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T1 getValue1() {
        return value1;
    }

    public void setValue1(T1 value1) {
        this.value1 = value1;
    }

    public T2 getValue2() {
        return value2;
    }

    public void setValue2(T2 value2) {
        this.value2 = value2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (getValue1() != null ? !getValue1().equals(pair.getValue1()) : pair.getValue1() != null) return false;
        return getValue2() != null ? getValue2().equals(pair.getValue2()) : pair.getValue2() == null;
    }

    @Override
    public int hashCode() {
        int result = getValue1() != null ? getValue1().hashCode() : 0;
        result = 31 * result + (getValue2() != null ? getValue2().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "value1=" + value1 +
                ", value2=" + value2 +
                '}';
    }
}
