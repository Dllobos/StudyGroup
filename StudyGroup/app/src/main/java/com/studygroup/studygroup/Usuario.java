package com.studygroup.studygroup;

import android.app.Application;

/**
 * Created by Daniel Lobos
 */

/** variable global a nivel de la aplicacion*/

public class Usuario extends Application{
    //atributos de usuario
    private String usuarioId,
            nombre,
            apellidos,
            descripcion,
            mail,
            numeroMovil,
            pass,
            carrera,
            nombreCarrera,
            ramo,
            nombreRamo,
            grupoTemporals,
            verificadores;

    //get y set de todos los atributos
    public String getUsuarioId(){
        return usuarioId;
    }
    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos(){
        return apellidos;
    }
    public void setApellidos(String apellidos){
        this.apellidos=apellidos;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public String getMail(){
        return mail;
    }
    public void setMail(String mail){
        this.mail=mail;
    }
    public String getNumeroMovil(){
        return numeroMovil;
    }
    public void setNumeroMovil(String numeroMovil){
        this.numeroMovil=numeroMovil;
    }
    public String getPass(){
        return pass;
    }
    public void setPass(String pass){
        this.pass=pass;
    }
    public String getCarrera(){
        return carrera;
    }
    public void setCarrera(String carrera){
        this.carrera=carrera;
    }
    public String getNombreCarrera(){
        return nombreCarrera;
    }
    public void setNombreCarrera(String nombreCarrera){
        this.nombreCarrera=nombreCarrera;
    }
    public String getGrupoTemporals(){
        return grupoTemporals;
    }
    public void setGrupoTemporals(String grupoTemporals){
        this.grupoTemporals=grupoTemporals;
    }
    public String getVerificadores(){
        return verificadores;
    }
    public void setVerificadores(String verificadores){
        this.verificadores=verificadores;
    }
    public String getRamo(){
        return ramo;
    }
    public void setRamo(String ramo){
        this.ramo=ramo;
    }
    public String getNombreRamo(){
        return nombreRamo;
    }
    public void setNombreRamo(String nombreRamo){
        this.nombreRamo=nombreRamo;
    }
}
