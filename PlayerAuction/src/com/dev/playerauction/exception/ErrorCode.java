package com.dev.playerauction.exception;

/**
 * This class contains the error codes for the exceptions which can be raised in application
 * @author Saurabh Kumar Singh
 * @version 1.0
 */
public class ErrorCode {

    /**
     * Error code for exception when the number of times a player can be rejected is a non-positive integer value
     */
    public static final int INVALID_MAXIMUM_REJECT_COUNT_FOR_PLAYER = 10000;

    /**
     * Error code for exception when the value of batting strength is not in valid range
     */
    public static final int INVALID_BATTING_STRENGTH =   10001;

    /**
     * Error code for exception when the value of bowling strength is not in valid range
     */
    public static final int INVALID_BOWLING_STRENGTH = 10002;

    /**
     * Error code for exception when the value of fielding strength is not in valid range
     */
    public static final int INVALID_FIELDING_STRENGTH = 10003;

    /**
     * Error code for exception when the value of keeping strength is not in valid range
     */
    public static final int INVALID_KEEPING_STRENGTH = 10004;

    /**
     * Error code for exception when the xml data of players in an invalid xml
     */
    public static final int INVALID_DATA_XML = 10005;
}
