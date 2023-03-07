package com.pe.crce.biblioteca.constant;

public final class BibliotecaConstant {
	
	// STATE
	public static final String STATE_ACTIVE = "1";
	public static final String STATE_DESABLE = "0";

	//API VERSION
	public static final String API_VERSION = "/v1";
	
	//PATH DEL SISTEMA BIBLIOTECA
	public static final String RESOURCE_GENERIC = API_VERSION + "/app-biblioteca";
	public static final String RESOURCE_EDITORIALS = "/editorials";
	public static final String RESOURCE_EDITORIALS_EDITORIAL = "/editorial";
	public static final String RESOURCE_GENERIC_ID = "/{id}";
	
	public static final String CLIENT_FRONTEND = "*";
}
