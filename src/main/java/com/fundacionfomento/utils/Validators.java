package com.fundacionfomento.utils;

public final class Validators {
    private Validators(){}

    public static boolean email(String validar){ return validar!=null && validar.matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$"); }
    public static boolean dinero(String validar){ return validar!=null && validar.matches("^\\d{1,9}(\\.\\d{1,2})?$"); }
    public static boolean documento(String validar){ return validar!=null && validar.matches("^[A-Za-z0-9-]{5,20}$"); }
    public static boolean passwordFuerte(String validar){
        return validar !=null && validar.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^\\w\\s]).{8,}$");
    }
}
