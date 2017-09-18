package com.jlgproject.model.newDebt;

/**
 * Created by yarolegovich on 07.03.2017.
 */

public class Item {

    private final int pid;
    //父级id
    private final int id;
    private final String name;
    private final int image;

    public Item(int pid, int id, String name, int image) {
        this.pid = pid;
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getPid() {
        return pid;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
