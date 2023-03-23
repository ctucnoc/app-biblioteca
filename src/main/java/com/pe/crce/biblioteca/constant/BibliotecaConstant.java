package com.pe.crce.biblioteca.constant;

public final class BibliotecaConstant {

	// =============================================================================================
	// CODIGO DE ERROR DEL CLIENTE Y SERVIDOR
	// =============================================================================================

	// CLIENT ERRORS
	public static final String BAD_REQUEST = "400";
	public static final String UNAUTHORIZED = "401";
	public static final String FORBIDDEN = "403";
	public static final String NOT_FOUND = "404";
	public static final String METHOD_NOT_ALLOWED = "405";
	public static final String NOT_ACCEPTABLE = "406";
	public static final String CONFLICT = "409";
	public static final String UNPROCESSABLE_ENTITY = "422";
	public static final String EXPECTATION_FAILED = "417";
	public static final Float NR_VUELTO_DEFAULT = (float) 0;

	// SERVER ERRORS
	public static final String INTERNAL_SERVER_ERROR = "500";
	public static final String NOT_IMPLEMENTED = "501";
	public static final String BAD_GATEWAY = "503";
	public static final String SERVICE_UNAVAILABLE = "504";
	public static final String GATEWAY_TIMEOUT = "505";
	public static final String NOT_VALIDATED = "506";

	// ERRORS
	public static final String PREFIX_SERVER_ERROR = "SRV";
	public static final String PREFIX_CLIENT_ERROR = "CLI";

	// STATE
	public static final String STATE_ACTIVE = "1";
	public static final String STATE_INACTIVE = "0";

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
	public static final String RESOURCE_LIBRARYS = "/libraries";
	public static final String RESOURCE_LIBRARYS_LIBRARY = "/library";
	public static final String RESOURCE_AREAS = "/areas";
	public static final String RESOURCE_AREAS_AREA = "/area";
	public static final String RESOURCE_SUBAREAS = "/sub-areas";
	public static final String RESOURCE_SUBAREAS_SUBAREA = "/sub-area";

	// =============================================================================================
	// PATH DEL SISTEMA BIBLIOTECA FROENT-END
	// =============================================================================================
	public static final String CLIENT_FRONTEND = "*";

	// =============================================================================================
	// NOMBRE DE LAS TABLAS
	// =============================================================================================
	public static final String TAB_NAME_AUTHOR = "t_author";
	public static final String TAB_NAME_EDITORIAL = "t_editorial";
	public static final String TAB_NAME_LIBRARY = "t_library";
	public static final String TAB_NAME_AREA = "t_area";
	public static final String TAB_NAME_SUB_AREA = "t_sub_area";



	// =============================================================================================
	// NOMBRE DE LOS ESQUEMAS
	// =============================================================================================
	public static final String SEC_NAME_DBO = "dbo";

	// =============================================================================================
	// NOMBRE DE LOS PROCEDIMIENTOS ALMACENADOS
	// =============================================================================================
	public static final String SP_SEARCH_AUTHOR = "select * from dbo.fn_search_author(?1,?2)";
	public static final String SP_SEARCH_AUTHOR_COUNT = "select count(*) from dbo.fn_search_author(?1,?2)";
	public static final String SP_SAVE_AUTHOR = "select * from dbo.fn_save_author(?1,?2)";
	public static final String SP_SAVE_AUTHOR_COUNT = "select count(*) from dbo.fn_save_author(?1,?2)";

}
