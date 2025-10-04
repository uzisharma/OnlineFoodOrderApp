package com.online.orderapp.util;

public final class Endpoints {

	public static final String[] PUBLIC = {
			"/api/user/save",
			"/api/user/login",
			"/api/restaurant/getAll",
			"/api/food/getAll"
	};
	
	public static final String[] USER = {
			"/api/user/update",
		    "/api/user/*/user/uploadImage",
		    "/api/user/*/user/getImage",
		    "/api/user/get/*",
		    "/api/user/*/delete",
		    "/api/restaurant/*/getAll",
		    "/api/restaurant/getAll",
		    "/api/restaurant/get/*",
		    "/api/food/getAll",
		    "/api/food/get/*",
		    "/api/place-order/**",
		    "/api/checkout/**",
		    "/api/cart/add",
		    "/api/cart/get/*",
		    "/api/cart/*/delete",
		    "/api/cart-item/**",
		    
		    
	};
	
	public static final String[] ADMIN = {
			"/api/restaurant/**",
			"/api/user/**",
			"/api/food/**",
			"/api/place-order/**",
			"/api/checkout/**",
			"/api/cart",
			"/api/cart-item/**"
	};
}
