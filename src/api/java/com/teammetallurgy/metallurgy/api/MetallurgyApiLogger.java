package com.teammetallurgy.metallurgy.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MetallurgyApiLogger {

	private static Logger logger = LogManager.getLogger("MetallurgyAPI");

	public static void info(String message) {
		logger.info(message);
	}

	public static void trace(String message) {
		logger.trace(message);
	}

	public static void warn(String message) {
		logger.warn(message);
	}
}