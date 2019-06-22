package com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle;

// Class for data transfer between fragments

import java.util.HashMap;
import java.util.LinkedHashMap;

public class EventTransfer {

    // Inner class to avoid creating so much class
    static public class popUp{
        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public popUp(int num) {
            this.num = num;
        }
    }

    static public class konuIcerigi{
        private java.util.LinkedHashMap<String,String > LinkedHashMap;

        public LinkedHashMap<String, String> getHashMap() {
            return LinkedHashMap;
        }

        public void setHashMap(LinkedHashMap<String, String> LinkedHashMap) {
            this.LinkedHashMap = LinkedHashMap;
        }

        public konuIcerigi(LinkedHashMap<String, String> LinkedHashMap) {
            this.LinkedHashMap = LinkedHashMap;
        }
    }
}
