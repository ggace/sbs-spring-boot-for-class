package com.min.sbs.util;

public class Util {

	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}

		if (!(obj instanceof String)) {
			return true;
		}

		String str = (String) obj;

		if (str.trim().length() == 0) {
			return true;
		}

		return false;

	}

	public static String format(String string, Object... args) {
		return String.format(string, args);
	}
	
	public static String jsHistoryBack(String msg) {
		if(msg == null) {
			msg = "";
		}
		return Util.format("""
				<script>
				const msg="%s";
				if(msg.length > 0){
					alert(msg);
				}
				history.back();
				</script>
				""", msg);
	}

	public static String jsReplace(String msg, String uri) {
		if(msg == null) {
			msg = "";
		}
		return Util.format("""
				<script>
				const msg="%s";
				if(msg.length > 0){
					alert(msg);
				}
				location.replace("%s")
				</script>
				""", msg, uri);
	}

	public static String jsJustReplace(String string) {
		return Util.format("""
				<script>
				
				location.replace("%s")
				</script>
				""", string);
	}

}
