package com.demo.tony.testplugindemo;
/**
 * Created by zhouduntao on 17/05/08.
 */


public class LogUtils {
	private static final String TAG = "phantom";
	private static LogImp debugLog = new LogImp() {

		@Override
		public void v(final String tag, final String msg, final Object... obj) {
			String log = obj == null ? msg : String.format(msg, obj);
			android.util.Log.v(tag, log);
		}

		@Override
		public void i(final String tag, final String msg, final Object... obj) {
			String log = obj == null ? msg : String.format(msg, obj);
			android.util.Log.i(tag, log);

		}

		@Override
		public void d(final String tag, final String msg, final Object... obj) {
			String log = obj == null ? msg : String.format(msg, obj);
			android.util.Log.d(tag, log);
		}

		@Override
		public void w(final String tag, final String msg, final Object... obj) {
			String log = obj == null ? msg : String.format(msg, obj);
			android.util.Log.w(tag, log);
		}

		@Override
		public void e(final String tag, final String msg, final Object... obj) {
			String log = obj == null ? msg : String.format(msg, obj);
			android.util.Log.e(tag, log);
		}

		@Override
		public void printErrStackTrace(String tag, Throwable tr, String format, Object... obj) {
			String log = obj == null ? format : String.format(format, obj);
			if (log == null) {
				log = "";
			}
			log += "  " + android.util.Log.getStackTraceString(tr);
			android.util.Log.e(tag, log);
		}
	};
	private static LogImp logImp = debugLog;

	public static void setLogImp(LogImp imp) {
		logImp = imp;
	}

	public static LogImp getImpl() {
		return logImp;
	}

	public static void v(final String tag, final String msg, final Object... obj) {
		if (logImp != null) {
			logImp.v(tag, msg, obj);
		}
	}

	public static void e(final String tag, final String msg, final Object... obj) {
		if (logImp != null) {
			logImp.e(tag, msg, obj);
		}
	}

	public static void w(final String tag, final String msg, final Object... obj) {
		if (logImp != null) {
			logImp.w(tag, msg, obj);
		}
	}

	public static void i(final String tag, final String msg, final Object... obj) {
		if (logImp != null) {
			logImp.i(tag, msg, obj);
		}
	}

	public static void d(final String tag, final String msg, final Object... obj) {
		if (logImp != null) {
			logImp.d(tag, msg, obj);
		}
	}

	public static void printErrStackTrace(String tag, Throwable tr, final String format, final Object... obj) {
		if (logImp != null) {
			logImp.printErrStackTrace(tag, tr, format, obj);
		}
	}

	public interface LogImp {

		void v(final String tag, final String msg, final Object... obj);

		void i(final String tag, final String msg, final Object... obj);

		void w(final String tag, final String msg, final Object... obj);

		void d(final String tag, final String msg, final Object... obj);

		void e(final String tag, final String msg, final Object... obj);

		void printErrStackTrace(String tag, Throwable tr, final String format, final Object... obj);

	}

}
