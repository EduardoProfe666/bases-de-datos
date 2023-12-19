package utils;

public final class FunctionBuilder {
	private FunctionBuilder() {}
	
	public static String constructCrudFunction(FunctionType type, String tableName, int countParameters) {
		String s = "{";
		boolean hasOut = false;
		String functionName = "";
		boolean countP = true;
		switch (type) {
		case GET_ALL:
			hasOut = true;
			functionName = "get_all_"+tableName;
			countP = false;
			break;
		case GET_BY_ID:
			hasOut = true;
			functionName = "get_"+tableName;
			countP = true;
			break;
		case DELETE:
			hasOut = false;
			functionName = "delete_"+tableName;
			countP = true;
			break;
		case UPDATE:
			hasOut = false;
			functionName = "update_"+tableName;
			countP = true;
			break;
		case ADD:
			hasOut = false;
			functionName = "add_"+tableName;
			countP = true;
			break;
		default:
			throw new IllegalArgumentException("Tipo de funcion ilegal");
		}
		if(hasOut)
			s = s + "? = ";
		s = s + "call "+functionName+"(";

		if(countP) {
			StringBuilder sb = new StringBuilder(s);
			for(int i=0;i<countParameters;i++) {
				sb.append("?,");
			}
			if(countParameters>0)
				sb.deleteCharAt(sb.length()-1);
			s = sb.toString();
		}
		s = s+")}";
		return s;
	}

	public static enum FunctionType{
		GET_ALL, GET_BY_ID, DELETE, UPDATE, ADD
	}

	public static String constructFunction(boolean hasOut, String functionName, int countParameters) {
		String s = "{";
		if(hasOut)
			s = s + "?=";
		s = s + "call "+functionName+"(";
		StringBuilder sb = new StringBuilder(s);
		for(int i=0;i<countParameters;i++) {
			sb.append("?,");
		}
		if(countParameters>0)
			sb.deleteCharAt(sb.length()-1);
		s = sb.toString()+")}";
		return s;
	}
	
}
