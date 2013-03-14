package com.dev.playerauction.exception;

/**
 * Wrapper exception class for the application
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class PlayerAuctionException extends Exception{

    /**
     * Constructor for the wrapper exception
     * @param errorCode identifies the error cause in error dictionary of application
     * @param message exception message
     */
     public PlayerAuctionException(int errorCode, String message){

         super("Error Code : "+errorCode+" \nException Message : "+message);
     }
}
