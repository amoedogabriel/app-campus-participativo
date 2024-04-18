package com.example.coleodegibis.controllers;

import android.database.Cursor;

import com.example.coleodegibis.entities.Gibi;
import com.example.coleodegibis.interfaces.IGibiController;

import java.util.ArrayList;
import java.util.List;

public class GibiControllerVetor implements IGibiController {

    private static final GibiControllerVetor instance = new GibiControllerVetor();

    private List<Gibi> gibis = new ArrayList<Gibi>();

    private GibiControllerVetor(){}

    public static GibiControllerVetor getInstance(){
        return instance;
    }

    @Override
    public void create(Gibi gibi) {
        gibis.add(gibi);
    }

    @Override
    public List<Gibi> read() {
        return gibis;
    }

    @Override
    public Gibi update(long i, Gibi gibi) {
        return gibis.set((int) i, gibi);
    }

    @Override
    public void delete(long i) {
        gibis.remove((int) i);
    }

    @Override
    public Gibi read(long i) {
        return gibis.get((int) i);
    }

    @Override
    public Cursor getCursor() {
        return null;
    }
}
