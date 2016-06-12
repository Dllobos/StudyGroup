package com.studygroup.studygroup;

/**
 * Created by Daniel Lobos
 */

public class Direcciones {
    // url del service
    public static final String urlService=
            "http://mongostudygroup-app4tbd.rhcloud.com/service/";
    //URL login usuario
    //post de usuario pide mínimo el mail y la pass
    public static final String UrlLoginUsuario=
            "http://mongostudygroup-app4tbd.rhcloud.com/service/login/";
    //URL Base url de usuarios
    //post de usuario pide mínimo el nombre, apellido, mail, pass, json carrera, con carreraId y nombreCarrera
    public static String UrlUsuarios=
            "http://mongostudygroup-app4tbd.rhcloud.com/service/usuarios";
    //Para acceso a Carreras:
    public static final String urlCarreras=
            "http://mongostudygroup-app4tbd.rhcloud.com/service/gestion_carreras/";
    //GET: Consultar todas las carreras
    public static final String UrlConsultarCarreas=
            "http://mongostudygroup-app4tbd.rhcloud.com/service/gestion_carreras/";
    //Consultar Ramos de carrera
    public static final String UrlConsultarRamosCarrera=
            "http://mongostudygroup-app4tbd.rhcloud.com/servicios/gestion_carreras/";//+id carrera
    //consultar ramos de usuario
    public static final String UrlConsultarRamosUsuario=
            "http://mongostudygroup-app4tbd.rhcloud.com/service/gestion_carreras/usuario/";//+id usuario

    //GET: Consultar una carrera por ID
    public static final String UrlConsultarCarreasId=
            "http://mongostudygroup-app4tbd.rhcloud.com/service/gestion_carreras/";
    //GET: Consultar todos los ramos
    public static final String UrlConsultarRamos=
            "http://mongostudygroup-app4tbd.rhcloud.com/service/gestion_carreras/ramos";
    //GET: Consultar un ramo por ID
    public static final String UrlConsultarRamosId=
            "http://mongostudygroup-app4tbd.rhcloud.com/service/gestion_carreras/ramos/";
    //POST: Agregar un Ramo de una Carrera.
    public static final String UrlAgregarRamoCarrera=
            "http://mongostudygroup-app4tbd.rhcloud.com/service/gestion_carreras/carreras/";
    //URL prueba temporal
    public static final String UrlUsuariosPrueba=
            "http://mongostudygroup-app4tbd.rhcloud.com/testing/usuarios";
    //ramos del usuario
    public static final String UrlUsuarioRamos=
            "http://mongostudygroup-app4tbd.rhcloud.com/service/usuarios/ramos";
    //gestion de relacion entre usuarios
    public static final String UrlGestionUsuarios=
            "http://mongostudygroup-app4tbd.rhcloud.com/service/testing/gestion_relacion_usuarios";
    //grupos de estudio
    public static final String UrlGrupoEstudio=
            "http://mongostudygroup-app4tbd.rhcloud.com/testing/gestion_relacion_usuarios/grupo_estudio";
    //faltan aun
}
