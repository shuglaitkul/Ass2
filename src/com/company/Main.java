package com.company;

class HashTable {
    LinkedList[] hashTable;
    int size;

    HashTable(int hashSize){
        hashTable = new LinkedList[hashSize];
        size = 0;
    }

    public boolean IfEmpty() {
        return size == 0;
    }

    public void delete()
    {
        int length = hashTable.length;
        hashTable = new LinkedList[length];
        size = 0;
    }

    public void addElement(int val) {
        size++;
        int place = hash(val);

        LinkedList n = new LinkedList(val);

        LinkedList start = hashTable[place];

        if (hashTable[place] == null) {
            hashTable[place] = n;
        }
        else {
            n.first = start;
            start.second = n;
            hashTable[place] = n;
        }
    }

    public void deleteElement(int value)
    {
        try {
            int place = hash(value);
            LinkedList start = hashTable[place];
            LinkedList finish = start;

            if (start.num == value) {
                size--;
                if (start.first == null) {
                    hashTable[place] = null;
                }
                start = start.first;
                start.second = null;
                hashTable[place] = start;
            }
            while (finish.first != null && finish.first.num != value)
                finish = finish.first;

            if (finish.first == null) {
                System.out.println("\nElement not found\n");
                return;
            }
            size--;

            if (finish.first.first == null) {
                finish.first = null;
                return;
            }

            finish.first.first.second = finish;
            finish.first = finish.first.first;

            hashTable[place] = start;
        }
        catch (Exception e) {
            System.out.println("\nElement not found\n");
        }
    }


    private int hash(Integer element)
    {
        int hashValue = element.hashCode();
        hashValue %= hashTable.length;

        if (hashValue < 0)
            hashValue += hashTable.length;

        return hashValue;
    }

    public void printHashTable()
    {
        System.out.println();
        for (int i = 0; i < hashTable.length; i++) {
            System.out.print(i + ":  ");

            LinkedList start = hashTable[i];

            while (start != null) {
                System.out.print(start.num + " ");
                start = start.first;
            }

            System.out.println();
        }
    }
}

class LinkedList{
    LinkedList first, second;
    int num;

    public LinkedList(int num) {
        this.num = num;
    }
}

public class Main {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(6);

        hashTable.addElement(17);
        hashTable.addElement(20);
        hashTable.addElement(34);
        hashTable.addElement(39);
        hashTable.addElement(45);
        hashTable.addElement(90);
        hashTable.printHashTable();
        System.out.println(hashTable.IfEmpty());

        hashTable.addElement(50);
        hashTable.addElement(60);
        hashTable.deleteElement(45);

        hashTable.printHashTable();
        System.out.println(hashTable.IfEmpty());

        hashTable.delete();
        hashTable.printHashTable();
        System.out.println(hashTable.IfEmpty());
    }
}