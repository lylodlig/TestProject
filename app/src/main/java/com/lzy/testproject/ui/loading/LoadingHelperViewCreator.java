package com.lzy.testproject.ui.loading;

/**
 * Created by dgg on 2017/11/7.
 */

public class LoadingHelperViewCreator {
	private static StatusViewCreator defaultStatusViewCreator;

	public static void setDefaultStatusViewCreator(StatusViewCreator defaultStatusViewCreator) {
		LoadingHelperViewCreator.defaultStatusViewCreator = defaultStatusViewCreator;
	}

	public static StatusViewCreator getDefaultStatusViewCreator() {
		return defaultStatusViewCreator;
	}
}
