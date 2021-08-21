package com.min.sbs.dto;

import lombok.Getter;

public class ResultData<DataType> {
	@Getter
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private String dataName;
	@Getter
	private DataType data;

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
		resultData.dataName = dataName;
		resultData.data = data;

		return resultData;
	}

	public static <DataType> ResultData<DataType> from(String resultCode, String msg) {
		return from(resultCode, msg, null, null);
	}

	public static <DataType> ResultData<DataType> newData(ResultData rd, String dataName, DataType obj) {
		return from(rd.resultCode, rd.msg, dataName, obj);
	}

}
