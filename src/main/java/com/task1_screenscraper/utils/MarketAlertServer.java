package com.task1_screenscraper.utils;

public interface MarketAlertServer {
    //https://restfulapi.net/http-status-codes/
    //https://restfulapi.net/http-methods/#post
    //https://restfulapi.net/http-methods/#delete
    //https://restfulapi.net/http-methods/#summary
    //https://www.restapitutorial.com/httpstatuscodes.html
    int OK = 200; // delete
    int CREATED = 201; // post
//    int ACCEPTED = 202;
//    int NO_CONTENT = 204; //post
//    public static int MOVED_PERMANENTLY = 301;
//    public static int FOUND = 302;
//    public static int SEE_OTHER = 303;
//    public static int NOT_MODIFIED = 304; // SIMILAR TO 204, BUTRESPONSE BODY EMPTY
//    public static int TEMPORARY_REDIRECT = 307;
    int BAD_REQUEST = 400; // both
//    public static int UNAUTHORIZED = 401;
//    public static int FORBIDDEN = 403;
//    public static int NOT_FOUND = 404; // delete
//    public static int METHOD_NOT_ALLOWED = 405; // delete
//    public static int NOT_ACCEPTABLE = 406;
//    public static int PRE_CONDITION_FAILED = 412;
    int UNSUPPORTED_MEDIA_TYPE = 415; // both
//    public static int INTERNAL_SERVER_ERROR = 500;
//    public static int NOT_IMPLEMENTED = 501;
    int SERVICE_UNAVAILABLE = 503;

}
