package com.example.acer.hayditrkiyeleri.First_stages.Fragments.DenemeEkle;

// Class for data transfer between fragments

public class EventTransfer {

    // Inner class to avoid creating so much class
    static class popUp{
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
}
