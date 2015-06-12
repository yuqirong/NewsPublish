package com.cjlu.newspublish.constant;

import java.io.File;
import java.nio.file.Path;

public class WebConstant {

	public static final String DIRECTORY_PATH = "D:\\GitRepo\\NewsPublish\\WebContent\\indexDir";
	public static final Path FILE_PATH = new File(DIRECTORY_PATH).toPath();
	public static final int PAGE_SIZE = 10;
	public static final int PAGE_MAX_SIZE = 15;
	public static final int RESULT = 6;
	public static final int MAX_RESULT = 10;
	public static final int LOGIN_FAILURE_LOCKED = 5;

}
