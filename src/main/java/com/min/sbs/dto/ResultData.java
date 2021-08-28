package com.min.sbs.dto;

import lombok.Getter;

public class ResultData<DataType> {
	@Getter
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private String data1Name;
	@Getter
	private DataType data1;
	@Getter
	private String data2Name;
	@Getter
	private Object data2;
	

	private ResultData() {
		// TODO Auto-generated constructor stub
	}

	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}

	public boolean isFail() {
		return !isSuccess();
	}

	public static <DataType> ResultData<DataType> from(String resultCode, String msg, String dataName, DataType data) {
		ResultData<DataType> resultData = new ResultData<>();
		resultData.resultCode = resultCode;
		resultData.msg = msg;
		resultData.data1Name = dataName;
		resultData.data1 = data;

		return resultData;
	}

	public static <DataType> ResultData<DataType> from(String resultCode, String msg) {
		return from(resultCode, msg, null, null);
	}

	public static <DataType> ResultData<DataType> newData(ResultData rd, String dataName, DataType obj) {
		return from(rd.resultCode, rd.msg, dataName, obj);
	}
	
	public void setData2(String dataName, Object data) {
		data2Name = dataName;
		data2 = data;
	}

}
