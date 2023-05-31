package com.ezyxip.runiwstart.system;

import java.util.Vector;

public class UriCacheStore {

    private Vector<String> uriHistory;
    private int cacheSize;
    UriCacheStore(int size){
        cacheSize = size;
        uriHistory = new Vector(1);
    }

    public void putUri(String uri){
        if(uriHistory.size() >= cacheSize){
            uriHistory.remove(0);
        }
        uriHistory.add(uri);
    }

    public Vector<String> getCache() {
        return uriHistory;
    }

    @Override
    public String toString() {
        return "UriCacheStore{" +
                "uriHistory=" + uriHistory +
                ", cacheSize=" + cacheSize +
                '}';
    }
}
