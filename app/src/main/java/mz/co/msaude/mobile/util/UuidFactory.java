/*
 * ® 2014 - 2017 MozView Technologies - Maputo, Moçambique ®
 */
package mz.co.msaude.mobile.util;

import java.util.UUID;

public class UuidFactory {

	public static String generate() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
