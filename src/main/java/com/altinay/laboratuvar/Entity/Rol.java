package com.altinay.laboratuvar.Entity;

public enum Rol {
    ADMIN , USER;

    public static boolean gecerliMi(String rol){
        try{
            Rol.valueOf(rol.toUpperCase());
            return true;
        }
        catch (IllegalArgumentException e){
            return false;
        }

    }

}
