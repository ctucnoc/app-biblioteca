package com.pe.crce.biblioteca.constant;

public final class BibliotecaConstant {

	// STATE
	public static final String STATE_ACTIVE = "1";
	public static final String STATE_INSCTIVE = "0";

	// API VERSION
	public static final String API_VERSION = "/v1";

	// =============================================================================================
	// PATH DEL SISTEMA BIBLIOTECA
	// =============================================================================================
	public static final String RESOURCE_GENERIC = API_VERSION + "/app-biblioteca";
	public static final String RESOURCE_EDITORIALS = "/editorials";
	public static final String RESOURCE_AUTHORS = "/authors";
	public static final String RESOURCE_AUTHORS_AUTHOR = "/author";
	public static final String RESOURCE_EDITORIALS_EDITORIAL = "/editorial";
	public static final String RESOURCE_GENERIC_ID = "/{id}";

	// =============================================================================================
	// PATH DEL SISTEMA BIBLIOTECA FROENT-END
	// =============================================================================================
	public static final String CLIENT_FRONTEND = "*";

	// =============================================================================================
	// NOMBRE DE LAS TABLAS
	// =============================================================================================
	public static final String TAB_NAME_AUTHOR = "t_author";

	// =============================================================================================
	// NOMBRE DE LOS ESQUEMAS
	// =============================================================================================
	public static final String SEC_NAME_DBO = "dbo";

	// =============================================================================================
	// NOMBRE DE LOS PROCEDIMIENTOS ALMACENADOS
	// =============================================================================================
	public static final String SP_SEARCH_AUTHOR = "select * from dbo.fn_search_author(?1,?2)";
	public static final String SP_SEARCH_AUTHOR_COUNT = "select count(*) from dbo.fn_search_author(?1,?2)";
	

}
