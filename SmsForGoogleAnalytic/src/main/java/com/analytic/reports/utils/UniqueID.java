/**
 * 
 */
package main.java.com.analytic.reports.utils;

/**
 * @author admin
 * Feb 24, 2015
 */
public class UniqueID {
	  static long current= System.currentTimeMillis();
	  static public synchronized String get(){
	    return String.valueOf(current++);
	    }
	}