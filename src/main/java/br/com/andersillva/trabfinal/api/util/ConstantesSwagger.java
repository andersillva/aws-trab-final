package br.com.andersillva.trabfinal.api.util;

public final class ConstantesSwagger {

	public static final String OK = "200";
	public static final String CREATED = "201";
	public static final String NO_CONTENT = "204";
	public static final String BAD_REQUEST = "400";
	public static final String UNAUTHORIZED = "401";
	public static final String FORBIDDEN = "403";
	public static final String NOT_FOUND = "404";
	public static final String CONFLICT = "409";
	public static final String INTERNAL_SERVER_ERROR = "500";
	
	public static final String BAD_REQUEST_DESCRIPTION = "Parâmetros não informados ou com valores inválidos.";
	public static final String UNAUTHORIZED_DESCRIPTION = "Usuário não autenticado. O token não foi informado, ou foi informado, mas é inválido.";
	public static final String FORBIDDEN_DESCRIPTION = "Usuário não tem permissão para realizar a operação solicitada.";
	public static final String INTERNAL_SERVER_ERROR_DESCRIPTION = "Ocorreu um erro interno no servidor.";

}
